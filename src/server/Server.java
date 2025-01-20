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

    Socket socketOne = this.acceptConnection();
    Socket socketTwo = this.acceptConnection();
    Socket socketThree = this.acceptConnection();
    Socket socketFour = this.acceptConnection();

    Card[][] hands = this.generateHands();

    this.teamOne = new Team(socketOne, socketThree, hands[0], hands[1]);
    this.teamTwo = new Team(socketTwo, socketFour, hands[2], hands[3]);

    for (Player player : this.getPlayers()) {
      player.informOfStart();
    }

    Move move = this.teamOne.getPlayerOne().getMove();

    this.teamOne.getPlayerTwo().informOfMove(move, "Your partner");
    this.teamTwo.getPlayerOne().informOfMove(move, "The opponent to your left");
    this.teamTwo.getPlayerTwo().informOfMove(move, "The opponent to your right");

    this.teamOne.informOfEnd(true, this.teamTwo.getPoints());
    this.teamTwo.informOfEnd(false, this.teamOne.getPoints());
  }

  private Socket acceptConnection() throws IOException {
    Socket socket = this.socket.accept();
    System.out.println("got a connection.");
    return socket;
  }

  private Card[][] generateHands() {
    Card[] cards = new Card[] {};
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        Card card = new NormalCard(suit, rank);
        cards = Arrays.copyOf(cards, cards.length + 1);
        cards[cards.length - 1] = card;
      }
    }
    Collections.shuffle(Arrays.asList(cards));

    Card[][] hands = new Card[][] {};

    int chunkSize = Math.floorDiv(cards.length, 4);
    for (int i = 0; i < cards.length; i += chunkSize) {
      hands = Arrays.copyOf(hands, hands.length + 1);
      hands[hands.length - 1] = Arrays.copyOfRange(cards, i, Math.min(cards.length, i + chunkSize));
    }

    return hands;
  }

  private Player[] getPlayers() {
    return new Player[] {
      this.teamOne.getPlayerOne(),
      this.teamOne.getPlayerTwo(),
      this.teamTwo.getPlayerOne(),
      this.teamTwo.getPlayerTwo(),
    };
  }

  private void close() throws IOException {
    for (Player player : this.getPlayers()) {
      player.close();
    }

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
