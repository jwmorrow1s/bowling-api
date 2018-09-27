package io.qdivision.demo.bowling.models;

import io.qdivision.demo.bowling.utils.FrameType;
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

    public void addScore(int id, int frameNumber, int score) {
        Frame activeFrame = getPlayerById(id)
                .getFrameByFrameNumber(frameNumber);
        Frame prevFrame = frameNumber > 1 ?
                getPlayerById(id)
                        .getFrameByFrameNumber(frameNumber - 1)
                : null;
        Frame prevPrevFrame = frameNumber > 2 ?
                getPlayerById(id)
                        .getFrameByFrameNumber(frameNumber - 2)
                : null;


        if(prevFrame != null){
            if(prevFrame.getFrameType() == FrameType.STRIKE){
                int bonus = 0;
                if(activeFrame.getFirstRoll() == null){
                    bonus = 10;
                }
                prevFrame.setTotal(prevFrame.getTotal() + score + bonus);
            }
        }

        if(prevFrame != null){
            if(prevFrame.getFrameType() == FrameType.SPARE
                    && activeFrame.getFirstRoll() == null){
                prevFrame.setTotal(prevFrame.getTotal() + score + 10);
            }
        }

        if (prevPrevFrame != null) {
            if(prevPrevFrame.getFrameType()==FrameType.STRIKE
            && prevFrame.getFrameType()==FrameType.STRIKE) {
                if(activeFrame.getFirstRoll() == null) {
                    prevPrevFrame.setTotal(20 + score);
                }
            }
        }

        if(activeFrame.getFirstRoll() == null){
            activeFrame.setFirstRoll(score);
            if(score == 10){
                activeFrame.setFrameType(FrameType.STRIKE);
            }
        } else {
            activeFrame.setSecondRoll(score);
            if(score + activeFrame.getFirstRoll() == 10){
                activeFrame.setFrameType(FrameType.SPARE);
            } else{
                activeFrame.setTotal(activeFrame.getFirstRoll() + score);
            }
        }



    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Player getPlayerById(int playerId){
        return players.stream()
                .filter(p -> p.getId() == playerId)
                .findFirst()
                .get();
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
