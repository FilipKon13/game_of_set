package com.example.gameofset.game;

import com.example.gameofset.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DeckFactory {
    private static final HashMap<String, List<Card>> hashMap = new HashMap<>();
    private static boolean hide_last_card = false;

    public static void init(MainActivity activity) {
        initName("card", activity);
        initName("fractal", activity);
    }

    public static void setState(boolean state) {
        System.out.println("New state " + state);
        hide_last_card = state;
    }

    private static void initName(String name, MainActivity activity) {
        ArrayList<Card> list = new ArrayList<>();
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                for(int k=0;k<3;k++) {
                    for(int l=0;l<3;l++) {
                        Card card = new Card(i,j,k,l,name,activity);
                        list.add(card);
                    }
                }
            }
        }
        hashMap.put(name, list);
    }


    public Deck getDeck(String name) {
        List<Card> cards = new ArrayList<>(Objects.requireNonNull(hashMap.get(name)));
        Collections.shuffle(cards);
        if(hide_last_card) {
            return new LocalDeckHide(cards);
        } else {
            return new LocalDeck(cards);
        }
    }
}
