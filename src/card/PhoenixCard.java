package src.card;

public class PhoenixCard extends Card {

    @Override
    public int compareTo(Card other) {
    	if (other instanceof NormalCard) {
    	    return 1;
    	} else {
    	    // Special cards are equal
    	    return 0;
    	}
    }

    @Override
    public String toString() {
        return "Phoenix";
    }
}
