package com.example.gameofset.game;

import java.util.List;
import java.util.Stack;

public class LocalDeck extends Stack<Card> implements Deck {
    public LocalDeck(List<Card> list) {
        super();
        this.addAll(list);
    }
}
