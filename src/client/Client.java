package src.client;

import src.card.*;
import src.card_combination.StreetCombination;
import src.card_combination.TripleCombination;

class Client {

    public static void main(String[] args) {
        NormalCard cardOne = new NormalCard(Suit.GREEN, Rank.EIGHT);
        NormalCard cardTwo = new NormalCard(Suit.RED, Rank.NINE);
        NormalCard cardThree = new NormalCard(Suit.RED, Rank.ACE);
        StreetCombination street = new StreetCombination(new Card[]{cardOne, cardTwo, cardThree});
    }
}
