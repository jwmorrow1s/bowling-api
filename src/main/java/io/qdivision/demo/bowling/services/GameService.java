package io.qdivision.demo.bowling.services;

import io.qdivision.demo.bowling.models.Game;
import io.qdivision.demo.bowling.models.Player;
import io.qdivision.demo.bowling.repositories.GameRepository;
import io.qdivision.demo.bowling.utils.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public Game addPlayer(Player player) {
        return gameRepository.addPlayer(player);
    }

    public Game removePlayer(int id) {
        return gameRepository.removePlayer(id);
    }

    public Game gameTimeStarted(GameStatus incomingGameStatus) {
        return gameRepository.gameTimeStarted(incomingGameStatus);
    }
}
