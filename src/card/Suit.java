package src.card;

public enum Suit {
    GREEN,
    BLUE,
    RED,
    BLACK;

    public String toString() {
        return switch (this) {
            case Suit.GREEN -> "Green";
            case Suit.BLUE -> "Blue";
            case Suit.RED -> "Red";
            case Suit.BLACK -> "Black";
        };
    }
}
