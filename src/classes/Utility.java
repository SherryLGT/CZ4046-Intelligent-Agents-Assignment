package classes;

import java.util.HashMap;

import main.Main;

public class Utility {

	// Intended outcome's probability
	private static final double PROB_INTENDED = 0.8;
	private static final double PROB_RIGHT_ANGLED = 0.1;

	// Agent's actions are trying to achieve (can be constructed by observing
	// agent's preferences)
	// U(s)

	// Expected Utility = Utility function + Outcome Probabilities
	// EU(a|e)
	public void expectedUtility() {

	}

	// Sum of rewards of individual states
	public void utilitiesOfStateSeq(State[][] states) {

	}

	// Expected utility obtained by policy starting in state
	public void utilitiesOfState(State s) {

	}

	public static ActionUtilPair getBestAction(Reward[][] maze, HashMap<State, ActionUtilPair> curUtilFunc, State s) {
		double upUtil = getActionUtility(maze, curUtilFunc, s, Action.UP);
		double max = upUtil;
		Action bestAct = Action.UP;
		double downUtil = getActionUtility(maze, curUtilFunc, s, Action.DOWN);
		if (downUtil > max) {
			max = downUtil;
			bestAct = Action.DOWN;
		}
		double leftUtil = getActionUtility(maze, curUtilFunc, s, Action.LEFT);
		if (leftUtil > max) {
			max = leftUtil;
			bestAct = Action.LEFT;
		}
		double rightUtil = getActionUtility(maze, curUtilFunc, s, Action.RIGHT);
		if (rightUtil > max) {
			max = rightUtil;
			bestAct = Action.RIGHT;
		}
		return new ActionUtilPair(bestAct, max);
	}

	public static double getActionUtility(Reward[][] maze, HashMap<State, ActionUtilPair> curUtilFunc, State s,
			Action a) {
		Action leftAngled, rightAngled;
		switch (a) {
		case UP:
			leftAngled = Action.LEFT;
			rightAngled = Action.RIGHT;
			break;
		case DOWN:
			leftAngled = Action.RIGHT;
			rightAngled = Action.LEFT;
			break;
		case LEFT:
			leftAngled = Action.DOWN;
			rightAngled = Action.UP;
			break;
		case RIGHT:
			leftAngled = Action.UP;
			rightAngled = Action.DOWN;
			break;
		default:
			leftAngled = null;
			rightAngled = null;
		}
		State intendedS = s.clone();
		State leftAngledS = s.clone();
		State rightAngledS = s.clone();
		intendedS.move(maze, a);
		leftAngledS.move(maze, leftAngled);
		rightAngledS.move(maze, rightAngled);
		double balance = curUtilFunc.get(intendedS).getUtil() * PROB_INTENDED
				+ curUtilFunc.get(leftAngledS).getUtil() * PROB_RIGHT_ANGLED
				+ curUtilFunc.get(rightAngledS).getUtil() * PROB_RIGHT_ANGLED;
		return balance * Main.DISCOUNT_FACTOR + maze[s.getCol()][s.getRow()].value();
	}
}
