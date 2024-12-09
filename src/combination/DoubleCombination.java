package src.combination;

import src.card.Card;

public class DoubleCombination extends CardCombination {

    private Card cardOne;
    private Card cardTwo;

    public DoubleCombination(Card cardOne, Card cardTwo) throws Exception {
        // TODO: verify that this combination can be created from the arguments
        this.cardOne = cardOne;
        this.cardTwo = cardTwo;
    }

    public Card getCardOne() {
        return this.cardOne;
    }

    public Card getCardTwo() {
        return this.cardTwo;
    }

    @Override
    public String toString() {
        return String.format("Double: %s, %s", this.cardOne, this.cardTwo);
    }
}
