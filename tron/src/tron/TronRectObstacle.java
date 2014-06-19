package tron;

import java.util.ArrayList;

/**
 * A rectangular obstacle
 * April 2010
 * @author Ben Bederson
 */
public class TronRectObstacle implements TronObstacle {
	private int x;
	private int y;
	private int width;
	private int height;
	
	public TronRectObstacle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public ArrayList<Cell> getCells() {
		ArrayList<Cell> cells = new ArrayList<Cell>(width * height);
		
		for (int row = y; row < (y + height); row++) {
			for (int col = x; col < (x + width); col++) {
				cells.add(new Cell(row, col));
			}
		}
		
		return cells;
	}
}