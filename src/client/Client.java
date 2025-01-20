package src.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import src.card.Card;
import src.move.Move;
import src.move.combination.InvalidCombinationException;
import src.serde.DeserializationException;
import src.serde.SerializationException;

public class Client {

  private Socket socket;
  private PrintWriter out;
  private BufferedReader in;

  public Client(String ip, int port)
      throws IOException, DeserializationException, SerializationException {
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
          break;
        case "move-made":
          this.moveMade();
          break;
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

  private void getMove() throws IOException, DeserializationException, SerializationException {
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

    Move move;

    outer:
    while (true) {
      Card[] cardsToPlay = new Card[] {};

      Scanner scanner = new Scanner(System.in);
      System.out.print("> ");
      String rawIndices = scanner.nextLine();
      scanner.close();

      for (String rawIndex : rawIndices.split(",")) {
        rawIndex = rawIndex.trim();
        int index = 0;
        try {
          index = Integer.parseInt(rawIndex);
        } catch (NumberFormatException e) {
          System.out.println(String.format("%s is not a valid number"));
          continue outer;
        }
        cardsToPlay = Arrays.copyOf(cardsToPlay, cardsToPlay.length + 1);
        cardsToPlay[cardsToPlay.length - 1] = cards[index];
      }

      try {
        move = Move.constructFromCards(cardsToPlay);
      } catch (InvalidCombinationException e) {
        System.out.println(String.format("this is not a valid combination: %s", e));
        continue;
      }
      break outer;
    }

    this.out.println(move.serialize());
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
