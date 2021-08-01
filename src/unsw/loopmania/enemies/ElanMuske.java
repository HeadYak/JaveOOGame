package unsw.loopmania.enemies;

import java.util.List;

import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.movement.MoveRandomly;

public class ElanMuske extends Boss {

    public ElanMuske(PathPosition position) {
        super(position);
        
        // ElanMuske stats
        setMoveSpeed(1);
        setCritChance(0.2);
        setBattleRadius(1);
        setSupportRadius(1);
        setHp(1000);
        setDmg(20);
        setWeight(0);

        // Behaviours
        setMoveBehaviour(new MoveRandomly());
    }

    @Override
    public void critEffect(Character player, List<BasicEnemy> battleEnemies) {
        
        // Heal all enemies
        for (BasicEnemy enemy : battleEnemies) {
            enemy.setHp(Math.max(enemy.getMaxHp(), enemy.getHp() + 20));
        }

    }

    
}
