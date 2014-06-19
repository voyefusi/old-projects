import game.Game;
import game.GameDef;
import game.GameState;

public class TicTacToeDriver {
	static public void main(String[] args) {
		Game ttt0 = Utils.createSoln();
		ttt0.setPlayerNum(0);
		Game ttt1 = Utils.createSoln();
		ttt1.setPlayerNum(1);
		
		TicTacToeDef gameDef = new TicTacToeDef();
		GameState state = gameDef.initialize();
		int playerNum = 0;
		GameDef.gameStatus gameStatus;
		
		System.out.println("Tic Tac Toe");
		System.out.println("-----------");
		System.out.println();
		System.out.println(state.dump());
		System.out.println();
		
		do {
			GameState prevState = Utils.cloneState(state);
			if (playerNum == 0) {
				ttt0.move(state);
			} else {
				ttt1.move(state);
			}
			if (!gameDef.validMove(playerNum, prevState, state)) {
				throw new RuntimeException("Invalid move");
			}
			
			System.out.println("Player #" + playerNum + ":");
			System.out.println("----------");
			System.out.println(state.dump());
			
			gameStatus = gameDef.getGameStatus(state);
			
			playerNum = (playerNum + 1) % 2;
		} while (gameStatus == GameDef.gameStatus.ONGOING);
		
		switch (gameStatus) {
		case TIE:
			System.out.println("Tie");
			break;
		case OVER:
			int winner = gameDef.getWinningPlayerNum();
			System.out.println("Player #" + winner + " won!");
			break;
		}
	}
}