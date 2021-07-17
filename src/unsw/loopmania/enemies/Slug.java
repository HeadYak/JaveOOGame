package unsw.loopmania.enemies;

import unsw.loopmania.PathPosition;
import unsw.loopmania.movement.MoveClockwise;
import unsw.loopmania.enemies.crits.CritNone;

public class Slug extends BasicEnemy {

    /**
     * Constructor for slug
     * @param position tile position of slug
     */
    public Slug(PathPosition position) {
        super(position);

        // Slug stats
        setMoveSpeed(1);
        setCritChance(0.1);
        setBattleRadius(1);
        setSupportRadius(1);
        setHp(100);

        // Slug behaviours
        setMoveBehaviour(new MoveClockwise());
        setCritBehaviour(new CritNone());
    }
    
}
