package io.qdivision.demo.bowling.models;

import io.qdivision.demo.bowling.utils.GameStatus;

public class Game {

    private final GameStatus gameStatus;

    private final Frame[] frames;

    public Game() {
        this.frames = new Frame[10];
        gameStatus = GameStatus.INITIALIZED;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Frame[] getFrames() {
        return frames;
    }
}
