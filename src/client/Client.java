package src.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
