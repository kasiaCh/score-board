package com.example.service;

import com.example.domain.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

}