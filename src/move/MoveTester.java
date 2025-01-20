package src.move;

import src.card.Card;
import src.card.NormalCard;
import src.card.Rank;
import src.card.Suit;
import src.move.combination.FullHouseCombination;
import src.move.combination.InvalidCombinationException;
import src.move.combination.PairCombination;
import src.move.combination.SingleCombination;
import src.move.combination.StairCombination;
import src.move.combination.StreetCombination;
import src.move.combination.TripleCombination;

public class MoveTester {

    public static void main(String[] args) throws Exception {
        assert Move.constructFromCards(new Card[] {new NormalCard(Suit.BLACK, Rank.ACE)})
                .equals(new SingleCombination(new NormalCard(Suit.BLACK, Rank.ACE)));

        assert Move.constructFromCards(
                        new Card[] {
                            new NormalCard(Suit.BLACK, Rank.TEN),
                            new NormalCard(Suit.RED, Rank.TEN),
                            new NormalCard(Suit.BLUE, Rank.TEN)
                        })
                .equals(
                        new TripleCombination(
                                new NormalCard(Suit.BLACK, Rank.TEN),
                                new NormalCard(Suit.RED, Rank.TEN),
                                new NormalCard(Suit.BLUE, Rank.TEN)));

        assert Move.constructFromCards(
                        new Card[] {
                            new NormalCard(Suit.BLACK, Rank.ACE),
                            new NormalCard(Suit.GREEN, Rank.KING),
                            new NormalCard(Suit.RED, Rank.ACE),
                            new NormalCard(Suit.BLUE, Rank.KING)
                        })
                .equals(
                        new StairCombination(
                                new PairCombination[] {
                                    new PairCombination(
                                            new NormalCard(Suit.BLACK, Rank.ACE),
                                            new NormalCard(Suit.RED, Rank.ACE)),
                                    new PairCombination(
                                            new NormalCard(Suit.GREEN, Rank.KING),
                                            new NormalCard(Suit.BLUE, Rank.KING))
                                }));

        assert Move.constructFromCards(
                        new Card[] {
                            new NormalCard(Suit.BLACK, Rank.ACE),
                            new NormalCard(Suit.RED, Rank.ACE),
                            new NormalCard(Suit.GREEN, Rank.KING),
                            new NormalCard(Suit.BLUE, Rank.KING),
                            new NormalCard(Suit.GREEN, Rank.QUEEN),
                            new NormalCard(Suit.BLUE, Rank.QUEEN)
                        })
                .equals(
                        new StairCombination(
                                new PairCombination[] {
                                    new PairCombination(
                                            new NormalCard(Suit.BLACK, Rank.ACE),
                                            new NormalCard(Suit.RED, Rank.ACE)),
                                    new PairCombination(
                                            new NormalCard(Suit.GREEN, Rank.KING),
                                            new NormalCard(Suit.BLUE, Rank.KING)),
                                    new PairCombination(
                                            new NormalCard(Suit.GREEN, Rank.QUEEN),
                                            new NormalCard(Suit.BLUE, Rank.QUEEN))
                                }));

        assert Move.constructFromCards(
                        new Card[] {
                            new NormalCard(Suit.BLACK, Rank.ACE),
                            new NormalCard(Suit.RED, Rank.KING),
                            new NormalCard(Suit.GREEN, Rank.QUEEN),
                            new NormalCard(Suit.BLUE, Rank.JACK),
                            new NormalCard(Suit.GREEN, Rank.TEN),
                            new NormalCard(Suit.BLUE, Rank.NINE)
                        })
                .equals(
                        new StreetCombination(
                                new Card[] {
                                    new NormalCard(Suit.BLACK, Rank.ACE),
                                    new NormalCard(Suit.RED, Rank.KING),
                                    new NormalCard(Suit.GREEN, Rank.QUEEN),
                                    new NormalCard(Suit.BLUE, Rank.JACK),
                                    new NormalCard(Suit.GREEN, Rank.TEN),
                                    new NormalCard(Suit.BLUE, Rank.NINE)
                                }));

        assert Move.constructFromCards(
                        new Card[] {
                            new NormalCard(Suit.BLACK, Rank.FIVE),
                            new NormalCard(Suit.RED, Rank.KING),
                            new NormalCard(Suit.GREEN, Rank.SEVEN),
                            new NormalCard(Suit.BLUE, Rank.TEN),
                            new NormalCard(Suit.GREEN, Rank.NINE),
                            new NormalCard(Suit.BLUE, Rank.QUEEN),
                            new NormalCard(Suit.BLUE, Rank.JACK),
                            new NormalCard(Suit.GREEN, Rank.SIX),
                            new NormalCard(Suit.BLACK, Rank.EIGHT)
                        })
                .equals(
                        new StreetCombination(
                                new Card[] {
                                    new NormalCard(Suit.GREEN, Rank.SIX),
                                    new NormalCard(Suit.BLUE, Rank.JACK),
                                    new NormalCard(Suit.BLUE, Rank.TEN),
                                    new NormalCard(Suit.GREEN, Rank.NINE),
                                    new NormalCard(Suit.RED, Rank.KING),
                                    new NormalCard(Suit.GREEN, Rank.SEVEN),
                                    new NormalCard(Suit.BLACK, Rank.EIGHT),
                                    new NormalCard(Suit.BLACK, Rank.FIVE),
                                    new NormalCard(Suit.BLUE, Rank.QUEEN)
                                }));

        assert Move.constructFromCards(
                        new Card[] {
                            new NormalCard(Suit.BLACK, Rank.TWO),
                            new NormalCard(Suit.RED, Rank.THREE),
                            new NormalCard(Suit.GREEN, Rank.TWO),
                            new NormalCard(Suit.BLUE, Rank.TWO),
                            new NormalCard(Suit.BLACK, Rank.THREE)
                        })
                .equals(
                        new FullHouseCombination(
                                new TripleCombination(
                                        new NormalCard(Suit.BLACK, Rank.TWO),
                                        new NormalCard(Suit.GREEN, Rank.TWO),
                                        new NormalCard(Suit.BLUE, Rank.TWO)),
                                new PairCombination(
                                        new NormalCard(Suit.BLACK, Rank.THREE),
                                        new NormalCard(Suit.RED, Rank.THREE))));

        assert Move.constructFromCards(
                        new Card[] {
                            new NormalCard(Suit.BLACK, Rank.ACE), new NormalCard(Suit.RED, Rank.ACE)
                        })
                .equals(
                        new PairCombination(
                                new NormalCard(Suit.BLACK, Rank.ACE),
                                new NormalCard(Suit.RED, Rank.ACE)));

        assert Move.constructFromCards(
                        new Card[] {
                            new NormalCard(Suit.BLACK, Rank.ACE), new NormalCard(Suit.RED, Rank.ACE)
                        })
                .equals(
                        new PairCombination(
                                new NormalCard(Suit.BLACK, Rank.ACE),
                                new NormalCard(Suit.RED, Rank.ACE)));

        try {
            Move.constructFromCards(
                    new Card[] {
                        new NormalCard(Suit.BLACK, Rank.EIGHT), new NormalCard(Suit.RED, Rank.SEVEN)
                    });
            throw new RuntimeException("this test expects an exception to happen.");
        } catch (InvalidCombinationException e) {

        }

        try {
            Move.constructFromCards(
                    new Card[] {
                        new NormalCard(Suit.BLACK, Rank.EIGHT),
                        new NormalCard(Suit.RED, Rank.SEVEN),
                        new NormalCard(Suit.BLUE, Rank.SEVEN)
                    });
            throw new RuntimeException("this test expects an exception to happen.");
        } catch (InvalidCombinationException e) {

        }

        try {
            Move.constructFromCards(
                    new Card[] {
                        new NormalCard(Suit.BLACK, Rank.EIGHT),
                        new NormalCard(Suit.RED, Rank.EIGHT),
                        new NormalCard(Suit.BLUE, Rank.SIX),
                        new NormalCard(Suit.GREEN, Rank.SIX)
                    });
            throw new RuntimeException("this test expects an exception to happen.");
        } catch (InvalidCombinationException e) {

        }

        try {
            Move.constructFromCards(
                    new Card[] {
                        new NormalCard(Suit.BLACK, Rank.EIGHT),
                        new NormalCard(Suit.RED, Rank.SEVEN),
                        new NormalCard(Suit.BLUE, Rank.NINE),
                        new NormalCard(Suit.GREEN, Rank.TEN),
                        new NormalCard(Suit.GREEN, Rank.QUEEN)
                    });
            throw new RuntimeException("this test expects an exception to happen.");
        } catch (InvalidCombinationException e) {

        }
    }
}
