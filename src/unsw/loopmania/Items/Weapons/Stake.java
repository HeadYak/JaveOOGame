package unsw.loopmania.Items.Weapons;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.enemies.Vampire;

public class Stake extends Weapon {

    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setDamageValue(10);
        setItemValue(8);
        setCritChance(0.1);
    }

    /**
     * Overridden implementation of method attack to account for vampire check
     */
    @Override
    public void attack(BasicEnemy enemy) {
        super.attack(enemy);

        if (enemy instanceof Vampire) {
            enemy.setHp(enemy.getHp() - (getDamageValue() * 4));
        }
    }

    /**
     * Overridden implementation of method rawCritAttack to account for vampire
     * check
     */
    @Override
    public void rawCritAttack(BasicEnemy enemy) {
        super.rawCritAttack(enemy);

        if (enemy instanceof Vampire) {
            enemy.setHp(enemy.getHp() - (getDamageValue() * 4 * 2));
        }
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

        if (target instanceof Vampire) {
            damage *= 2;
        }

        target.setHp(target.getHp() - damage);
        return null;
    }
    
}
