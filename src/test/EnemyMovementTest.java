package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
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
        PathPosition charP = new PathPosition(25, path);
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
        PathPosition charP = new PathPosition(25, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // If 
        PathPosition zombieP = new PathPosition(0, path);
        Zombie zombie = new Zombie(zombieP, playerChar);
        world.addEnemy(zombie);

        // Test that zombie is on the correct position
        assertEquals(0, zombie.getX());
        assertEquals(1, zombie.getY());

        // Simulate the movement of enemies 10 times and test that zombie never
        // moves
        for (int i = 0; i < 10; i++) {
            world.runTickMoves();

            // Test that zombie has not moved
            assertEquals(0, zombie.getX());
            assertEquals(1, zombie.getY());
        }

        // Simulate the movement of enemies + character by 1
        world.runTickMoves();

        // Test that zombie has not moved (as it has only just entered the range
        // of player)
        assertEquals(0, zombie.getX());
        assertEquals(1, zombie.getY());

        // Test that the zombie is preparing to move
        assertTrue(zombie.isMoving());
        assertEquals(zombie.getMoveCountdown(), 0);

        // Simulate the movement of enemeis + character by 1
        world.runTickMoves();

        // Test that zombie has now moved towards the player
        assertEquals(0, zombie.getX());
        assertEquals(0, zombie.getY());

        // Simulate the movement of enemeis + character by 1
        world.runTickMoves();

        // Test that zombie has not moved (as it is moving at 0.5 speed)
        assertEquals(0, zombie.getX());
        assertEquals(0, zombie.getY());

        // Simulate the movement of enemeis + character by 1
        world.runTickMoves();

        // Test that either player or zombie is missing (as zombie has moved
        // again into combat radius)
        assertTrue(world.getEnemies().size() == 0 || charP.getHp() == 0);

    }

    @Test
    public void testVampireMovement() {
        PathPosition vampireP = new PathPosition(0, path);
        Vampire vampire = new Vampire(vampireP);
        world.addEnemy(vampire);

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

        assertEquals(8, vampire.getX());
        assertEquals(3, vampire.getY());

        // Add campfire to (2, 2)
        // TODO: Add campfire to (2, 2) -> assumes range of campfire is 1
        Campfire campfire = new Campfire(7, 5);

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
