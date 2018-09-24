package io.qdivision.demo.bowling.models;

import io.qdivision.demo.bowling.utils.GameStatus;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final GameStatus gameStatus;
    private final List<Player> players;
    private final Frame[] frames;

    public Game() {
        this.frames = new Frame[10];
        this.players = new ArrayList<>();
        gameStatus = GameStatus.INITIALIZED;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Frame[] getFrames() {
        return frames;
    }


    public void addPlayer(String name) {
        players.add(new Player(name));
    }

    public List<Player> getPlayers() {
        return players;
    }
}
