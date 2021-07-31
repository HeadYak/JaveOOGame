package unsw.loopmania;

import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.movement.MoveClockwise;

public class Ally extends MovingEntity {

    public Ally(PathPosition position) {
        super(position);

        // Ally stats
        setMoveSpeed(1);
        setCritChance(0);
        setMaxHp(100);
        setHp(100);
        setDmg(5);

        // Ally behaviours
        setMoveBehaviour(new MoveClockwise());
    }

    /**
     * Attacks enemy (set damage)
     * @param enemy enemy ally is attacking
     */
    public void attack(BasicEnemy enemy) {
        int damage = getDmg() * 4;
        enemy.setHp(enemy.getHp() - damage);
    }
}
