package src.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String ip, int port) throws IOException {
        this.socket = new Socket(ip, port);
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        System.out.println("connected.");

        while (true) {
        }
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

        new Client(ip, port);
    }
}
