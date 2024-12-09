package src.combination;

import src.card.Card;

public class SingleCombination extends CardCombination {

    private Card card;

    public SingleCombination(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return this.card;
    }

    @Override
    public String toString() {
        return String.format("Single: %s", this.card);
    }
}
