package src.combination;

import java.util.StringJoiner;

import src.card.Rank;

public class StairCombination extends CardCombination {

    private DoubleCombination[] pairs;

    public StairCombination(DoubleCombination[] pairs) throws Exception{
        // TODO evtl. Sortieren falls das nicht vorher bereits passiert
        int currentInt = pairs[0].getRank().toHeight();
        for (DoubleCombination pair: pairs) {
            if (pair.getRank().toHeight() != currentInt + 1) {
                throw new Exception("invalid StairCombination");
            }
            currentInt++;
        }
        this.pairs = pairs;
    }

    public DoubleCombination[] getDoubleCombinations() {
        return this.pairs;
    }

    public Rank getRank() {
        return this.pairs[0].getRank();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "Stair: ", "");
        for (DoubleCombination pair : this.pairs) {
            joiner.add(pair.toString());
        }
        return joiner.toString();
    }
}
