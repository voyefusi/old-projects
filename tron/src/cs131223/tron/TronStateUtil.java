package cs131223.tron;

import java.util.ArrayList;

import tron.Cell;
import tron.TronObstacle;
import tron.TronStateInterface;

public class TronStateUtil {

	public static String dump(TronStateInterface state) {
		// Generates and returns a multi-line string representing a
		// human-readable pretty version of the board.
		int board[][] = state.getBoard();
		char temp[][] = new char[board.length][board.length];
		String bigDump = "";

		ArrayList<TronObstacle> obstacle = state.getObstacles();
		ArrayList<Cell> cellList = new ArrayList<Cell>();
		Cell head_0 = new Cell();
		Cell head_1 = new Cell();
		head_0 = state.getHead(0);
		head_1 = state.getHead(1);

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if ((board[row][col] == 0)){
					//'.' - if unplayed 'x' - if obstacle '*' - if head '0' or '1' -	
					temp[row][col] = '0';
				}
				if ((board[row][col] == 1)){
					//'.' - if unplayed 'x' - if obstacle '*' - if head '0' or '1' -		
					temp[row][col] = '1';
				}
				if ((board[row][col] == -1)){
					//'.' - if unplayed 'x' - if obstacle '*' - if head '0' or '1' -	
					temp[row][col] = '.';
				}
			}

		}

		temp[head_0.row][head_0.col] = '*';
		temp[head_1.row][head_1.col] = '*';

		for(TronObstacle tronny : obstacle){
			cellList.addAll(tronny.getCells());
		}

		for(Cell c: cellList){
			temp[c.row][c.col]='x';
			board[c.row][c.col]= -3;
		}

		for (int row = 0; row < temp.length; row++) {
			for (int col = 0; col < temp.length; col++) {
				bigDump += temp[row][col];
			}
			if (row != board.length-1){
				bigDump+="\n";
			}
		}
		return bigDump;
	}

	public static String toString(TronStateInterface state) {
		//Generates and returns a single line string that must contain exactly 
		//(dim*dim) string representations of integers separated by commas
		int board[][] = state.getBoard();
		String temp[][] = new String[board.length][board.length];
		String longPiss = "";

		ArrayList<TronObstacle> obstacle = state.getObstacles();
		ArrayList<Cell> cellList = new ArrayList<Cell>();
		Cell head_0 = new Cell();
		Cell head_1 = new Cell();
		head_0 = state.getHead(0);
		head_1 = state.getHead(1);

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if ((board[row][col] == 0)){
					temp[row][col] = "0";
				}
				if ((board[row][col] == 1)){
					temp[row][col] = "1";
				}
				if ((board[row][col] == -1)){
					temp[row][col] = "-1";
				}		
			}
		}
		temp[head_0.row][head_0.col] = "-2";
		temp[head_1.row][head_1.col] = "-2";

		for(TronObstacle tronny : obstacle){
			cellList.addAll(tronny.getCells());
		}

		for(Cell c: cellList){
			temp[c.row][c.col]="-3";
		}

		for (int row = 0; row < temp.length; row++) {
			for (int col = 0; col < temp.length; col++) {
				if((col != temp.length - 1)||((row != temp.length - 1))){
					longPiss += temp[row][col] + ",";
				}else{
					longPiss += temp[row][col];
				}
			}
		}
		return longPiss;	
	}
}