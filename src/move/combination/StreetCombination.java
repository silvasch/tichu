package src.move.combination;

import java.util.Arrays;
import java.util.StringJoiner;
import src.card.Card;
import src.card.NormalCard;
import src.card.Rank;
import src.serde.DeserializationException;
import src.serde.PartialDeserialization;
import src.serde.SerializationException;

public class StreetCombination extends Combination {
  private Card[] cards;

  public StreetCombination(Card[] cards) throws InvalidCombinationException {
    if (cards.length < 5) {
      throw new InvalidCombinationException("streets have to contain at least five cards.");
    }

    Arrays.sort(cards);
    int sum = 0;
    for (int i = 1; i < cards.length; i++) {
      if (cards[i - 1] instanceof NormalCard normalCardOne
          && cards[i] instanceof NormalCard normalCardTwo) {
        Rank rankOne = normalCardOne.getRank();
        Rank rankTwo = normalCardTwo.getRank();
        sum += rankTwo.ordinal() - rankOne.ordinal();
      }
    }
    if (sum + 1 != cards.length) {
      throw new InvalidCombinationException("the cards in a street have to be consecutive.");
    }

    this.cards = cards;
  }

  public String serialize() throws SerializationException {
    StringJoiner joiner = new StringJoiner(",");
    for (Card card : this.cards) {
      joiner.add(card.serialize());
    }
    return String.format("streetcomb(%s)", joiner);
  }

  public static PartialDeserialization<StreetCombination> partialDeserialize(String serialized)
      throws DeserializationException {
    if (!(serialized.startsWith("streetcomb"))) {
      throw new DeserializationException("the input does not start with 'streetcomb'");
    }

    serialized = serialized.substring(11);

    Card[] cards = new Card[] {};

    while (true) {
      // currently, this code fails if the street does not contain any cards.
      // this should never happen, as streets cannot be constructed with less
      // than five cards.
      PartialDeserialization<Card> de = Card.partialDeserializeCard(serialized);

      cards = Arrays.copyOf(cards, cards.length + 1);
      cards[cards.length - 1] = de.getResult();

      if (de.getRemainder().startsWith(")")) {
        serialized = de.getRemainder().substring(1);
        break;
      }

      serialized = de.getRemainder().substring(1);
    }

    try {
      StreetCombination street = new StreetCombination(cards);
      return new PartialDeserialization<StreetCombination>(street, serialized);
    } catch (InvalidCombinationException e) {
      throw new DeserializationException(e);
    }
  }

  public boolean equals(StreetCombination other) {
    if (this.cards.length != other.cards.length) {
      return false;
    }

    for (int i = 0; i < this.cards.length; i++) {
      if (!this.cards[i].equals(other.cards[i])) {
        return false;
      }
    }

    return true;
  }

  public int compareTo(StreetCombination other) {
    return this.cards[0].compareTo(other.cards[0]);
  }

  public String toString() {
    StringJoiner joiner = new StringJoiner(" - ");
    for (Card card : this.cards) {
      joiner.add(card.toString());
    }
    return String.format("Street: %s", joiner.toString());
  }

  public Card[] getCards() {
    return this.cards;
  }
}
