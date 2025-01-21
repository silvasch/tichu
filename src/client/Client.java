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
import src.serde.DeserializationException;
import src.serde.SerializationException;

public class Client {

  private Socket socket;
  private PrintWriter out;
  private BufferedReader in;

  private Scanner scanner;

  public Client(String ip, int port, String name)
      throws IOException, DeserializationException, SerializationException {
    this.socket = new Socket(ip, port);
    this.out = new PrintWriter(this.socket.getOutputStream(), true);
    this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

    this.scanner = new Scanner(System.in);

    if (name == null) {
      System.out.print("Enter your name: ");
      name = this.scanner.nextLine();
      System.out.println();
    }
    this.out.println(name);

    this.waitForGameStart();

    mainloop:
    while (true) {
      String message = this.in.readLine();
      switch (message) {
        case "new-move":
          this.onNewMove();
          break;
        case "end":
          this.onGameEnd();
          break mainloop;
        case "abort":
          throw new RuntimeException("the game was aborted");
        default:
          throw new RuntimeException(String.format("received invalid message '%s'", message));
      }
    }
  }

  private void waitForGameStart() throws DeserializationException, IOException {
    String message = this.in.readLine();
    switch (message) {
      case "start":
        String teammateName = this.in.readLine();
        String opponentOneName = this.in.readLine();
        String opponentTwoName = this.in.readLine();

        String rawCards = this.in.readLine();
        String[] rawCardsParts = rawCards.split("\\|");

        Card[] cards = new Card[] {};

        for (String rawCard : rawCardsParts) {
          Card card = Card.partialDeserializeCard(rawCard).deserialize();
          cards = Arrays.copyOf(cards, cards.length + 1);
          cards[cards.length - 1] = card;
        }

        Arrays.sort(cards);

        System.out.println(
            String.format(
                "The game is starting. Your teammate is '%s', your opponents are '%s' and '%s'.",
                teammateName, opponentOneName, opponentTwoName));
        System.out.println("This is your hand:");
        for (Card card : cards) {
          System.out.println(card);
        }

        break;
      case "abort":
        throw new RuntimeException("the game was aborted");
      default:
        throw new RuntimeException(String.format("received invalid message '%s'", message));
    }
    System.out.println();
  }

  private void onNewMove() throws DeserializationException, IOException {
    String player = this.in.readLine();
    String rawMove = this.in.readLine();

    System.out.println(String.format("'%s' made their move:", player));
    if (rawMove.equals("null")) {
      System.out.println("They passed.");
    } else {
      Move move = Move.partialDeserializeMove(rawMove).deserialize();
      System.out.println(move);
    }

    System.out.println();
  }

  private void onGameEnd() throws IOException {
    String rawPoints = this.in.readLine();
    String rawOpponentPoints = this.in.readLine();

    int points = 0;
    int opponentPoints = 0;
    try {
      points = Integer.parseInt(rawPoints);
      opponentPoints = Integer.parseInt(rawOpponentPoints);
    } catch (NumberFormatException e) {
      throw new RuntimeException(e);
    }

    System.out.println("The game is over.");
    if (points < opponentPoints) {
      System.out.println("You lost!");
    } else if (points == opponentPoints) {
      System.out.println("It was a draw!");
    } else {
      System.out.println("You won!");
    }

    System.out.println(
        String.format("You had %d points, while the opponent had %d.", points, opponentPoints));
  }

  public void close() throws IOException {
    this.in.close();
    this.out.close();
    this.socket.close();

    this.scanner.close();
  }

  public static void main(String[] args) throws IOException {
    String ip = null;
    int port = -1;
    String name = null;

    try {
      ip = args[0];
      port = Integer.parseInt(args[1]);
      name = args[2];
    } catch (IndexOutOfBoundsException e) {
      if (ip == null) {
        ip = "localhost";
      }

      if (port == -1) {
        port = 3000;
      }
    }

    Client client = null;
    try {
      client = new Client(ip, port, name);
    } catch (Exception e) {
      if (client != null) {
        client.close();
      }
      e.printStackTrace(System.out);
    }
  }
}
