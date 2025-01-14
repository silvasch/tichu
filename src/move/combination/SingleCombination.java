package src.move.combination;

import src.card.Card;
import src.move.Move;

public class SingleCombination extends Move implements Comparable<SingleCombination> {
    private Card card;

    public SingleCombination(Card card) {
        this.card = card;
    }

    public int compareTo(SingleCombination other) {
        return this.card.compareTo(other.card);
    }

    public String toString() {
        return String.format("Single: %s", this.card);
    }
}
