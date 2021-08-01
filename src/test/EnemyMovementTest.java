package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.Campfire;
import unsw.loopmania.Buildings.HeroCastle;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.enemies.Zombie;
import unsw.loopmania.Character;

public class EnemyMovementTest {
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
    // world.setRandomSpawnRate() = 0;

    @Test
    public void testSlugMovement() {
        PathPosition slugP = new PathPosition(0, path);
        Slug slug = new Slug(slugP);
        world.addEnemy(slug);

        // Create a character and place him at (8, 7) - furthest from the slug
        PathPosition charP = new PathPosition(16, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Set a Hero Castle where character spawns
        SimpleIntegerProperty cX = new SimpleIntegerProperty(playerChar.getX());
        SimpleIntegerProperty cY = new SimpleIntegerProperty(playerChar.getY());
        HeroCastle heroCastle = new HeroCastle(cX, cY);
        world.addBuilding(heroCastle);

        // Test that slug is on the correct position
        assertEquals(0, slug.getX());
        assertEquals(1, slug.getY());

        // Simulate the movement of enemies by 1
        world.runTickMoves();

        // Test that slug has moved
        assertEquals(0, slug.getX());
        assertNotEquals(1, slug.getY());

        // Test that slug has moved to (0, 0) or (0, 2)
        assertTrue(slug.getY() == 0 || slug.getY() == 2);
    }
    
    @Test
    public void testZombieMovement() {
        // Create a character so that zombie can have a target
        PathPosition charP = new PathPosition(16, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Set a Hero Castle where character spawns
        SimpleIntegerProperty cX = new SimpleIntegerProperty(playerChar.getX());
        SimpleIntegerProperty cY = new SimpleIntegerProperty(playerChar.getY());
        HeroCastle heroCastle = new HeroCastle(cX, cY);
        world.addBuilding(heroCastle);

        PathPosition zombieP = new PathPosition(0, path);
        Zombie zombie = new Zombie(zombieP, playerChar);
        world.addEnemy(zombie);

        // Test that zombie is on the correct position
        assertEquals(0, zombie.getX());
        assertEquals(1, zombie.getY());

        // Test that zombie is idle for the next tick
        assertEquals(1, zombie.getCountdown());

        // Simulate the movement of enemies + character by 1
        world.runTickMoves();

        // Test that zombie has not moved
        assertEquals(0, zombie.getX());
        assertEquals(1, zombie.getY());

        // Test that zombie will move in the next tick
        assertEquals(0, zombie.getCountdown());

        // Simulate the movement of enemies + character by 1
        world.runTickMoves();

        // Test that zombie has moved
        assertEquals(0, zombie.getX());
        assertNotEquals(1, zombie.getY());

        // Test that zombie has moved to (0, 0) or (0, 2)
        assertTrue(zombie.getY() == 0 || zombie.getY() == 2);

        // Test that zombie is idle for the next tick again
        assertEquals(1, zombie.getCountdown());

        // Plant a new zombie at (5, 0) so that the character is in the
        // detection range
        // NOTE: Character at this point is at coordinates (8, 4)
        PathPosition zombieP2 = new PathPosition(26, path);
        Zombie zombie2 = new Zombie(zombieP2, playerChar);
        world.addEnemy(zombie2);

        // Simulate the movement of enemies + character by 2
        world.runTickMoves();
        world.runTickMoves();
        
        // Test that zombie2 has moved anticlockwise towards the player
        assertEquals(6, zombie2.getX());
        assertEquals(0, zombie2.getY());

        // Simulate the movement of enemies + character by 2
        world.runTickMoves();
        world.runTickMoves();
        
        // Test that zombie2 has moved anticlockwise towards the player
        assertEquals(7, zombie2.getX());
        assertEquals(0, zombie2.getY());

    }

    @Test
    public void testZombieChaseConsistency() {
        List<Zombie> zombiesAntiClockwise = new ArrayList<Zombie>();
        List<Zombie> zombiesClockwise = new ArrayList<Zombie>();

        // Create a character at (5, 0) that is in range of zombies at (0, 1)
        PathPosition charP = new PathPosition(26, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Set a Hero Castle where character spawns
        SimpleIntegerProperty cX = new SimpleIntegerProperty(playerChar.getX());
        SimpleIntegerProperty cY = new SimpleIntegerProperty(playerChar.getY());
        HeroCastle heroCastle = new HeroCastle(cX, cY);
        world.addBuilding(heroCastle);

        // Generate 50 zombies at (0, 1)
        for (int i = 0; i < 50; i++) {
            PathPosition zombieP = new PathPosition(0, path);
            Zombie zombie = new Zombie(zombieP, playerChar);
            zombiesAntiClockwise.add(zombie);
            world.addEnemy(zombie);
        }

        // Generate 50 zombies at (8, 0)
        for (int i = 0; i < 50; i++) {
            PathPosition zombieP = new PathPosition(23, path);
            Zombie zombie = new Zombie(zombieP, playerChar);
            zombiesClockwise.add(zombie);
            world.addEnemy(zombie);
        }

        // Simulate the world for two ticks
        world.runTickMoves();
        world.runTickMoves();

        // Test that all 50 zombies in anti clockwise are now on (0, 0)
        for (Zombie zombie : zombiesAntiClockwise) {
            assertEquals(0, zombie.getX());
            assertEquals(0, zombie.getY());
        }

        // Test that all 50 zombies in clockwise are now on (8, 3)
        for (Zombie zombie : zombiesClockwise) {
            assertEquals(7, zombie.getX());
            assertEquals(0, zombie.getY());
        }

    }

    @Test
    public void testVampireMovement() {
        PathPosition vampireP = new PathPosition(0, path);
        Vampire vampire = new Vampire(vampireP, world);
        world.addEnemy(vampire);

        // Create a character and place him at (0, 5)
        PathPosition charP = new PathPosition(4, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Set a Hero Castle where character spawns
        SimpleIntegerProperty cX = new SimpleIntegerProperty(playerChar.getX());
        SimpleIntegerProperty cY = new SimpleIntegerProperty(playerChar.getY());
        HeroCastle heroCastle = new HeroCastle(cX, cY);
        world.addBuilding(heroCastle);

        // Test that vampire is on the correct position
        assertEquals(0, vampire.getX());
        assertEquals(1, vampire.getY());

        // Simulate the movement of enemies by 1
        world.runTickMoves();

        // Test that vampire has now moved 2 spaces anticlockwise
        assertEquals(1, vampire.getX());
        assertEquals(0, vampire.getY());

        // Simulate the movement of enemies by 5 and test that vampire has moved
        // 2 tiles anticlockwise per tick
        for (int i = 0; i < 5; i++) {
            world.runTickMoves();
        }

        // Test that vampire is at the coordinates (8, 3)
        assertEquals(8, vampire.getX());
        assertEquals(3, vampire.getY());

        // Add campfire to (7, 5) -> assums range of campfire is 1
        SimpleIntegerProperty x = new SimpleIntegerProperty(7);
        SimpleIntegerProperty y = new SimpleIntegerProperty(5);
        Campfire campfire = new Campfire(x, y);
        world.addBuilding(campfire);

        // Test that the vampire's direction is set to counter-clockwise
        assertFalse(vampire.isMovingClockwise());

        // Simulate the movement of enemies by 1 so that the vampire is now
        // within the range of the campfire
        world.runTickMoves();

        // Check that vampire is at position (8, 5)
        assertEquals(8, vampire.getX());
        assertEquals(5, vampire.getY());

        // Check that the vampire's direction has reversed (check applied after
        // moving)
        assertTrue(vampire.isMovingClockwise());

        // Simulate the movement of enemies by 1
        world.runTickMoves();

        // Check that the vampire has moved clockwise
        assertEquals(8, vampire.getX());
        assertEquals(3, vampire.getY());

        // Simulate the movement of enemies by 1
        world.runTickMoves();

        // Check that the vampire has moved clockwise
        assertEquals(8, vampire.getX());
        assertEquals(1, vampire.getY());
    }

    @Test
    public void testVampireBounces() {
        PathPosition vampireP = new PathPosition(0, path);
        Vampire vampire = new Vampire(vampireP, world);
        world.addEnemy(vampire);

        // Create a character and place him at (0, 5)
        PathPosition charP = new PathPosition(4, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Set a Hero Castle where character spawns
        SimpleIntegerProperty cX = new SimpleIntegerProperty(playerChar.getX());
        SimpleIntegerProperty cY = new SimpleIntegerProperty(playerChar.getY());
        HeroCastle heroCastle = new HeroCastle(cX, cY);
        world.addBuilding(heroCastle);

        // Add campfire to (3, 1) -> assumes range of campfire is 1
        SimpleIntegerProperty x1 = new SimpleIntegerProperty(3);
        SimpleIntegerProperty y1 = new SimpleIntegerProperty(1);
        Campfire campfire1 = new Campfire(x1, y1);
        world.addBuilding(campfire1);

        // Add campfire to (1, 3) -> assumes range of campfire is 1
        SimpleIntegerProperty x2 = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y2 = new SimpleIntegerProperty(3);
        Campfire campfire2 = new Campfire(x2, y2);
        world.addBuilding(campfire2);

        // Simulate movement and test that vampire is moving anticlockwise
        world.runTickMoves();
        assertFalse(vampire.isMovingClockwise());
        assertEquals(1, vampire.getX());
        assertEquals(0, vampire.getY());

        // Simulate movement and test that vampire is now preparing to move
        // clockwise
        world.runTickMoves();
        assertTrue(vampire.isMovingClockwise());
        assertEquals(3, vampire.getX());
        assertEquals(0, vampire.getY());

        // Simulate movement and test that vampire is moving clockwise
        world.runTickMoves();
        assertTrue(vampire.isMovingClockwise());
        assertEquals(1, vampire.getX());
        assertEquals(0, vampire.getY());

        // Simulate movement and test that vampire is moving clockwise
        world.runTickMoves();
        assertTrue(vampire.isMovingClockwise());
        assertEquals(0, vampire.getX());
        assertEquals(1, vampire.getY());

        // Simulate movement and test that vampire is now preparing to move
        // anticlockwise
        world.runTickMoves();
        assertFalse(vampire.isMovingClockwise());
        assertEquals(0, vampire.getX());
        assertEquals(3, vampire.getY());

        // Simulate movement and test that vampire is moving anticlockwise
        world.runTickMoves();
        assertFalse(vampire.isMovingClockwise());
        assertEquals(0, vampire.getX());
        assertEquals(1, vampire.getY());
    }

    @Test
    public void testDoggieMovement() {
        PathPosition doggieP = new PathPosition(0, path);
        Doggie doggie = new Doggie(doggieP);
        world.addEnemy(doggie);

        // Create a character and place him at (8, 7) - furthest from the doggie
        PathPosition charP = new PathPosition(16, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Set a Hero Castle where character spawns
        SimpleIntegerProperty cX = new SimpleIntegerProperty(playerChar.getX());
        SimpleIntegerProperty cY = new SimpleIntegerProperty(playerChar.getY());
        HeroCastle heroCastle = new HeroCastle(cX, cY);
        world.addBuilding(heroCastle);

        // Test that doggie is on the correct position
        assertEquals(0, doggie.getX());
        assertEquals(1, doggie.getY());

        // Simulate the movement of enemies by 1
        world.runTickMoves();

        // Test that doggie has moved
        assertEquals(0, doggie.getX());
        assertNotEquals(1, doggie.getY());

        // Test that doggie has moved to (0, 0) or (0, 2)
        assertTrue(doggie.getY() == 0 || doggie.getY() == 2);
    }

    @Test
    public void testElanMuskeMovement() {
        PathPosition elanP = new PathPosition(0, path);
        ElanMuske elan = new ElanMuske(elanP);
        world.addEnemy(elan);

        // Create a character and place him at (8, 7) - furthest from elan
        PathPosition charP = new PathPosition(16, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Set a Hero Castle where character spawns
        SimpleIntegerProperty cX = new SimpleIntegerProperty(playerChar.getX());
        SimpleIntegerProperty cY = new SimpleIntegerProperty(playerChar.getY());
        HeroCastle heroCastle = new HeroCastle(cX, cY);
        world.addBuilding(heroCastle);

        // Test that elan is on the correct position
        assertEquals(0, elan.getX());
        assertEquals(1, elan.getY());

        // Simulate the movement of enemies by 1
        world.runTickMoves();

        // Test that elan has moved
        assertEquals(0, elan.getX());
        assertNotEquals(1, elan.getY());

        // Test that elan has moved to (0, 0) or (0, 2)
        assertTrue(elan.getY() == 0 || elan.getY() == 2);
    }
}
