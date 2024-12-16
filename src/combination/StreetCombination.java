package src.combination;

import java.util.StringJoiner;

import src.card.*;

public class StreetCombination extends CardCombination {

    private Card[] cards;

    public StreetCombination(Card[] cards) throws Exception{
        // TODO: Sortieren aller Karten vor der Initialisierung bei allen Methoden (wird angenommen)
        this.cards = cards;
        int thisCardHeight = this.getRank().toHeight();
        for (Card card : cards) {
            if (card instanceof NormalCard normalCard) {
                if (normalCard.getRank().toHeight() != thisCardHeight) {
                    throw new Exception("invalid StreetCombination");
                }
            }
            else if (!(card instanceof PhoenixCard || card instanceof MahJongCard)) {
                // MahJong is sorted first and therefore defines the height. It cannot invalidate a street.
                // Phoenix can't for obvious reasons.
                throw new Exception("invalid StreetCombination");
            }
            thisCardHeight++;
        }
    }

    public Card[] getCards() {
        return this.cards;
    }


    public Rank getRank() {
        if (this.cards[0] instanceof NormalCard normalCardZero) {
            return normalCardZero.getRank();
        }
        if (this.cards[0] instanceof MahJongCard) {
            return Rank.ONE;
        }
        NormalCard normalCardOne =  (NormalCard) cards[1];  // nur ein Phönix, die Eins ist bereits aussortiert
        return Rank.heightToRank(normalCardOne.getRank().toHeight() - 1);

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
