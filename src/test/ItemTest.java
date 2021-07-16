package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.ChaCha20ParameterSpec;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.Items.Armor;
import unsw.loopmania.Items.BlockBehaviour;
import unsw.loopmania.Items.Helmet;
import unsw.loopmania.Items.Item;
import unsw.loopmania.Items.Sword;
import unsw.loopmania.Items.Weapon;
import unsw.loopmania.Items.basicChestArmor;
import unsw.loopmania.Items.basicHelmet;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 * this class is a dummy class demonstrating how to setup tests for the project
 * you should setup additional test classes in a similar fashion, aiming to achieve high coverage.
 * A clickable "Run Test" link should appear if you have installed the Java Extension Pack properly.
 */
public class ItemTest {
    @Test
    public void blahTest(){
        assertEquals("a", "a");
    }
    
    @Test
    public void blahTest2(){
        LoopManiaWorld d = new LoopManiaWorld(1, 2, new ArrayList<>());
        assertEquals(d.getWidth(), 1);
    }

    @Test
    public void swordTest(){
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);


        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);

        assertEquals(c.getWeapon(), null);

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Sword newsword = new Sword(x, y);
        c.setWeapon(newsword);
        
        assertEquals(c.getWeapon(), newsword);
        // System.out.println(c.getWeapon());
    }



    @Test
    public void swordgoldValueTest(){
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);


        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);

        assertEquals(c.getWeapon(), null);

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Sword newsword = new Sword(x, y);

        assertEquals(newsword.getGoldValue(), 5);

    }

    @Test
    public void characterInventoryTest(){
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);


        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);

        assertEquals(c.getInventory().size(), 0);
    }

    @Test
    public void characterHelmetBlockTest(){
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);


        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Helmet newhelmet = new basicHelmet(x, y);

    

        assertTrue(newhelmet.getBlockType() instanceof BlockBehaviour);

        assertEquals(c.getInventory().size(),0);
    }


    @Test
    public void characterChestArmorBlockTest(){
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Sword newSword = new Sword(x, y);

        System.out.println(newSword.getClass()); 

        assertTrue(newSword instanceof Weapon);
        assertTrue(newSword instanceof Item);
        assertTrue(newSword instanceof Sword);

    }

    @Test
    public void characterHelmetArmorBlockTest(){
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();
        
        basicChestArmor newChestArmor = new basicChestArmor(x, y);

        assertTrue(newChestArmor.getBlockType() instanceof BlockBehaviour);

    }

    public void characterArmorDamageReductionTest(){
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();
        basicChestArmor newChestArmor = new basicChestArmor(x, y);
        

        assertEquals(newChestArmor.getDamageTakenModifier(), 0.5);


        basicHelmet newHelmet = new basicHelmet(x, y);

        assertEquals(newChestArmor.getDamageTakenModifier(), 0.9);


    }







}
