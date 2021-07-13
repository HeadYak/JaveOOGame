package unsw.loopmania.enemies;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.PathPosition;

public class Slug extends BasicEnemy {
    private static final double CRIT_CHANCE = 10;

    /**
     * Constructor for slug
     * @param position tile position of slug
     */
    public Slug(PathPosition position) {
        super(position);
    }

    /**
     * Getter for slug crit chance
     * @return chance of slug critting
     */
    public static double getCritChance() {
        return CRIT_CHANCE;
    }
    
}
