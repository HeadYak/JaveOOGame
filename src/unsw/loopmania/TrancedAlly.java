package unsw.loopmania;

import unsw.loopmania.enemies.BasicEnemy;

public class TrancedAlly extends Ally {
    private int tranceDuration;
    private BasicEnemy originalBody;

    public TrancedAlly(PathPosition position, BasicEnemy originalBody) {
        super(position);
        tranceDuration = 5;
        this.originalBody = originalBody;
    }

    /**
     * Overridden method for attack to also subtract from tranceDuration
     */
    @Override
    public void attack(BasicEnemy enemy) {
        super.attack(enemy);
        tranceDuration -= 1;
    }

    public int getTranceDuration() {
        return tranceDuration;
    }

    /**
     * Get original body that trancedAlly used to be
     * @return the original body of tranced ally
     */
    public BasicEnemy getOriginalBody() {
        return originalBody;
    }
    
    
}
