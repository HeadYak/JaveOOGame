package unsw.loopmania;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;

public class HeroCastle extends Building{
    private ArrayList<Items> shop;
    
    public HeroCastle(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        shop = new ArrayList<Item>();
    }
    
}
