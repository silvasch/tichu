package src.move.combination;

import java.util.Arrays;
import java.util.StringJoiner;

import src.card.Card;
import src.card.NormalCard;
import src.card.Rank;
import src.serde.SerializationException;

public class StreetCombination extends Combination {
    private Card[] cards;

    public StreetCombination(Card[] cards) throws InvalidCombinationException {
        if (cards.length < 5) {
            throw new InvalidCombinationException("streets have to contain at least five cards.");
        }

        Arrays.sort(cards);
        int sum = 0;
        for (int i = 1; i < cards.length; i++) {
            if (cards[i - 1] instanceof NormalCard normalCardOne && cards[i] instanceof NormalCard normalCardTwo) {
                Rank rankOne = normalCardOne.getRank();
                Rank rankTwo = normalCardTwo.getRank();
                sum += rankTwo.ordinal() - rankOne.ordinal();
            }
        }
        if (sum + 1 != cards.length) {
            throw new InvalidCombinationException("the cards in a street have to be consecutive.");
        }

        this.cards = cards;
    }

    public String serialize() throws SerializationException {
        StringJoiner joiner = new StringJoiner(",");
        for (Card card : this.cards) {
            joiner.add(card.serialize());
        }
        return String.format("streetcomb(%s)", joiner);
    }

    public boolean equals(StreetCombination other) {
        return Arrays.deepEquals(this.cards, other.cards);
    }

    public int compareTo(StreetCombination other) {
        return this.cards[0].compareTo(other.cards[0]);
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner(" - ");
        for (Card card : this.cards) {
            joiner.add(card.toString());
        }
        return String.format("Street: %s", joiner.toString());
    }
}
