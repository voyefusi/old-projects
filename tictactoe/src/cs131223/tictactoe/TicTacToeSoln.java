package cs131223.tictactoe;
import tictactoe.TicTacToeStateInterface;

public class TicTacToeSoln implements game.Game {
	private static int rowNum = 3;
	private static int colNum = 3;
	private static char X = 'X';
	private static char O = 'O';
	private char myLetter;
	private boolean first;

	public  String getPlayerName(){
		//Specifies the name of the player playing this game.
		return "SlikVik";
	}

	public void move(game.GameState state){
		//Generates the next move for the game by modifying the game state.
		TicTacToeStateInterface tttState = (TicTacToeStateInterface)state;
		char[][] board = tttState.getBoard();

		//Codes for Player#1
		if (first == true){
			if (board[1][1] == ' '){
				board[1][1] = O;
				new TicTacToeState(board);
			}else if ((board[0][0] == X)&&(board[0][1] == X)){
				board[0][2] = O;
				new TicTacToeState(board);
			}else if ((board[0][0] == X)&&(board[0][2] == X)){
				board[0][1] = O;
				new TicTacToeState(board);
			}else if ((board[0][0] == X)&&(board[1][1] == X)){
				board[2][2] = O;
				new TicTacToeState(board);
			}else if ((board[0][0] == X)&&(board[2][2] == X)){
				board[1][1] = O;
				new TicTacToeState(board);
			}else if ((board[0][0] == X)&&(board[1][0] == X)){
				board[2][0] = O;
				new TicTacToeState(board);
			}else if ((board[0][0] == X)&&(board[2][0] == X)){
				board[1][0] = O;
				new TicTacToeState(board);
			}else if ((board[0][1] == X)&&(board[0][2] == X)){
				board[0][0] = O;
				new TicTacToeState(board);
			}else if ((board[0][1] == X)&&(board[0][0] == X)){
				board[0][2] = O;
				new TicTacToeState(board);
			}else if ((board[0][1] == X)&&(board[1][1] == X)){
				board[2][1] = O;
				new TicTacToeState(board);
			}else if ((board[0][1] == X)&&(board[2][1] == X)){
				board[1][1] = O;
				new TicTacToeState(board);
			}else if ((board[0][1] == X)&&(board[0][0] == X)){
				board[0][2] = O;
				new TicTacToeState(board);
			}else if ((board[0][1] == X)&&(board[0][2] == X)){
				board[0][0] = O;
				new TicTacToeState(board);
			}else if ((board[0][2] == X)&&(board[1][2] == X)){
				board[2][2] = O;
				new TicTacToeState(board);
			}else if ((board[0][2] == X)&&(board[2][2] == X)){
				board[1][2] = O;
				new TicTacToeState(board);
			}else if ((board[0][2] == X)&&(board[0][1] == X)){
				board[0][0] = O;
				new TicTacToeState(board);
			}else if ((board[0][2] == X)&&(board[0][0] == X)){
				board[0][1] = O;
				new TicTacToeState(board);
			}else if ((board[0][2] == X)&&(board[0][1] == X)){
				board[0][0] = O;
				new TicTacToeState(board);
			}else if ((board[0][2] == X)&&(board[2][0] == X)){
				board[1][1] = O;
				new TicTacToeState(board);
			}else if ((board[0][2] == X)&&(board[1][1] == X)){
				board[2][0] = O;
				new TicTacToeState(board);
			}else if ((board[1][0] == X)&&(board[1][1] == X)){
				board[1][2] = O;
				new TicTacToeState(board);
			}else if ((board[1][0] == X)&&(board[1][2] == X)){
				board[1][1] = O;
				new TicTacToeState(board);
			}else if ((board[1][0] == X)&&(board[2][0] == X)){
				board[0][0] = O;
				new TicTacToeState(board);
			}else if ((board[1][0] == X)&&(board[0][0] == X)){
				board[2][0] = O;
				new TicTacToeState(board);
			}else if ((board[1][0] == X)&&(board[0][0] == X)){
				board[2][0] = O;
				new TicTacToeState(board);
			}else if ((board[1][0] == X)&&(board[2][0] == X)){
				board[0][0] = O;
				new TicTacToeState(board);
			}else if ((board[1][1] == X)&&(board[0][0] == X)){
				board[2][2] = O;
				new TicTacToeState(board);
			}else if ((board[1][1] == X)&&(board[0][1] == X)){
				board[2][1] = O;
				new TicTacToeState(board);
			}else if ((board[1][1] == X)&&(board[0][2] == X)){
				board[2][0] = O;
				new TicTacToeState(board);
			}else if ((board[1][1] == X)&&(board[1][2] == X)){
				board[1][0] = O;
				new TicTacToeState(board);
			}else if ((board[1][1] == X)&&(board[2][2] == X)){
				board[0][0] = O;
				new TicTacToeState(board);
			}else if ((board[1][1] == X)&&(board[1][1] == X)){
				board[0][1] = O;
				new TicTacToeState(board);
			}else if ((board[1][1] == X)&&(board[2][1] == X)){
				board[0][2] = O;
				new TicTacToeState(board);
			}else if ((board[1][2] == X)&&(board[0][2] == X)){
				board[2][2] = O;
				new TicTacToeState(board);
			}else if ((board[1][2] == X)&&(board[1][1] == X)){
				board[1][0] = O;
				new TicTacToeState(board);
			}else if ((board[1][2] == X)&&(board[1][0] == X)){
				board[1][1] = O;
				new TicTacToeState(board);
			}else if ((board[2][0] == X)&&(board[2][1] == X)){
				board[2][2] = O;
				new TicTacToeState(board);
			}else if ((board[2][0] == X)&&(board[2][2] == X)){
				board[2][1] = O;
				new TicTacToeState(board);
			}else if ((board[2][0] == X)&&(board[1][1] == X)){
				board[0][2] = O;
				new TicTacToeState(board);
			}else if ((board[2][0] == X)&&(board[0][2] == X)){
				board[1][1] = O;
				new TicTacToeState(board);
			}else if ((board[2][0] == X)&&(board[1][0] == X)){
				board[0][0] = O;
				new TicTacToeState(board);
			}else if ((board[2][0] == X)&&(board[0][0] == X)){
				board[1][0] = O;
				new TicTacToeState(board);
			}else if ((board[2][1] == X)&&(board[2][0] == X)){
				board[2][2] = O;
				new TicTacToeState(board);
			}else if ((board[2][1] == X)&&(board[2][2] == X)){
				board[2][0] = O;
				new TicTacToeState(board);
			}else if ((board[2][1] == X)&&(board[1][1] == X)){
				board[0][1] = O;
				new TicTacToeState(board);
			}else if ((board[2][1] == X)&&(board[0][1] == X)){
				board[0][1] = O;	
				new TicTacToeState(board);
			}else if ((board[2][1] == X)&&(board[2][2] == X)){
				board[2][0] = O;
				new TicTacToeState(board);
			}else if ((board[2][2] == X)&&(board[1][2] == X)){
				board[0][2] = O;
				new TicTacToeState(board);
			}else if ((board[2][2] == X)&&(board[0][2] == X)){
				board[1][2] = O;
				new TicTacToeState(board);
			}else if ((board[2][2] == X)&&(board[2][1] == X)){
				board[2][0] = O;
				new TicTacToeState(board);
			}else if ((board[2][2] == X)&&(board[2][0] == X)){
				board[2][1] = O;
				new TicTacToeState(board);
			}else if ((board[2][2] == X)&&(board[1][1] == X)){
				board[0][0] = O;
				new TicTacToeState(board);
			}else if ((board[2][2] == X)&&(board[0][0] == X)){
				board[1][1] = O;
				new TicTacToeState(board);
			}else{
				for(int row = 2 ;row >= 0;row--){
					for(int col = 2;col >= 0;col--){
						if(board[row][col] != X || board[row][col] != O){ 
							board[row][col] = O;
							new TicTacToeState(board);
							break;
						}
					}
				}
			}
		}

		//Codes for Player#2
		if (first == false){
			if (board[1][1] == ' '){
				board[1][1] = X;
				new TicTacToeState(board);
			}else if ((board[0][0] == O)&&(board[0][1] == O)){
				board[0][2] = X;
				new TicTacToeState(board);
			}else if ((board[0][0] == O)&&(board[0][2] == O)){
				board[0][1] = X;	
				new TicTacToeState(board);
			}else if ((board[0][0] == O)&&(board[1][1] == O)){
				board[2][2] = X;
				new TicTacToeState(board);
			}else if ((board[0][0] == O)&&(board[2][2] == O)){
				board[1][1] = X;
				new TicTacToeState(board);
			}else if ((board[0][0] == O)&&(board[1][0] == O)){
				board[2][0] = X;
				new TicTacToeState(board);
			}else if ((board[0][0] == O)&&(board[2][0] == O)){
				board[1][0] = X;
				new TicTacToeState(board);
			}else if ((board[0][1] == O)&&(board[0][2] == O)){
				board[0][0] = X;
				new TicTacToeState(board);
			}else if ((board[0][1] == O)&&(board[0][0] == O)){
				board[0][2] = X;
				new TicTacToeState(board);
			}else if ((board[0][1] == O)&&(board[1][1] == O)){
				board[2][1] = X;
				new TicTacToeState(board);
			}else if ((board[0][1] == O)&&(board[2][1] == O)){
				board[1][1] = X;
				new TicTacToeState(board);
			}else if ((board[0][1] == O)&&(board[0][0] == O)){
				board[0][2] = X;
				new TicTacToeState(board);
			}else if ((board[0][1] == O)&&(board[0][2] == O)){
				board[0][0] = X;
				new TicTacToeState(board);
			}else if ((board[0][2] == O)&&(board[1][2] == O)){
				board[2][2] = X;
				new TicTacToeState(board);
			}else if ((board[0][2] == O)&&(board[2][2] == O)){
				board[1][2] = X;
				new TicTacToeState(board);
			}else if ((board[0][2] == O)&&(board[0][1] == O)){
				board[0][0] = X;
				new TicTacToeState(board);
			}else if ((board[0][2] == O)&&(board[0][0] == O)){
				board[0][1] = X;
				new TicTacToeState(board);
			}else if ((board[0][2] == O)&&(board[0][1] == O)){
				board[0][0] = X;
				new TicTacToeState(board);
			}else if ((board[0][2] == O)&&(board[2][0] == O)){
				board[1][1] = X;
				new TicTacToeState(board);
			}else if ((board[0][2] == O)&&(board[1][1] == O)){
				board[2][0] = X;
				new TicTacToeState(board);
			}else if ((board[1][0] == O)&&(board[1][1] == O)){
				board[1][2] = X;
				new TicTacToeState(board);
			}else if ((board[1][0] == O)&&(board[1][2] == O)){
				board[1][1] = X;
				new TicTacToeState(board);
			}else if ((board[1][0] == O)&&(board[2][0] == O)){
				board[0][0] = X;
				new TicTacToeState(board);
			}else if ((board[1][0] == O)&&(board[0][0] == O)){
				board[2][0] = X;
				new TicTacToeState(board);
			}else if ((board[1][0] == O)&&(board[0][0] == O)){
				board[2][0] = X;
				new TicTacToeState(board);
			}else if ((board[1][0] == O)&&(board[2][0] == O)){
				board[0][0] = X;
				new TicTacToeState(board);
			}else if ((board[1][1] == O)&&(board[0][0] == O)){
				board[2][2] = X;
				new TicTacToeState(board);
			}else if ((board[1][1] == O)&&(board[0][1] == O)){
				board[2][1] = X;
				new TicTacToeState(board);
			}else if ((board[1][1] == O)&&(board[0][2] == O)){
				board[2][0] = X;
				new TicTacToeState(board);
			}else if ((board[1][1] == O)&&(board[1][2] == O)){
				board[1][0] = X;
				new TicTacToeState(board);
			}else if ((board[1][1] == O)&&(board[2][2] == O)){
				board[0][0] = X;
				new TicTacToeState(board);
			}else if ((board[1][1] == O)&&(board[1][1] == O)){
				board[0][1] = X;
				new TicTacToeState(board);
			}else if ((board[1][1] == O)&&(board[2][1] == O)){
				board[0][2] = X;
				new TicTacToeState(board);
			}else if ((board[1][2] == O)&&(board[0][2] == O)){
				board[2][2] = X;
				new TicTacToeState(board);
			}else if ((board[1][2] == O)&&(board[1][1] == O)){
				board[1][0] = X;
				new TicTacToeState(board);
			}else if ((board[1][2] == O)&&(board[1][0] == O)){
				board[1][1] = X;
				new TicTacToeState(board);
			}else if ((board[2][0] == O)&&(board[2][1] == O)){
				board[2][2] = X;
				new TicTacToeState(board);
			}else if ((board[2][0] == O)&&(board[2][2] == O)){
				board[2][1] = X;
				new TicTacToeState(board);
			}else if ((board[2][0] == O)&&(board[1][1] == O)){
				board[0][2] = X;
				new TicTacToeState(board);
			}else if ((board[2][0] == O)&&(board[0][2] == O)){
				board[1][1] = X;
				new TicTacToeState(board);
			}else if ((board[2][0] == O)&&(board[1][0] == O)){
				board[0][0] = X;
				new TicTacToeState(board);
			}else if ((board[2][0] == O)&&(board[0][0] == O)){
				board[1][0] = X;
				new TicTacToeState(board);
			}else if ((board[2][1] == O)&&(board[2][0] == O)){
				board[2][2] = X;
				new TicTacToeState(board);
			}else if ((board[2][1] == O)&&(board[2][2] == O)){
				board[2][0] = X;
				new TicTacToeState(board);
			}else if ((board[2][1] == O)&&(board[1][1] == O)){
				board[0][1] = X;
				new TicTacToeState(board);
			}else if ((board[2][1] == O)&&(board[0][1] == O)){
				board[0][1] = X;	
				new TicTacToeState(board);
			}else if ((board[2][1] == O)&&(board[2][2] == O)){
				board[2][0] = X;
				new TicTacToeState(board);
			}else if ((board[2][2] == O)&&(board[1][2] == O)){
				board[0][2] = X;
				new TicTacToeState(board);
			}else if ((board[2][2] == O)&&(board[0][2] == O)){
				board[1][2] = X;
				new TicTacToeState(board);
			}else if ((board[2][2] == O)&&(board[2][1] == O)){
				board[2][0] = X;
				new TicTacToeState(board);
			}else if ((board[2][2] == O)&&(board[2][0] == O)){
				board[2][1] = X;
				new TicTacToeState(board);
			}else if ((board[2][2] == O)&&(board[1][1] == O)){
				board[0][0] = X;
				new TicTacToeState(board);
			}else if ((board[2][2] == O)&&(board[0][0] == O)){
				board[1][1] = X;
				new TicTacToeState(board);
			}else{
				for(int row = 2;row >= 0;row--){
					for(int col = 2;col >= 0;col--){
						if(board[row][col] != X || board[row][col] != O){
							board[row][col] = X;
							new TicTacToeState(board);
							break;
						}
					}
				}
			}
		}
	}

	public void setPlayerNum(int playerNum){
		//Lets the game know which player number they are (always either 0 or 1).
		if (playerNum == 0){
			myLetter = O;
			first = true;
		}else if(playerNum == 1){
			myLetter = X;
			first = false;
		}
	}
}
