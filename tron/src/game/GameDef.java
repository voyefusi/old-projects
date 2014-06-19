package game;


/**
 * Contest manager defines a game by implementing this interface
 * @author bederson
 * @see Game that a programmer implements for a particular solution to a game
 *
 */
public interface GameDef {
	public enum gameStatus { INVALID, TIE, ONGOING, OVER };
	
	/**
	 * Name of game which must match entry in database
	 * @return game name
	 */
	public String name();
	
	/**
	 * Initializes the game state to the starting position.
	 */
	public GameState initialize();
	
	/**
	 * Determines if the given game state represents a valid game
	 * @param playerNum the number of the player (starting at 0) whose move is being checked
	 * @param prevState the state of the game before the move
	 * @param currState the state of the game after the move
	 * @return true if the move was valid or false otherwise
	 */
	public boolean validMove(int playerNum, GameState prevState, GameState currState);
	
	/**
	 * Determines if the most recent player should go again (due to the rules of the game)
	 * based on the most recent call to validMove().
	 * @return
	 */
	public boolean playerGoesAgain();
	
	/**
	 * Determines if the given game state represents a game that is done
	 * @param state
	 * @return one of gameStates
	 */
	public gameStatus getGameStatus(GameState state);
	
	/**
	 * Return the player number who won the most recent game as determined by
	 * a recent call to gameStatus that returned GAME_OVER, or -1 if none.
	 * @return the winning player number (starting at 0)
	 */
	public int getWinningPlayerNum();
}