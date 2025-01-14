package src.move.combination;

import src.move.Move;

public class FullHouseCombination extends Move implements Comparable<FullHouseCombination> {
    private TripleCombination triple;
    private PairCombination pair;

    public FullHouseCombination(TripleCombination triple, PairCombination pair) {
        this.triple = triple;
        this.pair = pair;
    }

    public int compareTo(FullHouseCombination other) {
        return this.triple.compareTo(other.triple);
    }

    public String toString() {
        return String.format("Full House: %s - %s - %s - %s - %s", this.triple.getCardOne(), this.triple.getCardTwo(),
                this.triple.getCardThree(), this.pair.getCardOne(), this.pair.getCardTwo());
    }

    public TripleCombination getTriple() {
        return this.triple;
    }

    public PairCombination getPair() {
        return this.pair;
    }
}
