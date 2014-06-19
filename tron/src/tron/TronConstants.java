package tron;

/**
 * Some useful constants for the Tron game.
 * April 2010
 * @author Ben Bederson
 */
public class TronConstants {
	/**
	 * Value of a cell when it is empty.  This is used by the int[][] board array
	 * to represent that a cell has not been played.
	 */
	static final public int CELL_EMPTY = -1;
	
	/**
	 * Required value of an element of the string generated by TronStateUtil.toString() 
	 * to indicate that a cell is empty.
	 */
	static final public int STATE_EMPTY = -1;
	
	/**
	 * Required value of an element of the string generated by TronStateUtil.toString() 
	 * to indicate that a cell is the head of a trace.
	 */
	static final public int STATE_HEAD = -2;
	
	/**
	 * Required value of an element of the string generated by TronStateUtil.toString() 
	 * to indicate that a cell is an obstacle.
	 */
	static final public int STATE_OBSTACLE = -3;
}