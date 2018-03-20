package classes;

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
