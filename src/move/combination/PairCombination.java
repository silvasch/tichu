package src.move.combination;

import src.card.Card;
import src.card.NormalCard;
import src.move.Move;

public class PairCombination extends Move {
    private Card cardOne;
    private Card cardTwo;

    public PairCombination(Card cardOne, Card cardTwo) throws InvalidCombinationException {
        if (cardOne instanceof NormalCard normalCardOne && cardTwo instanceof NormalCard normalCardTwo) {
            if (normalCardOne.getRank() != normalCardTwo.getRank()) {
                throw new InvalidCombinationException("to form a pair, the two cards have to be of equal rank.");
            }
        }

        this.cardOne = cardOne;
        this.cardTwo = cardTwo;
    }

    public String toString() {
        return String.format("Pair: %s - %s", this.cardOne, this.cardTwo);
    }
}
