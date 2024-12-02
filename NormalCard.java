class NormalCard extends Card {

    private Suit suit;
    private Rank rank;

    public NormalCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return String.format("%s %s", suit, rank);
    }
}
