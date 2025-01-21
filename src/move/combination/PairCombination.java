package src.move.combination;

import src.card.Card;
import src.card.NormalCard;
import src.serde.DeserializationException;
import src.serde.PartialDeserialization;
import src.serde.SerializationException;

public class PairCombination extends Combination implements Comparable<PairCombination> {

  private Card cardOne;
  private Card cardTwo;

  public PairCombination(Card cardOne, Card cardTwo) throws InvalidCombinationException {
    // because this project only supports normal cards, this does not require any
    // further logic.
    if (cardOne instanceof NormalCard normalCardOne
        && cardTwo instanceof NormalCard normalCardTwo) {
      if (normalCardOne.getRank() != normalCardTwo.getRank()) {
        throw new InvalidCombinationException(
            "to form a pair, the two cards have to be of equal rank.");
      }
    }

    this.cardOne = cardOne;
    this.cardTwo = cardTwo;
  }

  public String serialize() throws SerializationException {
    return String.format("paircomb(%s,%s)", this.cardOne.serialize(), this.cardTwo.serialize());
  }

  public static PartialDeserialization<PairCombination> partialDeserialize(String serialized)
      throws DeserializationException {
    if (!serialized.startsWith("paircomb")) {
      throw new DeserializationException("the input does not start with 'paircomb'");
    }
    serialized = serialized.substring(9);

    PartialDeserialization<Card> cardOneDe = Card.partialDeserializeCard(serialized);
    serialized = cardOneDe.getRemainder().substring(1);
    PartialDeserialization<Card> cardTwoDe = Card.partialDeserializeCard(serialized);

    if (!cardTwoDe.getRemainder().startsWith(")")) {
      throw new DeserializationException("paircomb is unclosed");
    }

    serialized = cardTwoDe.getRemainder().substring(1);

    try {
      PairCombination pair = new PairCombination(cardOneDe.getResult(), cardTwoDe.getResult());
      return new PartialDeserialization<PairCombination>(pair, serialized);
    } catch (InvalidCombinationException e) {
      throw new DeserializationException(e);
    }
  }

  public boolean equals(PairCombination other) {
    if (other == null) {
      return false;
    }

    if (cardOne.equals(other.cardOne) && cardTwo.equals(other.cardTwo)) {
      return true;
    }

    if (cardOne.equals(other.cardTwo) && cardTwo.equals(other.cardOne)) {
      return true;
    }

    return false;
  }

  public int compareTo(PairCombination other) {
    // because cardOne and cardTwo have to be of the same rank, we can just compare
    // one of them.
    return this.cardOne.compareTo(other.cardOne);
  }

  public String toString() {
    return String.format("Pair: %s - %s", this.cardOne, this.cardTwo);
  }

  public Card getCardOne() {
    return this.cardOne;
  }

  public Card getCardTwo() {
    return this.cardTwo;
  }

  public Card[] getCards() {
    return new Card[] {
      this.cardOne, this.cardTwo,
    };
  }
}
