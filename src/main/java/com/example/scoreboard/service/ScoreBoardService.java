package com.example.scoreboard.service;

import com.example.scoreboard.domain.Game;
import com.example.scoreboard.domain.GameRepository;
import com.example.scoreboard.infrastructure.InMemoryGameRepository;
import com.example.scoreboard.service.exception.InValidDataException;
import jakarta.validation.constraints.NotBlank;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoardService {

    private final GameRepository gameRepository;

    public ScoreBoardService() {
        gameRepository = new InMemoryGameRepository(); // could be injected when using Spring
    }

    public void startGame(@NotBlank String homeTeam, @NotBlank String awayTeam) {
        validateGameNotExistYet(homeTeam, awayTeam);

        gameRepository.save(new Game(homeTeam, awayTeam));
    }

    public void finishGame(@NotBlank String homeTeam, @NotBlank String awayTeam) {
        validateGameExists(homeTeam, awayTeam);

        gameRepository.delete(homeTeam, awayTeam);
    }

    public void updateScore(@NotBlank String homeTeam, @NotBlank String awayTeam, int homeScore, int awayScore) {
        validateGameExists(homeTeam, awayTeam);
        validateScore(homeScore, awayScore);

        gameRepository.updateScore(homeTeam, awayTeam, homeScore, awayScore);
    }

    public List<Game> getSummary() {
        List<Game> games = gameRepository.findAllByAddingTimeAsc();
        return games.stream()
                .sorted(Comparator.comparingInt(Game::totalScore).reversed()
                        .thenComparing(games::indexOf, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    private void validateGameNotExistYet(String homeTeam, String awayTeam) {
        if (gameRepository.exists(homeTeam, awayTeam)) {
            throw new InValidDataException("Game between " + homeTeam + " and " + awayTeam + " already exists.");
        }
    }

    private void validateGameExists(String homeTeam, String awayTeam) {
        if (!gameRepository.exists(homeTeam, awayTeam)) {
            throw new InValidDataException("Game between " + homeTeam + " and " + awayTeam + " does not exist.");
        }
    }

    private static void validateScore(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new InValidDataException("Scores must be non-negative.");
        }
    }

}
