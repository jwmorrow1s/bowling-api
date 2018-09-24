package io.qdivision.demo.bowling.controllers;

import io.qdivision.demo.bowling.models.Game;
import io.qdivision.demo.bowling.models.Player;
import io.qdivision.demo.bowling.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseEntity<Player> addPlayer(@RequestBody String name) {
        final Player player = gameService.addPlayer(name);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(player);
    }


}



