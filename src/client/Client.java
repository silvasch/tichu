package src.client;

import src.Move;
import src.card.*;
import src.player.HumanPlayer;
import src.player.Player;

class Client {

    public static void main(String[] args) {
        Player player = new Player(new HumanPlayer(), new Card[] { new NormalCard(Suit.GREEN, Rank.KING),
                new NormalCard(Suit.RED, Rank.KING), new NormalCard(Suit.BLUE, Rank.SEVEN) });
        System.out.println(player.makeMove(new Move[] {}));
    }
}
