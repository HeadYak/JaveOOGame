package unsw.loopmania.enemies;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Buildings.Campfire;
import unsw.loopmania.movement.MoveAntiClockwise;
import unsw.loopmania.movement.MoveClockwise;
// import unsw.loopmania.enemies.crits.CritStackBuff;

public class Vampire extends BasicEnemy {
    private LoopManiaWorld worldReference;
    private boolean isMovingClockwise;
    private boolean isBuffed;
    private int buffDuration;

    /**
     * Constructor for Vampire
     */
    public Vampire(PathPosition position, LoopManiaWorld world) {
        super(position);
        worldReference = world;

        // Vampire stats
        setMoveSpeed(2);
        setCritChance(0.1);
        setBattleRadius(2);
        setSupportRadius(3);
        setHp(300);
        setDmg(8);
        isMovingClockwise = false;
        isBuffed = false;
        buffDuration = 0;

        // Behaviours
        setMoveBehaviour(new MoveAntiClockwise());
        // setCritBehaviour(new CritStackBuff());
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

    /**
     * Getter for if it is moving clockwise
     * @return if it is moving clockwise
     */
    public boolean isMovingClockwise() {
        return isMovingClockwise;
    }

    /**
     * Setter for if it is moving clockwise
     * @param isMovingClockwise whether it is moving clockwise or not
     */
    public void setMovingClockwise(boolean isMovingClockwise) {
        if (isMovingClockwise) {
            setMoveBehaviour(new MoveClockwise());
        } else {
            setMoveBehaviour(new MoveAntiClockwise());
        }
        this.isMovingClockwise = isMovingClockwise;
    }

    /**
     * Overridden method that moves vampire as usual, and then checks if it
     * is in the range of a campfire
     */
    @Override
    public void performMove() {
        super.performMove();

        // Check for campfires and see if they are in range
        for (Building building : worldReference.getBuildingEntities()) {
            double xComponent = Math.pow((building.getX() - getX()), 2);
            double yComponent = Math.pow((building.getY() - getY()), 2);

            double dist = Math.sqrt(xComponent + yComponent);

            if (building instanceof Campfire && dist <= building.getRange()) {
                setMovingClockwise(!isMovingClockwise);
                break;
            }
        }
    }
    
}
