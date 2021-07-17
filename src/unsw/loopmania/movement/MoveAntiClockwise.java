package unsw.loopmania.movement;

import unsw.loopmania.PathPosition;

public class MoveAntiClockwise implements MoveBehaviour {

    /**
     * Moves anticlockwise by X
     * @param position position in path where entity is currently at
     * @param moveSpeed speed of entity
     */
    @Override
    public void move(PathPosition position, double moveSpeed) {

        double noTiles = Math.ceil(moveSpeed);

        for (int i = 0; i < noTiles; i++) {
            position.moveDownPath();
        }

    }
    
}
