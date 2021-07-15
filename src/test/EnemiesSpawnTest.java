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
        Slug slug1 = new Slug(slugP);

        // Test that slug is on the correct position
        assertEquals(slug1.getX(), 0);
        assertEquals(slug1.getY(), 1);

        // Test that slug has correct properties
        assertEquals(slug1.getSpeed(), 1);
        assertEquals(slug1.getCritChance(), 10);
    }

    @Test
    public void testZombieSpawn() {
        PathPosition zombieP = new PathPosition(0, path);
        Zombie zombie1 = new Zombie(zombieP);

        // Test that zombie is on the correct position
        assertEquals(zombie1.getX(), 0);
        assertEquals(zombie1.getY(), 1);

        // Test that zombie has correct properties
        assertEquals(zombie1.getSpeed(), 0.5);
        assertEquals(zombie1.getCritChance(), 10);
    }

    @Test
    public void testVampireSpawn() {
        PathPosition vampireP = new PathPosition(0, path);
        Vampire vampire1 = new Vampire(vampireP);

        // Test that zombie is on the correct position
        assertEquals(vampire1.getX(), 0);
        assertEquals(vampire1.getY(), 1);

        // Test that zombie has correct properties
        assertEquals(vampire1.getSpeed(), 2);
        assertEquals(vampire1.getCritChance(), 10);
    }
}
