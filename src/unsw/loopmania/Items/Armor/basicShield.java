package unsw.loopmania.Items.Armor;

import javafx.beans.property.SimpleIntegerProperty;

public class basicShield extends Shield {

    public basicShield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setBlockBehaviour(new ShieldDamageBlock(new noDamageBlock()));
        setItemValue(10);
    }
    
}
