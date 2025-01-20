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
        this.teamTwo = new Team(socketTwo, socketFour);

        for (Player player : this.getPlayers()) {
            player.informOfStart();
        }

        for (Player player : this.getPlayers()) {
            player.informOfAbort();
        }
    }

    private Socket acceptConnection() throws IOException {
        Socket socket = this.socket.accept();
        System.out.println("got a connection.");
        return socket;
    }

    private Player[] getPlayers() {
        return new Player[] {
                this.teamOne.getPlayerOne(),
                this.teamOne.getPlayerTwo(),
                this.teamTwo.getPlayerOne(),
                this.teamTwo.getPlayerTwo(),
        };
    }

    private void close() throws IOException {
        for (Player player : this.getPlayers()) {
            player.close();
        }

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
