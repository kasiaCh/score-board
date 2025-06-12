package com.example.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    private Game game;

    private static final String HOME_TEAM = "Germany";
    private static final String AWAY_TEAM = "Mexico";

    @BeforeEach
    void setUp() {
        game = new Game(HOME_TEAM, AWAY_TEAM);
    }

    @Test
    void shouldInitialTotalScoreBeZero() {
        //given
        //when
        //then
        assertThat(game.totalScore()).isEqualTo(0);
    }

    @Test
    void shouldReturnProperTotalScoreWhenUpdated() {
        //given
        //when
        game.updateScore(2, 1);

        //then
        assertThat(game.totalScore()).isEqualTo(3);
    }

    @Test
    void shouldReturnProperTotalScoreWhenUpdatedTwice() {
        //given
        //when
        game.updateScore(2, 1);
        game.updateScore(3, 2);

        //then
        assertThat(game.totalScore()).isEqualTo(5);
    }

}