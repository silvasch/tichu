package src.player;

import java.util.Arrays;
import java.util.Scanner;

import src.Move;
import src.card.Card;
import src.combination.DoubleCombination;
import src.combination.SingleCombination;

public class HumanPlayer implements PlayerInterface {
    public Move makeMove(Move[] previousMoves, Card[] deck) {
        System.out.println("== It's your turn!");

        System.out.println("Previous moves:");
        for (Move move : previousMoves) {
            System.out.println(move);
        }

        System.out.println();
        System.out.println("Your cards:");
        for (int i = 0; i < deck.length; i++) {
            System.out.println(String.format("%02d => %s", i, deck[i]));
        }

        Scanner scanner = new Scanner(System.in);

        outer: while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            int[] indices = new int[] {};
            for (String rawNumber : input.split(",")) {
                int number = Integer.parseInt(rawNumber);
                indices = Arrays.copyOf(indices, indices.length + 1);
                indices[indices.length - 1] = number;
            }

            Card[] moveCards = new Card[indices.length];
            for (int i = 0; i < indices.length; i++) {
                int index = indices[i];
                if (index < 0 || index >= deck.length) {
                    System.out.println(String.format("Index %d is out of range.", index));
                    continue outer;
                }
                moveCards[i] = deck[indices[i]];
            }

            try {
                Move move = HumanPlayer.makeMoveFromCards(moveCards);
                scanner.close();
                return move;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private static Move makeMoveFromCards(Card[] cards) throws Exception {
        // TODO: handle other combinations
        if (cards.length == 1) {
            return new SingleCombination(cards[0]);
        } else if (cards.length == 2) {
            return new DoubleCombination(cards[0], cards[1]);
        } else {
            throw new Exception("Failed to create a move from the given cards.");
        }
    }
}
