

import game.GameState;

import java.util.ArrayList;

import tron.Cell;
import tron.TronConstants;
import tron.TronObstacle;
import tron.TronRectObstacle;
import tron.TronStateInterface;

/**
 * This represents the state of the Tron board.
 * April 2010
 * @author Ben Bederson
 */
public class TronState implements GameState, TronStateInterface {
	private int[][] board;  // Cells are TronConstants.CELL_EMPTY if unplayed, or player number if played 
	private Cell[] heads;  // Array of head positions (for each player)
	private ArrayList<TronObstacle> obstacles;  // List of obstacles

	/**
	 * Constructor that initializes the board of size (dim x dim) to being unplayed 
	 * (i.e., each cell should be empty.)
	 * @param dim the dimensions of each side of the square board
	 */
	public TronState(int dim) {
		board = new int[dim][dim];
		heads = new Cell[2];
		heads[0] = new Cell();
		heads[1] = new Cell();
		obstacles = new ArrayList<TronObstacle>();

		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				board[row][col] = TronConstants.CELL_EMPTY;
			}
		}
	}

	/**
	 * Copy Constructor - does deep copy.
	 * @param otherState the state to copy
	 */
	public TronState(TronState otherState) {
		int[][] otherBoard = otherState.getBoard();
		int dim = otherBoard.length;
		board = new int[dim][dim];
		heads = new Cell[2];
		heads[0] = new Cell();
		heads[1] = new Cell();
		obstacles = new ArrayList<TronObstacle>();

		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				this.board[row][col] = otherBoard[row][col];
			}
		}
		
		for (int playerNum = 0; playerNum < 2; playerNum++) {
			heads[playerNum] = otherState.getHead(playerNum);
		}
	}
	
	/**
	 * Returns a duplicate of this state
	 * @return the new state object
	 */
	public Object clone() {
		GameState state = new TronState(this);
		return state;
	}

	/**
	 * Determines if the specified instance TronState has the same contents as this instance.
	 * @param otherState The other instance to check equality with
	 * @return true if the specified parameter is equal to this, or false otherwise
	 */
	public boolean equals(TronStateInterface otherState) {
		int dim = board.length;
		int[][] otherBoard = otherState.getBoard();

		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				if (board[row][col] != otherBoard[row][col]) {
					return false;
				}
			}
		}

		for (int playerNum = 0; playerNum < 2; playerNum++) {
			if (!heads[playerNum].equals(otherState.getHead(playerNum))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns a copy of the array representing the board of the game.
	 * Cells are TronConstants.CELL_EMPTY if unplayed, or player number if played.
	 * <p>
	 * Note that the board contains <b>only</b> an indication of where players have been.  
	 * It does not contain the head position of the players, nor does it contain 
	 * the obstacles.  Your program will have to use all three elements of the state 
	 * to create a correct solution.
	 * @see getHead
	 * @see getObstacles
	 * @return the board
	 */
	public int[][] getBoard() {
		int dim = board.length;
		int[][] copy = new int[dim][dim];
		
		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				copy[row][col] = board[row][col];
			}
		}
		
		return copy;
	}

	/**
	 * Sets the specified cell of the board to the specified value. 
	 * The cell is specified by the row and col parameters
	 * @param row is row of the cell to set
	 * @param col is column of the cell to set
	 * @param value is new value of the cell
	 */
	public void setCell(int row, int col, int value) {
		board[row][col] = value;
	}
	
	/**
	 * Returns the head for the specified player
	 * @return the head row
	 */
	public Cell getHead(int playerNum) {
		return new Cell(heads[playerNum]);
	}

	/**
	 * Sets the head for the specified player 
	 */
	public void setHead(int playerNum, Cell head) {
		heads[playerNum].row = head.row;
		heads[playerNum].col = head.col;
	}
	
	/**
	 * Returns the obstacles in this board.
	 * @return the list of obstacles
	 */
	public ArrayList<TronObstacle> getObstacles() {
		return obstacles;
	}
	
	void addObstacles(int numObstacles) {
		int dim = board.length;
		int maxObstacleDim = dim / 2;
		for (int i=0; i<numObstacles; i++) {
			// Put obstacles on location that doesn't overlap heads
			// Try a few times but eventually give up
			int x = 0;
			int y = 0;
			int width = 0;
			int height = 0;
			boolean placed = false;
			int tryNum = 0;
			do {
				x = (int)(dim * Math.random());
				y = (int)(dim * Math.random());
				width = 1 + (int)(maxObstacleDim * Math.random());
				height = 1 + (int)(maxObstacleDim * Math.random());
				if ((x + width) > dim) {
					width = dim - x;
				}
				if ((y + height) > dim) {
					height = dim - y;
				}
				if (!rectContainsPoint(x, y, width, height, heads[0])
					&& !rectContainsPoint(x, y, width, height, heads[1])) {
					placed = true;
				}
				tryNum++;
			} while (!placed && (tryNum < 10));
			if (placed) {
				TronRectObstacle rect = new TronRectObstacle(x, y, width, height);
				addObstacle(rect);
			}
		}
	}
	
	public boolean rectContainsPoint(int x, int y, int width, int height, Cell pt) {
		if ((pt.col >= x) && (pt.col < (x + width))
			&& (pt.row >= y) && (pt.row < (y + height))) {
			return true;
		}
		return false;		
	}

	void addObstacle(TronObstacle obstacle) {
		obstacles.add(obstacle);
	}
	
	/**
	 * Generates and returns a multi-line string representing a human-readable pretty version of the board.
	 * This is like toString() in that it should return a String that represents the board.  But while toString()
	 * generates a single line in a format more easily understandable by computers, this string contains
	 * multiple lines designed to be readable by humans.  In particular,
	 * the textual driver program uses this method to display the game state after each player's move.
	 * <p>
	 * The string must have multiple lines separated by '\n'.  There must be one line per row of the board,
	 * and each line must have exactly the same number characters as the width of the board.  In other words,
	 * the string should be display as a grid of characters, one per cell in the board.  The characters should be:
	 * <ul>
	 * <li> '.' - if unplayed
	 * <li> 'x' - if obstacle
	 * <li> '*' - if head
	 * <li> '0' or '1' - representing which player has played that cell
	 * </ul> 
	 * <p>
	 * Unlike in the previous project, you do not have any flexibility in what this method generates - it 
	 * must create a string exactly as described above. 
	 * @return the string that represents the board
	 */
	public String dump() {
		return Utils.callDump(this);
	}

	/**
	 * Generates and returns a single line string that must contain exactly (dim*dim) string representations
	 * of integers separated by commas (with no spaces), where dim is the dimension of the board.  
	 * The integers must represent the cells of the board in natural grid order, one row
	 * at a time starting at the top, with each row going from left to right.  The values you should use
	 * in this string are as follow.  Empty cells must converted into TronConstants.STATE_EMPTY, 
	 * head cells must be converted into TronConstants.STATE_HEAD, 
	 * obstacles must be converted into TronConstants.STATE_OBSTACLE, and other spaces 
	 * must be converted into the player number in that cell.
	 * <p>
	 * A simple example would be as follows for a 4x4 board with one obstacle in the lower-left, and two
	 * short traces ending in heads in the upper-left and upper-right corners, respectively:
	 * "-2,-1,-1,-2,0,-1,-1,1,0,-1,-1,1,-3,-1,-1,-1".
	 * <p>
	 * Note that toString() is used by the tournament engine, so it won't have any impact anywhere
	 * except there (and in the release tests.)
	 */
	public String toString() {
		return Utils.callToString(this);
	}
}