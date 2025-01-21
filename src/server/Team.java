package src.server;

import java.io.IOException;

public class Team {

  private Player playerOne;
  private Player playerTwo;

  public Team(Player playerOne, Player playerTwo) {
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
  }

  public void close() throws IOException {
    this.playerOne.close();
    this.playerTwo.close();
  }
}
