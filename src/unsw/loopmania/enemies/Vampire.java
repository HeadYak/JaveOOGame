package unsw.loopmania.enemies;

import unsw.loopmania.PathPosition;

public class Vampire extends BasicEnemy {
    private static final double CRIT_CHANCE = 10;
    private boolean isBuffed;
    private int buffDuration;

    /**
     * Constructor for Vampire
     */
    public Vampire(PathPosition position) {
        super(position);
        isBuffed = false;
        buffDuration = 0;
    }

    /**
     * Getter for crit chance of vampire
     * @return chance of vampire critting
     */
    public static double getCritChance() {
        return CRIT_CHANCE;
    }

    /**
     * Checks if vampire is buffed
     * @return whether vampire is buffed or not
     */
    public boolean isBuffed() {
        return isBuffed;
    }

    /**
     * Sets or unsets the buff
     * @param isBuffed true if buffed, false otherwsie
     */
    public void setBuffed(boolean isBuffed) {
        this.isBuffed = isBuffed;
    }

    /**
     * Getter for buff duration
     * @return how long in secs until bath ends
     */
    public int getBuffDuration() {
        return buffDuration;
    }

    /**
     * Reduces buff duration
     * @param buffDuration new buff duration
     */
    public void setBuffDuration(int buffDuration) {
        this.buffDuration = buffDuration;
    }
    
}
