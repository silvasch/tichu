package src.card;

import src.serde.Serializable;

public abstract class Card implements Comparable<Card>, Serializable {

    public abstract String toString();
}
