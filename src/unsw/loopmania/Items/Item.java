package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;
import java.util.concurrent.ThreadLocalRandom;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Items.Weapons.Sword;

public abstract class Item extends StaticEntity {
    private int goldValue;
    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        // this.goldValue = goldValue;
        //TODO Auto-generated constructor stub
    }
    public int getGoldValue(){
        return goldValue;
    }

    public Item generateRandomItem(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);

        SimpleIntegerProperty x_randitem = new SimpleIntegerProperty();
        SimpleIntegerProperty y_randitem = new SimpleIntegerProperty();

        Sword randomSword = new Sword(x_randitem, y_randitem);

        return randomSword; 
    }

    public void setItemValue(int itemValue){
        goldValue = itemValue;
    }
}
