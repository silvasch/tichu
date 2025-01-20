package src.serde;

import src.card.NormalCard;
import src.card.Rank;
import src.card.Suit;
import src.move.combination.FullHouseCombination;
import src.move.combination.PairCombination;
import src.move.combination.SingleCombination;
import src.move.combination.StairCombination;
import src.move.combination.StreetCombination;
import src.move.combination.TripleCombination;

public class SerializationTester {
    public static void main(String[] args) throws Exception {
        SingleCombination single = new SingleCombination(new NormalCard(Suit.BLACK, Rank.ACE));

        PairCombination pair =
                new PairCombination(
                        new NormalCard(Suit.BLACK, Rank.ACE), new NormalCard(Suit.BLUE, Rank.ACE));

        TripleCombination triple =
                new TripleCombination(
                        new NormalCard(Suit.BLACK, Rank.ACE),
                        new NormalCard(Suit.BLUE, Rank.ACE),
                        new NormalCard(Suit.GREEN, Rank.ACE));

        FullHouseCombination fullHouse =
                new FullHouseCombination(
                        new TripleCombination(
                                new NormalCard(Suit.BLACK, Rank.ACE),
                                new NormalCard(Suit.BLUE, Rank.ACE),
                                new NormalCard(Suit.GREEN, Rank.ACE)),
                        new PairCombination(
                                new NormalCard(Suit.BLACK, Rank.ACE),
                                new NormalCard(Suit.BLUE, Rank.ACE)));

        StreetCombination street =
                new StreetCombination(
                        new NormalCard[] {
                            new NormalCard(Suit.BLACK, Rank.FIVE),
                            new NormalCard(Suit.GREEN, Rank.SIX),
                            new NormalCard(Suit.BLACK, Rank.SEVEN),
                            new NormalCard(Suit.RED, Rank.NINE),
                            new NormalCard(Suit.RED, Rank.EIGHT),
                            new NormalCard(Suit.BLUE, Rank.TEN),
                        });
        StairCombination stair =
                new StairCombination(
                        new PairCombination[] {
                            new PairCombination(
                                    new NormalCard(Suit.BLACK, Rank.SEVEN),
                                    new NormalCard(Suit.BLUE, Rank.SEVEN)),
                            new PairCombination(
                                    new NormalCard(Suit.RED, Rank.EIGHT),
                                    new NormalCard(Suit.BLACK, Rank.EIGHT)),
                        });

        System.out.println(single.serialize());
        System.out.println(pair.serialize());
        System.out.println(triple.serialize());
        System.out.println(fullHouse.serialize());
        System.out.println(street.serialize());
        System.out.println(stair.serialize());
    }
}
