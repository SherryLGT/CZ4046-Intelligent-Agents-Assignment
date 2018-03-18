package main;

import classes.CellType;
import classes.State;

public class Main {

	private static final double DISCOUNT_FACTOR = 0.99;
	private static final double EPSILON = 0; // change this value
	private static final double CONVERGENCE_CRITERIA = EPSILON * ((1.00 - DISCOUNT_FACTOR) / DISCOUNT_FACTOR); 

	private static final CellType[][] maze = {
			{ CellType.GREEN, CellType.WHITE, CellType.WHITE, CellType.WHITE, CellType.WHITE, CellType.WHITE },
			{ CellType.WALLE, CellType.BROWN, CellType.WHITE, CellType.WHITE, CellType.WALLE, CellType.WHITE },
			{ CellType.GREEN, CellType.WHITE, CellType.BROWN, CellType.WHITE, CellType.WALLE, CellType.WHITE },
			{ CellType.WHITE, CellType.GREEN, CellType.WHITE, CellType.BROWN, CellType.WALLE, CellType.WHITE },
			{ CellType.WHITE, CellType.WALLE, CellType.GREEN, CellType.WHITE, CellType.BROWN, CellType.WHITE },
			{ CellType.GREEN, CellType.BROWN, CellType.WHITE, CellType.GREEN, CellType.WHITE, CellType.WHITE } };

	public static void main(String[] args) {

		State agent = new State(2, 3);
		
//		System.out.println("Starting State: (" + agent.getLocX() + "," + agent.getLocY() + ")");
//		for (int i = 0; i < 50; i++) {
//			ActionUtilityPair bestAction = Utility.bestAction(maze, agent.getLocX(), agent.getLocY());
//			System.out.println("Action: " + bestAction.getAction());
//			System.out.println("Utility: " + bestAction.getUtility());
//			agent.move(bestAction.getAction(), maze);
//			System.out.println("Current State: (" + agent.getLocX() + "," + agent.getLocY() + ")");
//		}

//		 for (int col = 0; col < maze[0].length; col++) {
//		 for (int row = 0; row < maze.length; row++) {
//		 System.out.print(maze[col][row].value() + " | ");
//		 }
//		 System.out.print("\n");
//		 }

	}
}
