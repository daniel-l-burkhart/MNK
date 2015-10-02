package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import mnkgame.model.MNKGame;
import mnkgame.model.Mark;

/**
 * Test class for MNK Game
 * 
 * @author jcchurch
 * @version Fall 2015
 *
 */
public class WhenTestingMNKGame {

	/**
	 * empty board should be 9 moves.
	 */
	@Test
	public void when3by3BoardEmpty9OpenMoves() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertEquals(9, aBoard.getMoves().size());
	}

	/**
	 * 8 open moves
	 */
	@Test
	public void when3by3BoardOneMoveThen8OpenMoves() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertEquals(8, aBoard.makeMove(0).getMoves().size());
	}

	/**
	 * move at 0 should be X
	 */
	@Test
	public void when3by3BoardOneMoveAt0Then0isX() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertEquals(Mark.X, aBoard.makeMove(0).get(0));
	}

	/**
	 * empty board O moves next
	 */
	@Test
	public void when3by3BoardEmptyThenOMovesNext() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertEquals(Mark.O, aBoard.whoMovesNext());
	}

	/**
	 * one move at O X moves next
	 */
	@Test
	public void when3by3BoardOneMoveAt0ThenXMovesNext() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertEquals(Mark.X, aBoard.makeMove(0).whoMovesNext());
	}

	/**
	 * width is 4.
	 */
	@Test
	public void when4by3and5BoardWidthIs4() {
		MNKGame aBoard = new MNKGame(4, 3, 5);
		assertEquals(4, aBoard.getWidth());
	}

	/**
	 * board height is 3.
	 */
	@Test
	public void when4by3and5BoardHeightIs3() {
		MNKGame aBoard = new MNKGame(4, 3, 5);
		assertEquals(3, aBoard.getHeight());
	}

	/**
	 * win condition is 5
	 */
	@Test
	public void when4by3and5BoardWinConditionIs5() {
		MNKGame aBoard = new MNKGame(4, 3, 5);
		assertEquals(5, aBoard.getWinLength());
	}

	/**
	 * board empty game not over.
	 */
	@Test
	public void when3by3and3BoardEmptyGameOverNotOver() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertFalse(aBoard.isGameOver());
	}

	/**
	 * x wins vertically is game oer.
	 */
	@Test
	public void when3by3and3BoardAndXwinsVerticallyGameOver() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertTrue(aBoard.makeMove(0).makeMove(1).makeMove(3).makeMove(4).makeMove(6).isGameOver());
	}

	/**
	 * horizontally is game over
	 */
	@Test
	public void when3by3and3BoardAndXwinsHorizontallyGameOver() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertTrue(aBoard.makeMove(0).makeMove(3).makeMove(1).makeMove(4).makeMove(2).isGameOver());
	}

	/**
	 * first diagonal game over.
	 */
	@Test
	public void when3by3and3BoardAndXwinsFirstDiagonalGameOver() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertTrue(aBoard.makeMove(0).makeMove(1).makeMove(4).makeMove(2).makeMove(8).isGameOver());
	}

	/**
	 * 3 by 3 board second diagonal game over
	 */
	@Test
	public void when3by3and3BoardAndXwinsSecondDiagonalGameOver() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertTrue(aBoard.makeMove(2).makeMove(1).makeMove(4).makeMove(0).makeMove(6).isGameOver());
	}
}
