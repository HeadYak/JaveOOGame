package unsw.loopmania.Items.Armor;

import javafx.beans.property.SimpleIntegerProperty;
public class basicChestArmor extends ChestArmor  {

    public basicChestArmor(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);

        setBlockBehaviour(new ChestDamageBlock(new noDamageBlock()));
        // blockBehaviourType = new ChestArmorDamageBlock();
        setItemValue(10);
    }

    // public Double getDamageTakenModifier() {
    //     return getBlockType().getDamageTakenModifier();
    // }
    
}
