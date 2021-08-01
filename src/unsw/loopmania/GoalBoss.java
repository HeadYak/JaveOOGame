package unsw.loopmania;

public class GoalBoss extends GoalLeaf {

    public GoalBoss(LoopManiaWorld world) {
        super(world);
    }
    
    /**
     * Returns true if goal number is reached
     */
    @Override
    public Boolean completed() {
        return super.getWorld().getAllBossKilled();
    }

    @Override
    public String toString() {
        return "Kill All Bosses";
    }
}
