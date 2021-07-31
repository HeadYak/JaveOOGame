package unsw.loopmania.Items.Weapons;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Items.Item;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.Ally;
import unsw.loopmania.Character;

public abstract class Weapon extends Item {

    public WeaponEffect extraWeaponEffect;

    private int damage;
    private double critChance;

    public Weapon(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void setDamageValue(int damage){
        this.damage = damage;
    }

    public int getDamageValue(){
        return damage;
    }


    /**
     * Getter for critical strike chance
     * @return the critical strike chance as a decimal
     */
    public double getCritChance() {
        return critChance;
    }

    /**
     * Setter for critical strike chance
     * @param critChance new critical strike chance to be set
     */
    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }
    
    /**
     * Attacks enemy using weapon
     * @param enemy enemy to be attacked
     */
    public void attack(BasicEnemy enemy) {
        int damage = getDamageValue() * 4;
        enemy.setHp(enemy.getHp() - damage);
    }

    /**
     * Raw crit damage (used for buffed status)
     * @param enemy to be attacked
     */
    public void rawCritAttack(BasicEnemy enemy) {
        int damage = getDamageValue() * 4 * 2;
        enemy.setHp(enemy.getHp() - damage);
    }

    /**
     * Abstract method for crit attacks for weapon
     * @param battleEnemies list of all enemies in battle
     * @return enemy if tranced by staff
     */
    public abstract BasicEnemy critAttack(List<BasicEnemy> battleEnemies);
}
