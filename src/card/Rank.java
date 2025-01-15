package src.card;

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
