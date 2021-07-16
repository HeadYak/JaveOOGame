package unsw.loopmania.Items.Armor;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Items.Item;

public abstract class Armor extends Item {
    
    public BlockBehaviour blockBehaviourType;

    public Armor(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        //TODO Auto-generated constructor stub
    }
    public void setBlockBehaviour(BlockBehaviour newBlockBehaviourType){
        blockBehaviourType = newBlockBehaviourType;
    }

    public BlockBehaviour getBlockType(){
        return blockBehaviourType;
    }
}
