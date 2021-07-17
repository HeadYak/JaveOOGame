package unsw.loopmania.enemies;

import unsw.loopmania.PathPosition;
import unsw.loopmania.movement.MoveAntiClockwise;
import unsw.loopmania.enemies.crits.CritStackBuff;

public class Vampire extends BasicEnemy {
    private boolean isMovingClockwise;
    private boolean isBuffed;
    private int buffDuration;

    /**
     * Constructor for Vampire
     */
    public Vampire(PathPosition position) {
        super(position);

        // Vampire stats
        setMoveSpeed(2);
        setCritChance(0.1);
        setBattleRadius(2);
        setSupportRadius(3);
        setHp(100);
        isMovingClockwise = false;
        isBuffed = false;
        buffDuration = 0;

        // Behaviours
        setMoveBehaviour(new MoveAntiClockwise());
        setCritBehaviour(new CritStackBuff());
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
