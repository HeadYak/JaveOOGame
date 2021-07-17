package unsw.loopmania.enemies;

import unsw.loopmania.PathPosition;
import unsw.loopmania.movement.ChasePlayer;
import unsw.loopmania.enemies.crits.CritConvertToEnemy;

public class Zombie extends BasicEnemy {
    private int detectionRadius;
    private boolean isMoving;
    private int countdown;

    /**
     * Constructor for zombie
     * @param position tile position of zombie
     */
    public Zombie(PathPosition position) {
        super(position);

        // Zombie stats
        setMoveSpeed(0.5);
        setCritChance(0.1);
        setBattleRadius(1);
        setSupportRadius(2);
        setHp(100);
        detectionRadius = 5;
        isMoving = false;
        countdown = 0;

        // Behaviours
        setMoveBehaviour(new ChasePlayer());
        setCritBehaviour(new CritConvertToEnemy());
    }

    /**
     * Getter for detection radius
     * @return detection radius of zombie (always 5)
     */
    public int getDetectionRadius() {
        return detectionRadius;
    }

    /**
     * Getter for isMoving
     * @return if zombie is moving or not
     */
    public boolean isMoving() {
        return isMoving;
    }

    /**
     * Setter for isMoving
     * @param isMoving whether zombie is moving or not
     */
    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    /**
     * Getter for countdown
     * @return the countdown of zombie before it moves again (only if isMoving)
     */
    public int getCountdown() {
        return countdown;
    }

    /**
     * Setter for countdown
     * @param countdown new countdown of zombie when moving
     */
    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    
}
