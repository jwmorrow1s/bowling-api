package io.qdivision.demo.bowling.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.qdivision.demo.bowling.utils.FrameType;


@JsonPropertyOrder({"frameNumber", "firstRoll", "secondRoll", "thirdRoll", "total", "frameType", "frameComplete"})
public class Frame {

    private Integer firstRoll;
    private Integer secondRoll;
    private Integer thirdRoll;
    private FrameType frameType;
    private boolean frameComplete;
    private int total;


    private Integer frameNumber;
    private Integer currentRoll;

    public Frame() {}

    public Frame(Integer frameNumber) {
        this.frameNumber = frameNumber;
        frameType = FrameType.OPEN;
        total = 0;
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

    public FrameType getFrameType() {
        return frameType;
    }

    public void setFrameType(FrameType frameType) {
        this.frameType = frameType;
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

    public boolean isFrameComplete() {
        return frameComplete;
    }

    public Integer getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(Integer frameNumber) { this.frameNumber = frameNumber; }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        frameComplete = true;
    }
}
