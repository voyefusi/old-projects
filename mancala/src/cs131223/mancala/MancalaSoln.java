package cs131223.mancala;

import mancala.MancalaStateInterface;
import game.GameState;

public class MancalaSoln implements game.Game{

	private int playerNum;	
	private boolean first;
	private int moveEnd;
	public String getPlayerName() {
		// Specifies the name of the player playing this game.
		return "SlikVik";
	}

	public void move(GameState state) {
		// Generates the next move for the game by modifying the game state.
		boolean checkForWin;
		int add = 0;
		MancalaStateInterface mancalaState = (MancalaStateInterface)state;
		int[] board = mancalaState.getBoard();
		if (playerNum == 0){
			int high = 0;
			for(int index = 0; index < 6; index++ ){
				if(board[index] > board[high]){
					high = index;
				}
			}

			moveStones(high, board);
			gayUnnecessaryRule(moveEnd, board);
		}else{
			if (playerNum == 1){
				int high = 7;
				for(int index = 7; index < 13; index++ ){
					if(board[index] > board[high]){
						high = index;
					}
				}

				moveStones(high, board);
				gayUnnecessaryRule(moveEnd, board);
				checkForWin = didYouWin(board);
				if (checkForWin == true){
					if (playerNum == 1){
						for(int index = 0; index < 6;index++){
							add += board[index];
							board[index] = 0;
						}
						board[13] += add;
					}
					if (playerNum == 0){
						for(int index = 12; index > 6;index--){ 
							add += board[index];
							board[index] = 0;
						}
						board[6] += add;

					}
				}
			}
		}
	}


	public void gayUnnecessaryRule(int moveEnds, int board[]){
		//If player lands in empty spot, the piece on the opposite side is moved...unnecessary rule lol =/
		if ((moveEnd == 0) && (board[0] == 1)){
			moveStones(12, board);
		}else if ((moveEnd == 1) && (board[1] == 1)){
			moveStones(11, board);
		}
		else if ((moveEnd == 2) && (board[2] == 1)){
			moveStones(10, board);
		}
		else if ((moveEnd == 3) && (board[3] == 1)){
			moveStones(9, board);
		}
		else if ((moveEnd == 4) && (board[4] == 1)){
			moveStones(8, board);
		}
		else if ((moveEnd == 5) && (board[5] == 1)){
			moveStones(7, board);
		}
		else if ((moveEnd == 7) && (board[7] == 1)){
			moveStones(5, board);
		}
		else if ((moveEnd == 8) && (board[8] == 1)){
			moveStones(4, board);
		}
		else if ((moveEnd == 9) && (board[9] == 1)){
			moveStones(3, board); 
		}
		else if ((moveEnd == 10) && (board[10] == 1)){
			moveStones(2, board); 
		}
		else if ((moveEnd == 11) && (board[11] == 1)){
			moveStones(1, board);
		}
		else if ((moveEnd == 12) && (board[12] == 1)){
			moveStones(0, board);
		}
	}

	public void moveStones(int fromWhere, int board[]){
		//Does the actual movement
		int movements;
		movements = board[fromWhere];
		board[fromWhere] = 0;
		for (int index = fromWhere + 1; movements > 0;  index++){
			if (playerNum == 0){

				if(index == 14){
					index = -1;
				}else{ if(index != 13){ 
					board[index]+=1;
					movements--;
					moveEnd=index;
				}
				}
			}

			if (playerNum == 1){
				if (index == 14){
					index =-1;
				}
				else{ if(index != 6){ 
					board[index]+=1;
					movements--;
					moveEnd=index;
				}
				}
			}
			moveEnd = index;
		}
	}
	public void setPlayerNum(int arg0) {
		// Lets the game know which player number they are (always either 0 or 1)
		playerNum = arg0;
		if (playerNum == 0){
			first = true;
		}else if(playerNum == 1){
			first = false;
		}
	}

	public boolean didYouWin(int board[]){
		//Checks if a player no longer has any stones in his or her 6 pits. 
		boolean check = true;	
		if (playerNum == 0){
			for(int index = 0; index < 6;index++){
				if(board[index] != 0){
					check = false;
				}
			}
		}
		if (playerNum == 1){
			for(int index = 12; index > 6;index--){
				if(board[index] != 0){
					check = false;
				}
			}
		}

		return check;
	}
}
