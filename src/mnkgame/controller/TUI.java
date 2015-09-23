package mnkgame.controller;

import java.util.ArrayList;
import java.util.Scanner;

import mnkgame.model.MNKAlphaBeta;
import mnkgame.model.MNKGame;
import mnkgame.model.MNKMinimax;
import mnkgame.model.MNKPayoff;

/**
 * TUI for MNK algorithm
 * 
 * @author jcchurch
 * @version Fall 2015
 */
public class TUI {

	/**
	 * Begins the game.
	 * 
	 * @param args
	 *            CLI args
	 */
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		MNKGame aGame = new MNKGame(5, 5, 3);
		boolean humanTurn = true;

		while (!aGame.isGameOver() && aGame.getMoves().size() > 0) {
			System.out.println(aGame);

			if (humanTurn) {
				aGame = obtainHumanSelection(scan, aGame);
				humanTurn = false;
			} else {
				aGame = obtainComputerSelection(aGame);
				humanTurn = true;
			}
		}

		scan.close();
		System.out.println("Game Over");
		System.out.println(aGame);
	}

	private static MNKGame obtainComputerSelection(MNKGame aGame) {
		ArrayList<Integer> moves = aGame.getMoves();

		int bestMove = moves.get(0);
		int bestMoveScore = -1;
		for (int move : moves) {

			// For each algorithm below, implement and examine the results by
			// playing a few games.
			// Once you have finished Minimax, move to Alpha-Beta.
			// Once you have finished Alpha-Beta, move to iterative deening

			int value = MNKMinimax.minimax(aGame.makeMove(move), aGame.whoMoves(), 2, false);
			// int value = MNKAlphaBeta.alphabeta(aGame.makeMove(move), aGame.whoMoves(), 0, 8, false);
			// int value = MNKAlphaBeta.alphabetaIterativeDeepening(aGame.makeMove(move), aGame.whoMoves(), 0, 8, false);

			if (value > bestMoveScore) {
				bestMoveScore = value;
				bestMove = move;
			}
		}

		return aGame.makeMove(bestMove);
	}

	private static MNKGame obtainHumanSelection(Scanner scan, MNKGame aGame) {
		System.out.println("Make a move: ");
		int position = scan.nextInt();

		return aGame.makeMove(position);
	}

}
