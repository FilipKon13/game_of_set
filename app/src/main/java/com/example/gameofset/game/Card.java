package com.example.gameofset.game;

import androidx.annotation.NonNull;

import com.example.gameofset.MainActivity;

public class Card {
    public int color; // red, green, blue
    public int count; // 1, 2, 3
    public int shape; // rectangle, triangle, ellipse
    public int pattern; // empty, half, filled
    private static final int DECK_SIZE = 81;
    private static final int[] idFromCode = new int[DECK_SIZE];
    private static boolean initialized = false;
    @NonNull
    @Override
    public String toString() {
        return color + " " + count + " " + shape + " " + pattern;
    }

    public static boolean formSet(Card card1, Card card2, Card card3) {
        return (card1.color + card2.color + card3.color) % 3 == 0 &&
                (card1.count + card2.count + card3.count) % 3 == 0 &&
                (card1.pattern + card2.pattern + card3.pattern) % 3 == 0 &&
                (card1.shape + card2.shape + card3.shape) % 3 == 0;
    }

    public int getImageIdFromCard() {
        return idFromCode[code()];
    }

    public static void init(MainActivity activity) {
        if (initialized)  return;
        initialized = true;
        for(int i=0;i<DECK_SIZE;i++) {
            idFromCode[i] = activity.getResources().getIdentifier("card" + (i + 1), "drawable", activity.getPackageName());
        }
    }

    private int code() {
        return color * 27 + pattern * 9 + shape * 3 + count;
    }
}
