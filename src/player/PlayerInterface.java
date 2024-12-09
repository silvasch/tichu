package src.player;

import src.Move;

interface PlayerInterface {
    public int makeMove(Move[] previousMoves, Move[] validMoves);
}
