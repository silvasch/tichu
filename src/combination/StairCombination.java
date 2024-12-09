package src.combination;

import java.util.StringJoiner;

public class StairCombination extends CardCombination {

    private DoubleCombination[] pairs;

    public StairCombination(DoubleCombination[] pairs) {
        // TODO: verify that this combination can be created from the arguments
        this.pairs = pairs;
    }

    public DoubleCombination[] getDoubleCombinations() {
        return this.pairs;
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
