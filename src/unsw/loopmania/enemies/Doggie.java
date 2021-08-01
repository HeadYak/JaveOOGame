package unsw.loopmania.enemies;

import java.util.List;

import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.movement.MoveRandomly;

public class Doggie extends Boss {

    public Doggie(PathPosition position) {
        super(position);
        
        // Doggie stats
        setMoveSpeed(1);
        setCritChance(0.1);
        setBattleRadius(1);
        setSupportRadius(1);
        setHp(600);
        setMaxHp(600);
        setDmg(15);
        setWeight(0);

        // Behaviours
        setMoveBehaviour(new MoveRandomly());
    }

    @Override
    public void critEffect(Character player, List<BasicEnemy> battleEnemies) {
        
        // Set character to stunned
        player.setStunned(true);
    }
    
}
