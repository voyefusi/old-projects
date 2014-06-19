import cs131223.tictactoe.TicTacToeState;
import junit.framework.TestCase;

public class StudentTests extends TestCase {

	public void testTicTacToeStateNoParam(){
		//testTicTacToeState With No Parameters
		TicTacToeState test = new TicTacToeState();
		assertTrue(test.toString().equals("         "));
	}
	public void testTicTacToeStateWithParam(){
		//test TicTacToeState With Parameters
		char[][] orig = {{'X','O','O'},
				         {'O','O','X'},
				         {'O','X','X'}};
		TicTacToeState test = new TicTacToeState(orig);
		assertTrue(test.toString().equals("XOOOOXOXX"));
	}
	public void testToString(){
		//test To String
		char[][] orig = {{'X','O','O'},
				         {'O','O','X'},
				         {'O','X','X'}};

		TicTacToeState test = new TicTacToeState(orig);
		assertTrue(test.toString().equals("XOOOOXOXX"));
	}
	public void testGetBoard(){
		//test Get Board
		char[][] orig = {{'X','O','O'},
				         {'O','O','X'},
				         {'O','X','X'}};
		TicTacToeState test = new TicTacToeState(orig);
		char[][] testTest = test.getBoard(); 
		assertTrue(testTest[0][0] == orig[0][0]);
		assertTrue(testTest[0][1] == orig[0][1]);
		assertTrue(testTest[0][2] == orig[0][2]);
	}

	public void testDump(){
		//test Dump Method
		char[][] orig = {{'X','O','O'},
	         			 {'O','O','X'},
				         {'O','X','X'}};

		TicTacToeState test = new TicTacToeState(orig);
		assertTrue(test.dump().equals("XOO\nOOX\nOXX"));
	}

	public void testClone(){
		//test Clone Method
		char[][] orig = {{'X','O','O'},
				         {'O','O','X'},
			           	 {'O','X','X'}};
		TicTacToeState test = new TicTacToeState(orig);
		TicTacToeState newTest = test.clone();
		assertTrue(test.equals(newTest));
	}
	public void testEquals(){
		//test Equals method
		char[][] orig = {{'X','O','O'},
				         {'O','O','X'},
		 	             {'O','X','X'}};

		TicTacToeState test = new TicTacToeState(orig);
		char[][] testTest = test.getBoard(); 
		assertTrue(orig.equals(orig));
	}
}