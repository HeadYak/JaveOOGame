package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.movement.MoveBehaviour;

/**
 * The moving entity
 */
public abstract class MovingEntity extends Entity {
    /**
     * object holding position in the path
     */
    private PathPosition position;

    /**
     * entity's movement behaviour
     */
    private MoveBehaviour moveBehaviour;

    /**
     * entity's movement speed
     */
    private double moveSpeed;

    /**
     * entity's critical strike chance
     */
    private double critChance;

    /**
     * entity's current hp
     */
    private int hp;

    /**
     * entity's current dmg
     */
    private int dmg;

    /**
     * Create a moving entity which moves based on its moveBehaviour
     * @param position represents the current position in the path
     */
    public MovingEntity(PathPosition position) {
        super();
        this.position = position;
    }

    /**
     * Getter for the path position of a moving entity
     * @return the current position of moving entity
     */
    public PathPosition getPosition() {
        return position;
    }

    /**
     * Setter for move behaviour
     * @param moveBehaviour move behaviour we want to implement
     */
    public void setMoveBehaviour(MoveBehaviour moveBehaviour) {
        this.moveBehaviour = moveBehaviour;
    }

    /**
     * Make moving entity move
     */
    public void performMove() {
        moveBehaviour.move(position, getMoveSpeed());
    }

    /**
     * Getter for movement speed
     * @return movement speed of Moving Entity
     */
    public double getMoveSpeed() {
        return moveSpeed;
    }

    /**
     * Setter for movement speed
     * @param moveSpeed new movement speed to be set
     */
    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    /**
     * Getter for critical strike chance
     * @return the critical strike chance as a decimal
     */
    public double getCritChance() {
        return critChance;
    }

    /**
     * Setter for critical strike chance
     * @param critChance new critical strike chance to be set
     */
    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    /**
     * Getter for moving entity's hp
     * @return the current hp of moving entity
     */
    public int getHp() {
        return hp;
    }

    /**
     * Setter for moving entity's hp
     * @param hp new hp of moving entity
     */
    public void setHp(int hp) {
        this.hp = Math.max(hp, 0);
    }

    /**
     * Getter for moving entity's dmg
     * @return the current hp of moving entity
     */
    public int getDmg() {
        return dmg;
    }

    /**
     * Setter for moving entity's hp
     * @param hp new hp of moving entity
     */
    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public SimpleIntegerProperty x() {
        return position.getX();
    }

    public SimpleIntegerProperty y() {
        return position.getY();
    }

    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }
}
