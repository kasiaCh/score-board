package com.example.scoreboard.infrastructure;

import com.example.scoreboard.domain.Game;
import com.example.scoreboard.domain.GameRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InMemoryGameRepository implements GameRepository {
    private final Map<String, Game> games;

    public InMemoryGameRepository() {
        this.games = new LinkedHashMap<>();
    }

    @Override
    public void save(Game game) {
        games.put(generateKey(game.homeTeam(), game.awayTeam()), game);
    }

    @Override
    public void delete(String homeTeam, String awayTeam) {
        games.remove(generateKey(homeTeam, awayTeam));
    }

    @Override
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Game game = games.get(generateKey(homeTeam, awayTeam));
        if (game != null) {
            game.updateScore(homeScore, awayScore);
        }
    }

    @Override
    public List<Game> findAllByAddingTimeAsc() {
        return new ArrayList<>(games.values());
    }

    private String generateKey(String homeTeam, String awayTeam) {
        return homeTeam + "-" + awayTeam;
    }

}
