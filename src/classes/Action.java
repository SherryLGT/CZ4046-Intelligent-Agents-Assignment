package classes;

/**
 * Direction of movement.
 * @author Sherry Lau Geok Teng
 * 
 */
public enum Action {

	UP('↑'), DOWN('↓'), LEFT('←'), RIGHT('→');

	private final char symbol;

	private Action(char symbol) {
		this.symbol = symbol;
	}

	public char symbol() {
		return symbol;
	}
}
