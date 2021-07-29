package unsw.loopmania;

public class GoalGold extends GoalLeaf {
    private int gold;

    public GoalGold(LoopManiaWorld world, int gold) {
        super(world);
        this.gold = gold;
    }
    
    /**
     * Returns true if target quantity is reached
     */
    @Override
    public Boolean completed() {
        if (super.getWorld().getCharacter().getGold() >= gold) {
            return true;
        } else {
            return false;
        }
    }
}