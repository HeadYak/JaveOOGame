package unsw.loopmania;

import java.util.ArrayList;

public class GoalOr extends GoalComposite{

    private ArrayList<Goal> goals;

    
    public GoalOr() {
        goals = new ArrayList<>();
    }

    /**
     * Returns true if one goal is completed
     */
    @Override
    public Boolean completed() {
        for (Goal g : goals) {
            if (g.completed()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void add(Goal g) {
        goals.add(g);
    }
}
