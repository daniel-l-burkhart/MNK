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
		ArrayList<Integer> moves = aBoard.getMoves();

		if (depth == 0 || moves.size() == 0 || aBoard.isGameOver()) {
			return MNKPayoff.payoff(aBoard, myMark);
		}
		
		// TODO: Finish the minimax method

		return -1;
	}
}
