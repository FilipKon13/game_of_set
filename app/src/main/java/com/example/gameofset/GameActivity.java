package com.example.gameofset;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gameofset.game.DeckFactory;
import com.example.gameofset.game.SetGame;
import com.example.gameofset.util.CountUpTimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class GameActivity extends AppCompatActivity {

    protected SetGame game;

    protected final HashMap<Integer, Place> placeFromId = new HashMap<>();
    protected final ArrayList<Place> places = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        setContentView(R.layout.activity_game);
        initPlaceFromId(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.game_action_bar);

        prepare();
    }

    protected void prepare() {
        TextView textView = findViewById(R.id.chronometer);

        CountUpTimer timer = new CountUpTimer(100) {
            @Override
            public void update(long millis) {
                long seconds = millis / 1000;
                long minutes = seconds / 60;
                seconds %= 60;
                millis %= 1000;
                millis /= 100;
                textView.setText(String.format(Locale.ENGLISH, "%d:%02d.%01d", minutes, seconds, millis));
            }
        };

        timer.start();

        game = new SetGame(new DeckFactory().getDeck(), places, () -> {
            findViewById(R.id.textView).setVisibility(View.VISIBLE);
            timer.cancel();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item) {
            System.out.println("hint");
            game.showHint();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void initPlaceFromId(AppCompatActivity activity) {
        places.clear();
        placeFromId.clear();
        for (int i = 1; i <= 21; i++) {
            int id = activity.getResources().getIdentifier("imageView" + i, "id", activity.getPackageName());
            Place place = new Place(activity.findViewById(id), activity.getResources());
            places.add(place);
            placeFromId.put(id, place);
        }
    }


    public void onSelect(View view) {
        game.clickPlace(Objects.requireNonNull(placeFromId.get(view.getId())));
    }

    public void onGameOver(View view) {
        finish();
    }
}