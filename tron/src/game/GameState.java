package game;



public interface GameState {
	/**
	 * Clone must return a new object of the same type as the current instance
	 * with the same state as the current instance.
	 * @return
	 */
	public Object clone();
	
	public String dump();
}