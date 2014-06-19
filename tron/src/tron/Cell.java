package tron;

/**
 * A simple class that represents the position of a cell on the board
 * with a row and column.
 * @author bederson
 *
 */
public class Cell {
	public int row;
	public int col;
	
	/**
	 * Creates a cell with no position.  The row and column are 
	 * initialized to TronConstants.CELL_EMPTY;
	 */
	public Cell() {
		row = TronConstants.CELL_EMPTY;
		col = TronConstants.CELL_EMPTY;
	}
	
	/**
	 * Creates a cell position given a row and column
	 * @param row the row
	 * @param col the column
	 */
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Copy constructor
	 * @param otherCell the cell to copy
	 */
	public Cell(Cell otherCell) {
		row = otherCell.row;
		col = otherCell.col;
	}
	
	/**
	 * Determines if the parameter is a cell that has the same values as this cell.
	 * @return true if the parameter's content is equal to this.
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Cell) {
			Cell cell = (Cell)obj;
			if ((cell.row == row) && (cell.col == col)) {
				return true;
			}
		}
		
		return false;
	}
}