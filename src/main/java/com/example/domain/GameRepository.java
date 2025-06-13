package com.example.domain;

import java.util.List;

public interface GameRepository {

    void save(Game game);
    void delete(String homeTeam, String awayTeam);
    void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);
    List<Game> findAllByAddingTimeAsc();

}
