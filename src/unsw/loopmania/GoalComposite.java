package unsw.loopmania;

public class GoalComposite implements Goal {

	private Goal goals;
	
	/**
	 * A goal composite stores other goals 
	 * completed when 
	 * @param composition
	 */
	public GoalComposite() {
	}
	
	/**
	 * returns true if no goals are set. otherwise checks if all AND
	 * goals are completed or one Or goal is completed
	 */
	public Boolean completed() {
	    if (goals == null) return true;
	    return goals.completed();
	}
	
	public void add(Goal g) {
		goals = g;
	}

	@Override
    public String toString() {
		if (goals == null) {
			return "None";
		}
		return goals.toString();
    }
}
