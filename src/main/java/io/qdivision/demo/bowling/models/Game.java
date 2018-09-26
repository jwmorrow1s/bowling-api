package io.qdivision.demo.bowling.models;

import io.qdivision.demo.bowling.utils.GameStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Game {

    private GameStatus gameStatus;
    private final List<Player> players;
    private int gameId;
    private static int counter = 99;


    public Game() {
        gameId = ++counter;
        players = new ArrayList<>();
        gameStatus = GameStatus.INITIALIZED;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }


    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }


    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getGameId() {
        return gameId;
    }

    public void removePlayer(int id) {
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getId() == id){
                players.remove(i);
            }
        }
    }

    public void patchPlayer(int id, String name) {
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getId() == id) {
                players.get(i).setName(name);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return getGameStatus() == game.getGameStatus() &&
                Objects.equals(getPlayers(), game.getPlayers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameStatus(), getPlayers());
    }


}
