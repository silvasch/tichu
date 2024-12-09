package src.card_combination;

import src.card.Card;

public class TripleCombination extends CardCombination {
  private Card cardOne;
  private Card cardTwo;
  private Card cardThree;

  public TripleCombination(Card cardOne, Card cardTwo, Card cardThree) throws Exception {
    // TODO: verify if this combination can be created from the arguments
    this.cardOne = cardOne;
    this.cardTwo = cardTwo;
  }

  public Card getCardOne() {
    return this.cardOne;
  }

  public Card getCardTwo() {
    return this.cardTwo;
  }

  public Card getCardThree() {
    return this.cardThree;
  }

  @Override
  public String toString() {
    return String.format("Triple: %s, %s, %s", this.cardOne, this.cardTwo, this.cardThree);
  }
}
