package src.server;

import src.card.Card;

import java.io.IOException;
import java.net.Socket;

public class Team {
    private Player playerOne;
    private Player playerTwo;

    private int points;

    public Team(Socket socketOne, Socket socketTwo, Card[] cardsOne, Card[] cardsTwo)
            throws IOException {
        this.playerOne = new Player(socketOne, cardsOne);
        this.playerTwo = new Player(socketTwo, cardsTwo);
    }

    public void informOfEnd(boolean won, int enemyPoints) {
        this.playerOne.informOfEnd(won, this.points, enemyPoints);
        this.playerTwo.informOfEnd(won, this.points, enemyPoints);
    }

    public Player getPlayerOne() {
        return this.playerOne;
    }

    public Player getPlayerTwo() {
        return this.playerTwo;
    }

    public int getPoints() {
        return this.points;
    }
}
