package main;

import java.io.File;
import java.util.HashMap;

import classes.Action;
import classes.ActionUtilPair;
import classes.Helpers;
import classes.Reward;
import classes.State;
import classes.Utility;

/**
 * Main class with value and policy iteration.
 * @author Sherry Lau Geok Teng
 *
 */
public class Main {

	public static final double DISCOUNT_FACTOR = 0.99;
	public static final double CONVERGENCE_CRITERIA = 0.175;
	public static final int BELLMAN_UPDATE_COUNT = 10;

	// Output file name
	public static final String FILE_NAME = "output.txt";

	// Given maze environment
	public static final Reward[][] maze1 = {
			{ Reward.GREEN, Reward.WHITE, Reward.WHITE, Reward.WHITE, Reward.WHITE, Reward.WHITE },
			{ Reward.WALLE, Reward.BROWN, Reward.WHITE, Reward.WHITE, Reward.WALLE, Reward.WHITE },
			{ Reward.GREEN, Reward.WHITE, Reward.BROWN, Reward.WHITE, Reward.WALLE, Reward.WHITE },
			{ Reward.WHITE, Reward.GREEN, Reward.WHITE, Reward.BROWN, Reward.WALLE, Reward.WHITE },
			{ Reward.WHITE, Reward.WALLE, Reward.GREEN, Reward.WHITE, Reward.BROWN, Reward.WHITE },
			{ Reward.GREEN, Reward.BROWN, Reward.WHITE, Reward.GREEN, Reward.WHITE, Reward.WHITE } };

	/**
	 * 
	 * @param maze
	 */
	public static void valueIteration(Reward[][] maze) {
		HashMap<State, ActionUtilPair> curUtilFunc = new HashMap<State, ActionUtilPair>();
		HashMap<State, ActionUtilPair> newUtilFunc = new HashMap<State, ActionUtilPair>();

		for (int col = 0; col < maze.length; col++) {
			for (int row = 0; row < maze[col].length; row++) {
				if (maze[col][row] == Reward.WALLE)
					continue;
				newUtilFunc.put(new State(col, row), new ActionUtilPair());
			}
		}

		int count = 0;
		double maxDiff = Double.MIN_VALUE;
		double minDiff = Double.MAX_VALUE;
		do {
			maxDiff = Double.MIN_VALUE;
			minDiff = Double.MAX_VALUE;
			curUtilFunc.putAll(newUtilFunc);
			for (HashMap.Entry<State, ActionUtilPair> entry : curUtilFunc.entrySet()) {
				State s = entry.getKey();
				ActionUtilPair result = Utility.getBestAction(maze, curUtilFunc, s);
				newUtilFunc.put(s, result);
				ActionUtilPair newUtil = newUtilFunc.get(s);
				ActionUtilPair curUtil = curUtilFunc.get(s);
				double diff = Math.abs(newUtil.getUtil() - curUtil.getUtil());
				maxDiff = Math.max(maxDiff, diff);
				minDiff = Math.min(minDiff, diff);
			}
			output("\r\nValue Iteration " + count + ":\r\n");
			output(Helpers.aupMapToStr(newUtilFunc, maze.length, maze[0].length));
			count++;
		} while ((maxDiff - minDiff) >= Main.CONVERGENCE_CRITERIA);

		output("\r\nUtilities of all states:\r\n========================\r\n");
		for (HashMap.Entry<State, ActionUtilPair> entry : newUtilFunc.entrySet()) {
			output(entry.getKey().toString() + " " + entry.getValue().getUtil() + "\r\n");
		}
	}

	public static void policyIteration(Reward[][] maze) {
		HashMap<State, ActionUtilPair> curUtilFunc = new HashMap<State, ActionUtilPair>();

		for (int col = 0; col < maze.length; col++) {
			for (int row = 0; row < maze[col].length; row++) {
				if (maze[col][row] == Reward.WALLE)
					continue;
				curUtilFunc.put(new State(col, row), new ActionUtilPair(Action.DOWN, 0.0));
			}
		}
		output("\r\nInitial Policy Iteration State:\r\n");
		output(Helpers.aupMapToStr(curUtilFunc, maze.length, maze[0].length));

		int count = 0;
		boolean isUpdated = false;
		do {
			isUpdated = false;
			HashMap<State, ActionUtilPair> updatedUtilFunc = new HashMap<State, ActionUtilPair>();
			updatedUtilFunc.putAll(curUtilFunc);
			for (int i = 0; i < BELLMAN_UPDATE_COUNT; i++) {
				for (HashMap.Entry<State, ActionUtilPair> entry : curUtilFunc.entrySet()) {
					State s = entry.getKey();
					ActionUtilPair p = entry.getValue();
					ActionUtilPair updatedP = p.clone();
					updatedP.setUtil(Utility.getActionUtility(maze, curUtilFunc, s, updatedP.getAct()));
					updatedUtilFunc.put(s, updatedP);
				}
				curUtilFunc.putAll(updatedUtilFunc);
			}
			for (HashMap.Entry<State, ActionUtilPair> entry : curUtilFunc.entrySet()) {
				State s = entry.getKey();
				ActionUtilPair bestActionUtil = Utility.getBestAction(maze, curUtilFunc, entry.getKey());
				if (entry.getValue().getAct() != bestActionUtil.getAct()) {
					curUtilFunc.put(s, bestActionUtil);
					isUpdated = true;
				}
			}
			output("\r\nPolicy Iteration " + count + ":\r\n");
			output(Helpers.aupMapToStr(curUtilFunc, maze.length, maze[0].length));
			count++;
		} while (isUpdated);
	}

	public static void output(String content) {
		System.out.print(content);
		Helpers.writeToFile(content);
	}

	public static void main(String[] args) {
		File file = new File(FILE_NAME);
		try {
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[][] mazeStr = new String[maze1.length][maze1[0].length];
		for (int i = 0; i < maze1.length; i++) {
			for (int j = 0; j < maze1[i].length; j++) {
				if (maze1[i][j] == Reward.WALLE)
					mazeStr[i][j] = null;
				else
					mazeStr[i][j] = String.format("%.2f", maze1[i][j].value());
			}
		}
		output("=========== Default Maze Environment ===========\r\n");
		output(Helpers.arrayToStr(mazeStr));
		output("\r\n================================ Performing Value Iteration ================================");
		valueIteration(maze1);
		output("\r\n============================= Performing Policy Iteration =============================");
		policyIteration(maze1);
	}
}
