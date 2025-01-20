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

        boolean doesStart = this.waitForStart();
        if (!doesStart) {
            System.out.println("the game was aborted.");
            return;
        }
        System.out.println("the game is starting.");

        mainloop: while (true) {
            String message = this.in.readLine();

            switch (message) {
                case "abort":
                    System.out.println("the game has been aborted");
                    break mainloop;
                default: {
                }
            }
        }
    }

    private boolean waitForStart() throws IOException {
        String message = this.in.readLine();
        switch (message) {
            case "start":
                return true;
            case "abort":
                return false;
            default:
                throw new RuntimeException(String.format("received invalid message '%s'.", message));
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
