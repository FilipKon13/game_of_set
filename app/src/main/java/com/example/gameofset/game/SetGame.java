package com.example.gameofset.game;

import android.os.Handler;
import android.os.Looper;

import com.example.gameofset.GameOver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetGame {
    private final ArrayList<CardPlace> places;
    private final GameOver ending;
    private int selected = 0;
    private int visible = 12;
    private final CardPlace[] selectedPlaces;
    private Deck deck;

    private void addPlace(CardPlace place) {
        for(int i=0;i<3;i++) {
            if(selectedPlaces[i] == null) {
                selectedPlaces[i] = place;
                selected++;
                return;
            }
        }
        throw new RuntimeException("Too much cards selected");
    }

    private void removePlace(CardPlace place) {
        for(int i=0;i<3;i++) {
            if(selectedPlaces[i] != null && selectedPlaces[i].id() == place.id()) {
                selectedPlaces[i] = null;
                selected--;
                return;
            }
        }
        throw new RuntimeException("Removing non selected card");
    }

    private boolean checkFullSelected() {
        return selected == 3;
    }

    public SetGame(Deck deck, ArrayList<? extends CardPlace> cardPlaces, GameOver ending) {
        this.deck = deck;
        places = new ArrayList<>();
        places.addAll(cardPlaces);
        System.out.println("filling");
        fillBoard();
        selectedPlaces = new CardPlace[3];
        this.ending = ending;
    }

    public void endGame() {
        ending.endGame();
        for(CardPlace place : places) {
            place.deactivate();
        }
    }

    private void fillBoard() {
        for(int i=0;i<visible;i++) {
            CardPlace place = places.get(i);
            place.setCard(deck.pop());
            place.setVisible(true);
        }
        for(int i=visible; i < places.size(); i++) {
            CardPlace place = places.get(i);
            place.setVisible(false);
        }
        while(!checkSetExists(Collections.emptyList())) {
            addNewRow();
        }
    }

    public boolean clickPlace(CardPlace place) {
        if (place.isSelected()) {
            place.select(false);
            System.out.println("unselect");
            System.out.println(place.getCard());
            removePlace(place);
        } else if (!checkFullSelected()){
            place.select(true);
            System.out.println("select");
            System.out.println(place.getCard());
            addPlace(place);
            if (checkFullSelected()) {
                System.out.println("3rd selected");
                return checkSet();
            }
        }
        return false;
    }

    private boolean checkSet() {
        if (!Card.formSet(selectedPlaces[0].getCard(), selectedPlaces[1].getCard(), selectedPlaces[2].getCard()))
            return false;
        System.out.println("Set!");
        refillBoard();
        return true;
    }

    private void refillBoard() {
        ArrayList<Integer> usedIds = new ArrayList<>();
        for(CardPlace place : selectedPlaces) {
            usedIds.add(place.id());
        }
        if (visible > 12 && checkSetExists(usedIds)) {
            cleanUnnecessaryRow(usedIds);
            return;
        }
        if (deck.isEmpty()) {
            cleanUnnecessaryRow(usedIds);
            if (!checkSetExists(Collections.emptyList())) {
                ending.endGame();
            }
            return;
        }
        for(int i=0;i<3;i++) {
            selectedPlaces[i].setCard(deck.pop());
            selectedPlaces[i].select(false);
            selectedPlaces[i] = null;
        }
        selected = 0;
        while(!checkSetExists(Collections.emptyList())) {
            if(deck.isEmpty()) {
                ending.endGame();
                return;
            }
            addNewRow();
        }
    }

    private void addNewRow() {
        for(int i=0;i<3;i++) {
            CardPlace place = places.get(visible + i);
            place.setCard(deck.pop());
            place.setVisible(true);
        }
        visible += 3;
    }

    private void cleanUnnecessaryRow(List<Integer> usedIds) {
        System.out.println("cleaning");
        ArrayList<Card> cards = new ArrayList<>();
        for(CardPlace place : places) {
            if(!usedIds.contains(place.id()))    cards.add(place.getCard());
        }
        visible -= 3;
        for(int i=0;i<visible;i++) {
            CardPlace place = places.get(i);
            place.setCard(cards.get(i));
            place.select(false);
            place.setVisible(true);
        }
        for(int i=visible;i<places.size();i++) {
            CardPlace place = places.get(i);
            place.setVisible(false);
            place.select(false);
        }
        for(int i=0;i<3;i++)    selectedPlaces[i] = null;
        selected = 0;
        System.out.println("cleaning done");
    }

    private static class Selection {
        public int[] t = new int[3];
        public Selection(int x, int y, int z) {
            this.t[0] = x;
            this.t[1] = y;
            this.t[2] = z;
        }
    }

    private Selection findSet(List<Integer> usedIds) {
        CardPlace[] place = new CardPlace[3];
        for(int i=0;i<visible;i++) {
            place[0] = places.get(i);
            if (usedIds.contains(place[0].id()))
                continue;
            for(int j=i+1;j<visible;j++) {
                place[1] = places.get(j);
                if (usedIds.contains(place[1].id()) || place[1].id() == place[0].id())
                    continue;
                for(int k=j+1;k<visible;k++) {
                    place[2] = places.get(k);
                    if (usedIds.contains(place[2].id()) || place[2].id() == place[1].id() || place[2].id() == place[0].id())
                        continue;
                    if(Card.formSet(place[0].getCard(), place[1].getCard(), place[2].getCard()))
                        return new Selection(i,j,k);
                }
            }
        }
        return null;
    }

    private boolean checkSetExists(List<Integer> usedIds) {
        return findSet(usedIds) != null;
    }

    public void showHint() {
        Selection set = findSet(Collections.emptyList());
        if(set == null) return;
        for(int id : set.t) {
            CardPlace place = places.get(id);
            place.setHinted(true);
        }
        new Handler(Looper.myLooper()).postDelayed(() -> clearHint(set), 1000);
    }

    private void clearHint(Selection set) {
        System.out.println("Clear selection");
        for(int id : set.t) {
            CardPlace place = places.get(id);
            place.setHinted(false);
        }
    }
}
