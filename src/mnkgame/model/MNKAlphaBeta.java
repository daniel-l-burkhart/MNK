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

	public static int alphabetaIterativeDeepening(MNKGame aBoard, Mark myMark, int mindepth, int maxdepth,
			boolean maxNode) {

		// TODO: Finish Alpha-Beta with iterative deepening
		return -1;
	}

	private static int alphaBetaRecursive(MNKGame aBoard, Mark myMark, int alpha, int beta, int depth,
			boolean maxNode) {

		// TODO: Finish Alpha-Beta Recursive
		
		return -1;
	}

	private static int alphaBetaMinNode(MNKGame aBoard, Mark myMark, int alpha, int beta, int depth,
			ArrayList<Integer> moves) {
		
		// TODO: Finish Alpha-Beta for Min Nodes
		
		return -1;
	}

	private static int alphaBetaMaxNode(MNKGame aBoard, Mark myMark, int alpha, int beta, int depth,
			ArrayList<Integer> moves) {
		
		// TODO: Finish Alpha-Beta for Max Nodes
		
		return -1;
	}
}
