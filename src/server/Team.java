package src.server;

import java.io.IOException;
import java.net.Socket;

public class Team {
    private Player playerOne;
    private Player playerTwo;

    public Team(Socket socketOne, Socket socketTwo) throws IOException {
        this.playerOne = new Player(socketOne);
        this.playerTwo = new Player(socketTwo);
    }
}
