package io.qdivision.demo.bowling.repositories;

import io.qdivision.demo.bowling.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepository {

    private final Game game;

    @Autowired
    public GameRepository() {
        this.game = new Game();
    }

    public Game getScoreCard() {
        return game;
    }
}
