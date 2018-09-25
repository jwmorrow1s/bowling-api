package io.qdivision.demo.bowling.models;

import io.qdivision.demo.bowling.utils.GameStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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


    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }


    public void removePlayer(int id) {
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getId() == id){
                players.remove(i);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return getGameStatus() == game.getGameStatus() &&
                Objects.equals(getPlayers(), game.getPlayers()) &&
                Arrays.equals(getFrames(), game.getFrames());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getGameStatus(), getPlayers());
        result = 31 * result + Arrays.hashCode(getFrames());
        return result;
    }

}
