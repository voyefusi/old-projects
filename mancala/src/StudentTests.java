import mancala.MancalaStateInterface;
import cs131223.mancala.MancalaState;
import game.GameState;
import junit.framework.TestCase;



public class StudentTests extends TestCase {

	public void testMancalaStateConstructorNoParameters(){
		//Tests MancalaState Constructor With No Parameters
		MancalaState test = new MancalaState();
		assertTrue(test.toString().equals("0,0,0,0,0,0,0,0,0,0,0,0,0,0"));
	}
	public void testMancalaStateCopyConstructor(){
		//Tests MancalaState Copy Constructor
		int[] stuff = {5,5,5,5,5,5,0,3,3,3,3,3,3,0};
		MancalaState test = new MancalaState(stuff);
		assertTrue(test.toString().equals("5,5,5,5,5,5,0,3,3,3,3,3,3,0"));
	}

	public void testDump(){
		//Test String output of Mancala State in human readable form. Tested with System.out.println rather than assert
		MancalaState test = new MancalaState();
		System.out.println(test.dump());
	}

	public void testToStringandEquals(){
		//Tests MancalaState conversion to a String
		MancalaState test = new MancalaState();
		assertTrue(test.toString().equals("0,0,0,0,0,0,0,0,0,0,0,0,0,0"));
	}

	public void testGetBoard(){
		//Tests the call of the instance variable int array representing the MancalaState
		MancalaState test = new MancalaState();
		int[] stuff = new int[14];
		stuff = test.getBoard();
		System.out.println(stuff.toString());
		assertTrue(0 == stuff[0]);
		assertTrue(0 == stuff[1]);
		assertTrue(0 == stuff[2]);
		assertTrue(0 == stuff[3]);
		assertTrue(0 == stuff[4]);
		assertTrue(0 == stuff[5]);
		assertTrue(0 == stuff[6]);
		assertTrue(0 == stuff[7]);
		assertTrue(0 == stuff[8]);
		assertTrue(0 == stuff[9]);
		assertTrue(0 == stuff[10]);
		assertTrue(0 == stuff[11]);
		assertTrue(0 == stuff[12]);
		assertTrue(0 == stuff[13]);

	}


	public void testClone(){
		//Tests making a copy of Mancala State 
		MancalaState test = new MancalaState();
		MancalaState newTest = test.clone();
		assertTrue(test.toString().equals(newTest.toString()));
	}
}	