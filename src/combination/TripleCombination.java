package src.combination;

import src.card.Card;
import src.card.NormalCard;
import src.card.Rank;

public class TripleCombination extends CardCombination {

    private Card cardOne;
    private Card cardTwo;
    private Card cardThree;

    public TripleCombination(Card cardOne, Card cardTwo, Card cardThree) throws Exception {
        if (!(cardOne instanceof NormalCard normalCardOne && cardTwo instanceof NormalCard normalCardTwo && cardThree instanceof NormalCard normalCardThree)) {
            throw new Exception("non valid TripleCombination");
        }
        if (!(normalCardOne.getRank() == normalCardTwo.getRank() && normalCardOne.getRank() == normalCardThree.getRank())) {
            throw new Exception("non valid TripleCombination");
        }
        this.cardOne = cardOne;
        this.cardTwo = cardTwo;
        this.cardThree = cardThree;
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

    public Rank getRank() {
        if (this.cardOne instanceof NormalCard normalCardOne) {
            return normalCardOne.getRank();
        }
        NormalCard normalCardTwo = (NormalCard) this.cardTwo;
        return normalCardTwo.getRank();  // höchstens ein Phönix
    }

    @Override
    public String toString() {
        return String.format(
            "Triple: %s, %s, %s",
            this.cardOne,
            this.cardTwo,
            this.cardThree
        );
    }
}
