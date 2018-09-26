package io.qdivision.demo.bowling.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Player {

    private final List<Frame> frames;
    private String name;
    private int id;
    private static int counter = 0;

    //constructor for Jackson
    public Player() {
        frames = IntStream.range(1,11)
                .mapToObj(i -> new Frame(i))
                .collect(Collectors.toCollection(ArrayList::new));
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

    public List<Frame> getFrames() {
        return frames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getId() == player.getId() &&
                Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId());
    }
}
