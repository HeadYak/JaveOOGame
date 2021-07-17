package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.enemies.Zombie;

public class EnemiesSpawnTest {
    List<Pair<Integer, Integer>> path = Arrays.asList(
            new Pair<>(0, 1), new Pair<>(0, 2));

    LoopManiaWorld world = new LoopManiaWorld(4, 4, path);

    @Test
    public void testSlugSpawn() {
        // Test that there are no enemies
        assertEquals(world.getEnemies().size(), 0);

        // Spawn a slug
        PathPosition slugP = new PathPosition(0, path);
        Slug slug = new Slug(slugP);
        world.addEnemy(slug);

        // Test that there is now 1 enemy
        assertEquals(world.getEnemies().size(), 1);

        // Test that slug is on the correct position
        assertEquals(slug.getX(), 0);
        assertEquals(slug.getY(), 1);

        // Test that DEFAULT slug has correct properties
        assertEquals(slug.getMoveSpeed(), 1);
        assertEquals(slug.getCritChance(), 0.1);
        assertEquals(slug.getBattleRadius(), 1);
        assertEquals(slug.getSupportRadius(), 1);
        assertEquals(slug.getHp(), 100);
    }

    @Test
    public void testZombieSpawn() {
        // Test that there are no enemies
        assertEquals(world.getEnemies().size(), 0);

        // Spawn a zombie
        PathPosition zombieP = new PathPosition(0, path);
        Zombie zombie = new Zombie(zombieP);
        world.addEnemy(zombie);

        // Test that there is now 1 enemy
        assertEquals(world.getEnemies().size(), 1);

        // Test that zombie is on the correct position
        assertEquals(zombie.getX(), 0);
        assertEquals(zombie.getY(), 1);

        // Test that zombie has correct properties
        assertEquals(zombie.getMoveSpeed(), 0.5);
        assertEquals(zombie.getCritChance(), 0.1);
        assertEquals(zombie.getBattleRadius(), 1);
        assertEquals(zombie.getSupportRadius(), 2);
        assertEquals(zombie.getDetectionRadius(), 5);
        assertEquals(zombie.getCountdown(), 0);
        assertFalse(zombie.isMoving());
        assertEquals(zombie.getHp(), 100);
    }

    @Test
    public void testVampireSpawn() {
        // Test that there are no enemies
        assertEquals(world.getEnemies().size(), 0);

        // Spawn a vampire
        PathPosition vampireP = new PathPosition(0, path);
        Vampire vampire = new Vampire(vampireP);
        world.addEnemy(vampire);

        // Test that there is now 1 enemy
        assertEquals(world.getEnemies().size(), 1);

        // Test that zombie is on the correct position
        assertEquals(vampire.getX(), 0);
        assertEquals(vampire.getY(), 1);

        // Test that zombie has correct properties
        assertEquals(vampire.getMoveSpeed(), 2);
        assertEquals(vampire.getCritChance(), 0.1);
        assertEquals(vampire.getBattleRadius(), 2);
        assertEquals(vampire.getSupportRadius(), 3);
        assertFalse(vampire.isMovingClockwise());
        assertFalse(vampire.isBuffed());
        assertEquals(vampire.getBuffDuration(), 0);
        assertEquals(vampire.getHp(), 100);
    }

    @Test
    public void testEnemiesStack() {
        // TODO: not sure how to implement yet

        assertEquals(1, 0);
    }
}
