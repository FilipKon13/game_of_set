package com.example.gameofset;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gameofset.game.SetGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GameActivity extends AppCompatActivity {

    private SetGame game;

    private final HashMap<Integer, Place> placeFromId = new HashMap<>();
    private final ArrayList<Place> places = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        setContentView(R.layout.activity_game);
        initPlaceFromId(this);
        game = new SetGame(places, () -> findViewById(R.id.textView).setVisibility(View.VISIBLE));
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

    public void initPlaceFromId(AppCompatActivity activity) {
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