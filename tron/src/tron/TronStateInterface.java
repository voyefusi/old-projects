package tron;

import java.util.ArrayList;

/**
 * This is the interface that your TronState class must implement.
 * April 2010
 * @author bederson
 */
public interface TronStateInterface {
	/**
	 * Returns a copy of the array representing the board of the game.
	 * Cells are -1 if unplayed, or player number if played
	 * @return the board
	 */
	int[][] getBoard();
	
	/**
	 * Sets the specified cell of the board to the specified value. 
	 * The cell is specified by the row and col parameters
	 * @param row is row of the cell to set
	 * @param col is column of the cell to set
	 * @param value is new value of the cell
	 */
	void setCell(int row, int col, int value);
	
	/**
	 * Returns the list of obstacles on the board.
	 * @see TronObstacle
	 * @return the list of obstacles
	 */
	ArrayList<TronObstacle> getObstacles();
	
	/**
	 * Returns the head of the specified player.
	 * @param playerNum the player number to get the head row of (always 0 or 1)
	 * @return the cell where the head is
	 */
	Cell getHead(int playerNum);

	/**
	 * Sets the head position for the specified player.
	 * @param playerNum the player number to get the head column of (always 0 or 1)
	 * @param head the position of the head
	 */
	void setHead(int playerNum, Cell head);	
}