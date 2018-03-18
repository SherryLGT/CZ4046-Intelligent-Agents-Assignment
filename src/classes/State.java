package classes;

public class State {
	private int locX;
	private int locY;

	public State(int locX, int locY) {
		this.locX = locX;
		this.locY = locY;
	}

	public int getLocX() {
		return locX;
	}

	public void setLocX(int locX) {
		this.locX = locX;
	}

	public int getLocY() {
		return locY;
	}

	public void setLocY(int locY) {
		this.locY = locY;
	}

	public void move(Action act, CellType[][] maze) {
		switch (act) {
		case UP:
			if (locY > 0 && maze[locX][locY - 1] != CellType.WALLE)
				locY -= 1;
			break;
		case DOWN:
			if (locY < maze[0].length - 1 && maze[locX][locY + 1] != CellType.WALLE)
				locY += 1;
			break;
		case LEFT:
			if (locX > 0 && maze[locX - 1][locY] != CellType.WALLE)
				locX -= 1;
			break;
		case RIGHT:
			if (locX < maze.length - 1 && maze[locX + 1][locY] != CellType.WALLE)
				locX += 1;
			break;
		}
	}
}
