package src.card;

import src.serde.DeserializationException;
import src.serde.PartialDeserialization;
import src.serde.Serializable;
import src.serde.SerializationException;

public enum Rank implements Serializable {
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE;

    public String serialize() throws SerializationException {
        return String.format("rank(%s)", this.toString().toLowerCase());
    }

    public static PartialDeserialization<Rank> partialDeserialize(String serialized)
            throws DeserializationException {
        if (!serialized.startsWith("rank")) {
            throw new DeserializationException(
                    "the input does not start with 'rank'");
        }
        serialized = serialized.substring(5);

        String rawRank = "";

        while (true) {
            char ch = serialized.charAt(0);
            serialized = serialized.substring(1);

            if (ch == ')') {
                break;
            }

            rawRank += ch;
        }

        switch (rawRank) {
            case "two":
                return new PartialDeserialization<Rank>(Rank.TWO, serialized);
            case "three":
                return new PartialDeserialization<Rank>(Rank.THREE, serialized);
            case "four":
                return new PartialDeserialization<Rank>(Rank.FOUR, serialized);
            case "five":
                return new PartialDeserialization<Rank>(Rank.FIVE, serialized);
            case "six":
                return new PartialDeserialization<Rank>(Rank.SIX, serialized);
            case "seven":
                return new PartialDeserialization<Rank>(Rank.SEVEN, serialized);
            case "eight":
                return new PartialDeserialization<Rank>(Rank.EIGHT, serialized);
            case "nine":
                return new PartialDeserialization<Rank>(Rank.NINE, serialized);
            case "ten":
                return new PartialDeserialization<Rank>(Rank.TEN, serialized);
            case "jack":
                return new PartialDeserialization<Rank>(Rank.JACK, serialized);
            case "queen":
                return new PartialDeserialization<Rank>(Rank.QUEEN, serialized);
            case "king":
                return new PartialDeserialization<Rank>(Rank.KING, serialized);
            case "ace":
                return new PartialDeserialization<Rank>(Rank.ACE, serialized);
            default:
                throw new DeserializationException(
                        String.format("'%s' is not a valid rank.", rawRank));
        }
    }

    public String toString() {
        return switch (this) {
            case Rank.TWO -> "Two";
            case Rank.THREE -> "Three";
            case Rank.FOUR -> "Four";
            case Rank.FIVE -> "Five";
            case Rank.SIX -> "Six";
            case Rank.SEVEN -> "Seven";
            case Rank.EIGHT -> "Eight";
            case Rank.NINE -> "Nine";
            case Rank.TEN -> "Ten";
            case Rank.JACK -> "Jack";
            case Rank.QUEEN -> "Queen";
            case Rank.KING -> "King";
            case Rank.ACE -> "Ace";
        };
    }
}
