package src.move;

import src.card.Card;
import src.card.NormalCard;
import src.card.Rank;
import src.card.Suit;
import src.move.combination.SingleCombination;

public class MoveTester {

    public static void main(String[] args) {
        Card card = new NormalCard(Suit.BLACK, Rank.ACE);
        Move move = new SingleCombination(card);

        assert move.toString().equals("Single: Black Ace");
    }
}
