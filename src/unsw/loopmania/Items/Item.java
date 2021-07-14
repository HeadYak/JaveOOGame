package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;

public class Item extends StaticEntity {
    private int goldValue;
    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        // this.goldValue = goldValue;
        //TODO Auto-generated constructor stub
    }
    public int getGoldValue(){
        return goldValue;
    }
}
