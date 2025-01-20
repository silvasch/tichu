package src.card;

import src.serde.DeserializationException;
import src.serde.PartialDeserialization;
import src.serde.Serializable;

public abstract class Card implements Comparable<Card>, Serializable {

    public static PartialDeserialization<Card> partialDeserializeCard(String serialized)
            throws DeserializationException {
        if (serialized.startsWith("normalcard")) {
            PartialDeserialization<NormalCard> de = NormalCard.partialDeserialize(serialized);
            return new PartialDeserialization<Card>(de.getResult(), de.getRemainder());
        }

        throw new DeserializationException(
                String.format("could not deserialize '%s' as a card", serialized));
    }

    public abstract boolean equals(Card other);

    public abstract String toString();
}
