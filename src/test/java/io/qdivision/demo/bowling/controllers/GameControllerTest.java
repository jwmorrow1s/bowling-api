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

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @Mock
    private GameService gameService;

    @Test
    public void givenGameNotStarted_whenGetScoreCard_thenReturnScoreCardResponse(){
        final var gameController = new GameController(gameService);
        final var game = new Game();

        Mockito.when(gameService.getScoreCard())
            .thenReturn(game);

        final ResponseEntity<Game> response = gameController.getScoreCard();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(game, response.getBody());
        Assert.assertEquals(game.getGameStatus(), GameStatus.INITIALIZED);
        //integration test should check gameStatus from game
    }


    @Test
    public void givenGameHasStarted_whenPlayerAdded_thenReturnPlayersWithNewPlayerAdded() {
        final var gameController = new GameController(gameService);
        final var name = "Morpheus";
        final var game = new Game();
        final var pl = new Player();
        pl.setName(name);
        game.addPlayer(pl);
        List<Player> players = game.getPlayers();
        Player player = players.stream()
                .filter( p -> p.getName() == name)
                .findFirst()
                .get();

        Mockito.when(gameService.addPlayer(pl)).thenReturn(game);

        ResponseEntity<Game> response = gameController.addPlayer(player);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assert.assertEquals(gameController.addPlayer(player).getBody(), game);
    }

    @Test
    public void givenPlayerExists_whenRemovePlayer_thenRemovePlayerResponse(){
        final var gameController = new GameController(gameService);
        final int id = 1;
        final String name = "Morpheus";
        final var game = new Game();
        final var player = new Player();
        player.setName(name);

        Mockito.when(gameService.removePlayer(id)).thenReturn(game);
        ResponseEntity<Game> response = gameController.removePlayer(player);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test
    public void givenGameNotStarted_whenStartGame_thenHttpStatusAccepted(){
        final var gameController = new GameController(gameService);
        final var game = new Game();
        final var incomingGameStatus = GameStatus.IN_PROGRESS;
        game.setGameStatus(incomingGameStatus);

        Mockito.when(gameService.gameTimeStarted(incomingGameStatus)).thenReturn(game);

        ResponseEntity<Game> response = gameController.gameTimeStarted(game);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
        Assert.assertTrue(response.getBody() instanceof Game);
    }
}
