package unsw.loopmania;

public class GoalManager {

    private GoalComposite goals;
    
    public GoalManager() {
        this.goals = new GoalComposite();
    }
    
    public GoalComposite getGoals() {
    	return goals;
    }

    /**
     * if goals/subgoals are completed then returns true
     */
    public Boolean update() {
        return goals.completed();
    }

    @Override
    public String toString() {
        return "Goals: " + goals.toString();
    }
}
