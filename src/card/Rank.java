package src.card;

public enum Rank {
    ONE,  // Für Mah Jong
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

    @Override
    public String toString() {
        return switch (this) {
            case ONE -> "Mah Jong";
            case TWO -> "Two";
            case THREE -> "Three";
            case FOUR -> "Four";
            case FIVE -> "Five";
            case SIX -> "Six";
            case SEVEN -> "Seven";
            case EIGHT -> "Eight";
            case NINE -> "Nine";
            case TEN -> "Ten";
            case JACK -> "Jack";
            case QUEEN -> "Queen";
            case KING -> "King";
            case ACE -> "Ace";
        };
    }
    public int toHeight() {  // für Grössenvergleiche
        return this.ordinal() + 1;
    }

    public static Rank heightToRank(int height) {
        return Rank.values()[height - 1];
    }
}
