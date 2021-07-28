package unsw.loopmania.Items.Armor;

import javafx.beans.property.SimpleIntegerProperty;

public class basicHelmet extends Helmet {

    public basicHelmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setBlockBehaviour(new HelmetDamageBlock());
        setItemValue(10);
    }

    // public Double getDamageTakenModifier() {
    //     return getBlockType().getDamageTakenModifier();
    // }
    
}
