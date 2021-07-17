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

    @Test
    public void testSlugMovement() {
        PathPosition slugP = new PathPosition(0, path);
        Slug slug = new Slug(slugP);
        world.addEnemy(slug);

        // Create a character and place him at (8, 7) - furthest from the slug
        PathPosition charP = new PathPosition(16, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

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

        // Simulate the movement of enemies + character by 1
        world.runTickMoves();

        // Test that zombie has not moved
        assertEquals(0, zombie.getX());
        assertEquals(1, zombie.getY());

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
        assertEquals(5, zombie2.getX());
        assertEquals(0, zombie2.getY());

        

    }

    @Test
    public void testZombieChaseConsistency() {
        List<Zombie> zombies = new ArrayList<Zombie>();
    }

    @Test
    public void testVampireMovement() {
        PathPosition vampireP = new PathPosition(0, path);
        Vampire vampire = new Vampire(vampireP);
        world.addEnemy(vampire);

        // Create a character and place him at (0, 5)
        PathPosition charP = new PathPosition(4, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

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

        // Test that the vampire's direction is set to counter-clockwise
        assertFalse(vampire.isMovingClockwise());

        // Simulate the movement of enemies by 1 so that the vampire is now
        // within the range of the campfire
        world.runTickMoves();

        // Check that vampire is at position (3, 1)
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
    }
}
