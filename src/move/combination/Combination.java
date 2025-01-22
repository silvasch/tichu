package src.move.combination;

import src.card.Card;
import src.card.Rank;
import src.move.Move;

public abstract class Combination extends Move {

  public abstract Rank getRank();

  public abstract Card[] getCards();
}
