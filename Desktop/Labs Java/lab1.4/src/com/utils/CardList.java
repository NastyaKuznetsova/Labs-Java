package com.utils;

import com.account.Card;

public class CardList {
    private Card[] cards;

    public CardList() {
        cards = new Card[0];
    }

    public void addCard(Card card) {
        Card[] newCards = new Card[cards.length + 1];
        System.arraycopy(cards, 0, newCards, 0, cards.length);
        newCards[cards.length] = card;
        cards = newCards;
    }

    public void addCard(Card[] cards) {
        for (Card card : cards) {
            addCard(card);
        }
    }

    public Card[] getCards() {
        return cards;
    }
}
