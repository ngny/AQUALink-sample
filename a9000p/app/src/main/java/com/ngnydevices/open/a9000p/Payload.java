package com.ngnydevices.open.a9000p;


import java.util.ArrayList;
import java.util.LinkedList;

public class Payload {



    private final LinkedList<Frame> frames = new LinkedList<>();
    private int numRetries = 0;
    private String explanation;
    private Frame patientFrame;
    private Frame testOrder;
    private boolean separate;
    private boolean sent;
    private Result result;
    private int testOrderCount;


    public int getNumRetries() {
        return numRetries;
    }

    public void setNumRetries(int numRetries) {
        this.numRetries = numRetries;
    }

    public LinkedList<Frame> getFrames() {
        return frames;
    }


    public void add(Frame frame) {

        frames.add(frame);
    }


    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void exportToApplicationLayer(ArrayList<String> response) {
        for (Frame f : frames) {
            response.add(new String(f.generate()));
        }
    }

    public ArrayList<Frame> toArray() {
        return new ArrayList<>(frames);
    }

    public Frame getPatientFrame() {
        return patientFrame;
    }

    public void setPatientFrame(Frame patientFrame) {
        this.patientFrame = patientFrame;
    }

    public Frame getTestOrder() {
        return testOrder;
    }

    public void setTestOrder(Frame testOrder) {
        this.testOrder = testOrder;
    }

    public boolean getSeparate() {
        return separate;
    }

    public void setSeparate(boolean separate) {
        this.separate = separate;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public int getTestOrderCount() {
        return testOrderCount;
    }

    public void setTestOrderCount(int testOrderCount) {
        this.testOrderCount = testOrderCount;
    }

}
