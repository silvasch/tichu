package src.card;

public enum Suit {
    GREEN,
    BLUE,
    RED,
    BLACK;

    @Override
    public String toString() {
        switch (this) {
            case Suit.GREEN:
                return "Green";
            case Suit.BLUE:
                return "Blue";
            case Suit.RED:
                return "Red";
            case Suit.BLACK:
                return "Black";
            default:
                return "unreachable";
        }
    }
}
