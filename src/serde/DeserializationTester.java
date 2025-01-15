
package src.serde;

import src.card.NormalCard;
import src.card.Rank;
import src.card.Suit;
import src.move.combination.SingleCombination;

public class DeserializationTester {
    public static void main(String[] args) throws Exception {
        DeserializationTester.rankDeserializationTest();
        DeserializationTester.suitDeserializationTest();
        DeserializationTester.normalCardDeserializationTest();
        DeserializationTester.singleCombinationDeserializationTest();
    }

    public static void rankDeserializationTest() throws Exception {
        PartialDeserialization<Rank> de = Rank.partialDeserialize(Rank.ACE.serialize() + ",asdf");
        assert de.getResult().equals(Rank.ACE);
        assert de.getRemainder().equals(",asdf");
    }

    public static void suitDeserializationTest() throws Exception {
        PartialDeserialization<Suit> de = Suit.partialDeserialize(Suit.RED.serialize() + ",asdf");
        assert de.getResult().equals(Suit.RED);
        assert de.getRemainder().equals(",asdf");
    }

    public static void normalCardDeserializationTest() throws Exception {
        PartialDeserialization<NormalCard> de = NormalCard
                .partialDeserialize(new NormalCard(Suit.BLACK, Rank.ACE).serialize() + ",asdf");
        assert de.getResult().getSuit().equals(Suit.BLACK);
        assert de.getResult().getRank().equals(Rank.ACE);
        assert de.getRemainder().equals(",asdf");
    }

    public static void singleCombinationDeserializationTest() throws Exception {
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
}
