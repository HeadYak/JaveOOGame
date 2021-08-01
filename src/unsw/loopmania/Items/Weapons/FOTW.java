package unsw.loopmania.Items.Weapons;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.enemies.Boss;

public class FOTW extends Weapon{

    public FOTW(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setItemValue(50);
        setDamageValue(30);
        setCritChance(0.2);
        //TODO Auto-generated constructor stub
    }

    /**
     * Overridden implementation of attack to account for bosses
     */
    @Override
    public void attack(BasicEnemy enemy) {
        super.attack(enemy);

        // Attack an additional two times against bosses
        if (enemy instanceof Boss) {
            enemy.setHp(enemy.getHp() - (getDamageValue() * 4));
            enemy.setHp(enemy.getHp() - (getDamageValue() * 4));
        }
    }


    /**
     * Overridden implementation of abstract method critAttack that simply
     * multiplies damage by 2
     */
    @Override
    public BasicEnemy critAttack(List<BasicEnemy> battleEnemies) {
        BasicEnemy target = battleEnemies.get(0);
        int damage = (getDamageValue() * 4) * 2;

        if (target instanceof Boss) {
            damage *= 3;
        }

        target.setHp(target.getHp() - damage);
        return null;
    }
    
}
