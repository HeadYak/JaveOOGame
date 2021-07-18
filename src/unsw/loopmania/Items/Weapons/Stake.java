package unsw.loopmania.Items.Weapons;

import javafx.beans.property.SimpleIntegerProperty;

public class Stake extends Weapon {

    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setDamageValue(5);
        setItemValue(8);
    }
    
}
