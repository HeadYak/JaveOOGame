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
     * entity's movement behaviour (assigned in subclasses)
     */
    private MoveBehaviour moveBehaviour;

    /**
     * Create a moving entity which moves up and down the path in position
     * @param position represents the current position in the path
     */
    public MovingEntity(PathPosition position) {
        super();
        this.position = position;
    }

    /**
     * Setter for move behaviour
     * @param moveBehaviour move behaviour we want to implement
     */
    public void setMoveBehaviour(MoveBehaviour moveBehaviour) {
        this.moveBehaviour = moveBehaviour;
    }

    public void performMove() {
        
    }

    // /**
    //  * move clockwise through the path
    //  */
    // public void moveDownPath() {
    //     position.moveDownPath();
    // }

    // /**
    //  * move anticlockwise through the path
    //  */
    // public void moveUpPath() {
    //     position.moveUpPath();
    // }

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
