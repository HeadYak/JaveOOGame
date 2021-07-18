package unsw.loopmania;

import unsw.loopmania.movement.MoveClockwise;

public class Ally extends MovingEntity {

    public Ally(PathPosition position) {
        super(position);

        // Ally stats
        setMoveSpeed(1);
        setCritChance(0);
        setHp(100);
        setDmg(5);

        // Ally behaviours
        setMoveBehaviour(new MoveClockwise());
    }

}
