package src.move;

import src.card.Card;
import src.card.NormalCard;
import src.card.Rank;
import src.card.Suit;
import src.move.combination.InvalidCombinationException;
import src.move.combination.PairCombination;
import src.move.combination.SingleCombination;

public class MoveTester {

    public static void main(String[] args) {
        Card cardOne = new NormalCard(Suit.BLACK, Rank.ACE);
        Card cardTwo = new NormalCard(Suit.GREEN, Rank.SEVEN);
        Card cardThree = new NormalCard(Suit.BLUE, Rank.SEVEN);

        assert new SingleCombination(cardOne).toString().equals("Single: Black Ace");

        try {
            new PairCombination(cardTwo, cardThree);
        } catch (InvalidCombinationException e) {
            throw new RuntimeException(e.getMessage());
        }

        try {
            new PairCombination(cardOne, cardTwo);
            throw new RuntimeException("this test expects an exception to happen.");
        } catch (InvalidCombinationException e) {

        }
    }
}
