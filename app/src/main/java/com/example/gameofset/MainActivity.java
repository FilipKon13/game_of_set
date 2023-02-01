package com.example.gameofset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gameofset.game.Card;
import com.example.gameofset.game.DeckFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DeckFactory.init(this);
        setContentView(R.layout.activity_main);
    }

    public void onNewGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        GameActivity.curr_name = "card";
        startActivity(intent);
    }

    public void onNewGameV2(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        GameActivity.curr_name = "fractal";
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("requestCode " + requestCode);
        System.out.println("resultCode " + resultCode);
        System.out.println("RESULT_OK " + RESULT_OK);
        System.out.println("RESULT_CANCELED " + RESULT_CANCELED);
    }
}