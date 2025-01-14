package src.move.combination;

import src.card.Card;
import src.card.NormalCard;
import src.card.Rank;
import src.card.Suit;

public class CombinationTester {

    public static void main(String[] args) {
        assert new SingleCombination(new NormalCard(Suit.BLACK, Rank.ACE)).toString().equals("Single: Black Ace");

        try {
            new PairCombination(new NormalCard(Suit.BLACK, Rank.ACE), new NormalCard(Suit.RED, Rank.FIVE));
            throw new RuntimeException("this test expects an exception to happen.");
        } catch (InvalidCombinationException e) {

        }

        try {
            PairCombination pairOne = new PairCombination(new NormalCard(Suit.BLACK, Rank.ACE),
                    new NormalCard(Suit.GREEN, Rank.ACE)); // aces
            PairCombination pairTwo = new PairCombination(new NormalCard(Suit.RED, Rank.SEVEN),
                    new NormalCard(Suit.BLUE, Rank.SEVEN)); // aces
            assert pairOne.compareTo(pairTwo) > 0;
        } catch (InvalidCombinationException e) {
            throw new RuntimeException(e);
        }

        try {
            new TripleCombination(new NormalCard(Suit.BLACK, Rank.QUEEN), new NormalCard(Suit.RED, Rank.QUEEN),
                    new NormalCard(Suit.GREEN, Rank.QUEEN));
        } catch (InvalidCombinationException e) {
            throw new RuntimeException(e);
        }

        try {
            new TripleCombination(new NormalCard(Suit.BLACK, Rank.SEVEN), new NormalCard(Suit.RED, Rank.QUEEN),
                    new NormalCard(Suit.GREEN, Rank.QUEEN));
            throw new RuntimeException("this test expects an exception to happen.");
        } catch (InvalidCombinationException e) {

        }

        try {
            new StreetCombination(new Card[] {
                    new NormalCard(Suit.BLACK, Rank.FOUR),
                    new NormalCard(Suit.BLACK, Rank.TWO),
                    new NormalCard(Suit.RED, Rank.THREE),
                    new NormalCard(Suit.GREEN, Rank.SIX),
                    new NormalCard(Suit.BLUE, Rank.FIVE),
            });
        } catch (InvalidCombinationException e) {
            throw new RuntimeException(e);
        }

        try {
            new StreetCombination(new Card[] {
                    new NormalCard(Suit.BLACK, Rank.FOUR),
                    new NormalCard(Suit.BLACK, Rank.TWO),
                    new NormalCard(Suit.RED, Rank.THREE),
                    new NormalCard(Suit.GREEN, Rank.SIX),
                    new NormalCard(Suit.BLUE, Rank.KING),
            });
            throw new RuntimeException("this test expects an exception to happen.");
        } catch (InvalidCombinationException e) {
        }
    }
}
