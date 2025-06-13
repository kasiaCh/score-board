package com.example.service;

import com.example.domain.Game;
import com.example.domain.GameRepository;
import com.example.infrastructure.InMemoryGameRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoardService {

    private final GameRepository gameRepository;

    public ScoreBoardService() {
        gameRepository = new InMemoryGameRepository(); // could be injected when using Spring
    }

    public void startGame(String homeTeam, String awayTeam) {
        gameRepository.save(new Game(homeTeam, awayTeam));
    }

    public void finishGame(String homeTeam, String awayTeam) {
        gameRepository.delete(homeTeam, awayTeam);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        gameRepository.updateScore(homeTeam, awayTeam, homeScore, awayScore);
    }

    public List<Game> getSummary() {
        List<Game> games = gameRepository.findAllByAddingTimeAsc();
        return games.stream()
                .sorted(Comparator.comparingInt(Game::totalScore).reversed()
                        .thenComparing(games::indexOf, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

}
