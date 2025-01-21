package src.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import src.move.Move;
import src.serde.SerializationException;

public class Player {

  private Socket socket;
  private PrintWriter out;
  private BufferedReader in;

  private String name;

  public Player(Socket socket) throws IOException {
    this.socket = socket;
    this.out = new PrintWriter(this.socket.getOutputStream(), true);
    this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

    this.name = this.in.readLine();
  }

  public void informOfGameStart(
      String teammateName, String opponentOneName, String opponentTwoName) {
    this.out.println("start");
    this.out.println(teammateName);
    this.out.println(opponentOneName);
    this.out.println(opponentTwoName);
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
