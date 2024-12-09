package src.combination;

import src.card.Card;

public class SingleCombination extends CardCombination {

    private Card card;

    public SingleCombination(Card card) {
        // TODO: verify that this combination can be created from the arguments
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
