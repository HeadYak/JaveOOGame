package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;

public class basicHelmet extends Helmet {

    public basicHelmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        blockBehaviourType = new HelmetDamageBlock();
        //TODO Auto-generated constructor stub
    }

    public Double getDamageTakenModifier() {
        return blockBehaviourType.getDamageTakenModifier();
    }
    
}
