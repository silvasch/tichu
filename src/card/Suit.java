package src.card;

import src.serde.Serializable;

public enum Suit implements Serializable {
    GREEN,
    BLUE,
    RED,
    BLACK;

    public String serialize() {
        return String.format("suit(%s)", this.toString().toLowerCase());
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
