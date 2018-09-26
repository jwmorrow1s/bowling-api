package io.qdivision.demo.bowling.repositories;

import io.qdivision.demo.bowling.models.Game;
import io.qdivision.demo.bowling.models.Player;
import io.qdivision.demo.bowling.utils.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepository {

    private final Game game;
    private Player player;


    @Autowired
    public GameRepository() {
        this.game = new Game();
    }

    public Game getScoreCard() {
        return game;
    }

    public Game addPlayer(Player player) {
        game.addPlayer(player);
        return game;
    }

    public Game removePlayer(int id) {
        game.removePlayer(id);
        return game;
    }


    public Game gameTimeStarted(GameStatus incomingGameStatus) {
        game.setGameStatus(incomingGameStatus);
        return game;
    }
    public Game patchPlayer(int id, String name) {
        game.patchPlayer(id, name);
        return game;
    }

    public Game addScore(int id, int cardinality, int score) {
        game.addScore(id, cardinality, score);
        return game;
    }
}
