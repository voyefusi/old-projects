package cs131223.tron;
import java.util.ArrayList;
import tron.Cell;
import tron.TronConstants;
import tron.TronObstacle;
import tron.TronStateInterface;


public class TronSoln implements game.Game{
	private int playerNumber;

	public String getPlayerName(){
		//Specifies the name of the player playing this game. This is the name that will be identified publicly on the web, so this should be unique, friendly and anonymous (or not), as you choose.

		return "SlikVik";
	}

	public void setPlayerNum(int playerNum){

		//Lets the game know which player number they are (always either 0 or 1). Called once before game play starts. It is your job to remember your player number so that later on when the move() method is called, you will know how to fill in the board. See the documentation for TronState to see the meaning of the variables stored in the state board.
		playerNumber = playerNum;
	}

	public void move(game.GameState state){
		//Generates the next move for the game by modifying the game state. Gets called many times by the gaming engine, once every time it is the player's turn. You must play for the player number specified by the setPlayerNum() method. NOTE: The parameter is of type GameState, but in practice, it will always be an instance of a class that implements the TronStateInterface interface, so you can access the board from the state with these two lines of code:

		TronStateInterface tronState = (TronStateInterface)state;
		int[][] board = tronState.getBoard();

		Cell head_0 = new Cell();
		Cell head_1 = new Cell();

		ArrayList<TronObstacle> obstacle = tronState.getObstacles();		
		for(TronObstacle iuiu:obstacle){
			ArrayList<Cell> po = iuiu.getCells();
			for (Cell i: po){
				board[i.row][i.col] = TronConstants.STATE_OBSTACLE;
			}
		}

		if (playerNumber == 0){
			head_0 = tronState.getHead(0);
			if((head_0.row == 0)&&(head_0.col==0)){
				if (board[head_0.row+1][head_0.col]==-1/* && isObtacle(tronState, state, head_0.row, head_0.col) == false*/){
					Cell pt = new Cell(head_0.row+1, head_0.col);
					tronState.setCell((head_0.row+1), head_0.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_0.row][head_0.col+1]==-1){
					Cell pt = new Cell(head_0.row, head_0.col+1);
					tronState.setCell((head_0.row), head_0.col+1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else if((head_0.row == 0)&&(head_0.col != 0)&&(head_0.col != board.length - 1)){
				if (board[head_0.row+1][head_0.col]== -1){
					Cell pt = new Cell(head_0.row+1, head_0.col);
					tronState.setCell((head_0.row+1), head_0.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_0.row][head_0.col+1]==-1){
					Cell pt = new Cell(head_0.row, head_0.col+1);
					tronState.setCell((head_0.row), head_0.col+1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_0.row][head_0.col-1]==-1){
					Cell pt = new Cell(head_0.row, head_0.col-1);
					tronState.setCell((head_0.row), head_0.col-1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else if((head_0.row == 0)&&(head_0.col == board.length - 1)){
				if (board[head_0.row+1][head_0.col]==-1){
					Cell pt = new Cell(head_0.row+1, head_0.col);
					tronState.setCell((head_0.row+1), head_0.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_0.row][head_0.col-1]==-1){
					Cell pt = new Cell(head_0.row, head_0.col-1);
					tronState.setCell((head_0.row), head_0.col-1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else if((head_0.col == 0)&&(head_0.row != 0)&&(head_0.row != board.length - 1)){
				if (board[head_0.row+1][head_0.col]== -1){
					Cell pt = new Cell(head_0.row+1, head_0.col);
					tronState.setCell((head_0.row+1), head_0.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_0.row-1][head_0.col]==-1){
					Cell pt = new Cell(head_0.row-1, head_0.col);
					tronState.setCell((head_0.row-1), head_0.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_0.row][head_0.col+1]==-1){
					Cell pt = new Cell(head_0.row, head_0.col+1);
					tronState.setCell((head_0.row), head_0.col+1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else if((head_0.col == board.length-1)&&(head_0.row != 0)&&(head_0.row != board.length - 1)){
				if (board[head_0.row][head_0.col-1]== -1){
					Cell pt = new Cell(head_0.row, head_0.col-1);
					tronState.setCell((head_0.row), head_0.col-1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_0.row+1][head_0.col]==-1){
					Cell pt = new Cell(head_0.row+1, head_0.col);
					tronState.setCell((head_0.row+1), head_0.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_0.row-1][head_0.col]==-1){
					Cell pt = new Cell(head_0.row-1, head_0.col);
					tronState.setCell((head_0.row-1), head_0.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else if((head_0.col == 0)&&(head_0.row == board.length - 1)){
				if (board[head_0.row-1][head_0.col]==-1){
					Cell pt = new Cell(head_0.row-1, head_0.col);
					tronState.setCell((head_0.row-1), head_0.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_0.row][head_0.col+1]==-1){
					Cell pt = new Cell(head_0.row, head_0.col+1);
					tronState.setCell((head_0.row), head_0.col+1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else if((head_0.row == board.length-1)&&(head_0.col != 0)&&(head_0.col != board.length - 1)){
				if (board[head_0.row][head_0.col+1]== -1){
					Cell pt = new Cell(head_0.row, head_0.col+1);
					tronState.setCell((head_0.row), head_0.col+1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_0.row][head_0.col-1]==-1){
					Cell pt = new Cell(head_0.row, head_0.col-1);
					tronState.setCell((head_0.row), head_0.col-1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_0.row-1][head_0.col]==-1){
					Cell pt = new Cell(head_0.row-1, head_0.col);
					tronState.setCell((head_0.row-1), head_0.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else if((head_0.row == board.length-1)&&(head_0.col == board.length - 1)){
				if (board[head_0.row-1][head_0.col]==-1){
					Cell pt = new Cell(head_0.row-1, head_0.col);
					tronState.setCell((head_0.row-1), head_0.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_0.row][head_0.col-1]==-1){
					Cell pt = new Cell(head_0.row, head_0.col-1);
					tronState.setCell((head_0.row), head_0.col-1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else{
				if (board[head_0.row+1][head_0.col]== -1){
					Cell pt = new Cell(head_0.row+1, head_0.col);
					tronState.setCell((head_0.row+1), head_0.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_0.row-1][head_0.col]==-1){
					Cell pt = new Cell(head_0.row-1, head_0.col);
					tronState.setCell((head_0.row-1), head_0.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_0.row][head_0.col+1]==-1){
					Cell pt = new Cell(head_0.row, head_0.col+1);
					tronState.setCell((head_0.row), head_0.col+1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_0.row][head_0.col-1]==-1){
					Cell pt = new Cell(head_0.row, head_0.col-1);
					tronState.setCell((head_0.row), head_0.col-1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}	
		} 
		if (playerNumber == 1){
			head_1 = tronState.getHead(1);
			if((head_1.row == 0)&&(head_1.col==0)){
				if (board[head_1.row+1][head_1.col]==-1){
					Cell pt = new Cell(head_1.row+1, head_1.col);
					tronState.setCell((head_1.row+1), head_1.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_1.row][head_1.col+1]==-1){
					Cell pt = new Cell(head_1.row, head_1.col+1);
					tronState.setCell((head_1.row), head_1.col+1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else if((head_1.row == 0)&&(head_1.col != 0)&&(head_1.col != board.length - 1)){
				if (board[head_1.row+1][head_1.col]== -1){
					Cell pt = new Cell(head_1.row+1, head_1.col);
					tronState.setCell((head_1.row+1), head_1.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_1.row][head_1.col+1]==-1){
					Cell pt = new Cell(head_1.row, head_1.col+1);
					tronState.setCell((head_1.row), head_1.col+1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_1.row][head_1.col-1]==-1){
					Cell pt = new Cell(head_1.row, head_1.col-1);
					tronState.setCell((head_1.row), head_1.col-1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else if((head_1.row == 0)&&(head_1.col == board.length - 1)){
				if (board[head_1.row+1][head_1.col]==-1){
					Cell pt = new Cell(head_1.row+1, head_1.col);
					tronState.setCell((head_1.row+1), head_1.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_1.row][head_1.col-1]==-1){
					Cell pt = new Cell(head_1.row, head_1.col-1);
					tronState.setCell((head_1.row), head_1.col-1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else if((head_1.col == 0)&&(head_1.row != 0)&&(head_1.row != board.length - 1)){
				if (board[head_1.row+1][head_1.col]== -1){
					Cell pt = new Cell(head_1.row+1, head_1.col);
					tronState.setCell((head_1.row+1), head_1.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_1.row-1][head_1.col]==-1){
					Cell pt = new Cell(head_1.row-1, head_1.col);
					tronState.setCell((head_1.row-1), head_1.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_1.row][head_1.col+1]==-1){
					Cell pt = new Cell(head_1.row, head_1.col+1);
					tronState.setCell((head_1.row), head_1.col+1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else if((head_1.col == board.length-1)&&(head_1.row != 0)&&(head_1.row != board.length - 1)){
				if (board[head_1.row][head_1.col-1]== -1){
					Cell pt = new Cell(head_1.row, head_1.col-1);
					tronState.setCell((head_1.row), head_1.col-1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_1.row+1][head_1.col]==-1){
					Cell pt = new Cell(head_1.row+1, head_1.col);
					tronState.setCell((head_1.row+1), head_1.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_1.row-1][head_1.col]==-1){
					Cell pt = new Cell(head_1.row-1, head_1.col);
					tronState.setCell((head_1.row-1), head_1.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else if((head_1.col == 0)&&(head_1.row == board.length - 1)){
				if (board[head_1.row-1][head_1.col]==-1){
					Cell pt = new Cell(head_1.row-1, head_1.col);
					tronState.setCell((head_1.row-1), head_1.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_1.row][head_1.col+1]==-1){
					Cell pt = new Cell(head_1.row, head_1.col+1);
					tronState.setCell((head_1.row), head_1.col+1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else if((head_1.row == board.length-1)&&(head_1.col != 0)&&(head_1.col != board.length - 1)){
				if (board[head_1.row][head_1.col+1]== -1){
					Cell pt = new Cell(head_1.row, head_1.col+1);
					tronState.setCell((head_1.row), head_1.col+1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_1.row][head_1.col-1]==-1){
					Cell pt = new Cell(head_1.row, head_1.col-1);
					tronState.setCell((head_1.row), head_1.col-1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_1.row-1][head_1.col]==-1){
					Cell pt = new Cell(head_1.row-1, head_1.col);
					tronState.setCell((head_1.row-1), head_1.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else if((head_1.row == board.length-1)&&(head_1.col == board.length - 1)){
				if (board[head_1.row-1][head_1.col]==-1){
					Cell pt = new Cell(head_1.row-1, head_1.col);
					tronState.setCell((head_1.row-1), head_1.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_1.row][head_1.col-1]==-1){
					Cell pt = new Cell(head_1.row, head_1.col-1);
					tronState.setCell((head_1.row), head_1.col-1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}else{
				if (board[head_1.row+1][head_1.col]== -1){
					//				if (isEmpty(row+1, col, tronState)){

					Cell pt = new Cell(head_1.row+1, head_1.col);
					tronState.setCell((head_1.row+1), head_1.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_1.row-1][head_1.col]==-1){
					Cell pt = new Cell(head_1.row-1, head_1.col);
					tronState.setCell((head_1.row-1), head_1.col, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_1.row][head_1.col+1]==-1){
					Cell pt = new Cell(head_1.row, head_1.col+1);
					tronState.setCell((head_1.row), head_1.col+1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}else if(board[head_1.row][head_1.col-1]==-1){
					Cell pt = new Cell(head_1.row, head_1.col-1);
					tronState.setCell((head_1.row), head_1.col-1, playerNumber);
					tronState.setHead(playerNumber, pt);
				}
			}	
		} 
	}
}

