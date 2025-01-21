package src.server;

import java.io.IOException;

public class Team {

  private Player playerOne;
  private Player playerTwo;

  public Team(Player playerOne, Player playerTwo) {
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
  }

  public void informOfGameStart(String opponentOneName, String opponentTwoName) {
    this.playerOne.informOfGameStart(this.playerTwo.getName(), opponentOneName, opponentTwoName);
    this.playerTwo.informOfGameStart(this.playerOne.getName(), opponentOneName, opponentTwoName);
  }

  public void close() throws IOException {
    this.playerOne.close();
    this.playerTwo.close();
  }

  public Player getPlayerOne() {
    return this.playerOne;
  }

  public Player getPlayerTwo() {
    return this.playerTwo;
  }
}
