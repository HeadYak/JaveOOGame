package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;

public class basicChestArmor extends ChestArmor  {

    public basicChestArmor(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        blockBehaviourType = new ChestArmorDamageBlock();
        setItemValue(10);
        //TODO Auto-generated constructor stub
    }

    public Double getDamageTakenModifier() {
        return blockBehaviourType.getDamageTakenModifier();
    }
    
}
