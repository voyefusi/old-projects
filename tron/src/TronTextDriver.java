
import game.Game;
import game.GameDef;

/**
 * A simple textual driver of the Tron game.
 * April 2010
 * @author Ben Bederson
 */
public class TronTextDriver {
	static public void main(String[] args) {
		// STUDENTS - you can modify BOARD_DIM for testingkl.
		final int BOARD_DIM = 6;
		
		Game tron0 = Utils.createSoln();
		tron0.setPlayerNum(0);
		Game tron1 = Utils.createSoln();
		tron1.setPlayerNum(1);

		TronDef tronDef = new TronDef();
		TronState state = (TronState)tronDef.initialize(BOARD_DIM);
		state.addObstacles(2);
		int playerNum = 0;
		GameDef.gameStatus gameStatus;

		System.out.println("Tron");
		System.out.println("----");
		System.out.println();
		System.out.println(state.dump());
		System.out.println();

		int turnNum = 1;
		do {
			TronState prevState = (TronState)state.clone();
			if (playerNum == 0) {
				tron0.move(state);
			} else {
				tron1.move(state);
			}

			if (tronDef.validMove(playerNum, prevState, state)) {
				System.out.println("Player #" + playerNum + " (turn #" + turnNum + ")");
				System.out.println("----------");
				System.out.println(state.dump());
				System.out.println();
			} else {
				System.out.println("Player #" + playerNum + " lost due to invalid move:");
				System.out.println(tronDef.getInvalidMoveDescription());
				System.out.println();
				gameStatus = tronDef.getGameStatus(state);
			}

			gameStatus = tronDef.getGameStatus(state);
			if (!tronDef.playerGoesAgain()) {
				playerNum = (playerNum + 1) % 2;
			}
			turnNum++;
		} while (gameStatus == GameDef.gameStatus.ONGOING);

		switch (gameStatus) {
		case TIE:
			System.out.println("Tie");
			break;
		case OVER:
			int winner = tronDef.getWinningPlayerNum();
			System.out.println("Player #" + winner + " won!");
			break;
		}
	}
}