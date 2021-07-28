package unsw.loopmania;

public class GoalLeaf implements Goal {

	protected LoopManiaWorld world;
	
	/**
	 * Creates a goal componenet with a pointer to the world
	 * @param world
	 */
	public GoalLeaf(LoopManiaWorld world) {
		this.world = world;
	}
	
	/**
	 * Returns completeion status of tge goal
	 */
	public Boolean completed() {
	    return false;
	}

	public LoopManiaWorld getWorld() {
	    return world;
	}
}
