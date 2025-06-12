package com.example.service;

import com.example.domain.Game;
import com.example.domain.GameRepository;
import com.example.infrastructure.InMemoryGameRepository;

import java.util.List;

public class ScoreBoardService {

    GameRepository gameRepository;

    public ScoreBoardService() {
        gameRepository = new InMemoryGameRepository();
    }

    public void startGame(String homeTeam, String awayTeam) {

    }

    public void finishGame(String homeTeam, String awayTeam) {

    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {

    }

    public List<Game> getSummary() {
        return  null;
    }

}
