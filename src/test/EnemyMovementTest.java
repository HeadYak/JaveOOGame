package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Character;

public class EnemyMovementTest {
    List<Pair<Integer, Integer>> path = Arrays.asList(
            new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3),
            new Pair<>(1, 3), new Pair<>(2, 3), new Pair<>(3, 3),
            new Pair<>(3, 2), new Pair<>(3, 1), new Pair<>(3, 0),
            new Pair<>(2, 0), new Pair<>(1, 0), new Pair<>(0, 0));

    LoopManiaWorld world = new LoopManiaWorld(4, 4, new ArrayList<>());

    @Test
    public void testSlugMovement() {
        PathPosition slugP = new PathPosition(0, path);
        Slug slug = new Slug(slugP);

        // Test that slug is on the correct position
        assertEquals(0, slug.getX());
        assertEquals(1, slug.getY());

        // Simulate the movement of enemies by 1
        world.runTickMoves();

        // Test that slug has moved
        assertEquals(0, slug.getX());
        assertNotEquals(1, slug.getY());

        // Test that slug has moved to (0, 0) or (0, 2)
        assertTrue(slug.getY().equals(0) || slug.getY().equals(2));
    }
    
    @Test
    public void testZombieMovement() {
        PathPosition zombieP = new PathPosition(0, path);
        Zombie zombie = new Zombie(zombieP);

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

        // Create a character and place him 7 tiles counter-clockwise from the
        // zombie
        PathPosition charP = new PathPosition(6, path);
        Character playerChar = new Character(charP);

        // Simulate the movement of enemies + character by 1
        world.runTickMoves();

        // Test that zombie has not moved (as it has only just entered the range
        // of player)
        assertEquals(0, zombie.getX());
        assertEquals(1, zombie.getY());

        // Simulate the movement of enemeis + character by 1
        world.runTickMoves();

        // Test that zombie has now moved towards the player
        assertEquals(0, zombie.getX());
        assertEquals(0, zombie.getY());

        // Simulate the movement of enemeis + character by 1
        world.runTickMoves();

        // Test that zombie has not moved (as it is moving at a slower speed)
        assertEquals(0, zombie.getX());
        assertEquals(0, zombie.getY());

        // Simulate the movement of enemeis + character by 1
        world.runTickMoves();

        // Test that zombie is now on top of the character
        assertEquals(1, zombie.getX());
        assertEquals(0, zombie.getY());
    }
}
