package src.combination;

import src.card.Rank;

public class FullHouseCombination extends CardCombination {

    private DoubleCombination pair;
    private TripleCombination triple;

    public FullHouseCombination(
        DoubleCombination pair,
        TripleCombination triple
    ) {
        // TODO: verify that this combination can be created from the arguments
        this.pair = pair;
        this.triple = triple;
    }

    public DoubleCombination getPair() {
        return this.pair;
    }

    public TripleCombination getTriple() {
        return this.triple;
    }


    public Rank getRank() {
        return this.triple.getRank();
    }

    @Override
    public String toString() {
        return String.format(
            "Full House: %s, %s, %s, %s, %s",
            this.pair.getCardOne(),
            this.pair.getCardTwo(),
            this.triple.getCardOne(),
            this.triple.getCardTwo(),
            this.triple.getCardThree()
        );
    }
}
