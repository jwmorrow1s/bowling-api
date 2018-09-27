package io.qdivision.demo.bowling.repositories;

import io.qdivision.demo.bowling.models.Game;
import io.qdivision.demo.bowling.models.Player;
import io.qdivision.demo.bowling.services.GameService;
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


        Assert.assertEquals(game.getGameStatus(), GameStatus.INITIALIZED);
    }

    @Test
    public void givenGameHasStarted_whenPlayerAdded_thenReturnPlayersWithNewPlayerAdded(){
        final var gameRepository = new GameRepository();
        final String name = "Morpheus";
        final var player = new Player();
        player.setName(name);
        final int id = player.getId();
        final Game response = gameRepository.addPlayer(player);


        Assert.assertTrue(response.getPlayers()
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .isPresent());
    }

    @Test
    public void givenPlayerExists_whenRemovePlayer_thenRemovePlayer(){
        final var gameRepository= new GameRepository();
        final String name = "Morpheus";
        final var player = new Player();
        final int id = player.getId();
        player.setName(name);
        gameRepository.addPlayer(player);
        final int beforeLength = gameRepository.getScoreCard().getPlayers().size();
        gameRepository.removePlayer(id);
        final int afterLength = gameRepository.getScoreCard().getPlayers().size();

        Game response = gameRepository.removePlayer(id);

        Assert.assertEquals(beforeLength, afterLength + 1);
        Assert.assertFalse(response.getPlayers().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .isPresent());
    }

    @Test
    public void givenGameNotStarted_whenStartGame_thenReturnGameWithUpdatedStatusInProgress(){
        final var gameRepository = new GameRepository();
        final var incomingGameStatus = GameStatus.IN_PROGRESS;
        Game response = gameRepository.gameTimeStarted(incomingGameStatus);

        Assert.assertEquals(response.getGameStatus(), GameStatus.IN_PROGRESS);
    }


    @Test
    public void givenPlayerExists_whenPostAddScoreToPlayer_thenReturnGamewithScoreAdded() {
        final var gameRepository = new GameRepository();
        final int id = 1;
        final int score = 5;
        final int frameNumber = 1;
        final String name = "Morpheus";
        Player player = new Player();
        player.setName(name);
        Game response = gameRepository.addPlayer(player);
        response = gameRepository.addScore(id, frameNumber, score);
        Integer updatedScore = response.getPlayerById(id).getFrameByFrameNumber(frameNumber).getFirstRoll();

        Assert.assertTrue(score == updatedScore);
    }

    @Test
    public void givenPlayerExistsAndOneScoreAlreadyExists_whenPostAddScoreToPlayer_thenReturnGameWithSecondScoreAdded() {
        final var gameRepository = new GameRepository();
        final int id = 1;
        final int firstScore = 5;
        final int secondScore = 5;
        final int frameNumber = 1;
        final String name = "Morpheus";
        Player player = new Player();
        player.setName(name);
        Game response = gameRepository.addPlayer(player);
        response = gameRepository.addScore(id, frameNumber, firstScore);
        response = gameRepository.addScore(id, frameNumber, secondScore);
        Integer updatedScore = response.getPlayerById(id).getFrameByFrameNumber(frameNumber).getSecondRoll();

        Assert.assertTrue(secondScore == updatedScore);
    }

}
