package io.qdivision.demo.bowling.services;


import io.qdivision.demo.bowling.models.Game;
import io.qdivision.demo.bowling.repositories.GameRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
    }
}
