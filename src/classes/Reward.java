package classes;

/**
 * Reward value for respective square.
 * @author Sherry Lau Geok Teng
 *
 */
public enum Reward {

	WHITE(-0.04), GREEN(1.00), BROWN(-1.00), WALLE(0.00);

	private final double value;

	private Reward(double value) {
		this.value = value;
	}

	public double value() {
		return value;
	}
}
