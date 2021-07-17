package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.ChaCha20ParameterSpec;

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
        assertFalse(c.getMobSupported());

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Tower tower = new Tower(x, y);
        VampireCastleBuilding vampireCastle = new VampireCastleBuilding(x, y);
        tower.support(c);
        vampireCastle.support(c);
        
        assertTrue(c.getIsSupported());
        assertTrue(c.getMobSupported());
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
        assertEquals(c.getLoop(), 0);
        heroCastle.interact(c);
        assertEquals(c.getLoop(), 1);
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
        trap.trap(slug);
        assertTrue(slug.getHP() < slug.getMaxHp());
    }

    @Test
    public void spawnTest() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);


        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();
        VampireCastleBuilding vampireCastle = new VampireCastleBuilding(x, y);
        ZombiePit zPit = new ZombiePit(x, y);
        assertEquals(c.getLoop(), 0);
        assertEquals(d.get, actual);
        c.newLoop();
        c.newLoop();
        c.newLoop();
        c.newLoop();
        c.newLoop();
        assertEquals(c.getLoop(), 5);
        vampireCastle.spawn(c.getLoop(), d);
        zPit.spawn(c.getLoop(), d);
        assertEquals(d.getEnemieList().size(), 2);
    }


}
