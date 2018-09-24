package io.qdivision.demo.bowling.models;

import java.util.List;

public class Player {
    private final List<Turn> turns;
    private final int firstRoll;
    private final int secondRoll;
    private final int thirdRoll;

    public Player(List<Turn> turns, int firstRoll, int secondRoll, int thirdRoll) {
        this.turns = turns;
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
        this.thirdRoll = thirdRoll;
    }
}
