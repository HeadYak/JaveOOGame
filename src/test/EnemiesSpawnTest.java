package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;

public class EnemiesSpawnTest {
    List<Pair<Integer, Integer>> path = Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 2));

    @Test
    public void testSlugSpawn() {
        PathPosition slugP = new PathPosition(0, path);
        Slug slug = new Slug(slugP);

        // Test that slug is on the correct position
        assertEquals(slug.getX(), 0);
        assertEquals(slug.getY(), 1);

        // Test that slug has correct properties
        assertEquals(slug.getMoveSpeed(), 1);
        assertEquals(slug.getCritChance(), 10);
    }

    @Test
    public void testZombieSpawn() {
        PathPosition zombieP = new PathPosition(0, path);
        Zombie zombie = new Zombie(zombieP);

        // Test that zombie is on the correct position
        assertEquals(zombie.getX(), 0);
        assertEquals(zombie.getY(), 1);

        // Test that zombie has correct properties
        assertEquals(zombie.getMoveSpeed(), 1);
        assertEquals(zombie.getCritChance(), 10);
        assertFalse(zombie.isMoving());
        assertEquals(zombie.getMoveCountdown(), 1);
    }

    @Test
    public void testVampireSpawn() {
        PathPosition vampireP = new PathPosition(0, path);
        Vampire vampire = new Vampire(vampireP);

        // Test that zombie is on the correct position
        assertEquals(vampire.getX(), 0);
        assertEquals(vampire.getY(), 1);

        // Test that zombie has correct properties
        assertEquals(vampire.getMoveSpeed(), 2);
        assertEquals(vampire.getCritChance(), 10);
        assertFalse(vampire.isMovingClockwise());
        assertFalse(vampire.isBuffed());
        assertEquals(vampire.getBuffDuration(), 0);
    }
}
