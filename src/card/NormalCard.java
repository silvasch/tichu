package src.card;

public class NormalCard extends Card {

    private Suit suit;
    private Rank rank;

    public NormalCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
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
