package src.bombs;

import src.card.Card;

public class QuadrupleBomb extends Bomb {

    private Card cardOne;
    private Card cardTwo;
    private Card cardThree;
    private Card cardFour;

    public QuadrupleBomb(
        Card cardOne,
        Card cardTwo,
        Card cardThree,
        Card cardFour
    ) {
        // TODO: verify that this bomb can be created from the arguments
        this.cardOne = cardOne;
        this.cardTwo = cardTwo;
        this.cardThree = cardThree;
        this.cardFour = cardFour;
    }

    public Card getCardOne() {
        return this.cardOne;
    }

    public Card getCardTwo() {
        return this.cardTwo;
    }

    public Card getCardThree() {
        return this.cardThree;
    }

    public Card getCardFour() {
        return this.cardFour;
    }

    @Override
    public String toString() {
        return String.format(
            "Quadruple Bomb: %s, %s, %s, %s",
            this.cardOne,
            this.cardTwo,
            this.cardThree,
            this.cardFour
        );
    }
}
