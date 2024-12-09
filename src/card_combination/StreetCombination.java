package src.card_combination;

import java.util.StringJoiner;

import src.card.Card;

public class StreetCombination extends CardCombination {
  private Card[] cards;

  public StreetCombination(Card[] cards) {
    // TODO: verify if this combination can be created from the arguments
    this.cards = cards;
  }

  public Card[] getCards() {
    return this.cards;
  }

  @Override
  public String toString() {
    StringJoiner joiner = new StringJoiner(", ", "Street: ", "");
    for (Card card: this.cards) {
      joiner.add(card.toString());
    }
    return joiner.toString();
  }
}
