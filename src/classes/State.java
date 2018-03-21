package classes;

/**
 * Position in the maze grid (by column and row).
 * @author Sherry Lau Geok Teng
 *
 */
public class State {

	private int col;
	private int row;

	public State() {
	}

	public State(int col, int row) {
		this.col = col;
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Change position according to action direction as long as the resulting position is not a wall
	 * @param maze Maze environment
	 * @param a Action 
	 */
	public void move(Reward[][] maze, Action a) {
		switch (a) {
		case UP:
			if (row > 0 && maze[col][row - 1] != Reward.WALLE)
				row -= 1;
			break;
		case DOWN:
			if (row < maze[0].length - 1 && maze[col][row + 1] != Reward.WALLE)
				row += 1;
			break;
		case LEFT:
			if (col > 0 && maze[col - 1][row] != Reward.WALLE)
				col -= 1;
			break;
		case RIGHT:
			if (col < maze.length - 1 && maze[col + 1][row] != Reward.WALLE)
				col += 1;
			break;
		}
	}

	/**
	 * Creates and return a copy of State object with the column and row value
	 */
	@Override
	protected State clone() {
		return new State(this.col, this.row);
	}

	/**
	 * Format string to (col,row) format
	 */
	@Override
	public String toString() {
		return "(" + col + "," + row + ")";
	}

	/**
	 * Compares if the two objects has the same column and row
	 */
	@Override
	public boolean equals(Object obj) {
		State other = (State) obj;
		if (col == other.col && row == other.row)
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return col * 31 + row;
	}
}
