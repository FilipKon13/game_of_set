package com.example.gameofset.game;

import java.util.List;
import java.util.Stack;

public class LocalDeckHide implements Deck {
    private final Stack<Card> deck = new Stack<>();

    public LocalDeckHide(List<Card> list) {
        deck.addAll(list);
    }

    @Override
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    @Override
    public Card pop() {
        Card res = deck.pop();
        if(deck.isEmpty()) {
            res.makeInvisible();
        }
        return res;
    }

    @Override
    public Card peek() {
        return deck.peek();
    }
}
