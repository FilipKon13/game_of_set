package com.example.gameofset.game;

import java.util.List;
import java.util.Stack;

public class LocalDeck implements Deck{
    private final Stack<Card> deck = new Stack<>();

    public LocalDeck(List<Card> list) {
        deck.addAll(list);
    }

    @Override
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    @Override
    public Card pop() {
        return deck.pop();
    }

    @Override
    public Card peek() {
        return deck.peek();
    }
}
