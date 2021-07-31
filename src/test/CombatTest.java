package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.Campfire;
import unsw.loopmania.Buildings.HeroCastle;
import unsw.loopmania.Buildings.Tower;
import unsw.loopmania.Ally;
import unsw.loopmania.BattleManager;
import unsw.loopmania.Items.HealthPotion;
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
    public void testBattleWithHands() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Create new slug on same tile as player
        Slug slug = new Slug(charP);
        world.addEnemy(slug);

        // Simulating battle manually to test each component of battle is
        // working correctly
        BattleManager bm = world.getBattleManager();
        bm.setCritMode(1);

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
        int newSlugHp = 100 - (playerChar.getDmg() * 4);
        assertEquals(slug.getHp(), newSlugHp);

        // Complete battle
        bm.battle();

        // Test that combat has occurred by checking that the slug is now dead
        // NOTE: Mathematically impossible for slug to win
        assertTrue(world.getEnemies().get(0).getHp() <= 0);
    }

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
        bm.setCritMode(1);

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
        int newSlugHp = 100 - (sword.getDamageValue() * 4);
        assertEquals(slug.getHp(), newSlugHp);

        // Complete battle
        bm.battle();

        // Test that combat has occurred by checking that the slug is now dead
        // NOTE: Mathematically impossible for slug to win
        assertTrue(world.getEnemies().get(0).getHp() <= 0);

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
        bm.setCritMode(1);

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
        int newZombieHp = 200 - (sword.getDamageValue() * 4);
        assertEquals(zombie.getHp(), newZombieHp);


        // Complete battle
        bm.battle();

        // Test that combat has occurred by checking that the zombie is now dead
        // NOTE: Mathematically impossible for zombie to win
        assertTrue(world.getEnemies().get(0).getHp() <= 0);

        HealthPotion potion = new HealthPotion(x, y);


        assertEquals(playerChar.getInventory().size(),0);
        playerChar.addToInventory(potion);
        assertEquals(playerChar.getInventory().size(), 1);

        playerChar.useHealthPotion(playerChar.getInventory());

        assertEquals(playerChar.getHp(), playerChar.getMaxHp());
        assertEquals(playerChar.getInventory().size(), 0);

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
        bm.setCritMode(1);

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
        int newVampireHp = 300 - (sword.getDamageValue() * 4);
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
        newVampireHp = newVampireHp - (stake.getDamageValue() * 8);
        assertEquals(vampire.getHp(), newVampireHp);

        // Complete battle
        bm.battle();

        // Test that combat has occurred by checking that the slug is now dead
        // NOTE: Mathematically impossible for slug to win
        assertTrue(world.getEnemies().get(0).getHp() <= 0);
    }

    @Test
    public void testTower() {
        PathPosition charP = new PathPosition(31, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Spawn a tower next to character
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(1);
        Tower tower = new Tower(x, y);
        world.addBuilding(tower);

        // Simulate movement by 1 to activate tower
        world.runTickMoves();

        // Spawn two vampires to fight
        Vampire vampire1 = new Vampire(charP, world);
        world.addEnemy(vampire1);
        Vampire vampire2 = new Vampire(charP, world);
        world.addEnemy(vampire2);

        // Assert that character is supported by tower
        assertTrue(playerChar.getIsSupported());

        // Simulating battle manually to test each component of battle is
        // working correctly
        BattleManager bm = world.getBattleManager();
        bm.setCritMode(1);

        // Update battle manager with potential enemy and do single tick of
        // battle
        bm.update(world);
        bm.runTickBattle();

        // Check that the damage done by character and tower are the correct
        // behaviour
        int newVampireHp1 = 300 - (playerChar.getDmg() * 4 + 100);
        assertEquals(vampire1.getHp(), newVampireHp1);
        int newVampireHp2 = 300 - 100;
        assertEquals(vampire2.getHp(), newVampireHp2);

        bm.battle();

        // Test that combat has occurred by checking that the slug is now dead
        // NOTE: Mathematically impossible for slug to win
        assertTrue(world.getEnemies().get(0).getHp() <= 0);
    }

    @Test
    public void testCampfire() {
        PathPosition charP = new PathPosition(31, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Spawn a tower next to character
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(1);
        Campfire campfire = new Campfire(x, y);
        world.addBuilding(campfire);

        // Simulate movement by 1 to activate tower
        world.runTickMoves();

        // Spawn a vampire to fight
        Vampire vampire = new Vampire(charP, world);
        world.addEnemy(vampire);

        // Assert that character is supported by tower
        assertTrue(playerChar.getBuffStatus());

        // Simulating battle manually to test each component of battle is
        // working correctly
        BattleManager bm = world.getBattleManager();
        bm.setCritMode(1);

        // Update battle manager with potential enemy and do single tick of
        // battle
        bm.update(world);
        bm.runTickBattle();

        // Check that the damage done by character and tower are the correct
        // behaviour
        int newVampireHp = 300 - (playerChar.getDmg() * 4 * 2);
        assertEquals(vampire.getHp(), newVampireHp);

        bm.battle();

        // Test that combat has occurred by checking that the slug is now dead
        // NOTE: Mathematically impossible for slug to win
        assertTrue(world.getEnemies().get(0).getHp() <= 0);
    }

    @Test
    public void allySupport() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Equip a sword
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Sword sword = new Sword(x, y);
        playerChar.setWeapon(sword);

        // Spawn a vampire to fight
        Vampire vampire = new Vampire(charP, world);
        world.addEnemy(vampire);

        // Spawn two allies to support character
        Ally ally1 = new Ally(charP);
        playerChar.recruitAlly(ally1);
        Ally ally2 = new Ally(charP);
        playerChar.recruitAlly(ally2);

        // Simulating battle manually to test each component of battle is
        // working correctly
        BattleManager bm = world.getBattleManager();
        bm.setCritMode(1);

        // Update battle manager with potential enemy and do single tick of
        // battle
        bm.update(world);
        bm.runTickBattle();

        // Check that the damage done by character and ally are the correct
        // behaviour as well as ally being damaged
        int newVampireHp = 300 - ((sword.getDamageValue() + ally1.getDmg() +
                ally2.getDmg()) * 4);
        assertEquals(vampire.getHp(), newVampireHp);
        assertEquals(ally1.getHp(), 100);
        assertEquals(ally2.getHp(), 100);

        bm.battle();

        // Test that combat has occurred by checking that the slug is now dead
        // NOTE: Mathematically impossible for slug to win
        assertTrue(world.getEnemies().get(0).getHp() <= 0);
    }

    @Test
    public void testEnemySupport() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Create new slug on same tile as player
        Slug slug = new Slug(charP);
        world.addEnemy(slug);

        // Create new vampire in support radius
        PathPosition vampireP = new PathPosition(3, path);
        Vampire vampireSup = new Vampire(vampireP, world);
        world.addEnemy(vampireSup);

        // Create new vampire outside of support radius
        PathPosition outsideRange = new PathPosition(4, path);
        Vampire vampireIsolated = new Vampire(outsideRange, world);
        world.addEnemy(vampireIsolated);

        // Simulating battle manually to test each component of battle is
        // working correctly
        BattleManager bm = world.getBattleManager();
        bm.setCritMode(1);

        // Update battle manager with potential enemy and do single tick of
        // battle
        bm.update(world);
        bm.runTickBattle();

        // Assert that isolated vampire is not in any list
        assertFalse(bm.getBattleEnemies().contains(vampireIsolated));
        assertFalse(bm.getSupportEnemies().contains(vampireIsolated));

        // Check that character has received additional damage and vampireSup
        // is not affected
        int newCharHp = 300 - (slug.getDmg() * 4) - (vampireSup.getDmg() * 2);
        assertEquals(playerChar.getHp(), newCharHp);

    }

    @Test
    public void testWalkToBattle() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Disable crits
        BattleManager bm = world.getBattleManager();
        bm.setCritMode(1);

        // Set a Hero Castle where character spawns
        SimpleIntegerProperty cX = new SimpleIntegerProperty(playerChar.getX());
        SimpleIntegerProperty cY = new SimpleIntegerProperty(playerChar.getY());
        HeroCastle heroCastle = new HeroCastle(cX, cY);
        world.addBuilding(heroCastle);

        // Equip a sword
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Stake stake = new Stake(x, y);
        playerChar.setWeapon(stake);

        // Create vampire that walks to battle with playerChar at the most
        // furthest square
        PathPosition vampireP = new PathPosition(6, path);
        Vampire vampire = new Vampire(vampireP, world);
        world.addEnemy(vampire);

        // Create vampire that walks just out of battle range after walking once
        PathPosition vampireOut = new PathPosition(7, path);
        Vampire vampireIsol = new Vampire(vampireOut, world);
        world.addEnemy(vampireIsol);

        // Check that there are exactly two enemies
        assertEquals(world.getEnemies().size(), 2);

        world.runTickMoves();

        // Check that there are still exactly two enemies
        assertEquals(world.getEnemies().size(), 2);

        world.runTickMoves();

        // Check that there are exactly one enemy
        assertEquals(world.getEnemies().size(), 1);
        assertEquals(world.getEnemies().get(0), vampireIsol);
    }

    @Test
    public void testBlockBehaviour() {

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
        assertTrue(playerChar.getHp() <= 0);

    }
}
