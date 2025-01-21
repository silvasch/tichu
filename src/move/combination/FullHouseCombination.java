package src.move.combination;

import src.card.Card;
import src.serde.DeserializationException;
import src.serde.PartialDeserialization;
import src.serde.SerializationException;

public class FullHouseCombination extends Combination implements Comparable<FullHouseCombination> {
  private TripleCombination triple;
  private PairCombination pair;

  public FullHouseCombination(TripleCombination triple, PairCombination pair) {
    this.triple = triple;
    this.pair = pair;
  }

  public String serialize() throws SerializationException {
    return String.format("fullhousecomb(%s,%s)", this.triple.serialize(), this.pair.serialize());
  }

  public static PartialDeserialization<FullHouseCombination> partialDeserialize(String serialized)
      throws DeserializationException {
    if (!serialized.startsWith("fullhousecomb")) {
      throw new DeserializationException("the input does not start with 'fullhousecomb'");
    }
    serialized = serialized.substring(14);

    PartialDeserialization<TripleCombination> tripleDe =
        TripleCombination.partialDeserialize(serialized);
    serialized = tripleDe.getRemainder().substring(1);
    PartialDeserialization<PairCombination> pairDe = PairCombination.partialDeserialize(serialized);

    if (!pairDe.getRemainder().startsWith(")")) {
      throw new DeserializationException("fullhousecomb is unclosed");
    }

    serialized = pairDe.getRemainder().substring(1);

    FullHouseCombination fullHouse =
        new FullHouseCombination(tripleDe.getResult(), pairDe.getResult());
    return new PartialDeserialization<FullHouseCombination>(fullHouse, serialized);
  }

  public boolean equals(FullHouseCombination other) {
    return this.triple.equals(other.triple) && this.pair.equals(other.pair);
  }

  public int compareTo(FullHouseCombination other) {
    return this.triple.compareTo(other.triple);
  }

  public String toString() {
    return String.format(
        "Full House: %s - %s - %s - %s - %s",
        this.triple.getCardOne(),
        this.triple.getCardTwo(),
        this.triple.getCardThree(),
        this.pair.getCardOne(),
        this.pair.getCardTwo());
  }

  public TripleCombination getTriple() {
    return this.triple;
  }

  public PairCombination getPair() {
    return this.pair;
  }

  public Card[] getCards() {
    return new Card[] {
      this.triple.getCardOne(),
      this.triple.getCardTwo(),
      this.triple.getCardThree(),
      this.pair.getCardOne(),
      this.pair.getCardTwo(),
    };
  }
}
