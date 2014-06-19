package cs131223.tictactoe;
import tictactoe.TicTacToeStateInterface;

public class TicTacToeState implements game.GameState, TicTacToeStateInterface{
	private static int rowNum = 3;
	private static int colNum = 3;
	private char[][] theBoard = new char[rowNum][colNum];

	public TicTacToeState(){
		//Default constructor. Initializes the entire board to being unplayed	

		for(int row = 0 ;row < rowNum;row++){
			for(int col = 0;col < colNum ;col++){
				theBoard[row][col] = ' ';
			}
		}
	}
	public TicTacToeState(char[][] board){
		//Constructor that sets the state of the board to a copy of the specified board.
		//Note that you must not store a reference to the board that is passed in. 
		//Instead, you must allocate a new two-dimensional array and copy the contents from the parameter.

		for(int row = 0 ;row < rowNum;row++){
			for(int col = 0;col < colNum;col++){
				theBoard[row][col] = board[row][col];
			}
		}
	}

	public char[][] getBoard() {
		//Returns a reference to the two-dimensional array storing the board state.
		
		return theBoard;
	}

	public String dump() {
		//Generates and returns a multi-line string representing a human-readable pretty version of the board.

		String multiLineString;
		multiLineString = "";
		int count = 0;

		for(int row = 0 ;row < rowNum;row++){
			for(int col = 0;col < colNum;col++){
				if ((count == 2)||(count == 5)){
					multiLineString += theBoard[row][col]+ "\n";
				}else{
					multiLineString += theBoard[row][col];
				}
				count++;
			}
		}
		return multiLineString;
	}

	public TicTacToeState clone(){
		//Returns a new TicTacToeState object whose contents 
		//is exactly the same as this instance.
		
		TicTacToeState newBoard = new TicTacToeState(theBoard);
		return newBoard;
	}

	public boolean equals(TicTacToeStateInterface state){
		//Determines if the specified instance TicTacToeState has the same contents as this instance.
		
		char [][] compare;
		compare = state.getBoard();
		boolean check = true;
		for(int row = 0 ;row < rowNum;row++){
			for(int col = 0;col < colNum;col++){
				if(compare[row][col] != theBoard[row][col]){	
				check = false;
				}
			}
		}
		return check;
	}
	public String toString(){
		//Generates and returns a single line string that must contain exactly nine characters representing the state of the board. The nine characters must each be space, 'O' or 'X', and must represent the layout of the board in in the order specified in the project description of the TicTacToeState class.
		
		String boardState  = "";
		for(int row = 0 ;row < rowNum;row++){
			for(int col = 0;col < colNum;col++){
				boardState += theBoard[row][col];
			}
		}
		return boardState;
	}
}