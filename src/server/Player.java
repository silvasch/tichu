package src.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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

  public void close() throws IOException {
    this.in.close();
    this.out.close();
    this.socket.close();
  }

  public String getName() {
    return this.name;
  }
}
