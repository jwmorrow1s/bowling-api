package io.qdivision.demo.bowling.models;

import io.qdivision.demo.bowling.utils.FrameScore;

public class Frame {

    private Integer firstRoll;
    private Integer secondRoll;
    private Integer thirdRoll;
    private FrameScore score;



    private Integer frameNumber;
    private Integer currentRoll;

    public Frame() {}

    public Frame(Integer frameNumber) {
        this.frameNumber = frameNumber;
    }

    public void setFirstRoll(Integer firstRoll) {
        this.firstRoll = firstRoll;
    }

    public void setSecondRoll(Integer secondRoll) {
        this.secondRoll = secondRoll;
    }

    public void setThirdRoll(Integer thirdRoll) {
        this.thirdRoll = thirdRoll;
    }

    public void setScore(FrameScore score) {
        this.score = score;
    }

    public void setCurrentRoll(Integer currentRoll) { this.currentRoll = currentRoll; }

    public Integer getCurrentRoll() { return currentRoll; }

    public Integer getFirstRoll() {
        return firstRoll;
    }

    public Integer getSecondRoll() {
        return secondRoll;
    }

    public Integer getThirdRoll() {
        return thirdRoll;
    }

    public Integer getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(Integer frameNumber) { this.frameNumber = frameNumber; }


}
