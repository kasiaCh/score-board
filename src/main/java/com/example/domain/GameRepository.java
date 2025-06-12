package com.example.domain;

import java.util.List;

public interface GameRepository {

    void save(Game game);
    void delete(Game game);
    void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);
    List<Game> findAll();

}
