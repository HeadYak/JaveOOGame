package unsw.loopmania.Buildings;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.Items.HealthPotion;
import unsw.loopmania.Items.Item;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.*;

public class HeroCastle extends Building{
    private ArrayList<Item> shop;
    
    public HeroCastle(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        shop = new ArrayList<Item>();
        generateNewShop();
    }

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public Boolean getIsSpawner() {
        return false;
    }
    public void generateNewShop() {
        ArrayList<Item> newShop = new ArrayList<Item>();

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();
        Item placeHolderItem = new HealthPotion(x,y);
        for (int i = 0; i < 3; i++) {
            newShop.add(placeHolderItem.generateRandomItem());
        }
        shop = newShop;
    }

    public ArrayList<Item> getShop() {
        return shop;
    }

    public void purchaseItem(Character character, Item item) {
        if (character.getGold() > item.getGoldValue()) {
            shop.remove(item);
            character.buy(item);
        }
    }

    @Override
    public void interact(Character character) {
        getShop();
        character.newLoop();
    }

    @Override
    public Boolean canInteract(Character character) {
        if (character.getX() == getX() && character.getY() == getY()) {
            generateNewShop();
            return true;            
        }
        return false;
    }

    @Override
    public void interactMob(BasicEnemy enemy) {
    }

    @Override
    public Boolean canInteractMob(BasicEnemy enemy) {
        return false;
    }
    
    @Override
    public void newLoop(LoopManiaWorld world, Character character) {
    }
}
