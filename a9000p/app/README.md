
### to compile you need Maven

```
cd a9000p
cd app
mvn package
```

### to run the LIS simulator

```
java --jar target/a9000p-1.0.0.jar --lis-simulator
```

### to run the instrument simulator

```
java --jar target/a9000p-1.0.0.jar --instrument-simulator
```

### there is also a console client/simulator example

```
java --jar target/a9000p-1.0.0.jar 
```

## testing procedure

* press start in the instrument simulator (it is the TCP server)
* press start in the LIS simulator (it acts as TCP client)
* press the button Push [get-test] request and check the console output
* press the button Push [send-results] request and check the console output.
