package com.example.infrastructure;

import com.example.domain.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryGameRepositoryTest {

    private InMemoryGameRepository repository;

    private static final String TEAM_1 = "Germany";
    private static final String TEAM_2 = "Mexico";

    @BeforeEach
    public void setUp() {
        repository = new InMemoryGameRepository();
    }

    @Test
    void shouldSaveAndFindAll() {
        //given
        //when
        repository.save(new Game(TEAM_1, TEAM_2));

        //then
        assertThat(repository.findAll()).hasSize(1);
    }

    @Test
    void shouldDelete() {
        //given
        Game game = new Game(TEAM_1, TEAM_2);
        repository.save(game);

        //when
        repository.delete(TEAM_1, TEAM_2);

        //then
        assertThat(repository.findAll()).hasSize(0);
    }

    @Test
    void shouldUpdate() {
        //given
        Game game = new Game(TEAM_1, TEAM_2);
        repository.save(game);
        Game updatedGame = new Game(TEAM_1, TEAM_2);
        updatedGame.updateScore(1, 1);

        //when
        repository.updateScore(TEAM_1, TEAM_2, 1, 1);

        //then
        assertThat(repository.findAll()).hasSize(1);
        assertThat(repository.findAll().getFirst()).isEqualTo(updatedGame);
    }

}