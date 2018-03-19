package main;

import java.util.HashMap;

import classes.Reward;
import classes.State;
import classes.Utility;

public class Main {

	public static final double DISCOUNT_FACTOR = 0.99;
	public static final double CONVERGENCE_CRITERIA = 0.175;

	public static final Reward[][] maze = {
			{ Reward.GREEN, Reward.WHITE, Reward.WHITE, Reward.WHITE, Reward.WHITE, Reward.WHITE },
			{ Reward.WALLE, Reward.BROWN, Reward.WHITE, Reward.WHITE, Reward.WALLE, Reward.WHITE },
			{ Reward.GREEN, Reward.WHITE, Reward.BROWN, Reward.WHITE, Reward.WALLE, Reward.WHITE },
			{ Reward.WHITE, Reward.GREEN, Reward.WHITE, Reward.BROWN, Reward.WALLE, Reward.WHITE },
			{ Reward.WHITE, Reward.WALLE, Reward.GREEN, Reward.WHITE, Reward.BROWN, Reward.WHITE },
			{ Reward.GREEN, Reward.BROWN, Reward.WHITE, Reward.GREEN, Reward.WHITE, Reward.WHITE } };

	public static void ValueIteration() {
		HashMap<State, Double> curUtilFunc = new HashMap<State, Double>();
		HashMap<State, Double> newUtilFunc = new HashMap<State, Double>();

		for (int col = 0; col < Main.maze.length; col++) {
			for (int row = 0; row < Main.maze[col].length; row++) {
				if (Main.maze[col][row] == Reward.WALLE)
					continue;
				newUtilFunc.put(new State(col, row), 0.0);
			}
		}

		double maxDiff = Double.MIN_VALUE;
		double minDiff = Double.MAX_VALUE;
		do {
			maxDiff = Double.MIN_VALUE;
			minDiff = Double.MAX_VALUE;
			curUtilFunc.clear();
			curUtilFunc.putAll(newUtilFunc);
			for (HashMap.Entry<State, Double> entry : curUtilFunc.entrySet()) {
				State s = entry.getKey();
				newUtilFunc.put(s, Utility.getBestAction(curUtilFunc, s).getUtil());
				double newUtil = newUtilFunc.get(s);
				double curUtil = curUtilFunc.get(s);
				double diff = Math.abs(newUtil - curUtil);
				maxDiff = Math.max(maxDiff, diff);
				minDiff = Math.min(minDiff, diff);
			}
		} while ((maxDiff - minDiff) >= Main.CONVERGENCE_CRITERIA);
		
		for (HashMap.Entry<State, Double> entry : newUtilFunc.entrySet()) {
			System.out.println(entry.getKey().getString() + " " + entry.getValue());
		}
	}
	
	public static void main(String[] args) {
		ValueIteration();
	}
}
