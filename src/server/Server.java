package src.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  private ServerSocket socket;

  private Team teamOne;
  private Team teamTwo;

  public Server(int port) throws IOException {
    this.socket = new ServerSocket(port);

    Socket socketOne = this.acceptConnection();
    Socket socketTwo = this.acceptConnection();
    Socket socketThree = this.acceptConnection();
    Socket socketFour = this.acceptConnection();

    this.teamOne = new Team(socketOne, socketThree);
    this.teamOne = new Team(socketTwo, socketFour);

    while (true) {}
  }

  private Socket acceptConnection() throws IOException {
    Socket socket = this.socket.accept();
    System.out.println("got a connection.");
    return socket;
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

    new Server(port);
  }
}
