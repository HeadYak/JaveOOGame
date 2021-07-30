package unsw.loopmania;

import java.util.ArrayList;

public class GoalAnd extends GoalComposite{

    private ArrayList<Goal> goals;

    
    public GoalAnd() {
        goals = new ArrayList<>();
    }

    /**
     * Returns true if all subgoals are completed
     */
    @Override
    public Boolean completed() {
        for (Goal g : goals) {
            if (!g.completed()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void add(Goal g) {
        goals.add(g);
    }
}