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
    private static String curr_deck = "card";

    public final static String[] DECKS = new String[]{"card", "fractal", "sier"};

    public static void init(MainActivity activity) {
        for(String deck : DECKS) {
            initName(deck, activity);
        }
    }

    public static void setState(boolean state) {
        System.out.println("New state " + state);
        hide_last_card = state;
    }

    public static void setDeck(String name) {
        System.out.println("New deck " + name);
        curr_deck = name;
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


    public Deck getDeck() {
        List<Card> cards = new ArrayList<>(Objects.requireNonNull(hashMap.get(curr_deck)));
        Collections.shuffle(cards);
        if(hide_last_card) {
            return new LocalDeckHide(cards);
        } else {
            return new LocalDeck(cards);
        }
    }
}
