package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.Buildings.Barracks;
import unsw.loopmania.Buildings.Campfire;
import unsw.loopmania.Buildings.HeroCastle;
import unsw.loopmania.Buildings.Tower;
import unsw.loopmania.Buildings.Trap;
import unsw.loopmania.Buildings.VampireCastleBuilding;
import unsw.loopmania.Buildings.Village;
import unsw.loopmania.Buildings.ZombiePit;
import unsw.loopmania.enemies.Slug;


public class BuildingTest {
    @Test
    public void buffTest(){
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);


        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);

        assertFalse(c.getBuffStatus());

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Campfire campfire = new Campfire(x, y);
        campfire.buff(c);
        
        assertTrue(c.getBuffStatus());
    }

    @Test
    public void supportTest(){
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);


        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);

        assertFalse(c.getIsSupported());

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Tower tower = new Tower(x, y);
        tower.support(c);
        
        assertTrue(c.getIsSupported());
    }

    @Test
    public void villageTest() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);


        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Village village = new Village(x, y);
        assertEquals(c.getHp(), c.getMaxHp());
        c.setHp(79);
        assertEquals(c.getHp(), 79);
        village.heal(c);
        assertEquals(c.getHp(), 99);
        village.heal(c);
        assertEquals(c.getHp(), c.getMaxHp());
    }
    
    @Test
    public void barracksTest() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);


        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Barracks barracks = new Barracks(x, y);
        assertEquals(c.getAllyList().size(), 0);
        barracks.addAlly(c);
        assertEquals(c.getAllyList().size(), 1);
    }

    @Test
    public void heroCastleTest() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);


        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        HeroCastle heroCastle = new HeroCastle(x, y);
        assertEquals(heroCastle.getShop().size(), 3);
        c.addGold(300);
        heroCastle.purchaseItem(c, heroCastle.getShop().get(0));
        assertEquals(heroCastle.getShop().size(), 2);
        assertEquals(c.getInventory().size(), 1);
    }

    @Test
    public void trapTest() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        PathPosition temp = new PathPosition(0, tempPath);
        Slug slug = new Slug(temp);

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Trap trap = new Trap(x, y);
        int oldHp = slug.getHp();
        trap.trap(slug);
        assertTrue(slug.getHp() < oldHp);
    }

    @Test
    public void spawnTest() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);


        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();
        VampireCastleBuilding vampireCastle = new VampireCastleBuilding(x, y);
        ZombiePit zPit = new ZombiePit(x, y);
        assertEquals(d.getLoops(), 0);
        assertEquals(d.getEnemies().size(), 0);
        d.newLoop();
        d.newLoop();
        d.newLoop();
        d.newLoop();
        d.newLoop();
        assertEquals(d.getLoops(), 5);
        vampireCastle.spawn(d);
        zPit.spawn(d);
        assertEquals(d.getEnemies().size(), 2);
    }


}
