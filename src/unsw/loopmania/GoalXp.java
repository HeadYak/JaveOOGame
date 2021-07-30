package unsw.loopmania;

public class GoalXp extends GoalLeaf {
    private int xp;

    public GoalXp(LoopManiaWorld world, int xp) {
        super(world);
        this.xp = xp;
    }
    
    /**
     * Returns true if goal number is reached
     */
    @Override
    public Boolean completed() {
        if (super.getWorld().getCharacter().getXp() >= xp) {
            return true;
        } else {
            return false;
        }
    }
}