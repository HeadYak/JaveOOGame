package unsw.loopmania;

public class GoalLoops extends GoalLeaf {
    private int loops;

    public GoalLoops(LoopManiaWorld world, int loops) {
        super(world);
        this.loops = loops;
    }
    
    /**
     * Returns true if target quantity is reached
     */
    @Override
    public Boolean completed() {
        if (super.getWorld().getLoops() >= loops) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Complete " + loops + " Loops";
    }
}
