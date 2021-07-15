package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;

public class ChestArmor extends Armor {

    public ChestArmor(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        blockBehaviourType = new ChestArmorDamageBlock();
        //TODO Auto-generated constructor stub
    }
    
}
