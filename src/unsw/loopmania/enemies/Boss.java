package unsw.loopmania.enemies;

import java.util.List;

import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;

public abstract class Boss extends BasicEnemy {

    public Boss(PathPosition position) {
        super(position);
    }

    @Override
    public void critAttack(Character player, List<BasicEnemy> battleEnemies) {
        // deal damage
        attack(player);
        critEffect(player, battleEnemies);
    }
    
    public abstract void critEffect(Character player, List<BasicEnemy> battleEnemies);
}
