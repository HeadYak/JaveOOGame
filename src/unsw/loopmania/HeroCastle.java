package unsw.loopmania;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;

public class HeroCastle extends Building{
    private ArrayList<Item> shop;
    
    public HeroCastle(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        shop = new ArrayList<Item>();
        generateNewShop();
    }

    public void generateNewShop() {
        ArrayList<Item> newShop = new ArrayList<Item>();
        for (int i = 0; i < 3; i++) {
            newShop.add(makerandomitem())
        }
        shop = newShop;
    }

    public ArrayList<Item> getShop() {
        return shop;
    }

    public purchaseItem(Character character, Item item) {
        if (character.getGold() > item.getValue()) {
            shop.remove(item);
            character.spend(item.getValue());
        }
    }
    
}
