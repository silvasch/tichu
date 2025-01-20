package src.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import src.card.Card;
import src.move.Move;
import src.serde.DeserializationException;

public class Client {

  private Socket socket;
  private PrintWriter out;
  private BufferedReader in;

  public Client(String ip, int port) throws IOException, DeserializationException {
    this.socket = new Socket(ip, port);
    this.out = new PrintWriter(this.socket.getOutputStream(), true);
    this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    System.out.println("connected.");

    boolean doesStart = this.waitForStart();
    if (!doesStart) {
      System.out.println("the game was aborted.");
      return;
    }
    System.out.println("the game is starting.");

    mainloop:
    while (true) {
      String message = this.in.readLine();

      switch (message) {
        case "get-move":
          this.getMove();
        case "move-made":
          this.moveMade();
        case "abort":
          System.out.println("the game has been aborted");
          break mainloop;
        case "ended":
          this.endOfGame();
          break mainloop;
        default:
          {
          }
      }
    }
  }

  private void getMove() throws IOException, DeserializationException {
    String rawCards = this.in.readLine();
    String rejection = this.in.readLine();

    if (!rejection.equals("null")) {
      System.out.println(String.format("your move was rejected: %s", rejection));
    } else {
      System.out.println("it's your turn!");
    }

    String[] cardsParts = rawCards.split("\\|");
    Card[] cards = new Card[] {};
    for (String rawCard : cardsParts) {
      Card card = Card.partialDeserializeCard(rawCard).deserialize();
      cards = Arrays.copyOf(cards, cards.length + 1);
      cards[cards.length - 1] = card;
    }

    for (int i = 0; i < cards.length; i++) {
      System.out.println(String.format("%02d -> %s", i, cards[i]));
    }
  }

  private boolean waitForStart() throws IOException {
    String message = this.in.readLine();
    switch (message) {
      case "start":
        return true;
      case "abort":
        return false;
      default:
        throw new RuntimeException(String.format("received invalid message '%s'.", message));
    }
  }

  private void moveMade() throws IOException, DeserializationException {
    String player = this.in.readLine();
    String rawMove = this.in.readLine();

    Move move = Move.partialDeserializeMove(rawMove).deserialize();

    System.out.println(String.format("%s made the following move:", player));
    System.out.println(move);
  }

  private void endOfGame() throws IOException {
    String rawWon = this.in.readLine();
    String rawPoints = this.in.readLine();
    String rawEnemyPoints = this.in.readLine();

    boolean won = true;
    if (rawWon.equals("false")) {
      won = false;
    }

    int points = 0;
    int enemyPoints = 0;
    try {
      points = Integer.parseInt(rawPoints);
      enemyPoints = Integer.parseInt(rawEnemyPoints);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    System.out.println("the game has ended.");
    if (won) {
      System.out.println(String.format("your team won with %d points.", points));
      System.out.println(String.format("the other team had %d points.", enemyPoints));
    } else {
      System.out.println(String.format("the other team won with %d points.", enemyPoints));
      System.out.println(String.format("your team had %d points.", points));
    }
  }

  public void close() throws IOException {
    this.in.close();
    this.out.close();
    this.socket.close();
  }

  public static void main(String[] args) throws IOException {
    String ip = null;
    int port = -1;

    try {
      ip = args[0];
      port = Integer.parseInt(args[1]);
    } catch (IndexOutOfBoundsException e) {
      if (ip == null) {
        ip = "localhost";
      }

      if (port == -1) {
        port = 3000;
      }
    }

    System.out.println(String.format("connecting to '%s:%s'.", ip, port));

    Client client = null;
    try {
      client = new Client(ip, port);
    } catch (Exception e) {
      if (client != null) {
        client.close();
      }
      e.printStackTrace(System.out);
    }
  }
}
