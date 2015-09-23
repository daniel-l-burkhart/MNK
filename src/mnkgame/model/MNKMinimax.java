package mnkgame.model;

import java.util.ArrayList;

/**
 * Our minimax algorithm
 *
 * @author jcchurch
 * @version Fall 2015
 */
public class MNKMinimax {

	/**
	 * Returns the minimax'd payoff of a board state.
	 *
	 * @param aBoard
	 *            the current game board
	 * @param myMark
	 *            the current CPU player
	 * @param depth
	 *            the current depth
	 * @param maxNode
	 *            if this is a maxNode then true, false if min node
	 * @return the minimax'd payoff
	 */
	public static int minimax(MNKGame aBoard, Mark myMark, int depth, boolean maxNode) {
		/*
		 * if maxnode: bestValue = -\infty for child in childnodes: value =
		 * minimax(child, false, depth-1) bestValue = max(bestValue, value)
		 * return bestValue else: bestValue = \infty for child in childnodes:
		 * value = minimax(child, true, depth-1) bestValue = min(bestValue,
		 * value) return bestValue
		 *
		 */

		ArrayList<Integer> moves = aBoard.getMoves();

		if (depth == 0 || moves.size() == 0 || aBoard.isGameOver()) {
			return MNKPayoff.payoff(aBoard, myMark);
		}

		if (maxNode) {
			int bestValue = Integer.MAX_VALUE;
			for (Integer move : moves) {
				MNKGame nextBoard = aBoard.makeMove(move);
				int value = minimax(nextBoard, myMark, depth - 1, false);
				bestValue = Math.max(value, bestValue);
				return bestValue;
			}
		} else {
			int bestValue = Integer.MIN_VALUE;
			for (Integer move : moves) {
				int value = minimax(aBoard.makeMove(move), myMark, depth - 1, true);
				bestValue = Math.min(value, bestValue);
				return bestValue;
			}
		}

		return -1;
	}
}
