package src.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
    Player playerOne = new Player(socketOne);
    System.out.println(String.format("'%s' connected!", playerOne.getName()));

    Socket socketTwo = this.acceptConnection();
    Player playerTwo = new Player(socketTwo);
    System.out.println(String.format("'%s' connected!", playerTwo.getName()));

    Socket socketThree = this.acceptConnection();
    Player playerThree = new Player(socketThree);
    System.out.println(String.format("'%s' connected!", playerThree.getName()));

    Socket socketFour = this.acceptConnection();
    Player playerFour = new Player(socketFour);
    System.out.println(String.format("'%s' connected!", playerFour.getName()));

    this.teamOne = new Team(playerOne, playerTwo);
    this.teamTwo = new Team(playerThree, playerFour);

    this.teamOne.informOfGameStart(
        this.teamTwo.getPlayerOne().getName(), this.teamTwo.getPlayerTwo().getName());
    this.teamTwo.informOfGameStart(
        this.teamOne.getPlayerOne().getName(), this.teamOne.getPlayerTwo().getName());
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
