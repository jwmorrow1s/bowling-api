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

import java.sql.SQLOutput;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Test
    public void givenGameNotStarted_whenGetScoreCard_thenReturnGame(){
        var gameService = new GameService(gameRepository);
        var game = new Game();

        Mockito.when(gameRepository.getScoreCard())
                .thenReturn(game);

        Assert.assertEquals(gameService.getScoreCard(), game);
        Assert.assertEquals(game.getGameStatus(), GameStatus.INITIALIZED);
    }

    @Test
    public void givenGameHasStarted_whenPlayerAdded_thenReturnPlayersWithNewPlayerAdded(){
        var gameService = new GameService(gameRepository);
        var name = "Morpheus";
        var player = new Player(name);

        Mockito.when(gameRepository.addPlayer(name))
                .thenReturn(player);

        Player response = gameService.addPlayer(name);
        Assert.assertEquals(gameService.addPlayer(name), player);
    }

}
