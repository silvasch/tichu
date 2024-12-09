package src.player;

import src.Deck;
import src.Move;

public class Player {
    private PlayerInterface playerInterface;
    private Deck deck;

    public Player(PlayerInterface playerInterface, Deck deck) {
        this.playerInterface = playerInterface;
        this.deck = deck;
    }

    public Move[] generateValidMoves() {
        return new Move[0];
    }
}
