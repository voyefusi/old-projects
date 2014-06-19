import game.Game;
import game.GameDef;
import game.GameState;

public class MancalaDriver {
	static public void main(String[] args) {
		Game mancala0 = Utils.createSoln();
		mancala0.setPlayerNum(0);
		Game mancala1 = Utils.createSoln();
		mancala1.setPlayerNum(1);

		MancalaDef gameDef = new MancalaDef();
		GameState state = gameDef.initialize();
		int playerNum = 0;
		GameDef.gameStatus gameStatus;

		System.out.println("Mancala");
		System.out.println("-------");
		System.out.println();
		System.out.println(state.dump());
		System.out.println();

		do {
			GameState prevState = Utils.cloneState(state);
			if (playerNum == 0) {
				mancala0.move(state);
			} else {
				mancala1.move(state);
			}
			if (!gameDef.validMove(playerNum, prevState, state)) {
				throw new RuntimeException("Invalid move");
			}

			System.out.println("Player #" + playerNum + ":");
			System.out.println("----------");
			System.out.println(state.dump());

			gameStatus = gameDef.getGameStatus(state);
			if (!gameDef.playerGoesAgain()) {
				playerNum = (playerNum + 1) % 2;
			}
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