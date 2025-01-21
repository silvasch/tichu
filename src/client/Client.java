package src.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
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
    }
    this.out.println(name);
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
