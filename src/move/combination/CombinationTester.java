package src.move.combination;

import src.card.Card;
import src.card.NormalCard;
import src.card.Rank;
import src.card.Suit;

public class CombinationTester {

    public static void main(String[] args) {
        Card cardOne = new NormalCard(Suit.BLACK, Rank.ACE);
        Card cardTwo = new NormalCard(Suit.GREEN, Rank.SEVEN);
        Card cardThree = new NormalCard(Suit.BLUE, Rank.SEVEN);
        Card cardFour = new NormalCard(Suit.RED, Rank.SEVEN);
        Card cardFive = new NormalCard(Suit.RED, Rank.ACE);

        assert new SingleCombination(cardOne).toString().equals("Single: Black Ace");

        try {
            new PairCombination(cardOne, cardTwo);
            throw new RuntimeException("this test expects an exception to happen.");
        } catch (InvalidCombinationException e) {

        }

        try {
            PairCombination pairOne = new PairCombination(cardOne, cardFive); // aces
            PairCombination pairTwo = new PairCombination(cardTwo, cardThree); // sevens
            assert pairOne.compareTo(pairTwo) > 0;
        } catch (InvalidCombinationException e) {
            throw new RuntimeException(e.getMessage());
        }

        try {
            new TripleCombination(cardTwo, cardThree, cardFour);
        } catch (InvalidCombinationException e) {
            throw new RuntimeException(e.getMessage());
        }

        try {
            new TripleCombination(cardOne, cardTwo, cardThree);
            throw new RuntimeException("this test expects an exception to happen.");
        } catch (InvalidCombinationException e) {

        }
    }
}
