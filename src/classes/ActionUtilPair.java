package classes;

public class ActionUtilPair {

	private Action act;
	private double util;

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

}
