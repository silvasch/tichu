
package src.serde;

import src.card.NormalCard;
import src.card.Rank;
import src.card.Suit;
import src.move.combination.PairCombination;
import src.move.combination.SingleCombination;
import src.move.combination.TripleCombination;

public class DeserializationTester {
    public static void main(String[] args) throws Exception {
        DeserializationTester.rankDeserializationTest();
        DeserializationTester.suitDeserializationTest();
        DeserializationTester.normalCardDeserializationTest();
        DeserializationTester.singleCombinationDeserializationTest();
        DeserializationTester.pairCombinationDeserializationTest();
        DeserializationTester.tripleCombinationDeserializationTest();
    }

    private static void rankDeserializationTest() throws Exception {
        PartialDeserialization<Rank> de = Rank.partialDeserialize(Rank.ACE.serialize() + ",asdf");
        assert de.getResult().equals(Rank.ACE);
        assert de.getRemainder().equals(",asdf");
    }

    private static void suitDeserializationTest() throws Exception {
        PartialDeserialization<Suit> de = Suit.partialDeserialize(Suit.RED.serialize() + ",asdf");
        assert de.getResult().equals(Suit.RED);
        assert de.getRemainder().equals(",asdf");
    }

    private static void normalCardDeserializationTest() throws Exception {
        PartialDeserialization<NormalCard> de = NormalCard
                .partialDeserialize(new NormalCard(Suit.BLACK, Rank.ACE).serialize() + ",asdf");
        assert de.getResult().getSuit().equals(Suit.BLACK);
        assert de.getResult().getRank().equals(Rank.ACE);
        assert de.getRemainder().equals(",asdf");
    }

    private static void singleCombinationDeserializationTest() throws Exception {
        PartialDeserialization<SingleCombination> de = SingleCombination
                .partialDeserialize(new SingleCombination(new NormalCard(Suit.BLACK, Rank.ACE)).serialize() + ",asdf");
        if (de.getResult().getCard() instanceof NormalCard normalCard) {
            assert normalCard.getSuit().equals(Suit.BLACK);
            assert normalCard.getRank().equals(Rank.ACE);
        } else {
            throw new Exception("single combination did not contain a normal card");
        }
        assert de.getRemainder().equals(",asdf");
    }

    private static void pairCombinationDeserializationTest() throws Exception {
        PartialDeserialization<PairCombination> de = PairCombination
                .partialDeserialize(
                        new PairCombination(new NormalCard(Suit.BLACK, Rank.ACE), new NormalCard(Suit.GREEN, Rank.ACE))
                                .serialize() + ",asdf");
        if (de.getResult().getCardOne() instanceof NormalCard normalCardOne
                && de.getResult().getCardTwo() instanceof NormalCard normalCardTwo) {
            assert normalCardOne.getSuit().equals(Suit.BLACK);
            assert normalCardOne.getRank().equals(Rank.ACE);
            assert normalCardTwo.getSuit().equals(Suit.GREEN);
            assert normalCardTwo.getRank().equals(Rank.ACE);
        } else {
            throw new Exception("pair combination did not contain a normal card");
        }
        assert de.getRemainder().equals(",asdf");
    }

    private static void tripleCombinationDeserializationTest() throws Exception {
        PartialDeserialization<TripleCombination> de = TripleCombination
                .partialDeserialize(
                        new TripleCombination(new NormalCard(Suit.BLACK, Rank.ACE),
                                new NormalCard(Suit.GREEN, Rank.ACE),
                                new NormalCard(Suit.BLUE, Rank.ACE))
                                .serialize() + ",asdf");
        if (de.getResult().getCardOne() instanceof NormalCard normalCardOne
                && de.getResult().getCardTwo() instanceof NormalCard normalCardTwo
                && de.getResult().getCardThree() instanceof NormalCard normalCardThree) {
            assert normalCardOne.getSuit().equals(Suit.BLACK);
            assert normalCardOne.getRank().equals(Rank.ACE);
            assert normalCardTwo.getSuit().equals(Suit.GREEN);
            assert normalCardTwo.getRank().equals(Rank.ACE);
            assert normalCardThree.getSuit().equals(Suit.BLUE);
            assert normalCardThree.getRank().equals(Rank.ACE);
        } else {
            throw new Exception("pair combination did not contain a normal card");
        }
        assert de.getRemainder().equals(",asdf");
    }
}
