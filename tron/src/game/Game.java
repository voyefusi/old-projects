package game;


/**
 * A programmer must implement this interface to define their game
 * which gets entered into a contest. 
 * @author bederson
 * @date 3/8/2010
 * @see GameDef that defines a particular game
 */
public interface Game {
	/**
	 * Specifies the name of the player playing this game.
	 * This is the name that will be identified publicly on the web,
	 * so this should be unique, friendly and anonymous (or not), as you choose.
	 * @return
	 */
	public String getPlayerName();
	
	/**
	 * Lets the game know which player number they are (always either 0 or 1).
	 * Called once before game play starts.
	 * @param playerNum
	 */
	public void setPlayerNum(int playerNum);
	
	/**
	 * Generate the next move for the game by modifying the game state
	 * @param state game state.
	 */
	public void move(GameState state);
}