package src.move.combination;

import src.card.Card;
import src.card.NormalCard;
import src.card.Rank;
import src.move.Move;

public class TripleCombination extends Move implements Comparable<TripleCombination> {
    private Card cardOne;
    private Card cardTwo;
    private Card cardThree;

    public TripleCombination(Card cardOne, Card cardTwo, Card cardThree) throws InvalidCombinationException {
        // because this project only supports normal cards, this does not require any
        // further logic.
        if (cardOne instanceof NormalCard normalCardOne && cardTwo instanceof NormalCard normalCardTwo
                && cardThree instanceof NormalCard normalCardThree) {
            Rank expectedRank = normalCardOne.getRank();
            if (normalCardTwo.getRank() != expectedRank || normalCardThree.getRank() != expectedRank) {
                throw new InvalidCombinationException("to form a triple, all three cards have to be of equal rank.");
            }
        }

        this.cardOne = cardOne;
        this.cardTwo = cardTwo;
        this.cardThree = cardThree;
    }

    public int compareTo(TripleCombination other) {
        // because cardOne cardTwo and cardThree have to be of the same rank, we can
        // just compare one of them.
        return this.cardOne.compareTo(other.cardOne);
    }

    public String toString() {
        return String.format("Triple: %s - %s - %s", this.cardOne, this.cardTwo, this.cardThree);
    }
}
