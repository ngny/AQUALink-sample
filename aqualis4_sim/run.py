#!/usr/bin/env python3
import argparse
import threading
import time
import statistics
from dataclasses import dataclass, field
from typing import List, Optional, Tuple

import json

with open("soap_payloads.json", "r", encoding="utf-8") as f:
    SOAP_PAYLOADS = json.loads(f.read())



@dataclass
class Stats:
    lock: threading.Lock = field(default_factory=threading.Lock)
    total: int = 0
    ok: int = 0
    fail: int = 0
    status_counts: dict = field(default_factory=dict)
    latencies_ms: List[float] = field(default_factory=list)
    errors: List[str] = field(default_factory=list)

    def record(self, ok: bool, status: int, latency_ms: float, err: Optional[str] = None):
        with self.lock:
            self.total += 1
            if ok:
                self.ok += 1
            else:
                self.fail += 1
            self.status_counts[status] = self.status_counts.get(status, 0) + 1
            self.latencies_ms.append(latency_ms)
            if err:
                # Keep only first N errors to avoid huge memory usage
                if len(self.errors) < 50:
                    self.errors.append(err)


def load_payloads_from_file(path: str) -> List[str]:
    """
    File format:
      - Put each full SOAP XML payload
      - Separate payloads with a line that is exactly: ---
    """
    with open(path, "r", encoding="utf-8") as f:
        text = f.read()
    parts = [p.strip() for p in text.split("\n---\n")]
    payloads = [p for p in parts if p]
    return payloads


def pick_payload(payloads, idx: int) -> str:
    return payloads[idx % len(payloads)]


# ---------------------------
# HTTP POST SOAP (requests preferred)
# ---------------------------
def post_soap(url, xml: str, sample,timeout_s )-> Tuple[int, str]:
    headers = {
        "Content-Type": "text/xml; charset=utf-8",
        "Accept": "text/xml",
        "SOAPAction": xml["action"]
    }

    # Try requests first
    try:
        import requests  # type: ignore
        r = requests.post(url, data=xml["content"].replace("XXXXXXX",sample).encode("utf-8"), headers=headers, timeout=timeout_s)
        return r.status_code, r.text
    except ImportError:
        # Fallback to urllib
        import urllib.request
        req = urllib.request.Request(
            url=url,
            data=xml.encode("utf-8"),
            headers=headers,
            method="POST",
        )
        with urllib.request.urlopen(req, timeout=timeout_s) as resp:
            body = resp.read().decode("utf-8", errors="replace")
            return resp.status, body


def worker_thread(
    worker_id: int,
    url: str,
    payloads: List[str],
    end_time: float,
    timeout_s: float,
    stats: Stats,
):
    i = 0
    import random
    sample="000"
    while time.time() < end_time:
        if i == 0:
            sample = ''.join(str(random.randint(0, 9)) for _ in range(12))

        xml = pick_payload(payloads, i)
        i += 1



        t0 = time.perf_counter()
        status = 0
        try:
            status, _body = post_soap(url+xml["port"], xml,sample, timeout_s)
            ok = 200 <= status < 300
            latency_ms = (time.perf_counter() - t0) * 1000.0
            stats.record(ok=ok, status=status, latency_ms=latency_ms, err=None if ok else f"HTTP {status}")
        except Exception as e:
            latency_ms = (time.perf_counter() - t0) * 1000.0
            stats.record(ok=False, status=status, latency_ms=latency_ms, err=str(e))
        if i>=len(payloads):
            i=0



def percentile(values: List[float], p: float) -> float:
    if not values:
        return 0.0
    xs = sorted(values)
    k = (len(xs) - 1) * (p / 100.0)
    f = int(k)
    c = min(f + 1, len(xs) - 1)
    if f == c:
        return xs[f]
    d0 = xs[f] * (c - k)
    d1 = xs[c] * (k - f)
    return d0 + d1


def main():
    ap = argparse.ArgumentParser(description="Simple SOAP load test (threads).")
    ap.add_argument("--url", required=True, help="SOAP endpoint URL (e.g. https://host/service)")
    ap.add_argument("--workers", type=int, required=True, help="Number of concurrent workers")
    ap.add_argument("--duration", type=int, required=True, help="Load test duration in seconds")
    ap.add_argument("--timeout", type=float, default=10.0, help="HTTP timeout per request in seconds (default: 10)")
    ap.add_argument("--payloads-file", help="File containing SOAP XML payloads separated by a line '---'")

    args = ap.parse_args()

    if args.workers <= 0:
        raise SystemExit("--workers must be > 0")
    if args.duration <= 0:
        raise SystemExit("--duration must be > 0")

    payloads =  SOAP_PAYLOADS

    stats = Stats()
    start = time.time()
    end_time = start + args.duration

    threads = []
    for wid in range(args.workers):
        t = threading.Thread(
            target=worker_thread,
            name=f"worker-{wid}",
            args=(wid, args.url, payloads, end_time, args.timeout,  stats),
            daemon=True,
        )
        threads.append(t)
        t.start()

    for t in threads:
        t.join()

    elapsed = max(0.001, time.time() - start)

    lats = stats.latencies_ms
    avg = statistics.mean(lats) if lats else 0.0
    p50 = percentile(lats, 50)
    p95 = percentile(lats, 95)
    p99 = percentile(lats, 99)
    rps = stats.total / elapsed

    print("\n=== SOAP Load Test Summary ===")
    print(f"URL:        {args.url}")
    print(f"Workers:    {args.workers}")
    print(f"Duration:   {args.duration}s (elapsed {elapsed:.2f}s)")
    print(f"Payloads:   {len(payloads)}")
    print("")
    print(f"Requests:   total={stats.total} ok={stats.ok} fail={stats.fail}")
    print(f"RPS:        {rps:.2f}")
    print(f"Latency ms: avg={avg:.1f} p50={p50:.1f} p95={p95:.1f} p99={p99:.1f}")

    if stats.status_counts:
        print("\nStatus codes:")
        for code in sorted(stats.status_counts.keys()):
            print(f"  {code}: {stats.status_counts[code]}")

    if stats.errors:
        print("\nSample errors (up to 50):")
        for e in stats.errors[:10]:
            print(f"  - {e}")
        if len(stats.errors) > 10:
            print(f"  ... ({len(stats.errors)} total captured)")

    print("")


if __name__ == "__main__":
    main()

