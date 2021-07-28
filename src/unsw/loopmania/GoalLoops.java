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
        if (world.getLoops() >= loops) {
            return true;
        } else {
            return false;
        }
    }
}
