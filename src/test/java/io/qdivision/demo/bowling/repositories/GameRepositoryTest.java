package io.qdivision.demo.bowling.repositories;

import io.qdivision.demo.bowling.models.Game;
import io.qdivision.demo.bowling.utils.GameStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameRepositoryTest {
    @Test
    public void givenGameNotStarted_whenGetScoreCard_thenReturnNewGameAndGameStatusInitializing(){
        final var game = new Game();

        Assert.assertNotNull(game.getFrames());
        Assert.assertEquals(game.getGameStatus(), GameStatus.INITIALIZED);
    }
}
