package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Weapon extends Item {

    private int damage;
    private int goldValue;
    public Weapon(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        //TODO Auto-generated constructor stub
    }

    public int getGoldValue(){
        return goldValue;
    }

    public int getDamageValue(){
        return damage;
    }
    
}
