
package src.serde;

import src.card.NormalCard;
import src.card.Rank;
import src.card.Suit;
import src.move.combination.StreetCombination;
import src.move.combination.FullHouseCombination;
import src.move.combination.PairCombination;
import src.move.combination.SingleCombination;
import src.move.combination.StairCombination;
import src.move.combination.TripleCombination;

public class DeserializationTester {
    public static void main(String[] args) throws Exception {
        DeserializationTester.rankDeserializationTest();
        DeserializationTester.suitDeserializationTest();
        DeserializationTester.normalCardDeserializationTest();
        DeserializationTester.singleCombinationDeserializationTest();
        DeserializationTester.pairCombinationDeserializationTest();
        DeserializationTester.tripleCombinationDeserializationTest();
        DeserializationTester.fullHouseCombinationDeserializationTest();
        DeserializationTester.streetCombinationDeserializationTest();
        DeserializationTester.stairCombinationDeserializationTest();
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
        NormalCard normalCard = new NormalCard(Suit.BLACK, Rank.ACE);
        PartialDeserialization<NormalCard> de = NormalCard
                .partialDeserialize(normalCard.serialize() + ",asdf");
        assert de.getResult().equals(normalCard);
        assert de.getRemainder().equals(",asdf");
    }

    private static void singleCombinationDeserializationTest() throws Exception {
        SingleCombination single = new SingleCombination(new NormalCard(Suit.BLACK, Rank.ACE));
        PartialDeserialization<SingleCombination> de = SingleCombination
                .partialDeserialize(single.serialize() + ",asdf");
        assert de.getResult().equals(single);
        assert de.getRemainder().equals(",asdf");
    }

    private static void pairCombinationDeserializationTest() throws Exception {
        PairCombination pair = new PairCombination(new NormalCard(Suit.BLACK, Rank.ACE),
                new NormalCard(Suit.GREEN, Rank.ACE));
        PartialDeserialization<PairCombination> de = PairCombination
                .partialDeserialize(pair.serialize() + ",asdf");
        assert de.getResult().equals(pair);
        assert de.getRemainder().equals(",asdf");
    }

    private static void tripleCombinationDeserializationTest() throws Exception {
        TripleCombination triple = new TripleCombination(new NormalCard(Suit.BLACK, Rank.ACE),
                new NormalCard(Suit.GREEN, Rank.ACE),
                new NormalCard(Suit.BLUE, Rank.ACE));
        PartialDeserialization<TripleCombination> de = TripleCombination
                .partialDeserialize(triple.serialize() + ",asdf");
        assert de.getResult().equals(triple);
        assert de.getRemainder().equals(",asdf");
    }

    private static void fullHouseCombinationDeserializationTest() throws Exception {
        FullHouseCombination fullHouse = new FullHouseCombination(
                new TripleCombination(new NormalCard(Suit.BLACK, Rank.ACE),
                        new NormalCard(Suit.GREEN, Rank.ACE), new NormalCard(Suit.BLUE, Rank.ACE)),
                new PairCombination(new NormalCard(Suit.BLACK, Rank.SEVEN),
                        new NormalCard(Suit.GREEN, Rank.SEVEN)));
        PartialDeserialization<FullHouseCombination> de = FullHouseCombination
                .partialDeserialize(fullHouse.serialize() + ",asdf");
        assert de.getResult().equals(fullHouse);
        assert de.getRemainder().equals(",asdf");
    }

    private static void streetCombinationDeserializationTest() throws Exception {
        StreetCombination street = new StreetCombination(new NormalCard[] { new NormalCard(Suit.BLACK, Rank.SEVEN),
                new NormalCard(Suit.GREEN, Rank.EIGHT),
                new NormalCard(Suit.BLUE, Rank.NINE),
                new NormalCard(Suit.BLACK, Rank.TEN),
                new NormalCard(Suit.RED, Rank.JACK) });
        PartialDeserialization<StreetCombination> de = StreetCombination
                .partialDeserialize(street.serialize() + ",asdf");
        assert de.getResult().equals(street);
        assert de.getRemainder().equals(",asdf");
    }

    private static void stairCombinationDeserializationTest() throws Exception {
        StairCombination stair = new StairCombination(new PairCombination[] {
                new PairCombination(new NormalCard(Suit.BLACK, Rank.FIVE), new NormalCard(Suit.RED, Rank.FIVE)),
                new PairCombination(new NormalCard(Suit.BLACK, Rank.SIX), new NormalCard(Suit.RED, Rank.SIX)),
        });
        PartialDeserialization<StairCombination> de = StairCombination
                .partialDeserialize(stair.serialize() + ",asdf");
        assert de.getResult().equals(stair);
        assert de.getRemainder().equals(",asdf");
    }
}
