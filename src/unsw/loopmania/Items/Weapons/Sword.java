package unsw.loopmania.Items.Weapons;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends Weapon {
    // TODO = add more weapon/item types

    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setDamageValue(10);
        setItemValue(5);
    }    

}
