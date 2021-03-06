package com.cnu.blackjack;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Hand {
    private Deck deck;
    private List<Card> cardList = new ArrayList<Card>();

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        Card card = deck.drawCard();
        cardList.add(card);
        return card;
    }

    public Card drawSelectedCard(Card card) {
        cardList.add(card);
        return card;
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
