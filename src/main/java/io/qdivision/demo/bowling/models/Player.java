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
    private int playerTotal;

    //constructor for Jackson
    public Player() {
        frames = IntStream.range(1,11)
                .mapToObj(i -> new Frame(i))
                .collect(Collectors.toCollection(ArrayList::new));
        id = ++counter;
        playerTotal = 0;
    }

    public Frame getFrameByFrameNumber(int frameNumber){
        return frames.stream()
                .filter(f -> f.getFrameNumber() == frameNumber)
                .findFirst()
                .get();
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


    public int getPlayerTotal() { return playerTotal; }


    public void tallyPlayerTotal() {
        playerTotal = frames.stream()
                    .map(f -> f.getTotal())
                    .reduce(0, (acc, curr) -> acc + curr);
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
