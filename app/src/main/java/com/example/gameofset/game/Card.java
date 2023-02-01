package com.example.gameofset.game;

import androidx.annotation.NonNull;

import com.example.gameofset.MainActivity;

public class Card {
    public int color; // red, green, blue
    public int count; // 1, 2, 3
    public int shape; // rectangle, triangle, ellipse
    public int pattern; // empty, half, filled
    public int image_id;

    private static int blank_id;
    public static void init(MainActivity activity) {
        blank_id = activity.getResources().getIdentifier("x", "drawable", activity.getPackageName());
    }

    @NonNull
    @Override
    public String toString() {
        return color + " " + count + " " + shape + " " + pattern;
    }

    public Card(int color, int count, int shape, int pattern, String name, MainActivity activity) {
        this.color = color;
        this.count = count;
        this.shape = shape;
        this.pattern = pattern;
        this.image_id = activity.getResources().getIdentifier(name + code(), "drawable", activity.getPackageName());
    }

    public static boolean formSet(Card card1, Card card2, Card card3) {
        return (card1.color + card2.color + card3.color) % 3 == 0 &&
                (card1.count + card2.count + card3.count) % 3 == 0 &&
                (card1.pattern + card2.pattern + card3.pattern) % 3 == 0 &&
                (card1.shape + card2.shape + card3.shape) % 3 == 0;
    }

    public int getImageIdFromCard() {
        return image_id;
    }

    private int code() {
        return color * 27 + pattern * 9 + shape * 3 + count;
    }

    public void makeInvisible() {
        this.image_id = blank_id;
    }
}
