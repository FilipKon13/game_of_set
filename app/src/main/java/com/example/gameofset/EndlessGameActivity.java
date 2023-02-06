package com.example.gameofset;

import android.view.View;
import android.widget.TextView;

import com.example.gameofset.game.DeckFactory;
import com.example.gameofset.game.ScorePoint;
import com.example.gameofset.game.SetGame;
import com.example.gameofset.util.AdditiveCountDownTimer;
import com.example.gameofset.util.TimeDispenser;

import java.util.Locale;
import java.util.Objects;

public class EndlessGameActivity extends GameActivity {

    private AdditiveCountDownTimer timer;
    private final TimeDispenser dispenser = new TimeDispenser(5000, -0.1);
    @Override
    protected void prepare() {
        TextView textView = findViewById(R.id.chronometer);

        game = new SetGame(new DeckFactory().getEndless(), places, () -> {
            findViewById(R.id.textView).setVisibility(View.VISIBLE);
        }, new ScorePoint(findViewById(R.id.scoreboard)));

        timer = new AdditiveCountDownTimer(20000, 100) {
            @Override
            public void onTick(long millis) {
                long seconds = millis / 1000;
                long minutes = seconds / 60;
                seconds %= 60;
                millis %= 1000;
                millis /= 100;
                textView.setText(String.format(Locale.ENGLISH, "%d:%02d.%01d", minutes, seconds, millis));
            }

            @Override
            public void onFinish() {
                game.endGame();
            }
        };

        timer.start();
    }

    @Override
    public void onSelect(View view) {
        if(game.clickPlace(Objects.requireNonNull(placeFromId.get(view.getId())))) {
            timer.addTime(dispenser.getNext());
        }
    }
}
