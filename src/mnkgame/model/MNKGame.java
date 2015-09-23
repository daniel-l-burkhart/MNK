package mnkgame.model;

import java.util.ArrayList;

/**
 * The MNK game is a generalization of Tic-Tac-Toe.
 * 
 * @author jcchurch
 * @version Fall 2015
 */
public class MNKGame {

	private final Mark[] board;
	private final boolean gameover;
	private final int height;
	private final int width;
	private final int winlength;
	private final Mark currentMover;

	/**
	 * Creates a new game state for the game MNK.
	 * 
	 * @precondition m >= 3 and n >= 3 and k >= 3
	 * @postcondition the game state is created with an empty board and X is set
	 *                to play first.
	 * 
	 * @param mwidth
	 *            the board width
	 * @param nheight
	 *            the board height
	 * @param kwinLength
	 *            the win condition
	 */
	public MNKGame(int mwidth, int nheight, int kwinLength) {
		if (mwidth < 3) {
			throw new IllegalArgumentException("m must be at least 3");
		}

		if (nheight < 3) {
			throw new IllegalArgumentException("n must be at least 3");
		}

		if (kwinLength < 3) {
			throw new IllegalArgumentException("k must be at least 3");
		}

		this.width = mwidth;
		this.height = nheight;
		this.winlength = kwinLength;
		this.currentMover = Mark.X;
		this.board = new Mark[this.width * this.height];
		this.gameover = false;
		this.clearBoard();
	}

	private MNKGame(MNKGame toCopy, int position) {
		this.width = toCopy.width;
		this.height = toCopy.height;
		this.winlength = toCopy.winlength;
		this.board = new Mark[this.width * this.height];

		for (int i = 0; i < this.width * this.height; i++) {
			this.board[i] = toCopy.board[i];
		}

		this.board[position] = toCopy.currentMover;
		this.gameover = this.checkVictoryCondition(toCopy.currentMover);

		this.currentMover = toCopy.whoMovesNext();
	}

	private void checkForFilledPosition(int position) {
		if (this.board[position] != Mark.OPEN) {
			throw new IllegalArgumentException("Position is already filled");
		}
	}

	private void checkForIllegalPosition(int position) {
		if (position < 0 || position >= this.width * this.height) {
			throw new IllegalArgumentException("Position is out of bounds");
		}
	}

	private boolean checkVictoryCondition(Mark toCheck) {
		return this.longestLine(toCheck) >= this.winlength;
	}

	private void clearBoard() {
		for (int i = 0; i < this.width * this.height; i++) {
			this.board[i] = Mark.OPEN;
		}
	}

	private int diagonalAlternateCount(Mark toCheck, int position) {
		int myCount = 0;
		int width = this.getWidth();
		int height = this.getHeight();

		int myColumn = position % width;
		int myRow = position / width;
		int current = position;

		while (myRow < height && myColumn >= 0 && this.board[current] == toCheck) {
			current += width - 1;
			myRow += 1;
			myColumn -= 1;
			myCount += 1;
		}

		return myCount;
	}

	private int diagonalCount(Mark toCheck, int position) {
		int myCount = 0;
		int width = this.getWidth();
		int height = this.getHeight();

		int myColumn = position % width;
		int myRow = position / width;
		int current = position;

		while (myRow < height && myColumn < width && this.board[current] == toCheck) {
			current += width + 1;
			myRow += 1;
			myColumn += 1;
			myCount += 1;
		}

		return myCount;
	}

	/**
	 * Returns the marker at this position
	 * 
	 * @precondition the position is >= 0 and < the overall board length
	 * @postcondition none
	 * 
	 * @param position
	 *            the desired position
	 * @return the marker of this position
	 */
	public Mark get(int position) {
		this.checkForIllegalPosition(position);
		return this.board[position];
	}

	/**
	 * Returns the board height
	 * 
	 * @return the board height
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Returns the board width
	 * 
	 * @return the board width
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Returns the stretch length of pieces need to win
	 * 
	 * @return the stretch length of pieces needed to win
	 */
	public int getWinLength() {
		return this.winlength;
	}

	/**
	 * Returns the list of open positions
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of open positions
	 */
	public ArrayList<Integer> getMoves() {
		ArrayList<Integer> moves = new ArrayList<>();

		for (int i = 0; i < this.width * this.height; i++) {
			if (this.board[i] == Mark.OPEN) {
				moves.add(i);
			}
		}

		return moves;
	}

	private int horizontalCount(Mark toCheck, int position) {
		int myCount = 0;
		int width = this.getWidth();

		int myColumn = position % width;
		int current = position;

		while (myColumn < width && this.board[current] == toCheck) {
			current += 1;
			myColumn += 1;
			myCount += 1;
		}

		return myCount;
	}

	/**
	 * Returns true if the game is over, false otherwise.
	 * 
	 * @return true if the game is over, false otherwise
	 */
	public boolean isGameOver() {
		return this.gameover;
	}

	private int longestDirectionFromPosition(Mark toCheck, int position) {
		int longestCount = -1 * this.winlength;

		int[] directionalCounts;

		directionalCounts = new int[] { this.horizontalCount(toCheck, position), this.verticalCount(toCheck, position),
				this.diagonalCount(toCheck, position), this.diagonalAlternateCount(toCheck, position) };

		for (int count : directionalCounts) {
			if (count > longestCount) {
				longestCount = count;
			}
		}

		return longestCount;
	}

	/**
	 * Returns the longest stretch of pieces of the specified type.
	 * 
	 * @precondition toCheck must be X or O
	 * @postcondition none
	 * 
	 * @param toCheck
	 *            the pieces to check for (either X or O)
	 * @return the longest stretch of pieces of the specified type
	 */
	public int longestLine(Mark toCheck) {
		if (toCheck == Mark.OPEN) {
			throw new IllegalArgumentException("Cannot check for length of open spaces.");
		}

		int longestLine = 0;

		for (int position = 0; position < this.width * this.height; position++) {
			if (this.get(position) == toCheck) {
				longestLine = Math.max(longestLine, this.longestDirectionFromPosition(toCheck, position));
			}
		}

		return longestLine;
	}

	/**
	 * Returns a new game state based on the current game state with this
	 * position filled.
	 * 
	 * @precondition the desired position is legal and open
	 * @postcondition none
	 * 
	 * @param position
	 *            the desired position
	 * @return A new game state
	 */
	public MNKGame makeMove(int position) {
		this.checkForIllegalPosition(position);
		this.checkForFilledPosition(position);
		return new MNKGame(this, position);
	}

	/**
	 * Returns a String representing this object.
	 * 
	 * @return a String representing this object
	 */
	public String toString() {
		String puzzleShape = "[ ";

		for (int i = 0; i < this.board.length; i++) {
			if (i > 0 && i % this.width == 0) {
				puzzleShape += "]\n[ ";
			}

			if (this.board[i] == Mark.OPEN) {
				puzzleShape += String.format("%3d|", i);
			} else if (this.board[i] == Mark.O) {
				puzzleShape += String.format(" O |", i);
			} else {
				puzzleShape += String.format(" X |", i);
			}
		}
		puzzleShape += "]\n";
		return puzzleShape;
	}

	private int verticalCount(Mark toCheck, int position) {
		int myCount = 0;
		int width = this.getWidth();
		int height = this.getHeight();

		int myRow = position / width;
		int current = position;

		while (myRow < height && this.board[current] == toCheck) {
			current += width;
			myRow += 1;
			myCount += 1;
		}

		return myCount;
	}

	/**
	 * Returns the current player.
	 * 
	 * @return the current player
	 */
	public Mark whoMoves() {
		return this.currentMover;
	}

	/**
	 * Returns who moves after the current player
	 * 
	 * @return who moves after the current player
	 */
	public Mark whoMovesNext() {
		if (this.currentMover == Mark.X) {
			return Mark.O;
		}
		return Mark.X;
	}
}
