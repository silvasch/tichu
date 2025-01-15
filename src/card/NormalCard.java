package src.card;

import src.serde.DeserializationException;
import src.serde.PartialDeserialization;
import src.serde.SerializationException;

public class NormalCard extends Card {

    private Suit suit;
    private Rank rank;

    public NormalCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String serialize() throws SerializationException {
        return String.format("normalcard(%s,%s)", this.suit.serialize(), this.rank.serialize());
    }

    public static PartialDeserialization<NormalCard> partialDeserialize(String serialized)
            throws DeserializationException {
        if (!serialized.startsWith("normalcard")) {
            throw new DeserializationException(
                    "the input does not start with 'normalcard'");
        }
        serialized = serialized.substring(11);

        PartialDeserialization<Suit> suitDe = Suit.partialDeserialize(serialized);
        serialized = suitDe.getRemainder().substring(1);
        PartialDeserialization<Rank> rankDe = Rank.partialDeserialize(serialized);

        if (!rankDe.getRemainder().startsWith(")")) {
            throw new DeserializationException("normalcard is unclosed");
        }

        serialized = rankDe.getRemainder().substring(1);

        return new PartialDeserialization<NormalCard>(new NormalCard(suitDe.getResult(), rankDe.getResult()),
                serialized);
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    public int compareTo(Card other) {
        if (other instanceof NormalCard normalOther) {
            return this.rank.compareTo(normalOther.rank);
        }

        // this project does not currently support special cards.
        // however, if it did, most special cards would be stronger than
        // a normal card, so we can say that the normal card is weaker.
        return -1;
    }

    public String toString() {
        return String.format("%s %s", this.suit, this.rank);
    }
}
