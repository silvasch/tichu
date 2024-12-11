package src.player;

import src.Move;
import src.card.Card;

public class Player {
    private PlayerInterface playerInterface;
    private Card[] deck;

    public Player(PlayerInterface playerInterface, Card[] deck) {
        this.playerInterface = playerInterface;
        this.deck = deck;
    }

    public Move makeMove(Move[] previousMoves) {
        return this.playerInterface.makeMove(previousMoves, this.deck);
    }
}
