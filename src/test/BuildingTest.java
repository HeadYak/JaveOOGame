package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
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
        c.setHp(c.getMaxHp()-21);
        village.heal(c);
        assertEquals(c.getHp(), c.getMaxHp()-1);
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

    @Test
    public void testBuildingInteract() {
        List<Pair<Integer, Integer>> path = Arrays.asList(
            new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3),
            new Pair<>(0, 4), new Pair<>(0, 5), new Pair<>(0, 6),
            new Pair<>(0, 7), new Pair<>(0, 8), new Pair<>(1, 8),
            new Pair<>(2, 8), new Pair<>(3, 8), new Pair<>(4, 8),
            new Pair<>(5, 8), new Pair<>(6, 8), new Pair<>(7, 8),
            new Pair<>(8, 8), new Pair<>(8, 7), new Pair<>(8, 6),
            new Pair<>(8, 5), new Pair<>(8, 4), new Pair<>(8, 3),
            new Pair<>(8, 2), new Pair<>(8, 1), new Pair<>(8, 0),
            new Pair<>(7, 0), new Pair<>(6, 0), new Pair<>(5, 0),
            new Pair<>(4, 0), new Pair<>(3, 0), new Pair<>(2, 0),
            new Pair<>(1, 0), new Pair<>(0, 0));

        LoopManiaWorld world = new LoopManiaWorld(9, 9, path);
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);
        assertEquals(world.getBuildingEntities().size(), 1);
        SimpleIntegerProperty x = new SimpleIntegerProperty(0);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);

        SimpleIntegerProperty x1 = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y1 = new SimpleIntegerProperty(2);
        SimpleIntegerProperty x2 = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y2 = new SimpleIntegerProperty(3);
        playerChar.setHp(80);
        Barracks barrack = new Barracks(x, y);
        world.addBuilding(barrack);
        Village village = new Village(x, y);

        VampireCastleBuilding vCastleBuilding = new VampireCastleBuilding(x2, y2);
        world.addBuilding(vCastleBuilding);
        ZombiePit zPit = new ZombiePit(x2, y2);
        world.addBuilding(zPit);
        world.addBuilding(village);
        Campfire camp = new Campfire(x1, y1);
        world.addBuilding(camp);
        assertEquals(playerChar.getX(), 0);
        assertEquals(playerChar.getY(), 1);
        Trap trap = new Trap(x, y);
        world.addBuilding(trap);
        Tower t = new Tower(x1, y1);
        world.addBuilding(t);
        world.runTickMoves();
        assertEquals(playerChar.getX(), 0);
        assertEquals(playerChar.getY(), 2);
        assertTrue(playerChar.getIsSupported());
        assertEquals(playerChar.getAllyList().size(), 1);
        assertEquals(playerChar.getHp(), 100);
        assertTrue(playerChar.getBuffStatus());
        
        world.newLoop();
        world.newLoop();
        world.newLoop();
        world.newLoop();
        assertEquals(world.getLoops(), 4);
        while (!(playerChar.getX() == 0 && playerChar.getY() == 1)) {
            world.runTickMoves();
        }
        assertEquals(playerChar.getX(), 0);
        assertEquals(playerChar.getY(), 1);
        assertTrue(world.getEnemies().size() >= 2);
        
    }

}
