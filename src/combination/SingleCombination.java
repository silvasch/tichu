package src.combination;

import src.card.Card;
import src.card.Rank;

public class SingleCombination extends CardCombination {

    private Card card;

    public SingleCombination(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return this.card;
    }

    public Rank getRank() {
        return this.card.getRank();
    }

    @Override
    public String toString() {
        return String.format("Single: %s", this.card);
    }
}
