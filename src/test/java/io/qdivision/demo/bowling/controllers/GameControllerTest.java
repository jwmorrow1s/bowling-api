package io.qdivision.demo.bowling.controllers;

import io.qdivision.demo.bowling.models.Game;
import io.qdivision.demo.bowling.models.Player;
import io.qdivision.demo.bowling.services.GameService;
import io.qdivision.demo.bowling.utils.GameStatus;
import org.apache.coyote.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @Mock
    private GameService gameService;

    @Test
    public void givenGameNotStarted_whenGetScoreCard_thenReturnScoreCardResponse(){
        var gameController = new GameController(gameService);
        var game = new Game();

        Mockito.when(gameService.getScoreCard())
            .thenReturn(game);

        ResponseEntity<Game> response = gameController.getScoreCard();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(game, response.getBody());
        Assert.assertEquals(game.getGameStatus(), GameStatus.INITIALIZED);
        //integration test should check gameStatus from game
    }


    @Test
    public void givenGameHasStarted_whenPlayerAdded_thenReturnPlayersWithNewPlayerAdded() {
        var gameController = new GameController(gameService);
        var name = "Morpheus";
        var player = new Player(name);

        Mockito.when(gameService.addPlayer(name)).thenReturn(player);

        ResponseEntity<Player> response = gameController.addPlayer(player);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assert.assertNotNull(gameController.addPlayer(player));
    }
}
