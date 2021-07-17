package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.enemies.*;
import unsw.loopmania.Character;

public class CombatTest {
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
    public void testBattleWithSlug() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);

        // Create new slug on same tile as player
        PathPosition slugP = new PathPosition(0, path);
        Slug slug = new Slug(slugP);

        // Simulate the world by 1 tick
        world.runTickMoves();

        // Test that combat has occurred by checking that the slug or the player
        // is now dead
        assertTrue(world.getEnemies().size() == 0 || charP.getHp() == 0);

    }

    @Test
    public void testBattleWithZombie() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);

        // Create new zombie on same tile as player
        PathPosition zombieP = new PathPosition(0, path);
        Zombie zombie = new Zombie(zombieP);

        // Simulate the world by 1 tick
        world.runTickMoves();

        // Test that combat has occurred by checking that the vampire or the
        // player is now dead
        assertTrue(world.getEnemies().size() == 0 || charP.getHp() == 0);
    }

    @Test
    public void testBattleWithVampire() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);

        // Create new vampire on same tile as player
        PathPosition vampireP = new PathPosition(0, path);
        Vampire vampire = new Vampire(vampireP);

        // Simulate the world by 1 tick
        world.runTickMoves();

        // Test that combat has occurred by checking that the slug or the player
        // is now dead
        assertTrue(world.getEnemies().size() == 0 || charP.getHp() == 0);
    }

    @Test
    public void testPlayerDefeat() {
        // need to decide on stats before implementing
    }
}
