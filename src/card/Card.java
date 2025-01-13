package src.card;

public abstract class Card implements Comparable<Card> {

    public abstract int compareTo(Card other);

    public abstract String toString();
}
