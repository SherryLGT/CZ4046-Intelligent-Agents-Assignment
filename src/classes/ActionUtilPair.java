package classes;

/**
 * Encapsulate Action and Utility (double) value.
 * @author Sherry Lau Geok Teng
 *
 */
public class ActionUtilPair {

	private Action act;
	private double util;

	public ActionUtilPair() {
	}

	public ActionUtilPair(Action act, double util) {
		this.act = act;
		this.util = util;
	}

	public Action getAct() {
		return act;
	}

	public void setAct(Action act) {
		this.act = act;
	}

	public double getUtil() {
		return util;
	}

	public void setUtil(double util) {
		this.util = util;
	}

	/**
	 * Creates and return a copy of ActionUtilPair object with the Action and Utility (double) value
	 */
	@Override
	public ActionUtilPair clone() {
		return new ActionUtilPair(act, util);
	}

}
