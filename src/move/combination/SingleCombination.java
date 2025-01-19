package src.move.combination;

import src.card.Card;
import src.move.Move;
import src.serde.DeserializationException;
import src.serde.PartialDeserialization;
import src.serde.SerializationException;

public class SingleCombination extends Move implements Comparable<SingleCombination> {
    private Card card;

    public SingleCombination(Card card) {
        this.card = card;
    }

    public String serialize() throws SerializationException {
        return String.format("singlecomb(%s)", this.card.serialize());
    }

    public static PartialDeserialization<SingleCombination> partialDeserialize(String serialized)
            throws DeserializationException {
        if (!serialized.startsWith("singlecomb")) {
            throw new DeserializationException(
                    "the input does not start with 'singlecomb'");
        }
        serialized = serialized.substring(11);

        PartialDeserialization<Card> cardDe = Card.partialDeserializeCard(serialized);

        if (!cardDe.getRemainder().startsWith(")")) {
            throw new DeserializationException("singlecomb is unclosed");
        }

        serialized = cardDe.getRemainder().substring(1);

        return new PartialDeserialization<SingleCombination>(new SingleCombination(cardDe.getResult()),
                serialized);
    }

    public boolean equals(SingleCombination other) {
        return this.card.equals(other.card);
    }

    public int compareTo(SingleCombination other) {
        return this.card.compareTo(other.card);
    }

    public String toString() {
        return String.format("Single: %s", this.card);
    }

    public Card getCard() {
        return this.card;
    }
}
