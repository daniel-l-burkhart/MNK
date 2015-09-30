/**
 *
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mnkgame.model.MNKGame;
import mnkgame.model.MNKPayoff;
import mnkgame.model.Mark;

/**
 * Testing the payoff function.
 *
 * @author dburkha1
 * @version Fall 2015
 */
public class whenTestingMNKPayoff {

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link mnkgame.model.MNKPayoff#payoff(mnkgame.model.MNKGame, mnkgame.model.Mark)}
	 * .
	 */
	@Test
	public void testPayoff() {
		MNKGame aBoard = new MNKGame(3, 3, 3);

		assertEquals(-1, MNKPayoff.payoff(aBoard.makeMove(1), Mark.O));
	}

	/**
	 * 2
	 */
	@Test
	public void testSecondX() {
		MNKGame aBoard = new MNKGame(3, 3, 3);

		assertEquals(0, MNKPayoff.payoff(aBoard.makeMove(1).makeMove(2), Mark.X));
	}

	/**
	 * 3
	 */
	@Test
	public void testSecondMove() {
		MNKGame aBoard = new MNKGame(3, 3, 3);

		assertEquals(0, MNKPayoff.payoff(aBoard.makeMove(1).makeMove(3), Mark.O));
	}

	/**
	 * 4
	 */
	@Test
	public void testThirdX() {
		MNKGame aBoard = new MNKGame(3, 3, 3);

		assertEquals(1, MNKPayoff.payoff(aBoard.makeMove(1).makeMove(2).makeMove(4), Mark.X));
	}

	/**
	 * 5
	 */
	@Test
	public void testThirdMove() {
		MNKGame aBoard = new MNKGame(3, 3, 3);

		assertEquals(-1, MNKPayoff.payoff(aBoard.makeMove(1).makeMove(3).makeMove(5), Mark.O));
	}

	/**
	 * 6
	 */
	@Test
	public void testFourthX() {
		MNKGame aBoard = new MNKGame(3, 3, 3);

		assertEquals(1, MNKPayoff.payoff(aBoard.makeMove(1).makeMove(2).makeMove(4).makeMove(6), Mark.X));
	}

	/**
	 * 7
	 */
	@Test
	public void testFourthMove() {
		MNKGame aBoard = new MNKGame(3, 3, 3);

		assertEquals(0, MNKPayoff.payoff(aBoard.makeMove(1).makeMove(3).makeMove(5).makeMove(7), Mark.O));
	}

	// 051837

	/**
	 * 8
	 */
	@Test
	public void test051837() {
		MNKGame aBoard = new MNKGame(3, 3, 3);

		assertEquals(0, MNKPayoff.payoff(aBoard.makeMove(0).makeMove(5).makeMove(1).makeMove(8).makeMove(7), Mark.X));
	}

	/**
	 * 9
	 */
	@Test
	public void testFifthX() {
		MNKGame aBoard = new MNKGame(3, 3, 3);

		assertEquals(1, MNKPayoff.payoff(aBoard.makeMove(1).makeMove(2).makeMove(4).makeMove(6).makeMove(8), Mark.X));
	}

}
