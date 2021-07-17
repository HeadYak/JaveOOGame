package unsw.loopmania.enemies;

import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Character;
import unsw.loopmania.enemies.crits.CritBehaviour;

/**
 * a basic form of enemy in the world
 */
public abstract class BasicEnemy extends MovingEntity {
    /**
     * enemy's battle radius
     */
    private int battleRadius;

    /**
     * enemy's's support radius
     */
    private int supportRadius;

    /**
     * enemy's crit behaviour
     */
    private CritBehaviour critBehaviour;

    /**
     * Create a basic enemy
     * @param position represents the current position in the path
     */
    public BasicEnemy(PathPosition position) {
        super(position);
    }

    /**
     * Getter for enemy's battle radius
     * @return the battle radius
     */
    public int getBattleRadius() {
        return battleRadius;
    }

    /**
     * Setter for battle radius
     * @param battleRadius new battle radius to be set
     */
    public void setBattleRadius(int battleRadius) {
        this.battleRadius = battleRadius;
    }

    /**
     * Getter for enemy's support radius
     * @return the support radius
     */
    public int getSupportRadius() {
        return supportRadius;
    }

    /**
     * Setter for support radius
     * @param supportRadius new support radius to be set
     */
    public void setSupportRadius(int supportRadius) {
        this.supportRadius = supportRadius;
    }

    /**
     * Setter for critical strikes
     * @param critBehaviour move behaviour we want to implement
     */
    public void setCritBehaviour(CritBehaviour critBehaviour) {
        this.critBehaviour = critBehaviour;
    }

    /**
     * Make enemy do crit effects
     */
    public void performCritEffects() {
        critBehaviour.critEffects();
    }

    public double distanceFromCharacter(Character character) {
        double xComponent = Math.pow((character.getX() - getX()), 2);
        double yComponent = Math.pow((character.getY() - getY()), 2);
        double distance = Math.sqrt(xComponent + yComponent);

        return distance;
    }
}
