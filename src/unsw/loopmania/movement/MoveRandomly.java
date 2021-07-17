package unsw.loopmania.movement;

import java.util.Random;

import unsw.loopmania.PathPosition;

public class MoveRandomly implements MoveBehaviour {

    @Override
    public void move(PathPosition position, double moveSpeed) {
        int directionChoice = (new Random()).nextInt(1);

        // 50% chance of moving up the path or down the path
        if (directionChoice == 0){
            position.moveUpPath();
        }
        else if (directionChoice == 1){
            position.moveDownPath();
        }
    }
    
}
