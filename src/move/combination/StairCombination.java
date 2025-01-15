package src.move.combination;

import java.util.Arrays;
import java.util.StringJoiner;

import src.card.Card;
import src.card.NormalCard;
import src.card.Rank;
import src.serde.SerializationException;

public class StairCombination extends Combination {
    private PairCombination[] pairs;

    public StairCombination(PairCombination[] pairs) throws InvalidCombinationException {
        if (pairs.length < 2) {
            throw new InvalidCombinationException("stairs have to contain at least two pairs.");
        }

        Arrays.sort(pairs);
        int sum = 0;
        for (int i = 1; i < pairs.length; i++) {
            Card cardOne = pairs[i - 1].getCardOne();
            Card cardTwo = pairs[i].getCardOne();
            if (cardOne instanceof NormalCard normalCardOne && cardTwo instanceof NormalCard normalCardTwo) {
                Rank rankOne = normalCardOne.getRank();
                Rank rankTwo = normalCardTwo.getRank();
                sum += rankTwo.ordinal() - rankOne.ordinal();
            }
        }
        if (sum + 1 != pairs.length) {
            throw new InvalidCombinationException("the pairs in a stair have to be consecutive.");
        }

        this.pairs = pairs;
    }

    public String serialize() throws SerializationException {
        StringJoiner joiner = new StringJoiner(",");
        for (PairCombination pair : this.pairs) {
            joiner.add(pair.getCardOne().serialize());
            joiner.add(pair.getCardTwo().serialize());
        }
        return String.format("staircomb(%s)", joiner);
    }

    public boolean equals(StairCombination other) {
        return Arrays.deepEquals(this.pairs, other.pairs);
    }

    public int compareTo(StairCombination other) {
        return this.pairs[0].compareTo(other.pairs[0]);
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner(" - ");
        for (PairCombination pair : this.pairs) {
            joiner.add(pair.getCardOne().toString());
            joiner.add(pair.getCardTwo().toString());
        }
        return String.format("Stair: %s", joiner.toString());
    }
}
