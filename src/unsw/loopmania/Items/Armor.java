package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;

public class Armor extends Item {
    
    public BlockBehaviour blockBehaviourType;


    public Armor(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        //TODO Auto-generated constructor stub
    }
    public void setBlockBehaviour(BlockBehaviour newBlockBehaviourType){
        blockBehaviourType = newBlockBehaviourType;
    }
}
