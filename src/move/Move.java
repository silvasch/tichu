package src.move;
import java.util.Arrays;

import src.card.Card;
import src.card.NormalCard;
import src.move.combination.FullHouseCombination;
import src.move.combination.InvalidCombinationException;
import src.move.combination.SingleCombination;
import src.move.combination.StairCombination;
import src.move.combination.StreetCombination;
import src.move.combination.PairCombination;
import src.move.combination.TripleCombination;
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

    public static Move constructFromCards(Card[] cards) throws InvalidCombinationException {
        
        NormalCard[] normalCards = new NormalCard[cards.length];
        

        for (int i = 0; i < cards.length; i++) {
            if (cards[i] instanceof NormalCard normalcard) {
                normalCards[i] = normalcard;
            }
        }

        Arrays.sort(normalCards);

        if (normalCards.length == 1) {  // single card
            return new SingleCombination(normalCards[0]);
        }
        
        if (normalCards.length == 2) {  // pair
            try {
                return new PairCombination(normalCards[0], normalCards[1]);
            } catch (InvalidCombinationException e){ }
            
        }

        if (normalCards.length == 3) {  // triple
            try {
                return new TripleCombination(normalCards[0], normalCards[1], normalCards[2]);
            } catch (InvalidCombinationException e){ }
        }
        
        if (normalCards.length == 5) {  // full house
            if (normalCards[0].getRank() == normalCards[1].getRank() && normalCards[0].getRank() == normalCards[2].getRank() && normalCards[3].getRank() == normalCards[4].getRank()) {
                // checks if the full house is valid.
                return new FullHouseCombination(new TripleCombination(normalCards[0], normalCards[1], normalCards[2]), new PairCombination(normalCards[3], normalCards[4]));
            }

            if (normalCards[0].getRank() == normalCards[1].getRank() && normalCards[2].getRank() == normalCards[3].getRank() && normalCards[2].getRank() == normalCards[4].getRank()) {
                // checks the other possibility for a valid full house.
                return new FullHouseCombination(new TripleCombination(normalCards[2], normalCards[3], normalCards[4]), new PairCombination(normalCards[0], normalCards[1]));
            }
        }

        // streets and stairs can vary in length

        try {  // street
            return new StreetCombination(normalCards);
        } catch (InvalidCombinationException e){ }
        
        if (normalCards.length % 2 == 0) {  // stair
            PairCombination[] pairs = new PairCombination[normalCards.length / 2];
            for (int i = 0; i < normalCards.length / 2; i++) {
                if (normalCards[2*i].getRank() != normalCards[2*i + 1].getRank()) {
                    break;
                }
                pairs[i] = new PairCombination(normalCards[2*i], normalCards[2*i + 1]);
                
                if (i == normalCards.length / 2 - 1) { // all pairs have been checked 
                    try {
                        return new StairCombination(pairs);
                    } catch (InvalidCombinationException e){ }
                }
            }
        }

        // no valid combination has been found
        throw new InvalidCombinationException("invalid combination");
         
    }
}
