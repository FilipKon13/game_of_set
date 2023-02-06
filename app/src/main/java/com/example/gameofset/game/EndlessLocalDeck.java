package com.example.gameofset.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EndlessLocalDeck implements Deck {
    private final List<Card> cards = new ArrayList<>();
    int cnt = 0;
    public EndlessLocalDeck(Collection<Card> cards) {
        this.cards.addAll(cards);
        Collections.shuffle(this.cards);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Card pop() {
        Card res = cards.get(cnt++);
        if(cnt == cards.size()) {
            cnt = 0;
            Collections.shuffle(cards);
        }
        return res;
    }
}
