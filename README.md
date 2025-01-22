# Tichu
An implementation of the game [Tichu](https://en.wikipedia.org/wiki/Tichu) by Urs Hostettler. The game is not fully implemented with features such as the 4 special cards as well as bombs to be added.  

The Implementation extensively uses object oriented programming. It uses sockets to connect multiple clients to play together. This allows us to reinforce our understanding about classes and learn using sockets to connect online. 

The possibility of online multiplayer makes this implementation of [Tichu](https://en.wikipedia.org/wiki/Tichu) very useful when there is no possibility of playing on one device such as in a boring school lesson or when the players are at different locations.
## Running

This project uses [just](https://just.systems/) as a build system.
Use `just run-server <PORT>` to start the server, and
`just run-client <IP> <PORT>` to connect a client.

If you don't want to use just, you can use the following commands:

```bash
# For running the server
mkdir -p out
javac -d out src/server/Server.java
java -cp out src.server.Server <PORT>

# For running the client
mkdir -p out
javac -d out src/client/Client.java
java -cp out src.client.Client <IP> <PORT>
```
