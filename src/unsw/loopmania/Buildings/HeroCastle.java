package unsw.loopmania.Buildings;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.enemies.BasicEnemy;
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

    /**
     * @return the range of the building
     */
    @Override
    public int getRange() {
        return 0;
    }

    /**
     * @return true if the building is a spawner and false if not
     */
    @Override
    public Boolean getIsSpawner() {
        return false;
    }

    /**
     * replaces the old shop with a new one
     */
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

    /**
     * @return shop (list of items)
     */
    public ArrayList<Item> getShop() {
        return shop;
    }

    /**
     * @param character
     * @param item
     * adds an item to the characters inventory and charges the player on the items cost
     * if the character has enough gold
     */
    public void purchaseItem(Character character, Item item) {
        if (character.getGold() > item.getGoldValue()) {
            shop.remove(item);
            character.buy(item);
        }
    }

    /**
     * @param character
     * performs the buildings interaction with the building
     */
    @Override
    public void interact(Character character) {
        getShop();
        character.newLoop();
    }

    /**
     * @param character
     * @return true if the character can interact with the building else false
     */
    @Override
    public Boolean canInteract(Character character) {
        if (character.getX() == getX() && character.getY() == getY()) {
            generateNewShop();
            return true;            
        }
        return false;
    }

    /**
     * @param enemy
     * performs the buildings interaction with the enemy
     */
    @Override
    public void interactMob(BasicEnemy enemy) {
    }

    /**
     * @param enemy
     * @return true if the enemy can interact with the building else false
     */
    @Override
    public Boolean canInteractMob(BasicEnemy enemy) {
        return false;
    }

    /**
     * @param world
     * @param character
     * performs the buildings action on every new loop
     */
    @Override
    public void newLoop(LoopManiaWorld world, Character character) {
    }
}
