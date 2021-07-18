package unsw.loopmania.Items.Weapons;

import javafx.beans.property.SimpleIntegerProperty;

public class Staff extends Weapon{

    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setItemValue(15);
        setDamageValue(7);
    }
    
}
