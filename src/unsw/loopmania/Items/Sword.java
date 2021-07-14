package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends Weapon {
    // TODO = add more weapon/item types

    private int damage;
    private int goldValue;
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.damage = 10;
        this.goldValue = 5;

    }    
    public int getGoldValue(){
        return goldValue;
    }
}
