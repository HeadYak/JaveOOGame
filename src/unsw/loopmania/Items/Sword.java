package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends Weapon {
    // TODO = add more weapon/item types

    private int damage;
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.damage = 10;
    }    
}
