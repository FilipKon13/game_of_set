package com.example.gameofset;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gameofset.game.Card;
import com.example.gameofset.game.DeckFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DeckFactory.init(this);
        Card.init(this);
        DeckFactory.setDeck("card");
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.deck_spinner, DeckFactory.DECKS);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                DeckFactory.setDeck((String) parent.getItemAtPosition(position));
                System.out.println("deck "  + parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onNewGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void changeState(View view) {
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch hide_switch = findViewById(R.id.switch1);
        DeckFactory.setState(hide_switch.isChecked());
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