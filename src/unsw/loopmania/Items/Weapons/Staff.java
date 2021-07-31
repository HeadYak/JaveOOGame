package unsw.loopmania.Items.Weapons;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.enemies.BasicEnemy;

public class Staff extends Weapon{

    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setDamageValue(7);
        setItemValue(15);
        setCritChance(0.2);
    }

    /**
     * Overridden implementation of abstract method critAttack to set dmg to 0
     * but cause trance
     */
    @Override
    public BasicEnemy critAttack(List<BasicEnemy> battleEnemies) {
        BasicEnemy target = battleEnemies.get(0);
        int damage = 0;

        // Added in case we want behaviour to be modified
        target.setHp(target.getHp() - damage);

        // Store enemy to return and remove
        target = battleEnemies.remove(0);

        // Return target to signify BattleManager the enemy has been tranced
        return target;
    }
    
}
