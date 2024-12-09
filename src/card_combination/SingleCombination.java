package src.card_combination;

import src.card.Card;

public class SingleCombination extends CardCombination {
  private Card card;

  public SingleCombination(Card card) {
    // TODO: verify if this combination can be created from the arguments
    this.card = card;
  }

  public Card getCard() {
    return this.card;
  }

  @Override
  public String toString() {
  	return String.format("Single: %s", this.card);
  }
}
