package io.qdivision.demo.bowling.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.qdivision.demo.bowling.utils.FrameType;
import io.qdivision.demo.bowling.utils.GameStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonPropertyOrder({"gameId", "gameStatus", "gameTotal", "players"})
public class Game {

    private GameStatus gameStatus;
    private final List<Player> players;
    private int gameId;
    private static int counter = 99;
    private static int tenthFrameTotal = 0;
    private int gameTotal = 0;


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

        //1st - 9th frames behaviour
        if(activeFrame.getFrameNumber() != 10) {
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


        //tenth frame behaviour
        if(activeFrame.getFrameNumber() == 10) {
            if(prevFrame != null){
                if(prevFrame.getFrameType() == FrameType.STRIKE){
                    int bonus = 0;
                    if(activeFrame.getFirstRoll() == null){
                        bonus = 10;
                    }
                    if(activeFrame.getSecondRoll() == null) {
                        prevFrame.setTotal(prevFrame.getTotal() + score + bonus);
                    }
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


            tenthFrameTotal += score;
            if (activeFrame.getFirstRoll() == null) {
                activeFrame.setFirstRoll(score);
            }

            else if (activeFrame.getFirstRoll() != null && activeFrame.getSecondRoll() == null) {
                activeFrame.setSecondRoll(score);
                activeFrame.setTotal(tenthFrameTotal);
                if (tenthFrameTotal < 10) {
                    
                }
            }

            else if (tenthFrameTotal >= 10 && activeFrame.getSecondRoll() != null) {
                activeFrame.setThirdRoll(score);
                activeFrame.setTotal(tenthFrameTotal);
                activeFrame.setFrameType(FrameType.TENTH_CLOSED);

            }
        }
        getPlayerById(id).tallyPlayerTotal();
        tallyGameTotal();
        //if there does not exist a player that is not complete, then the game is finished.
        if(!players.stream().filter(p -> !p.isPlayerComplete()).findFirst().isPresent()){
            gameStatus = GameStatus.COMPLETE;
        }
    }

    public void tallyGameTotal() {
        gameTotal = getPlayers().stream()
                .map(p -> p.getPlayerTotal())
                .reduce(0, (acc, curr) -> acc + curr);
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

    public int getGameTotal() {
        return gameTotal;
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
