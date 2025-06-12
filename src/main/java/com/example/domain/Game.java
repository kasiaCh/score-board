package com.example.domain;

import java.util.Objects;

public class Game {
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;

    public Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public void updateScore(int homeScore, int awayScore) {

    }

    public int totalScore() {
        return homeScore + awayScore;
    }

    @Override
    public String toString() {
        return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Game game)) return false;
        return homeScore == game.homeScore && awayScore == game.awayScore && Objects.equals(homeTeam, game.homeTeam) && Objects.equals(awayTeam, game.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam, homeScore, awayScore);
    }

}
