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
import unsw.loopmania.Items.Item;
// import unsw.loopmania.Items.Armor.BlockBehaviour;
import unsw.loopmania.Items.Armor.Helmet;
import unsw.loopmania.Items.Armor.HelmetDamageBlock;
import unsw.loopmania.Items.Armor.ShieldDamageBlock;
import unsw.loopmania.Items.Armor.basicChestArmor;
import unsw.loopmania.Items.Armor.basicHelmet;
import unsw.loopmania.Items.Armor.noDamageBlock;
import unsw.loopmania.Items.Weapons.Sword;
import unsw.loopmania.Items.Weapons.Weapon;
import unsw.loopmania.Items.Armor.Armor;
import unsw.loopmania.Items.Armor.ArmorBlock;
import unsw.loopmania.Items.Armor.ChestDamageBlock;
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
        
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Sword newsword = new Sword(x, y);

        assertEquals(newsword.getGoldValue(), 5);

    }

    @Test
    public void chestarmorgoldValueTest(){
        
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        basicChestArmor newChestArmor = new basicChestArmor(x, y);

        assertEquals(newChestArmor.getGoldValue(), 10);

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
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        ArmorBlock test = new ShieldDamageBlock(new HelmetDamageBlock(new ChestDamageBlock(new noDamageBlock())));

        System.out.println(test.getPercentDamageBlocked());
        assertEquals(test.getPercentDamageBlocked(), 0.64);

        ArmorBlock test1 =  new HelmetDamageBlock(new ChestDamageBlock(new noDamageBlock()));

        assertEquals(test1.getPercentDamageBlocked(), 0.55);

        ArmorBlock test2 = new ChestDamageBlock(new noDamageBlock());

        assertEquals(test2.getPercentDamageBlocked(), 0.5);

        ArmorBlock test3 = new noDamageBlock();

        assertEquals(test3.getPercentDamageBlocked(), 0);
    }


    @Test
    public void characterChestArmorBlockTest(){

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Sword newSword = new Sword(x, y);

        // System.out.println(newSword.getClass()); 

        assertTrue(newSword instanceof Weapon);
        assertTrue(newSword instanceof Item);
        assertTrue(newSword instanceof Sword);

    }

    // @Test
    // public void characterHelmetArmorBlockTest(){
    //     SimpleIntegerProperty x = new SimpleIntegerProperty();
    //     SimpleIntegerProperty y = new SimpleIntegerProperty();
        
    //     basicChestArmor newChestArmor = new basicChestArmor(x, y);

    //     assertTrue(newChestArmor.getBlockType() instanceof BlockBehaviour);

    // }

    // @Test
    // public void characterArmorDamageReductionTest(){
    //     SimpleIntegerProperty x = new SimpleIntegerProperty();
    //     SimpleIntegerProperty y = new SimpleIntegerProperty();
    //     basicChestArmor newChestArmor = new basicChestArmor(x, y);
        

    //     assertEquals(newChestArmor.getDamageTakenModifier(), 0.5);


    //     basicHelmet newHelmet = new basicHelmet(x, y);

    //     assertEquals(newHelmet.getDamageTakenModifier(), 0.9);


    // }

    // @Test
    // public void testingPairs(){
    //     List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();
    //     Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

    //     tempPath.add(pathtile);
    //     LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);

    //     List<Pair<Integer, Integer>> temp = d.getPath();
    //     Pair<Integer, Integer> testpair = new Pair<>(0, 0);
    //     System.out.println(!temp.contains(testpair));
    // }

}
