package src.server;

import src.card.Card;
import src.move.Move;
import src.serde.SerializationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private Card[] cards;

    public Player(Socket socket, Card[] cards) throws IOException {
        this.socket = socket;
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

        this.cards = cards;
    }

    public void informOfStart() {
        this.out.println("start");
    }

    public void informOfMove(Move move, String player) throws SerializationException {
        this.out.println("move-made");
        this.out.println(player);
        this.out.println(move.serialize());
    }

    public void informOfEnd(boolean won, int points, int enemyPoints) {
        this.out.println("ended");
        if (won) {
            this.out.println("true");
        } else {
            this.out.println("false");
        }
        this.out.println(Integer.toString(points));
        this.out.println(Integer.toString(enemyPoints));
    }

    public void informOfAbort() {
        this.out.println("abort");
    }

    public void close() throws IOException {
        this.in.close();
        this.out.close();
        this.socket.close();
    }
}
