package unsw.loopmania.Items.Weapons;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.enemies.BasicEnemy;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends Weapon {

    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setDamageValue(15);
        setItemValue(5);
        setCritChance(0.1);
    }

    /**
     * Overridden implementation of abstract method critAttack to simply
     * multiply damage by 2
     */
    @Override
    public BasicEnemy critAttack(List<BasicEnemy> battleEnemies) {
        BasicEnemy target = battleEnemies.get(0);
        int damage = (getDamageValue() * 4) * 2;
        target.setHp(target.getHp() - damage);

        return null;
    }    

}
