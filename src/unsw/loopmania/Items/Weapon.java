package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Weapon extends Item {

    private int damage;
    public Weapon(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        //TODO Auto-generated constructor stub
    }

    public void setDamageValue(int damage){
        this.damage = damage;
    }

    public int getDamageValue(){
        return damage;
    }
    
}
