package src.move.combination;

import src.card.Card;
import src.card.NormalCard;
import src.card.Rank;
import src.move.Move;
import src.serde.DeserializationException;
import src.serde.PartialDeserialization;
import src.serde.SerializationException;

public class TripleCombination extends Move implements Comparable<TripleCombination> {
    private Card cardOne;
    private Card cardTwo;
    private Card cardThree;

    public TripleCombination(Card cardOne, Card cardTwo, Card cardThree)
            throws InvalidCombinationException {
        // because this project only supports normal cards, this does not require any
        // further logic.
        if (cardOne instanceof NormalCard normalCardOne
                && cardTwo instanceof NormalCard normalCardTwo
                && cardThree instanceof NormalCard normalCardThree) {
            Rank expectedRank = normalCardOne.getRank();
            if (normalCardTwo.getRank() != expectedRank
                    || normalCardThree.getRank() != expectedRank) {
                throw new InvalidCombinationException(
                        "to form a triple, all three cards have to be of equal rank.");
            }
        }

        this.cardOne = cardOne;
        this.cardTwo = cardTwo;
        this.cardThree = cardThree;
    }

    public String serialize() throws SerializationException {
        return String.format(
                "triplecomb(%s,%s,%s)",
                this.cardOne.serialize(), this.cardTwo.serialize(), this.cardThree.serialize());
    }

    public static PartialDeserialization<TripleCombination> partialDeserialize(String serialized)
            throws DeserializationException {
        if (!serialized.startsWith("triplecomb")) {
            throw new DeserializationException("the input does not start with 'triplecomb'");
        }
        serialized = serialized.substring(11);

        PartialDeserialization<Card> cardOneDe = Card.partialDeserializeCard(serialized);
        serialized = cardOneDe.getRemainder().substring(1);
        PartialDeserialization<Card> cardTwoDe = Card.partialDeserializeCard(serialized);
        serialized = cardTwoDe.getRemainder().substring(1);
        PartialDeserialization<Card> cardThreeDe = Card.partialDeserializeCard(serialized);

        if (!cardThreeDe.getRemainder().startsWith(")")) {
            throw new DeserializationException("triplecomb is unclosed");
        }

        serialized = cardThreeDe.getRemainder().substring(1);

        try {
            TripleCombination triple =
                    new TripleCombination(
                            cardOneDe.getResult(), cardTwoDe.getResult(), cardThreeDe.getResult());
            return new PartialDeserialization<TripleCombination>(triple, serialized);
        } catch (InvalidCombinationException e) {
            throw new DeserializationException(e);
        }
    }

    public boolean equals(TripleCombination other) {
        return this.cardOne.equals(other.cardOne)
                && this.cardTwo.equals(other.cardTwo)
                && this.cardThree.equals(other.cardThree);
    }

    public int compareTo(TripleCombination other) {
        // because cardOne cardTwo and cardThree have to be of the same rank, we can
        // just compare one of them.
        return this.cardOne.compareTo(other.cardOne);
    }

    public String toString() {
        return String.format("Triple: %s - %s - %s", this.cardOne, this.cardTwo, this.cardThree);
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
}
