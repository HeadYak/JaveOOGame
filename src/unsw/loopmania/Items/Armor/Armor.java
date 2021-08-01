package unsw.loopmania.Items.Armor;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Items.Item;

public abstract class Armor extends Item {
    
    private ArmorBlock armorBlockType;

    public Armor(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
    public void setBlockBehaviour(ArmorBlock newArmorBlockType){
        armorBlockType = newArmorBlockType;
    }

    public ArmorBlock getArmorBlockType(){
        return armorBlockType;
    }
    
    public Double getDamageBlockModifier() {
        return armorBlockType.getPercentDamageBlocked();
    }
}
