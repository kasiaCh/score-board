package com.example.scoreboard.service;

import com.example.scoreboard.domain.Game;
import com.example.scoreboard.service.exception.InValidDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreBoardServiceTest {

    private ScoreBoardService board;

    private static final String TEAM_1 = "Germany";
    private static final String TEAM_2 = "Mexico";
    private static final String TEAM_3 = "Brazil";
    private static final String TEAM_4 = "Spain";

    @BeforeEach
    void setUp() {
        board = new ScoreBoardService();
    }

    @Test
    void shouldStartGameReflectInSummary() {
        //given
        board.startGame(TEAM_1, TEAM_2);

        //when
        List<Game> games = board.getSummary();

        //then
        assertThat(games.size()).isEqualTo(1);
        assertThat(games.getFirst().toString()).isEqualTo("Germany 0 - Mexico 0");
    }

    @Test
    void shouldFinishGameReflectInSummary() {
        //given
        board.startGame(TEAM_1, TEAM_2);
        board.startGame(TEAM_3, TEAM_4);
        //assert the initial state
        List<Game> initialSummary = board.getSummary();
        assertThat(initialSummary.size()).isEqualTo(2);

        //when
        board.finishGame(TEAM_1, TEAM_2);

        //then
        List<Game> updatedSummary = board.getSummary();
        assertThat(updatedSummary.size()).isEqualTo(1);
        assertThat(updatedSummary.getFirst().toString()).isEqualTo("Brazil 0 - Spain 0");
    }

    @Test
    void shouldUpdateGameReflectInSummary() {
        //given
        board.startGame(TEAM_1, TEAM_2);
        //assert the initial state
        List<Game> initialSummary = board.getSummary();
        assertThat(initialSummary.size()).isEqualTo(1);
        assertThat(initialSummary.getFirst().toString()).isEqualTo("Germany 0 - Mexico 0");

        //when
        var newHomeScore = 3;
        var newAwayScore = 2;
        board.updateScore(TEAM_1, TEAM_2, newHomeScore, newAwayScore);

        //then
        List<Game> updatedSummary = board.getSummary();
        assertThat(updatedSummary.size()).isEqualTo(1);
        assertThat(updatedSummary.getFirst().toString()).isEqualTo("Germany 3 - Mexico 2");
    }

    @Test
    void shouldGamesInSummaryBeSortedCorrectly() {
        //given
        var TEAM_5 = "Poland";
        var TEAM_6 = "Hungary";
        board.startGame(TEAM_1, TEAM_2);
        board.startGame(TEAM_3, TEAM_4);
        board.startGame(TEAM_5, TEAM_6);

        board.updateScore(TEAM_1, TEAM_2, 4, 2);
        board.updateScore(TEAM_3, TEAM_4, 2, 1);
        board.updateScore(TEAM_5, TEAM_6, 6, 0);

        //when
        List<Game> games = board.getSummary();

        //then
        assertThat(games.size()).isEqualTo(3);
        assertThat(games.get(0).toString()).isEqualTo("Poland 6 - Hungary 0");
        assertThat(games.get(1).toString()).isEqualTo("Germany 4 - Mexico 2");
        assertThat(games.get(2).toString()).isEqualTo("Brazil 2 - Spain 1");
    }

    @Test
    void shouldThrowExceptionWhenStartDuplicatedGame() {
        board.startGame(TEAM_1, TEAM_2);

        assertThrows(
                InValidDataException.class,
                () ->  board.startGame(TEAM_1, TEAM_2));
        }

    @Test
    void shouldThrowExceptionWhenFinishGameThatNotExist() {
        board.startGame(TEAM_1, TEAM_2);

        assertThrows(
                InValidDataException.class,
                () ->  board.finishGame(TEAM_3, TEAM_4));
    }

    @Test
    void shouldThrowExceptionWhenUpdateGameThatNotExist() {
        board.startGame(TEAM_1, TEAM_2);

        assertThrows(
                InValidDataException.class,
                () ->  board.updateScore(TEAM_3, TEAM_4, 2, 5));
    }

    @Test
    void shouldThrowExceptionWhenUpdateGameWithNegativeScore() {
        board.startGame(TEAM_1, TEAM_2);

        assertThrows(
                InValidDataException.class,
                () ->  board.updateScore(TEAM_1, TEAM_2, -2, 5));
    }

}