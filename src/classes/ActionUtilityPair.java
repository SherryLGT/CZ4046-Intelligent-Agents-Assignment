package classes;

public class ActionUtilityPair {
	private Action action;
	private double utility;

	public ActionUtilityPair(Action action, double utility) {
		this.action = action;
		this.utility = utility;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public double getUtility() {
		return utility;
	}

	public void setUtility(double utility) {
		this.utility = utility;
	}
}
