package tron;

import java.util.ArrayList;

/**
 * An interface that specifies an obstacle in the Tron game.
 * April 2010
 * @author Ben Bederson
 */
public interface TronObstacle {
	/**
	 * Returns a list of cells that represent this obstacle.
	 * @return list of cells in obstacle.
	 */
	ArrayList<Cell> getCells();
}