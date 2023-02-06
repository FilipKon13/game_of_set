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
    private static int diff = 1;

    public final static String[] DECKS = new String[]{"card", "fractal", "sier"};
    public final static String[] DIFFS = new String[]{"normal", "easy"};

    public static void init(MainActivity activity) {
        for(String deck : DECKS) {
            initName(deck, activity);
        }
    }

    public static void setHideLastCard(boolean state) {
        System.out.println("New state " + state);
        hide_last_card = state;
    }

    public static void setDeck(String name) {
        System.out.println("New deck " + name);
        curr_deck = name;
    }

    public static void setDiff(int new_diff) {
        System.out.println("New diff " + new_diff);
        diff = new_diff;
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
        if(diff == 0) {
            cards = cards.subList(0,27);
        }
        Collections.shuffle(cards);
        if(hide_last_card) {
            return new LocalDeckHide(cards);
        } else {
            return new LocalDeck(cards);
        }
    }

    public Deck getEndless() {
        List<Card> cards = new ArrayList<>(Objects.requireNonNull(hashMap.get(curr_deck)));
        if(diff == 0) {
            cards = cards.subList(0,27);
        }
        return new EndlessLocalDeck(cards);
    }
}
