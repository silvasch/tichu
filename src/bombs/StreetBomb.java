package src.bombs;

import java.util.StringJoiner;
import src.card.Card;

public class StreetBomb extends Bomb {

    private Card[] cards;

    public StreetBomb(Card[] cards) {
        // TODO: verify that this bomb can be created from the arguments
        this.cards = cards;
    }

    public Card[] getCards() {
        return this.cards;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "Street Bomb: ", "");
        for (Card card : this.cards) {
            joiner.add(card.toString());
        }
        return joiner.toString();
    }
}
