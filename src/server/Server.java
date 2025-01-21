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
    Socket socketTwo = this.acceptConnection();
    Socket socketThree = this.acceptConnection();
    Socket socketFour = this.acceptConnection();

    Player playerOne = new Player(socketOne);
    Player playerTwo = new Player(socketTwo);
    Player playerThree = new Player(socketThree);
    Player playerFour = new Player(socketFour);

    this.teamOne = new Team(playerOne, playerTwo);
    this.teamTwo = new Team(playerThree, playerFour);
  }

  private Socket acceptConnection() throws IOException {
    Socket socket = this.socket.accept();
    System.out.println("got a connection");
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
