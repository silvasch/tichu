package src.client;

import src.card.*;

class Client {

    public static void main(String[] args) {
        NormalCard card = new NormalCard(Suit.GREEN, Rank.EIGHT);
        System.out.println(card);
    }
}
