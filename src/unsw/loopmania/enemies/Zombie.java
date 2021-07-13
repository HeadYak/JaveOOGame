package unsw.loopmania.enemies;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.PathPosition;

public class Zombie extends BasicEnemy {
    private static final double CRIT_CHANCE = 10;

    /**
     * Constructor for zombie
     * @param position tile position of zombie
     */
    public Zombie(PathPosition position) {
        super(position);
    }

    /**
     * Getter for zombie crit chance
     * @return chance of zombie critting
     */
    public static double getCritChance() {
        return CRIT_CHANCE;
    }
    
}
