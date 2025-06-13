package com.example.scoreboard.presentation;

import com.example.scoreboard.service.ScoreBoardService;

public class ScoreBoardPresenter {

    private final ScoreBoardService scoreBoardService;

    public ScoreBoardPresenter(ScoreBoardService scoreBoardService) {
        this.scoreBoardService = scoreBoardService;
    }

    public void printSummary() {
        scoreBoardService.getSummary().forEach(System.out::println);
    }

}
