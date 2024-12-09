package src.card;

public class NormalCard extends Card {

    private Suit suit;
    private Rank rank;

    public NormalCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    @Override
    public int compareTo(Card other) {
    	if (other instanceof NormalCard) {
    	    return Integer.compare(this.rank.ordinal(), ((NormalCard) other).getRank().ordinal());
    	} else {
    	    // Special cards are higher
    	    return -1;
    	}
    }

    @Override
    public String toString() {
        return String.format("%s %s", suit, rank);
    }
}
