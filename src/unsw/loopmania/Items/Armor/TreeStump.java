package unsw.loopmania.Items.Armor;

import javafx.beans.property.SimpleIntegerProperty;

public class TreeStump extends Shield {

    public TreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setBlockBehaviour(new ShieldDamageBlock(new noDamageBlock()));
        setItemValue(60);
    }
    
}
