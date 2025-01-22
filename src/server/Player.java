package src.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.StringJoiner;
import src.card.Card;
import src.move.Move;
import src.move.combination.Combination;
import src.serde.DeserializationException;
import src.serde.SerializationException;

public class Player {

  private Socket socket;
  private PrintWriter out;
  private BufferedReader in;

  private String name;

  private Card[] cards;

  public Player(Socket socket, Card[] cards) throws IOException {
    this.socket = socket;
    this.out = new PrintWriter(this.socket.getOutputStream(), true);
    this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

    this.name = this.in.readLine();

    this.cards = cards;
  }

  public void informOfGameStart(String teammateName, String opponentOneName, String opponentTwoName)
      throws SerializationException {
    this.out.println("start");

    this.out.println(teammateName);
    this.out.println(opponentOneName);
    this.out.println(opponentTwoName);

    StringJoiner joiner = new StringJoiner("|");
    for (Card card : this.cards) {
      joiner.add(card.serialize());
    }
    this.out.println(joiner);
  }

  public Move getMove(Move firstMove)
      throws DeserializationException, IOException, SerializationException {
    this.out.println("get-move");
    StringJoiner joiner = new StringJoiner("|");
    for (Card card : this.cards) {
      joiner.add(card.serialize());
    }
    this.out.println(joiner);

    Move move = null;

    while (true) {
      String rawMove = this.in.readLine();

      if (rawMove.equals("null")) {
        move = null;
      } else {
        move = Move.partialDeserializeMove(rawMove).deserialize();
      }

      String rejection = this.verifyMove(firstMove, move);
      if (rejection != null) {
        this.out.println(rejection);
        continue;
      }

      // remove the played cards from the hand
      if (move != null && move instanceof Combination combination) {
        Card[] newCards = new Card[] {};
        for (Card card : this.cards) {
          for (Card playedCard : combination.getCards()) {
            if (!card.equals(playedCard)) {
              newCards = Arrays.copyOf(newCards, newCards.length + 1);
              newCards[newCards.length - 1] = card;
            }
          }
        }
        this.cards = newCards;
      }

      break;
    }

    this.out.println("ok");

    return move;
  }

  // return null if ok, else return a reason why not
  private String verifyMove(Move firstMove, Move move) {
    // there is no firstMove -> move is the first one
    if (firstMove == null) {
      if (move == null) {
        return "You cannot pass as the first player!";
      }
      return null;
    }

    if (move == null) {
      return null;
    }

    if (firstMove.getClass() != move.getClass()) {
      return String.format("You have to play the same kind of combination as the first move.");
    }

    // TODO: verify that the combination is of higher value

    return null;
  }

  public void informOfMove(Move move, String player) throws SerializationException {
    String serializedMove = "null";
    if (move != null) {
      serializedMove = move.serialize();
    }

    this.out.println("new-move");
    this.out.println(player);
    this.out.println(serializedMove);
  }

  public void informOfGameEnd(int points, int opponentPoints) {
    this.out.println("end");
    this.out.println(points);
    this.out.println(opponentPoints);
  }

  public void close() throws IOException {
    this.in.close();
    this.out.close();
    this.socket.close();
  }

  public String getName() {
    return this.name;
  }
}
