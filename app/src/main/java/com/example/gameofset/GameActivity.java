package com.example.gameofset;

import android.os.Bundle;
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

    public static String curr_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        setContentView(R.layout.activity_game);
        initPlaceFromId(this);
        game = new SetGame(places, () -> findViewById(R.id.textView).setVisibility(View.VISIBLE),curr_name);
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