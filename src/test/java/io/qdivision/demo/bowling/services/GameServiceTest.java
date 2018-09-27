package io.qdivision.demo.bowling.services;


import io.qdivision.demo.bowling.models.Game;
import io.qdivision.demo.bowling.models.Player;
import io.qdivision.demo.bowling.repositories.GameRepository;
import io.qdivision.demo.bowling.utils.GameStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.sql.SQLOutput;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Test
    public void givenGameNotStarted_whenGetScoreCard_thenReturnGame(){
        final var gameService = new GameService(gameRepository);
        final var game = new Game();

        Mockito.when(gameRepository.getScoreCard())
                .thenReturn(game);

        Assert.assertEquals(gameService.getScoreCard(), game);
        Assert.assertEquals(game.getGameStatus(), GameStatus.INITIALIZED);
    }

    @Test
    public void givenGameHasStarted_whenPlayerAdded_thenReturnPlayersWithNewPlayerAdded(){
        final var gameService = new GameService(gameRepository);
        final var name = "Morpheus";
        final var game = new Game();
        final Player player = new Player();
        player.setName(name);
        game.addPlayer(player);

        Mockito.when(gameRepository.addPlayer(player))
                .thenReturn(game);

        final Game response = gameService.addPlayer(player);
        Assert.assertEquals(gameService.addPlayer(player), game);
    }

    @Test
    public void givenPlayerExists_whenRemovePlayer_thenRemovePlayer(){
        final var gameService= new GameService(gameRepository);
        final int id = 1;
        final String name = "Morpheus";
        final var game = new Game();
        final var player = new Player();
        player.setName(name);

        Mockito.when(gameRepository.removePlayer(id)).thenReturn(game);

        Assert.assertNotNull(gameRepository.removePlayer(id));
    }

    @Test
    public void givenGameNotStarted_whenStartGame_thenReturnGame(){
        final var gameService = new GameService(gameRepository);
        final var game = new Game();
        final var incomingGameStatus = GameStatus.IN_PROGRESS;
        game.setGameStatus(incomingGameStatus);

        Mockito.when(gameRepository.gameTimeStarted(incomingGameStatus)).thenReturn(game);

        Game response = gameService.gameTimeStarted(incomingGameStatus);

        Assert.assertTrue(response instanceof Game);
    }

    @Test
    public void givenPlayerExists_whenPostAddScoreToPlayer_thenReturnTypeGame() {
        final var gameService = new GameService(gameRepository);
        final var game = new Game();
        final int id = 1;
        final int score = 5;
        final int frameNumber = 1;

        Mockito.when(gameRepository.addScore(id, frameNumber, score)).thenReturn(game);

        Game response = gameService.addScore(id, frameNumber, score);
        Assert.assertTrue(response instanceof Game);
    }

    @Test
    public void givenPlayerExistsAndOneScoreAlreadyExists_whenPostAddScoreToPlayer_thenReturnTypeGame() {
        final var gameService = new GameService(gameRepository);
        final var game = new Game();
        final int id = 1;
        final int score = 5;
        final int frameNumber = 1;

        Mockito.when(gameRepository.addScore(id, frameNumber, score)).thenReturn(game);

        Game response = gameService.addScore(id, frameNumber, score);
        Assert.assertTrue(response instanceof Game);
    }

}
