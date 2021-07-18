package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.Tower;
import unsw.loopmania.BattleManager;
import unsw.loopmania.Items.Weapons.Stake;
import unsw.loopmania.Items.Weapons.Sword;
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
    // world.setRandomSpawnRate() = 0;

    @Test
    public void testBattleWithSlug() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Equip character with sword
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Sword sword = new Sword(x, y);
        playerChar.setWeapon(sword);

        // Create new slug on same tile as player
        Slug slug = new Slug(charP);
        world.addEnemy(slug);

        // Simulating battle manually to test each component of battle is
        // working correctly
        BattleManager bm = world.getBattleManager();
        // bm.lowerCrit(BasicEnemy.class, 1);

        // Update battle manager with potential enemy and character
        bm.update(world);
        assertEquals(bm.getAllies().size(), 0);
        assertEquals(bm.getSupportEnemies().size(), 0);

        assertEquals(bm.getBattleEnemies().size(), 1);
        assertTrue(bm.getBattleEnemies().get(0) instanceof Slug);

        // Do a single tick of battle
        bm.runTickBattle();

        // Check that the damage done by character and slug are the correct
        // behaviour
        int newCharHp = 300 - (slug.getDmg() * 4);
        assertEquals(playerChar.getHp(), newCharHp);
        int newSlugHp = 100 - ((playerChar.getDmg() +
                sword.getDamageValue()) * 4);
        assertEquals(slug.getHp(), newSlugHp);

        // Complete battle
        bm.battle();

        // Test that combat has occurred by checking that the slug is now dead
        // NOTE: Mathematically impossible for slug to win
        assertTrue(world.getEnemies().get(0).getHp() < 0);

    }

    @Test
    public void testBattleWithZombie() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Equip character with sword
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Sword sword = new Sword(x, y);
        playerChar.setWeapon(sword);

        // Create new zombie on same tile as player
        Zombie zombie = new Zombie(charP, playerChar);
        world.addEnemy(zombie);

        // Simulating battle manually to test each component of battle is
        // working correctly
        BattleManager bm = world.getBattleManager();
        // bm.lowerCrit(BasicEnemy.class, 1);

        // Update battle manager with potential enemy and character
        bm.update(world);
        assertEquals(bm.getAllies().size(), 0);
        assertEquals(bm.getSupportEnemies().size(), 0);

        assertEquals(bm.getBattleEnemies().size(), 1);
        assertTrue(bm.getBattleEnemies().get(0) instanceof Zombie);

        // Do a single tick of battle
        bm.runTickBattle();

        // Check that the damage done by character and zombie are the correct
        // behaviour
        int newCharHp = 300 - (zombie.getDmg() * 4);
        assertEquals(playerChar.getHp(), newCharHp);
        int newZombieHp = 200 - ((playerChar.getDmg() +
                sword.getDamageValue()) * 4);
        assertEquals(zombie.getHp(), newZombieHp);

        // Complete battle
        bm.battle();

        // Test that combat has occurred by checking that the zombie is now dead
        // NOTE: Mathematically impossible for zombie to win
        assertTrue(world.getEnemies().get(0).getHp() < 0);
    }

    @Test
    public void testBattleWithVampire() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Equip character with sword
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Sword sword = new Sword(x, y);
        playerChar.setWeapon(sword);

        // Create new vampire on same tile as player
        Vampire vampire = new Vampire(charP, world);
        world.addEnemy(vampire);

        // Simulating battle manually to test each component of battle is
        // working correctly
        BattleManager bm = world.getBattleManager();
        // bm.lowerCrit(BasicEnemy.class, 1);

        // Update battle manager with potential enemy and character
        bm.update(world);
        assertEquals(bm.getAllies().size(), 0);
        assertEquals(bm.getSupportEnemies().size(), 0);

        assertEquals(bm.getBattleEnemies().size(), 1);
        assertTrue(bm.getBattleEnemies().get(0) instanceof Vampire);

        // Do a single tick of battle
        bm.runTickBattle();

        // Check that the damage done by character and vampire are the correct
        // behaviour
        int newCharHp = 300 - (vampire.getDmg() * 4);
        assertEquals(playerChar.getHp(), newCharHp);
        int newVampireHp = 300 - ((playerChar.getDmg() +
                sword.getDamageValue()) * 4);
        assertEquals(vampire.getHp(), newVampireHp);

        // Equip a stake
        Stake stake = new Stake(x, y);
        playerChar.setWeapon(stake);

        // Do a single tick of battle
        bm.runTickBattle();

        // Check that the damage done by character and vampire are the correct
        // behaviour
        newCharHp = newCharHp - (vampire.getDmg() * 4);
        assertEquals(playerChar.getHp(), newCharHp);
        newVampireHp = newVampireHp - ((playerChar.getDmg() +
                stake.getDamageValue()) * 4);
        assertEquals(vampire.getHp(), newVampireHp);

        // Complete battle
        bm.battle();

        // Test that combat has occurred by checking that the slug is now dead
        // NOTE: Mathematically impossible for slug to win
        assertTrue(world.getEnemies().get(0).getHp() < 0);
    }

    @Test
    public void testAlliesAndBuildings() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Spawn a vampire to fight
        Vampire vampire = new Vampire(charP, world);
        world.addEnemy(vampire);

        // Spawn a tower next to character
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(1);
        Tower tower = new Tower(x, y);
        world.addBuilding(tower);

        // Simulating battle manually to test each component of battle is
        // working correctly
        BattleManager bm = world.getBattleManager();
        /// bm.lowerCrit(BasicEnemy.class, 1);

        // Update battle manager with potential enemy and do single tick of
        // battle
        bm.update(world);
        bm.runTickBattle();

        // Check that the damage done by character and tower are the correct
        // behaviour
        int newVampireHp = 300 - (playerChar.getDmg() * 4 - 100);
        assertEquals(vampire.getHp(), newVampireHp);

        bm.battle();

    }

    @Test
    public void testEnemySupport() {

    }

    @Test
    public void testAutoBattle() {

    }

    @Test
    public void testBlockBehaviour() {

    }

    @Test
    public void testCrits() {
        
    }

    @Test
    public void testPlayerDefeat() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Spawn 10 vampires on the same path position as character
        for (int i = 0; i < 10; i++) {
            world.addEnemy(new Vampire(charP, world));
        }

        // Get BattleManager and run the battle
        BattleManager bm = world.getBattleManager();
        bm.update(world);
        bm.battle();

        // Test that character is now dead
        assertTrue(playerChar.getHp() < 0);

    }

    @Test
    public void testDifferentDamages() {

    }
}
