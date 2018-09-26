package io.qdivision.demo.bowling.controllers;

import io.qdivision.demo.bowling.models.Game;
import io.qdivision.demo.bowling.models.Player;
import io.qdivision.demo.bowling.services.GameService;
import io.qdivision.demo.bowling.utils.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<Game> getScoreCard() {
        final Game gameScoreCard = gameService.getScoreCard();
        return ResponseEntity.status(HttpStatus.OK).body(gameScoreCard);
    }

    @PostMapping
    public ResponseEntity<Game> addPlayer(@RequestBody Player pl) {
        final Game game = gameService.addPlayer(pl);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(game);
    }

    @DeleteMapping
    public ResponseEntity<Game> removePlayer(@RequestBody Player pl) {
        final Game game = gameService.removePlayer(pl.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(game);
    }


    @PostMapping("/start")
    public ResponseEntity<Game> gameTimeStarted(@RequestBody Game g) {
        final Game game = gameService.gameTimeStarted(g.getGameStatus());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(game);
    }
    @PatchMapping
    public ResponseEntity<Game> patchPlayer(@RequestBody Player pl) {
        final Game game = gameService.patchPlayer(pl.getId(), pl.getName());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(game);
    }
}



