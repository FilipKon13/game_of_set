package com.example.gameofset.game;

import android.widget.TextView;

import java.util.Locale;

public class ScorePoint {
    private final TextView scoreboard;
    private long score = 0;

    public ScorePoint(TextView scoreboard) {
        this.scoreboard = scoreboard;
    }

    void scorePoint() {
        System.out.println("Point!");
        scoreboard.setText(String.format(Locale.ENGLISH, "%d", ++score));
    }
}
