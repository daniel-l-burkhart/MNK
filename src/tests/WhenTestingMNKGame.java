package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import mnkgame.model.MNKGame;
import mnkgame.model.Mark;

public class WhenTestingMNKGame {

	@Test
	public void When3by3BoardEmpty9OpenMoves() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertEquals(9, aBoard.getMoves().size());
	}

	@Test
	public void When3by3BoardOneMoveThen8OpenMoves() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertEquals(8, aBoard.makeMove(0).getMoves().size());
	}

	@Test
	public void When3by3BoardOneMoveAt0Then0isX() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertEquals(Mark.X, aBoard.makeMove(0).get(0));
	}

	@Test
	public void When3by3BoardEmptyThenOMovesNext() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertEquals(Mark.O, aBoard.whoMovesNext());
	}

	@Test
	public void When3by3BoardOneMoveAt0ThenXMovesNext() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertEquals(Mark.X, aBoard.makeMove(0).whoMovesNext());
	}

	@Test
	public void When4by3and5BoardWidthIs4() {
		MNKGame aBoard = new MNKGame(4, 3, 5);
		assertEquals(4, aBoard.getWidth());
	}

	@Test
	public void When4by3and5BoardHeightIs3() {
		MNKGame aBoard = new MNKGame(4, 3, 5);
		assertEquals(3, aBoard.getHeight());
	}

	@Test
	public void When4by3and5BoardWinConditionIs5() {
		MNKGame aBoard = new MNKGame(4, 3, 5);
		assertEquals(5, aBoard.getWinLength());
	}

	@Test
	public void When3by3and3BoardEmptyGameOverNotOver() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertFalse(aBoard.isGameOver());
	}

	@Test
	public void When3by3and3BoardAndXwinsVerticallyGameOver() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertTrue(aBoard.makeMove(0).makeMove(1).makeMove(3).makeMove(4).makeMove(6).isGameOver());
	}

	@Test
	public void When3by3and3BoardAndXwinsHorizontallyGameOver() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertTrue(aBoard.makeMove(0).makeMove(3).makeMove(1).makeMove(4).makeMove(2).isGameOver());
	}

	@Test
	public void When3by3and3BoardAndXwinsFirstDiagonalGameOver() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertTrue(aBoard.makeMove(0).makeMove(1).makeMove(4).makeMove(2).makeMove(8).isGameOver());
	}

	@Test
	public void When3by3and3BoardAndXwinsSecondDiagonalGameOver() {
		MNKGame aBoard = new MNKGame(3, 3, 3);
		assertTrue(aBoard.makeMove(2).makeMove(1).makeMove(4).makeMove(0).makeMove(6).isGameOver());
	}
}
