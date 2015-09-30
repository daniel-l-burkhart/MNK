package mnkgame.model;

/**
 * Our Payoff method
 *
 * @author jcchurch
 * @version 1
 */
public class MNKPayoff {

	/**
	 * Given an MNK board, the current player, and the opponent, determines the
	 * score for this arrangement.
	 *
	 * @param aBoard
	 *            the current game board
	 * @param myMark
	 *            the current CPU player
	 * @return the payoff score of this board
	 */
	public static int payoff(MNKGame aBoard, Mark myMark) {

		Mark myOpponent = MNKPayoff.getOpponent(myMark);

		if (aBoard.longestLine(myMark) >= aBoard.getWinLength()) {
			return aBoard.longestLine(myMark) + 1;
		}

		if (aBoard.longestLine(myOpponent) >= aBoard.getWinLength()) {
			return -1 * (aBoard.longestLine(myOpponent) + 1);
		}

		return (aBoard.longestLine(myMark) - aBoard.longestLine(myOpponent));

	}

	private static Mark getOpponent(Mark myMark) {
		Mark myOpponent = Mark.X;

		if (myMark == Mark.X) {
			myOpponent = Mark.O;
		}
		return myOpponent;
	}

}
