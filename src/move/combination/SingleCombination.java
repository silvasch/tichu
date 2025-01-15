package src.move.combination;

import src.card.Card;
import src.move.Move;
import src.serde.SerializationException;

public class SingleCombination extends Move implements Comparable<SingleCombination> {
    private Card card;

    public SingleCombination(Card card) {
        this.card = card;
    }

    public String serialize() throws SerializationException {
        return String.format("singlecomb(%s)", this.card.serialize());
    }

    public int compareTo(SingleCombination other) {
        return this.card.compareTo(other.card);
    }

    public String toString() {
        return String.format("Single: %s", this.card);
    }

    public Card getCard() {
        return this.card;
    }
}
