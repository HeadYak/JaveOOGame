package unsw.loopmania.enemies;

import unsw.loopmania.PathPosition;
import unsw.loopmania.movement.ChasePlayer;
import unsw.loopmania.enemies.crits.CritConvertToEnemy;

public class Zombie extends BasicEnemy {
    private int detectionRadius;
    private boolean isMoving;
    private int countdown;

    /**
     * Constructor for zombie
     * @param position tile position of zombie
     */
    public Zombie(PathPosition position) {
        super(position);

        // Zombie stats
        setMoveSpeed(0.5);
        setCritChance(0.1);
        setBattleRadius(1);
        setSupportRadius(2);
        setHp(100);
        detectionRadius = 5;
        isMoving = false;
        countdown = 0;

        // Behaviours
        setMoveBehaviour(new ChasePlayer());
        setCritBehaviour(new CritConvertToEnemy());
    }
    
}
