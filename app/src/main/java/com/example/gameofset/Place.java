package com.example.gameofset;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;

import com.example.gameofset.game.Card;
import com.example.gameofset.game.CardPlace;

import java.util.Collections;

public class Place implements CardPlace {
    private boolean selected = false;
    private final ImageView place;
    Card card;
    private final Resources resources;

    public Place(ImageView place, Resources resources) {
        this.place = place;
        this.resources = resources;
    }

    @Override
    public void setVisible(boolean visible) {
        this.place.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void setHinted(boolean visible) {
        this.place.setImageTintList(visible ? ColorStateList.valueOf(0x2F000000) : ColorStateList.valueOf(0));
    }

    @Override
    public void deactivate() {
        this.place.setOnClickListener(view -> {});
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public Card getCard() {
        return card;
    }

    @Override
    public void setCard(Card card) {
        this.card = card;
        if(card != null) {
            this.place.setImageDrawable(ResourcesCompat.getDrawable(resources, card.getImageIdFromCard(),null));
        }
    }

    @Override
    public void select(boolean select) {
        selected = select;
        place.setAlpha(select ? 0.5f : 1f);
    }

    @Override
    public int id() {
        return place.getId();
    }
}
