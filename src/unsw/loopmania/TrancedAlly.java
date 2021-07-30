package unsw.loopmania;

import unsw.loopmania.enemies.BasicEnemy;

public class TrancedAlly extends Ally {
    int tranceDuration;

    public TrancedAlly(PathPosition position) {
        super(position);
        tranceDuration = 5;
    }

    /**
     * Overridden method for attack to also subtract from tranceDuration
     */
    @Override
    public void attack(BasicEnemy enemy) {
        super.attack(enemy);
        tranceDuration -= 1;
    }
    
    
}
