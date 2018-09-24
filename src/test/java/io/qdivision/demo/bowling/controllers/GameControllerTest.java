package io.qdivision.demo.bowling.controllers;

import io.qdivision.demo.bowling.models.Game;
import io.qdivision.demo.bowling.services.GameService;
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
        //integration test should check gameStatus from game
    }
}
