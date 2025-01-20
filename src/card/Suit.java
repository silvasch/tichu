package src.card;

import src.serde.DeserializationException;
import src.serde.PartialDeserialization;
import src.serde.Serializable;
import src.serde.SerializationException;

public enum Suit implements Serializable {
    GREEN,
    BLUE,
    RED,
    BLACK;

    public String serialize() throws SerializationException {
        return String.format("suit(%s)", this.toString().toLowerCase());
    }

    public static PartialDeserialization<Suit> partialDeserialize(String serialized)
            throws DeserializationException {
        if (!serialized.startsWith("suit")) {
            throw new DeserializationException("the input does not start with 'suit'");
        }
        serialized = serialized.substring(5);

        String rawSuit = "";

        while (true) {
            char ch = serialized.charAt(0);
            serialized = serialized.substring(1);

            if (ch == ')') {
                break;
            }

            rawSuit += ch;
        }

        switch (rawSuit) {
            case "green":
                return new PartialDeserialization<Suit>(Suit.GREEN, serialized);
            case "blue":
                return new PartialDeserialization<Suit>(Suit.BLUE, serialized);
            case "red":
                return new PartialDeserialization<Suit>(Suit.RED, serialized);
            case "black":
                return new PartialDeserialization<Suit>(Suit.BLACK, serialized);
            default:
                throw new DeserializationException(
                        String.format("'%s' is not a valid suit.", rawSuit));
        }
    }

    public String toString() {
        return switch (this) {
            case Suit.GREEN -> "Green";
            case Suit.BLUE -> "Blue";
            case Suit.RED -> "Red";
            case Suit.BLACK -> "Black";
        };
    }
}
