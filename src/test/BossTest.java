package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.HeroCastle;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.Character;

public class BossTest {
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

    @Test
    public void testDoggieSpawnCondition() {
        // Create a character and place him at (0, 1)
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Set a Hero Castle where character spawns
        SimpleIntegerProperty cX = new SimpleIntegerProperty(playerChar.getX());
        SimpleIntegerProperty cY = new SimpleIntegerProperty(playerChar.getY());
        HeroCastle heroCastle = new HeroCastle(cX, cY);
        world.addBuilding(heroCastle);

        assertEquals(world.getLoops(), 0);

        // Run tick moves until one loop is finished and check that loop
        // increments
        for (int i = 0; i < 32; i++) {
            world.runTickMoves();
            playerChar.regen(playerChar.getMaxHp());
        }

        assertEquals(world.getLoops(), 1);

        // Run tick moves until world.getLoops() = 20 and check that Doggie
        // never spawns
        int noDoggies = 0;

        while (world.getLoops() != 20) {
            world.runTickMoves();
            playerChar.regen(playerChar.getMaxHp());

            for (BasicEnemy enemy : world.getEnemies()) {
                if (enemy instanceof Doggie) {
                    noDoggies++;
                }
            }
        }

        assertEquals(noDoggies, 0);

        // Check that only one Doggie has spawned
        boolean doggieSpawned = false;
        for (BasicEnemy enemy : world.getEnemies()) {
            if (enemy instanceof Doggie) {
                doggieSpawned = true;
                noDoggies++;
            }
        }

        assertTrue(doggieSpawned);
        assertEquals(noDoggies, 1);
        
    }

    @Test
    public void testElanMuskeSpawnCondition() {
        // Create a character and place him at (0, 1)
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Set a Hero Castle where character spawns
        SimpleIntegerProperty cX = new SimpleIntegerProperty(playerChar.getX());
        SimpleIntegerProperty cY = new SimpleIntegerProperty(playerChar.getY());
        HeroCastle heroCastle = new HeroCastle(cX, cY);
        world.addBuilding(heroCastle);

        assertEquals(world.getLoops(), 0);

        // Run tick moves until one loop is finished and check that loop
        // increments
        for (int i = 0; i < 32; i++) {
            world.runTickMoves();
            playerChar.regen(playerChar.getMaxHp());
        }

        assertEquals(world.getLoops(), 1);

        // Run tick moves until world.getLoops() = 40 and check that Elan
        // never spawns
        int noElans = 0;

        while (world.getLoops() != 40) {
            world.runTickMoves();
            playerChar.regen(playerChar.getMaxHp());

            for (BasicEnemy enemy : world.getEnemies()) {
                if (enemy instanceof ElanMuske) {
                    noElans++;
                }
            }
        }

        assertEquals(noElans, 0);

        // Check that only one Doggie has spawned
        boolean elanSpawned = false;
        for (BasicEnemy enemy : world.getEnemies()) {
            if (enemy instanceof ElanMuske) {
                elanSpawned = true;
                noElans++;
            }
        }

        assertFalse(elanSpawned);
        assertEquals(noElans, 0);
        
        // Set exp to 10000 and run moves once
        playerChar.addXp(10000 - playerChar.getXp());
        world.runTickMoves();

        // Check that Elan has spawned
        for (BasicEnemy enemy : world.getEnemies()) {
            if (enemy instanceof ElanMuske) {
                elanSpawned = true;
                noElans++;
            }
        }

        assertTrue(elanSpawned);
        assertEquals(noElans, 1);
    }
}
