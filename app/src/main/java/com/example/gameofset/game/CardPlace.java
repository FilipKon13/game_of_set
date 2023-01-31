package com.example.gameofset.game;

public interface CardPlace {
    boolean isSelected();
    void select(boolean select);
    int id();
    Card getCard();
    void setCard(Card card);

    void setVisible(boolean visible);
}
