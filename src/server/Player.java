package src.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import src.card.Card;

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

    public void informOfAbort() {
        this.out.println("abort");
    }

    public void close() throws IOException {
        this.in.close();
        this.out.close();
        this.socket.close();
    }
}
