package com.example.scoreboard.service;

import com.example.scoreboard.domain.Game;
import com.example.scoreboard.domain.GameRepository;
import com.example.scoreboard.infrastructure.InMemoryGameRepository;
import com.example.scoreboard.service.exception.InValidDataException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoardService {

    private final GameRepository gameRepository;

    public ScoreBoardService() {
        gameRepository = new InMemoryGameRepository(); // could be injected when using Spring
    }

    public void startGame(String homeTeam, String awayTeam) {
        if (gameRepository.exists(homeTeam, awayTeam)) {
            throw new InValidDataException("Game between " + homeTeam + " and " + awayTeam + " already exists.");
        }

        gameRepository.save(new Game(homeTeam, awayTeam));
    }

    public void finishGame(String homeTeam, String awayTeam) {
        if (!gameRepository.exists(homeTeam, awayTeam)) {
            throw new InValidDataException("Game between " + homeTeam + " and " + awayTeam + " does not exist.");
        }

        gameRepository.delete(homeTeam, awayTeam);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        if (!gameRepository.exists(homeTeam, awayTeam)) {
            throw new InValidDataException("Game between " + homeTeam + " and " + awayTeam + " does not exist.");
        }

        if (homeScore < 0 || awayScore < 0) {
            throw new InValidDataException("Scores must be non-negative.");
        }

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
