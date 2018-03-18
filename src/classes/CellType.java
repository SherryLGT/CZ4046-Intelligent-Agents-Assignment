package classes;

public enum CellType {
	WHITE(-0.04), GREEN(1.00), BROWN(-1.00), WALLE(0.00);

	private final double cellValue;

	private CellType(double value) {
		cellValue = value;
	}

	public double value() {
		return cellValue;
	}
}
