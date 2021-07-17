package unsw.loopmania.movement;

import unsw.loopmania.PathPosition;

public class MoveChasePlayer implements MoveBehaviour {
    Character player;

    public MoveChasePlayer(Character player) {
        this.player = player;
    }

    @Override
    public void move(PathPosition position, double moveSpeed) {
        
        double noTiles = Math.ceil(moveSpeed);

        
        
    }
    
}
