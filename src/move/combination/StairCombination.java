package src.move.combination;

import java.util.Arrays;
import java.util.StringJoiner;
import src.card.Card;
import src.card.NormalCard;
import src.card.Rank;
import src.serde.DeserializationException;
import src.serde.PartialDeserialization;
import src.serde.SerializationException;

public class StairCombination extends Combination {
  private PairCombination[] pairs;

  public StairCombination(PairCombination[] pairs) throws InvalidCombinationException {
    if (pairs.length < 2) {
      throw new InvalidCombinationException("stairs have to contain at least two pairs.");
    }

    Arrays.sort(pairs);
    int sum = 0;
    for (int i = 1; i < pairs.length; i++) {
      Card cardOne = pairs[i - 1].getCardOne();
      Card cardTwo = pairs[i].getCardOne();
      if (cardOne instanceof NormalCard normalCardOne
          && cardTwo instanceof NormalCard normalCardTwo) {
        Rank rankOne = normalCardOne.getRank();
        Rank rankTwo = normalCardTwo.getRank();
        sum += rankTwo.ordinal() - rankOne.ordinal();
      }
    }
    if (sum + 1 != pairs.length) {
      throw new InvalidCombinationException("the pairs in a stair have to be consecutive.");
    }

    this.pairs = pairs;
  }

  public String serialize() throws SerializationException {
    StringJoiner joiner = new StringJoiner(",");
    for (PairCombination pair : this.pairs) {
      joiner.add(pair.serialize());
    }
    return String.format("staircomb(%s)", joiner);
  }

  public static PartialDeserialization<StairCombination> partialDeserialize(String serialized)
      throws DeserializationException {
    if (!(serialized.startsWith("staircomb"))) {
      throw new DeserializationException("the input does not start with 'staircomb'");
    }

    serialized = serialized.substring(10);

    PairCombination[] pairs = new PairCombination[] {};

    while (true) {
      // currently, this code fails if the stair does not contain any pairs.
      // this should never happen, as stairs cannot be constructed with less
      // than two pairs.
      PartialDeserialization<PairCombination> de = PairCombination.partialDeserialize(serialized);

      pairs = Arrays.copyOf(pairs, pairs.length + 1);
      pairs[pairs.length - 1] = de.getResult();

      if (de.getRemainder().startsWith(")")) {
        serialized = de.getRemainder().substring(1);
        break;
      }

      serialized = de.getRemainder().substring(1);
    }

    try {
      StairCombination stair = new StairCombination(pairs);
      return new PartialDeserialization<StairCombination>(stair, serialized);
    } catch (InvalidCombinationException e) {
      throw new DeserializationException(e);
    }
  }

  public boolean equals(StairCombination other) {
    if (this.pairs.length != other.pairs.length) {
      return false;
    }

    for (int i = 0; i < this.pairs.length; i++) {
      if (!this.pairs[i].equals(other.pairs[i])) {
        return false;
      }
    }

    return true;
  }

  public int compareTo(StairCombination other) {
    return this.pairs[0].compareTo(other.pairs[0]);
  }

  public String toString() {
    StringJoiner joiner = new StringJoiner(" - ");
    for (PairCombination pair : this.pairs) {
      joiner.add(pair.getCardOne().toString());
      joiner.add(pair.getCardTwo().toString());
    }
    return String.format("Stair: %s", joiner.toString());
  }

  public Rank getRank() {
    return this.pairs[0].getRank();
  }

  public Card[] getCards() {
    Card[] cards = new Card[] {};

    for (PairCombination pair : this.pairs) {
      cards = Arrays.copyOf(cards, cards.length + 2);
      cards[cards.length - 2] = pair.getCardOne();
      cards[cards.length - 1] = pair.getCardTwo();
    }

    return cards;
  }
}
