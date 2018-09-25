package io.qdivision.demo.bowling.repositories;

import io.qdivision.demo.bowling.models.Game;
import io.qdivision.demo.bowling.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Player addPlayer(String name) {
        game.addPlayer(name);
        return new Player(name);
    }
}
