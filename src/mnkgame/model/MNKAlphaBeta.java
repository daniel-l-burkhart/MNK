package mnkgame.model;

import java.util.ArrayList;

/**
 * MNK method for Alpha-Beta pruning.
 *
 * @author jcchurch
 * @version Fall 2015
 */
public class MNKAlphaBeta {

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
	public static int alphabeta(MNKGame aBoard, Mark myMark, int depth, boolean maxNode) {
		return alphaBetaRecursive(aBoard, myMark, -1 * (aBoard.getWinLength() + 1), aBoard.getWinLength() + 1, depth,
				maxNode);
	}

	/**
	 * Iterative deepening of the alpha beta search algorithm.
	 * 
	 * @param aBoard
	 *            The current game board
	 * @param myMark
	 *            The current CPU player
	 * @param mindepth
	 *            The min depth
	 * @param maxdepth
	 *            the max depth
	 * @param maxNode
	 *            the max node
	 * @return the alpha beta search of the best score.
	 */
	public static int alphabetaIterativeDeepening(MNKGame aBoard, Mark myMark, int mindepth, int maxdepth,
					boolean maxNode) {
		int bestValue = 0;

		ArrayList<Integer> moves = aBoard.getMoves();

		if (moves.size() == 0 || aBoard.isGameOver()) {
			return MNKPayoff.payoff(aBoard, myMark);
		} else {

			for (int move : moves) {
				bestValue = MNKAlphaBeta.alphabeta(aBoard.makeMove(move), aBoard.whoMoves(), mindepth, false);
				bestValue = runAlphaBetaSearchOnEachMove(aBoard, mindepth, maxdepth, bestValue, move);

			}
		}
		return bestValue;
	}

	private static int runAlphaBetaSearchOnEachMove(MNKGame aBoard, int mindepth, int maxdepth, int bestValue,
					int move) {

		int value;
		int alphaBetaBestVal = bestValue;
		for (int depth = mindepth + 1; depth < maxdepth; depth++) {
			value = MNKAlphaBeta.alphabeta(aBoard.makeMove(move), aBoard.whoMoves(), depth, false);

			if (value > alphaBetaBestVal) {
				alphaBetaBestVal = value;
			}
		}
		return alphaBetaBestVal;
	}

	private static int alphaBetaRecursive(MNKGame aBoard, Mark myMark, int alpha, int beta, int depth,
					boolean maxNode) {

		int alphaVal = alpha;
		int betaVal = beta;

		ArrayList<Integer> moves = aBoard.getMoves();

		if (depth == 0 || moves.size() == 0 || aBoard.isGameOver()) {
			return MNKPayoff.payoff(aBoard, myMark);
		} else {

			if (maxNode) {
				alphaVal = alphaBetMaxNode(aBoard, myMark, alphaVal, beta, depth);
				return alphaVal;

			} else if (!maxNode) {
				betaVal = alphaBetaMinNode(aBoard, myMark, alphaVal, betaVal, depth);
				return betaVal;
			}
		}

		return -1;
	}

	private static int alphaBetMaxNode(MNKGame aBoard, Mark myMark, int alpha, int beta, int depth) {
		int value;
		int alphaVal = alpha;
		for (int move : aBoard.getMoves()) {

			if (alpha >= beta) {
				break;
			}
			value = alphaBetaRecursive(aBoard.makeMove(move), myMark, alphaVal, beta, depth - 1, false);

			if (value >= beta) {
				return beta;
			}

			if (value > alphaVal) {
				alphaVal = value;
			}
		}

		return alphaVal;
	}

	private static int alphaBetaMinNode(MNKGame aBoard, Mark myMark, int alpha, int beta, int depth) {

		int value;
		int betaVal = beta;

		for (int move : aBoard.getMoves()) {

			if (alpha >= beta) {
				break;
			}

			value = alphaBetaRecursive(aBoard.makeMove(move), myMark, alpha, betaVal, depth - 1, true);

			if (value <= alpha) {
				return alpha;
			}

			if (value < betaVal) {
				betaVal = value;
			}
		}

		return betaVal;
	}

}
