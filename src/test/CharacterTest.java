package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Items.HealthPotion;
import unsw.loopmania.Items.Item;
// import unsw.loopmania.Items.Armor.BlockBehaviour;
import unsw.loopmania.Items.Armor.ChestArmor;
import unsw.loopmania.Items.Armor.Helmet;
import unsw.loopmania.Items.Armor.Shield;
import unsw.loopmania.Items.Armor.basicChestArmor;
import unsw.loopmania.Items.Armor.basicHelmet;
import unsw.loopmania.Items.Armor.basicShield;
import unsw.loopmania.Items.Weapons.Sword;
import unsw.loopmania.Items.Weapons.Weapon;

public class CharacterTest {
    
    @Test
    public void inventoryTestSword() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Sword newsword = new Sword(x, y);
        Sword newsword1 = new Sword(x, y);
        Sword newsword2= new Sword(x, y);
        Sword newsword3 = new Sword(x, y);
        Sword newsword4 = new Sword(x, y);
        Sword newsword5 = new Sword(x, y);
        Sword newsword6 = new Sword(x, y);
        Sword newsword7 = new Sword(x, y);
        Sword newsword8 = new Sword(x, y);

        assertEquals(c.getGold(), 0);
        assertEquals(c.getXp(), 0);
        // assertEquals(c.get, 0);
        c.addToInventory(newsword);
        c.addToInventory(newsword1);
        c.addToInventory(newsword2);
        c.addToInventory(newsword3);
        c.addToInventory(newsword4);
        c.addToInventory(newsword5);
        c.addToInventory(newsword6);
        c.addToInventory(newsword7);
        assertEquals(c.getGold(), 0);
        assertEquals(c.getXp(), 0);
        c.addToInventory(newsword8);
        assertEquals(c.getGold(), 5);
        assertEquals(c.getXp(), 5);


        assertEquals(c.getInventory().size(), 8);
        assertEquals(c.getWeapon(), null);

        c.setWeapon(newsword);

        assertEquals(c.getWeapon(), newsword);

    }

    @Test
    public void inventoryTestHelmet() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Helmet newHelmet = new basicHelmet(x, y);
        

        assertEquals(c.getGold(), 0);
        assertEquals(c.getXp(), 0);
        // assertEquals(c.get, 0);
        c.addToInventory(newHelmet);
        


        assertEquals(c.getInventory().size(), 1);
        assertEquals(c.getHelmet(), null);

        c.setHelmet(newHelmet);

        assertEquals(c.getHelmet(), newHelmet);

    }

    @Test
    public void inventoryTestArmor() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        ChestArmor newArmor = new basicChestArmor(x, y);

        assertEquals(c.getGold(), 0);
        assertEquals(c.getXp(), 0);
        // assertEquals(c.get, 0);
        c.addToInventory(newArmor);

        assertEquals(c.getInventory().size(), 1);
        assertEquals(c.equippedChestArmor(), null);

        c.setChestArmor(newArmor);

        assertEquals(c.equippedChestArmor(), newArmor);

    }


    @Test
    public void ArmorDecorator() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        ChestArmor newArmor = new basicChestArmor(x, y);
        c.setChestArmor(newArmor);
        assertEquals(c.getDamageTakenModifier(), 0.5);


        Helmet newHelmet = new basicHelmet(x, y);
        c.setHelmet(newHelmet);
        assertEquals(c.getDamageTakenModifier(), 0.55, 0.01);
        // System.out.println(c.getDamageTakenModifier());

        Shield newShield = new basicShield(x, y);
        c.setShield(newShield);

        assertEquals(c.getDamageTakenModifier(), 0.64,0.01);

        System.out.println(c.getDamageTakenModifier());

        HealthPotion potion = new HealthPotion(x, y);

        c.addToInventory(potion);

        for(Item i:c.getInventory()){
            if(i instanceof HealthPotion){
                System.out.println("YOYO");
            }
        }

    }

}
