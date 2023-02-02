package com.example.gameofset.game;

import java.util.List;
import java.util.Stack;

public class LocalDeckHide extends Stack<Card> implements Deck {

    public LocalDeckHide(List<Card> list) {
        super();
        this.addAll(list);
    }

    @Override
    public Card pop() {
        Card res = super.pop();
        if(isEmpty()) {
            res.makeInvisible();
        }
        return res;
    }
}
