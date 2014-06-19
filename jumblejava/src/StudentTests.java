import game.Jumble;
import junit.framework.TestCase;
import puzzleLib.*;
import puzzleLib.Dictionary131;
import java.util.Arrays;

public class StudentTests extends TestCase {
	//tests toString Method()
	public void testToString(){ 
		Jumble J = new Jumble();
		String Victor = "Victor";
		J.generatePuzzle(Victor);
		assertTrue(J.toString().equals("Victor"));
	}
	//test swap Method()
	public void testSwap(){	
		Jumble J = new Jumble();
		String Victor = "Victor";
		J.generatePuzzle(Victor);
		J.swap(5,0);
		assertTrue(J.toString().equals("rictoV"));
	}
	//test insert Method()
	public void testInsert(){
		Jumble J = new Jumble();
		Jumble K = new Jumble();
		
		String Victor = "Victor";
		String Victor2 = "Victor";
		
		J.generatePuzzle(Victor);
		J.insert(5,0);
		assertTrue(J.toString().equals("rVicto"));
		
		K.generatePuzzle(Victor2);
		K.insert(0,5);
		assertTrue(K.toString().equals("ictorV"));		
	}
	//	test multipleSwap Method()
	public void testMultipleSwap(){
		Jumble J = new Jumble();
		String Victor = "Victor";
		J.generatePuzzle(Victor);
		J.multipleSwap(0,5);
		assertTrue(J.toString().equals("rotciV"));	
	}
	
	//test generatePuzzle Method()
	public void testGeneratePuzzle(){
		Jumble J = new Jumble();
		String Victor = "Victor";
		J.generatePuzzle(Victor);
		assertTrue(J.toString().equals(Victor));
		assertTrue(J.getSolution().equals(Victor));
		assertTrue(J.getNumOfInitTransformations()==0);
		assertTrue(J.getAttempts()==0);
	}
	//test generateScrambledPuzzle()
	public void testGenerateScrambledPuzzle(){
		Jumble J = new Jumble();
		String Victor = "Victor";
		J.generateScrambledPuzzle(Victor,8);
		assertTrue(J.getNumOfInitTransformations()==8);
		String currWord = J.toString();
		String origWord = J.getSolution();
		for (int i = 0; i < Victor.length(); i++){
			char currChar = Victor.charAt(i);
			assertTrue(currWord.indexOf(currChar)!=-1);
			assertTrue(origWord.indexOf(currChar)!=-1);
		}
	}
	//test generateRandomScrambledPuzzle()
	public void testGenerateRandomScrambledPuzzle(){
		Jumble J = new Jumble();	
		J.generateRandomScrambledPuzzle(10,5);
		String currWord = J.toString();
		String origWord = J.getSolution();
		for (int i = 0; i < origWord.length(); i++){
			char currChar = origWord.charAt(i);
			assertTrue(currWord.indexOf(currChar)!=-1);
			assertTrue(origWord.indexOf(currChar)!=-1);
		}
	}
	// test isSolutionCorrect()
	public void IsSolutionCorrect(){
		Jumble J = new Jumble();
		String Victor = "Victor";
		J.generateScrambledPuzzle(Victor,5);
		assertTrue(J.getSolution().equals("Victor"));
	}
	// test getSolution()
	public void TestGetSolution(){
		Jumble J = new Jumble();
		String Victor = "Victor";
		J.generatePuzzle(Victor);
		assertTrue(J.getSolution().equals("Victor"));	
	}
	// test getCurrentContentAsCells()
	public void testGetCurrentContentsAsCells(){
		Jumble J = new Jumble();
		String Victor = "Victor";
		J.generatePuzzle(Victor);
		Cell[] cellulose;
		cellulose = J.getCurrentContentsAsCells();
		for (int i = 0; i < Victor.length(); i++){
			assertTrue(cellulose[i].getColor() == 3);
		}
		
		Jumble swapmydoo = new Jumble();
		swapmydoo.generatePuzzle(Victor);
		swapmydoo.swap(5,0);		
	}
}
