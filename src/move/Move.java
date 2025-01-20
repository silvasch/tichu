package src.move;

import src.move.combination.FullHouseCombination;
import src.move.combination.PairCombination;
import src.move.combination.SingleCombination;
import src.move.combination.StairCombination;
import src.move.combination.StreetCombination;
import src.move.combination.TripleCombination;
import src.serde.DeserializationException;
import src.serde.PartialDeserialization;
import src.serde.Serializable;

public abstract class Move implements Serializable {

    public static PartialDeserialization<Move> partialDeserializeMove(String serialized)
            throws DeserializationException {
        if (serialized.startsWith("singlecomb")) {
            PartialDeserialization<SingleCombination> de = SingleCombination.partialDeserialize(serialized);
            return new PartialDeserialization<Move>(de.getResult(), de.getRemainder());
        } else if (serialized.startsWith("paircomb")) {
            PartialDeserialization<PairCombination> de = PairCombination.partialDeserialize(serialized);
            return new PartialDeserialization<Move>(de.getResult(), de.getRemainder());
        } else if (serialized.startsWith("triplecomb")) {
            PartialDeserialization<TripleCombination> de = TripleCombination.partialDeserialize(serialized);
            return new PartialDeserialization<Move>(de.getResult(), de.getRemainder());
        } else if (serialized.startsWith("fullhousecomb")) {
            PartialDeserialization<FullHouseCombination> de = FullHouseCombination.partialDeserialize(serialized);
            return new PartialDeserialization<Move>(de.getResult(), de.getRemainder());
        } else if (serialized.startsWith("streetcomb")) {
            PartialDeserialization<StreetCombination> de = StreetCombination.partialDeserialize(serialized);
            return new PartialDeserialization<Move>(de.getResult(), de.getRemainder());
        } else if (serialized.startsWith("staircomb")) {
            PartialDeserialization<StairCombination> de = StairCombination.partialDeserialize(serialized);
            return new PartialDeserialization<Move>(de.getResult(), de.getRemainder());
        } else {
            throw new DeserializationException(String.format("failed to deserialize '%s' as a move.", serialized));
        }
    }

    public abstract String toString();
}
