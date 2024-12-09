package src.player;

import src.Move;
import src.card.Card;

interface PlayerInterface {
    public Move makeMove(Move[] previousMoves, Card[] deck);
}
