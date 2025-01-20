package src.server;

import java.io.IOException;
import java.net.Socket;

import src.card.Card;

public class Team {
    private Player playerOne;
    private Player playerTwo;

    public Team(Socket socketOne, Socket socketTwo, Card[] cardsOne, Card[] cardsTwo) throws IOException {
        this.playerOne = new Player(socketOne, cardsOne);
        this.playerTwo = new Player(socketTwo, cardsTwo);
    }

    public Player getPlayerOne() {
        return this.playerOne;
    }

    public Player getPlayerTwo() {
        return this.playerTwo;
    }
}
