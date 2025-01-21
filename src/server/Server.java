package src.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import src.card.Card;
import src.card.NormalCard;
import src.card.Rank;
import src.card.Suit;
import src.move.Move;
import src.move.combination.InvalidCombinationException;
import src.serde.DeserializationException;
import src.serde.SerializationException;

public class Server {

  private ServerSocket socket;

  private Team teamOne;
  private Team teamTwo;

  public Server(int port)
      throws DeserializationException,
          IOException,
          InvalidCombinationException,
          SerializationException {
    this.socket = new ServerSocket(port);

    Card[][] hands = this.generateHands();

    Socket socketOne = this.acceptConnection();
    Player playerOne = new Player(socketOne, hands[0]);
    System.out.println(String.format("'%s' connected!", playerOne.getName()));

    Socket socketTwo = this.acceptConnection();
    Player playerTwo = new Player(socketTwo, hands[1]);
    System.out.println(String.format("'%s' connected!", playerTwo.getName()));

    Socket socketThree = this.acceptConnection();
    Player playerThree = new Player(socketThree, hands[2]);
    System.out.println(String.format("'%s' connected!", playerThree.getName()));

    Socket socketFour = this.acceptConnection();
    Player playerFour = new Player(socketFour, hands[3]);
    System.out.println(String.format("'%s' connected!", playerFour.getName()));

    this.teamOne = new Team(playerOne, playerTwo);
    this.teamTwo = new Team(playerThree, playerFour);

    this.teamOne.informOfGameStart(
        this.teamTwo.getPlayerOne().getName(), this.teamTwo.getPlayerTwo().getName());
    this.teamTwo.informOfGameStart(
        this.teamOne.getPlayerOne().getName(), this.teamOne.getPlayerTwo().getName());

    Move move = this.teamOne.getPlayerOne().getMove();

    this.teamOne.getPlayerTwo().informOfMove(move, "1");
    this.teamTwo.getPlayerOne().informOfMove(move, "1");
    this.teamTwo.getPlayerTwo().informOfMove(move, "1");

    this.teamOne.getPlayerOne().informOfMove(null, "1");
    this.teamOne.getPlayerTwo().informOfMove(null, "1");
    this.teamTwo.getPlayerOne().informOfMove(null, "1");
    this.teamTwo.getPlayerTwo().informOfMove(null, "1");

    this.teamOne.informOfGameEnd(this.teamTwo.getPoints());
    this.teamTwo.informOfGameEnd(this.teamOne.getPoints());
  }

  private Card[][] generateHands() {
    Card[] cards = this.generateCards();

    Card[][] hands = new Card[][] {};

    int chunkSize = Math.floorDiv(cards.length, 4);
    for (int i = 0; i < cards.length; i += chunkSize) {
      hands = Arrays.copyOf(hands, hands.length + 1);
      hands[hands.length - 1] = Arrays.copyOfRange(cards, i, Math.min(cards.length, i + chunkSize));
    }

    return hands;
  }

  private Card[] generateCards() {
    Card[] cards = new Card[] {};

    for (Rank rank : Rank.values()) {
      for (Suit suit : Suit.values()) {
        Card card = new NormalCard(suit, rank);
        cards = Arrays.copyOf(cards, cards.length + 1);
        cards[cards.length - 1] = card;
      }
    }

    Collections.shuffle(Arrays.asList(cards));

    return cards;
  }

  private Socket acceptConnection() throws IOException {
    Socket socket = this.socket.accept();
    return socket;
  }

  private void close() throws IOException {
    this.teamOne.close();
    this.teamTwo.close();
    this.socket.close();
  }

  public static void main(String[] args) throws IOException {
    int port = -1;

    try {
      port = Integer.parseInt(args[0]);
    } catch (IndexOutOfBoundsException e) {
      if (port == -1) {
        port = 3000;
      }
    }

    System.out.println(String.format("starting the server on '%s'.", port));

    Server server = null;
    try {
      server = new Server(port);
    } catch (Exception e) {
      if (server != null) {
        server.close();
      }
      e.printStackTrace(System.out);
    }
  }
}
