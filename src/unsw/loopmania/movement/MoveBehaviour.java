package unsw.loopmania.movement;

import unsw.loopmania.PathPosition;

public interface MoveBehaviour {
    public void move(PathPosition position, double moveSpeed);
}
