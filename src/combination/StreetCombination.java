package src.combination;

import java.util.Arrays;
import java.util.StringJoiner;

import src.card.*;

public class StreetCombination extends CardCombination {

    private Card[] cards;

    public StreetCombination(Card[] cards) throws Exception{
        Arrays.sort(cards);
        this.cards = cards;
        int thisCardHeight = this.getRank().toHeight();
        for (Card card : cards) {
            if (card instanceof NormalCard normalCard) {
                if (normalCard.getRank().toHeight() != thisCardHeight) {
                    throw new Exception("invalid StreetCombination");
                }
            }
            else {
                throw new Exception("invalid StreetCombination");
            }
            thisCardHeight++;
        }
    }

    public Card[] getCards() {
        return this.cards;
    }


    public Rank getRank() {
        return this.cards[0].getRank();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "Street: ", "");
        for (Card card : this.cards) {
            joiner.add(card.toString());
        }
        return joiner.toString();
    }
}
