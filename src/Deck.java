package src;

import src.card.Card;

public class Deck {
    private Card[] cards;

    public Deck(Card[] cards) {
        this.cards = cards;
    }

    public Card[] getCards() {
        return this.cards;
    }
}
