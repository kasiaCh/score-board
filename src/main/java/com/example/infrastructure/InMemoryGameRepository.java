package com.example.infrastructure;

import com.example.domain.Game;
import com.example.domain.GameRepository;

import java.util.List;
import java.util.Map;

public class InMemoryGameRepository implements GameRepository {
    private Map<String, Game> games;

    @Override
    public void save(Game game) {

    }

    @Override
    public void delete(Game game) {

    }

    @Override
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {

    }

    @Override
    public List<Game> findAll() {
        return List.of();
    }

    private String generateKey(String homeTeam, String awayTeam) {
        return "";
    }

}
