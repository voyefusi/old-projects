import game.Game;
import java.lang.reflect.Method;
import tron.TronStateInterface;

/**
 * Tron reflection utilities for calling student code in their own packages.
 * @author Ben Bederson
 * @date April 2010
 */
public class Utils {
	static public Game createSoln() {
		Game game = null;
		try {
			String fullName = StudentAcct.studentAcct + ".tron.TronSoln";
			game = (Game) Class.forName(fullName).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Trouble creating solution");
		}
		
		return game;
	}
	
	@SuppressWarnings("unchecked")
	static public String callDump(TronStateInterface state) {
		String result = null;
		
		try {
			String className = StudentAcct.studentAcct + ".tron.TronStateUtil";
			Class c = Class.forName(className);
			Method m = c.getMethod("dump", TronStateInterface.class);
			Object[] args = new Object[] {state};
			result = (String)m.invoke(null, (Object[])args);
		} catch (Exception e) {
			throw new RuntimeException("Trouble calling TronStateUtil.dump()");
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	static public String callToString(TronStateInterface state) {
		String result = null;
		
		try {
			String className = StudentAcct.studentAcct + ".tron.TronStateUtil";
			Class c = Class.forName(className);
			Method m = c.getMethod("toString", TronStateInterface.class);
			Object[] args = new Object[] {state};
			result = (String)m.invoke(null, (Object[])args);
		} catch (Exception e) {
			throw new RuntimeException("Trouble calling TronStateUtil.toString()");
		}

		return result;
	}

	static public String getPlayerName(Game soln) {
		String name = null;
		try {
			name = soln.getPlayerName();
		} catch (Exception e) {
			throw new RuntimeException("Trouble calling getPlayerName()");			
		}
		return name;
	}
}