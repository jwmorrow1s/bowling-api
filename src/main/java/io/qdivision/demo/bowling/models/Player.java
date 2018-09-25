package io.qdivision.demo.bowling.models;

import java.util.Arrays;
import java.util.Objects;

public class Player {

    private final Turn[] turns;
    private String name;
    private int id;
    private static int counter = 0;

    //constructor for Jackson
    public Player() {
        this.turns = new Turn[10];
        id = ++counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Arrays.equals(turns, player.turns) &&
                Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName());
        result = 31 * result + Arrays.hashCode(turns);
        return result;
    }
}
