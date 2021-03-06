package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.enemies.Doggie;
import unsw.loopmania.enemies.ElanMuske;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.enemies.Zombie;

public class EnemiesSpawnTest {
    List<Pair<Integer, Integer>> path = Arrays.asList(
            new Pair<>(0, 1), new Pair<>(0, 2));

    LoopManiaWorld world = new LoopManiaWorld(4, 4, path);
    // world.setRandomSpawnRate() = 0;

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
        assertEquals(slug.getMaxHp(), 100);
        assertEquals(slug.getDmg(), 1);
    }

    @Test
    public void testZombieSpawn() {
        // Test that there are no enemies
        assertEquals(world.getEnemies().size(), 0);

        // Create a character so that zombie can have a target
        PathPosition charP = new PathPosition(1, path);
        Character playerChar = new Character(charP);

        // Spawn a zombie
        PathPosition zombieP = new PathPosition(0, path);
        Zombie zombie = new Zombie(zombieP, playerChar);
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
        assertEquals(zombie.getCountdown(), 1);
        assertEquals(zombie.getHp(), 200);
        assertEquals(zombie.getMaxHp(), 200);
        assertEquals(zombie.getDmg(), 5);
    }

    @Test
    public void testVampireSpawn() {
        // Test that there are no enemies
        assertEquals(world.getEnemies().size(), 0);

        // Spawn a vampire
        PathPosition vampireP = new PathPosition(0, path);
        Vampire vampire = new Vampire(vampireP, world);
        world.addEnemy(vampire);

        // Test that there is now 1 enemy
        assertEquals(world.getEnemies().size(), 1);

        // Test that zombie is on the correct position
        assertEquals(vampire.getX(), 0);
        assertEquals(vampire.getY(), 1);

        // Test that zombie has correct properties
        assertEquals(vampire.getMoveSpeed(), 2);
        assertEquals(vampire.getCritChance(), 0.2);
        assertEquals(vampire.getBattleRadius(), 2);
        assertEquals(vampire.getSupportRadius(), 3);
        assertFalse(vampire.isMovingClockwise());
        assertFalse(vampire.isBuffed());
        assertEquals(vampire.getBuffDuration(), 0);
        assertEquals(vampire.getHp(), 300);
        assertEquals(vampire.getMaxHp(), 300);
        assertEquals(vampire.getDmg(), 8);
    }

    @Test
    public void testDoggieSpawn() {
        // Test that there are no enemies
        assertEquals(world.getEnemies().size(), 0);

        // Spawn a Doggie
        PathPosition doggieP = new PathPosition(0, path);
        Doggie doggie = new Doggie(doggieP);
        world.addEnemy(doggie);

        // Test that there is now 1 enemy
        assertEquals(world.getEnemies().size(), 1);

        // Test that doggie is on the correct position
        assertEquals(doggie.getX(), 0);
        assertEquals(doggie.getY(), 1);

        // Test that doggie has correct properties
        assertEquals(doggie.getMoveSpeed(), 1);
        assertEquals(doggie.getCritChance(), 0.1);
        assertEquals(doggie.getBattleRadius(), 1);
        assertEquals(doggie.getSupportRadius(), 1);
        assertEquals(doggie.getHp(), 600);
        assertEquals(doggie.getMaxHp(), 600);
        assertEquals(doggie.getDmg(), 15);
    }

    @Test
    public void testElanMuskeSpawn() {
        // Test that there are no enemies
        assertEquals(world.getEnemies().size(), 0);

        // Spawn an Elan Muske
        PathPosition elanP = new PathPosition(0, path);
        ElanMuske elan = new ElanMuske(elanP);
        world.addEnemy(elan);

        // Test that there is now 1 enemy
        assertEquals(world.getEnemies().size(), 1);

        // Test that elan is on the correct position
        assertEquals(elan.getX(), 0);
        assertEquals(elan.getY(), 1);

        // Test that elan has correct properties
        assertEquals(elan.getMoveSpeed(), 1);
        assertEquals(elan.getCritChance(), 0.1);
        assertEquals(elan.getBattleRadius(), 1);
        assertEquals(elan.getSupportRadius(), 1);
        assertEquals(elan.getHp(), 1000);
        assertEquals(elan.getDmg(), 20);
    }

    @Test
    public void testEnemiesStack() {
        // Test that there are no enemies
        assertEquals(world.getEnemies().size(), 0);

        // Spawn 10 slugs on the same path position
        PathPosition slugP = new PathPosition(0, path);
        for (int i = 0; i < 10; i++) {
            world.addEnemy(new Slug(slugP));
        }

        // Test that there are 10 slugs in the world (i.e. they are allowed to
        // share a space)
        assertEquals(world.getEnemies().size(), 10);
    }
}
