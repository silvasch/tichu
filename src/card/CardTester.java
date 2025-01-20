package src.card;

public class CardTester {

  public static void main(String[] args) {
    Card cardOne = new NormalCard(Suit.BLACK, Rank.EIGHT);
    Card cardTwo = new NormalCard(Suit.GREEN, Rank.QUEEN);
    Card cardThree = new NormalCard(Suit.RED, Rank.QUEEN);

    assert cardOne.toString().equals("Black Eight");
    assert cardOne.compareTo(cardTwo) < 0;
    assert cardTwo.compareTo(cardThree) == 0;
  }
}
