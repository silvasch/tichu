package src.combination;

import src.card.Card;
import src.card.NormalCard;
import src.card.PhoenixCard;
import src.card.Rank;

public class DoubleCombination extends CardCombination{

    private Card cardOne;
    private Card cardTwo;

    public DoubleCombination(Card cardOne, Card cardTwo) throws Exception {
        if (!(cardOne instanceof NormalCard normalCardOne && cardTwo instanceof NormalCard normalCardTwo)) {
            throw new Exception("non valid DoubleCombination");
        }
        if (normalCardOne.getRank() != normalCardTwo.getRank()) {
            throw new Exception("non valid DoubleCombination");
        }
        this.cardOne = cardOne;
        this.cardTwo = cardTwo;
    }

    public Card getCardOne() {
        return this.cardOne;
    }

    public Card getCardTwo() {
        return this.cardTwo;
    }

    public Rank getRank() {
        if (this.cardOne instanceof NormalCard normalCardOne) {
            return normalCardOne.getRank();
        }
        NormalCard normalCardTwo = (NormalCard) this.cardTwo;
        return normalCardTwo.getRank();
    }

    @Override
    public String toString() {
        return String.format("Double: %s, %s", this.cardOne, this.cardTwo);
    }
}
