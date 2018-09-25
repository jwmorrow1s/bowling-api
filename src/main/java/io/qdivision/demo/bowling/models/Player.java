package io.qdivision.demo.bowling.models;

import java.util.List;

public class Player {

    private final Turn[] turns;
    private String name;


    public Player() {
        this.turns = new Turn[10];
    }

    public Player(String name) {
        this.turns = new Turn[10];
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }
}
