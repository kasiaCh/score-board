package com.example.scoreboard;

import com.example.scoreboard.presentation.ScoreBoardPresenter;
import com.example.scoreboard.service.ScoreBoardService;

public class Main {

    public static void main(String[] args) {

        var scoreBoardService = new ScoreBoardService();
        var presenter = new ScoreBoardPresenter(scoreBoardService);

        scoreBoardService.startGame("Mexico", "Canada");
        scoreBoardService.startGame("Spain", "Brazil");
        scoreBoardService.startGame("Germany", "France");
        scoreBoardService.startGame("Uruguay", "Italy");
        scoreBoardService.startGame("Argentina", "Australia");

        scoreBoardService.updateScore("Mexico", "Canada", 0, 5);
        scoreBoardService.updateScore("Spain", "Brazil", 10, 2);
        scoreBoardService.updateScore("Germany", "France", 2, 2);
        scoreBoardService.updateScore("Uruguay", "Italy", 6, 6);
        scoreBoardService.updateScore("Argentina", "Australia", 3, 1);

        presenter.printSummary();
    }

}
