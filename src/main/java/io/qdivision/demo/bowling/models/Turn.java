package io.qdivision.demo.bowling.models;

public class Turn {
    private final int firstRoll;
    private final int secondRoll;
    private final int thirdRoll;

    public Turn(int firstRoll, int secondRoll, int thirdRoll) {
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
        this.thirdRoll = thirdRoll;
    }
}
