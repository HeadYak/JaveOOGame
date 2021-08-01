package unsw.loopmania.enemies;

import java.util.List;

import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.movement.MoveRandomly;

public class ElanMuske extends Boss {
    private int healValue;

    public ElanMuske(PathPosition position) {
        super(position);
        
        // ElanMuske stats
        setMoveSpeed(1);
        setCritChance(0.1);
        setBattleRadius(1);
        setSupportRadius(1);
        setHp(1000);
        setMaxHp(1000);
        setDmg(20);
        setWeight(10);
        healValue = 50;

        // Behaviours
        setMoveBehaviour(new MoveRandomly());
    }

    /**
     * Overridden method for crit effect of Elan Muske
     */
    @Override
    public void critEffect(Character player, List<BasicEnemy> battleEnemies) {

        // Heal all enemies
        for (BasicEnemy enemy : battleEnemies) {
            enemy.setHp(Math.min(enemy.getMaxHp(), enemy.getHp() + healValue));
        }
    }

    /**
     * Getter for heal value
     * @return amount Elan will heal on crits
     */
    public int getHealValue() {
        return healValue;
    }
    
}
