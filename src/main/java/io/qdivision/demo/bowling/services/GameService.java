package io.qdivision.demo.bowling.services;

import io.qdivision.demo.bowling.models.Game;
import io.qdivision.demo.bowling.models.Player;
import io.qdivision.demo.bowling.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {

        this.gameRepository = gameRepository;
    }

    public Game getScoreCard() {
        return gameRepository.getScoreCard();
    }

    public Player addPlayer(String name) {
        return gameRepository.addPlayer(name);
    }
}
