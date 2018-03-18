package classes;

public class Utility {

	// Transition model
	private static final double PROB_STRAIGHT = 0.8;
	private static final double PROB_LEFT = 0.1;
	private static final double PROB_RIGHT = 0.1;

	public static ActionUtilityPair bestAction(CellType[][] maze, int locX, int locY) {
		ActionUtilityPair pair;
		double upUtility = moveAttempt(maze, Action.UP, locX, locY);
		pair = new ActionUtilityPair(Action.UP, upUtility);
		double downUtility = moveAttempt(maze, Action.DOWN, locX, locY);
		if (pair.getUtility() < downUtility)
			pair = new ActionUtilityPair(Action.DOWN, downUtility);
		double leftUtility = moveAttempt(maze, Action.LEFT, locX, locY);
		if (pair.getUtility() < leftUtility)
			pair = new ActionUtilityPair(Action.LEFT, leftUtility);
		double rightUtility = moveAttempt(maze, Action.RIGHT, locX, locY);
		if (pair.getUtility() < rightUtility)
			pair = new ActionUtilityPair(Action.RIGHT, rightUtility);
		return pair;
	}

	public static double moveAttempt(CellType[][] maze, Action act, int locX, int locY) {
		Action left;
		Action right;

		switch (act) {
		case UP:
			left = Action.LEFT;
			right = Action.RIGHT;
			break;
		case DOWN:
			left = Action.RIGHT;
			right = Action.LEFT;
			break;
		case LEFT:
			left = Action.DOWN;
			right = Action.UP;
			break;
		case RIGHT:
			left = Action.UP;
			right = Action.DOWN;
			break;
		default:
			return 0;
		}

		return PROB_STRAIGHT * move(maze, act, locX, locY) + PROB_LEFT * move(maze, left, locX, locY)
				+ PROB_RIGHT * move(maze, right, locX, locY);
	}

	public static double move(CellType[][] maze, Action act, int locX, int locY) {
		State state = new State(locX, locY);
		state.move(act, maze);
		return maze[state.getLocX()][state.getLocY()].value();
	}
}
