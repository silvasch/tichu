
package src.serde;

import src.card.Rank;
import src.card.Suit;

public class DeserializationTester {
    public static void main(String[] args) throws Exception {
        DeserializationTester.rankDeserializationTest();
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
}
