package unsw.loopmania;

public class GoalComposite implements Goal {

	private Goal goals;
	
	/**
	 * A goal composite stores other goal composites or goal leaves and 
	 * this composite is only complete when all subgoals are completed and follow
	 * this composite's composition rule
	 * @param composition
	 */
	public GoalComposite() {
	}
	
	/**
	 * Uses the composite pattern to check all subgoals within this goal
	 * to return a boolean value of whether or not it is fulfilled.
	 * The composition of this class determines whether ALL (AND) goals need
	 * to be completed, or AT LEAST ONE (OR) goal needs to be completed
	 * 
	 * If there are no goals set, automatically return true
	 */
	public Boolean completed() {
	    if (goals == null) return true;
	    return goals.completed();
	}
	
	public void add(Goal g) {
		goals = g;
	}

}
