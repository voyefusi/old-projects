package cs131223.mancala;
import mancala.MancalaStateInterface;
public class MancalaState implements game.GameState, MancalaStateInterface{
	private int size = 14;
	private int[] theBoard = new int[size];	
	public MancalaState(){
		//Default constructor. Initializes the entire board to being unplayed	

		for(int index = 12;index > 6 ;index--){
			theBoard[index] = 0;
		}

		theBoard[13] = 0;
		theBoard[6] = 0;

		for(int index = 0;index < 6 ;index++){
			theBoard[index] = 0;
		}
	}

	public MancalaState(int[] board){
		for(int index = 0;index < size ;index++){
			this.theBoard[index] = board[index];
		}
	}
	public String dump(){
		//Generates and returns a multi-line string representing a human-readable pretty version of the board.
		String dump = "  ";
		for(int index = 12;index > 6 ;index--){
			dump += theBoard[index]+" ";
		}
		dump += "\n";
		dump += theBoard[13];
		dump += "             " + theBoard[6]+"\n  ";
		for(int index = 0;index < 6 ;index++){
			dump += theBoard[index]+" ";
		}

		return dump;

	}

	public int[] getBoard() {
		// Returns a reference to the array storing the board state
		return theBoard;
	}
	public MancalaState clone(){
		//Returns a new MancalaState object whose contents is exactly the same as this instance.
		MancalaState b = new MancalaState(theBoard);
		return b;
	}
	public boolean equals(MancalaState same){
		//Determines if the specified instance MancalaState has the same contents as this instance.

		int [] compare;
		compare = same.getBoard();
		boolean check = true;
		for(int index = 0 ;index < size;index++){
			if(compare[size] != theBoard[size]){	
				check = false;
			}
		}
		return check;
	}
	public String toString(){
		//Generates and returns a single line string that must contain exactly 14 integers separated by commas (with no spaces).
		String stringIt = "";
		for(int index = 0;index < size-1 ;index++){
			stringIt +=  theBoard[index] + ",";
		}
		stringIt +=  theBoard[13];
		return stringIt;
	}
}	
