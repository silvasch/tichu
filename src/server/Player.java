package src.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringJoiner;
import src.card.Card;
import src.move.Move;
import src.serde.DeserializationException;
import src.serde.SerializationException;

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

  public Move getMove(String rejection)
      throws IOException, DeserializationException, SerializationException {
    this.out.println("get-move");

    StringJoiner joiner = new StringJoiner("|");
    for (Card card : this.cards) {
      joiner.add(card.serialize());
    }
    this.out.println(joiner);
    this.out.println(rejection);

    String serialized = this.in.readLine();

    if (serialized.equals("pass")) {
      return null;
    }

    Move move = Move.partialDeserializeMove(serialized).deserialize();
    return move;
  }

  public Move getMove() throws IOException, DeserializationException, SerializationException {
    return this.getMove("null");
  }

  public void informOfStart() {
    this.out.println("start");
  }

  public void informOfMove(Move move, String player) throws SerializationException {
    this.out.println("move-made");
    this.out.println(player);

    if (move == null) {
      this.out.println("pass");
    } else {
      this.out.println(move.serialize());
    }
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
