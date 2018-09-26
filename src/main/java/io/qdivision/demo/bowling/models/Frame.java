package io.qdivision.demo.bowling.models;

import io.qdivision.demo.bowling.utils.FrameScore;

public class Frame {

    private int firstRoll;
    private int secondRoll;
    private int thirdRoll;
    private FrameScore score;
    private final int cardinality;

    public Frame(int cardinality) {
        this.cardinality = cardinality;
    }

    public void setFirstRoll(int firstRoll) {
        this.firstRoll = firstRoll;
    }

    public void setSecondRoll(int secondRoll) {
        this.secondRoll = secondRoll;
    }

    public void setThirdRoll(int thirdRoll) {
        this.thirdRoll = thirdRoll;
    }

    public void setScore(FrameScore score) {
        this.score = score;
    }

    public int getFirstRoll() {
        return firstRoll;
    }

    public int getSecondRoll() {
        return secondRoll;
    }

    public int getThirdRoll() {
        return thirdRoll;
    }

    public int getCardinality() {
        return cardinality;
    }
}
