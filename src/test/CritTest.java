package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.TrancedAlly;
import unsw.loopmania.Buildings.HeroCastle;
import unsw.loopmania.Items.Weapons.Staff;
import unsw.loopmania.Items.Weapons.Stake;
import unsw.loopmania.Items.Weapons.Sword;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.enemies.Zombie;
import unsw.loopmania.Ally;
import unsw.loopmania.BattleManager;
import unsw.loopmania.Character;

public class CritTest {
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
    public void testWeaponCritDmg() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Set a Hero Castle where character spawns
        SimpleIntegerProperty cX = new SimpleIntegerProperty(playerChar.getX());
        SimpleIntegerProperty cY = new SimpleIntegerProperty(playerChar.getY());
        HeroCastle heroCastle = new HeroCastle(cX, cY);
        world.addBuilding(heroCastle);

        // Create new slug on same tile as player
        Slug slug = new Slug(charP);
        world.addEnemy(slug);

        // Simulate battle manually to test each component of battle is working
        // correctly and set crit chance to 100%
        BattleManager bm = world.getBattleManager();
        bm.setCritMode(2);

        bm.update(world);

        // Do a single tick of battle
        bm.runTickBattle();

        // Check that player has no crit damage
        int newCharHp = 300 - (slug.getDmg() * 4 * 2);
        assertEquals(playerChar.getHp(), newCharHp);
        int newSlugHp = slug.getMaxHp() - (playerChar.getDmg() * 4);
        assertEquals(slug.getHp(), newSlugHp);

        // Kill slug and regen character
        world.runTickMoves();
        playerChar.regen(playerChar.getMaxHp());

        // Character's new position
        PathPosition nextCharP = new PathPosition(1, path);

        // Equip all weapons and test that they are working as intended against
        // modified slug with 10000 hp and 0 dmg
        Slug trainingDummy = new Slug(nextCharP);
        trainingDummy.setMaxHp(10000);
        trainingDummy.setHp(10000);
        trainingDummy.setDmg(0);
        world.addEnemy(trainingDummy);

        bm.update(world);
        
        // Sword:
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Sword sword = new Sword(x, y);
        playerChar.setWeapon(sword);

        bm.runTickBattle();

        int dummyHp = 10000 - (sword.getDamageValue() * 8);
        assertEquals(trainingDummy.getHp(), dummyHp);

        // Stake:
        Stake stake = new Stake(x, y);
        playerChar.setWeapon(stake);

        bm.runTickBattle();

        dummyHp -= (stake.getDamageValue() * 8);
        assertEquals(trainingDummy.getHp(), dummyHp);

        // Staff (does no damage):
        Staff staff = new Staff(x, y);
        playerChar.setWeapon(staff);

        bm.runTickBattle();

        assertEquals(trainingDummy.getHp(), dummyHp);


        // Anduril:
        // TODO:
    }

    @Test
    public void testStakeAgainstVampire() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Create new vampire on same tile as player
        Vampire vampire = new Vampire(charP, world);
        vampire.setDmg(0);
        world.addEnemy(vampire);

        // Equip stake:
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Stake stake = new Stake(x, y);
        playerChar.setWeapon(stake);

        // Simulate battle manually to test each component of battle is working
        // correctly and set crit chance to 0%
        BattleManager bm = world.getBattleManager();
        bm.setCritMode(1);

        bm.update(world);

        // Do a single tick of battle
        bm.runTickBattle();

        // Check that the vampire received expected damage (extra 2x dmg)
        int newVampireHp = 300 - (stake.getDamageValue() * 8);
        assertEquals(vampire.getHp(), newVampireHp);

        vampire.setHp(300);
        bm.update(world);

        // Set crit chance to 100%
        bm.setCritMode(2);

        bm.runTickBattle();

        // Check that the vampire received expected damage (extra 4x dmg)
        newVampireHp = 300 - (stake.getDamageValue() * 16);
        assertEquals(vampire.getHp(), newVampireHp);
    }

    @Test
    public void testAndurilAgainstBosses() {

    }

    @Test
    public void testZombieCrits() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Give character two allies
        Ally ally = new Ally(charP);
        playerChar.recruitAlly(ally);
        assertEquals(playerChar.getAllyList().size(), 1);

        // Create new zombie on same tile as player
        Zombie zombie = new Zombie(charP, playerChar);
        world.addEnemy(zombie);

        // Simulate battle manually to test each component of battle is working
        // correctly and set crit chance to 100%
        BattleManager bm = world.getBattleManager();
        bm.setCritMode(2);

        bm.update(world);

        // Check that there is 1 zombie
        assertEquals(bm.getBattleEnemies().size(), 1);

        // Do a single tick of battle
        bm.runTickBattle();

        // Check that the character received expected damage (extra 2x dmg) and
        // the extra zombie converted from ally
        int newCharHp = 300 - (zombie.getDmg() * 8) - (zombie.getDmg() * 8);
        assertEquals(playerChar.getHp(), newCharHp);

        // Check that the allies has reduced to 1
        assertEquals(playerChar.getAllyList().size(), 0);

        // Check that there are two zombies
        assertEquals(bm.getBattleEnemies().size(), 2);
    }

    @Test
    public void testVampireCrits() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Create new vampire on same tile as player
        Vampire vampire = new Vampire(charP, world);
        world.addEnemy(vampire);

        // Simulate battle manually to test each component of battle is working
        // correctly and set crit chance to 100%
        BattleManager bm = world.getBattleManager();
        bm.setCritMode(2);

        bm.update(world);

        // Do a single tick of battle
        bm.runTickBattle();

        // Check that the vampire's buff are in the correct range
        assertTrue(vampire.getBuffDuration() >= 1 &&
                vampire.getBuffDuration() <= 5);
        assertTrue(vampire.getBuffDmg() >= 5 &&
                vampire.getBuffDuration() <= 15);

        System.out.println(vampire.getBuffDmg());

        // Check that the character received expected damage range (extra 2x dmg)
        int newCharHp = playerChar.getMaxHp() - (vampire.getDmg() * 8) -
                (vampire.getBuffDmg());
        assertEquals(playerChar.getHp(), newCharHp);
    }

    @Test
    public void testRandomVampireConsistency() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Create new vampire on same tile as player
        Vampire vampire = new Vampire(charP, world);
        world.addEnemy(vampire);

        // Simulate battle manually to test each component of battle is working
        // correctly and set crit chance to 100%
        BattleManager bm = world.getBattleManager();
        bm.setCritMode(2);

        bm.update(world);

        // Loop 100 times and check that vampire buff stats satisfy range all
        // 100 times
        for (int i = 0; i < 100; i++) {
            playerChar.regen(playerChar.getMaxHp());
            bm.runTickBattle();

            // Check that the vampire's buff are in the correct range
            assertTrue(vampire.getBuffDuration() >= 1 &&
                    vampire.getBuffDuration() <= 5);
            assertTrue(vampire.getBuffDmg() >= 5 &&
                    vampire.getBuffDuration() <= 15);

            vampire.setBuffDuration(0);
            vampire.setBuffDmg(0);
            vampire.setHp(vampire.getMaxHp());
        }

        // Test that buff stacks
        bm.runTickBattle();

        assertTrue(vampire.getBuffDuration() >= 1 &&
                vampire.getBuffDuration() <= 5);
        assertTrue(vampire.getBuffDmg() >= 5 &&
                vampire.getBuffDuration() <= 15);

        bm.runTickBattle();
        
        assertTrue(vampire.getBuffDuration() >= 1 &&
                vampire.getBuffDuration() <= 9);
        assertTrue(vampire.getBuffDmg() >= 10 &&
                vampire.getBuffDuration() <= 30);

    }

    @Test
    public void testDoggieCrits() {

    }

    @Test
    public void testElanMuskeCrits() {

    }

    @Test
    public void testTranceExpiry() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Set a Hero Castle where character spawns
        SimpleIntegerProperty cX = new SimpleIntegerProperty(playerChar.getX());
        SimpleIntegerProperty cY = new SimpleIntegerProperty(playerChar.getY());
        HeroCastle heroCastle = new HeroCastle(cX, cY);
        world.addBuilding(heroCastle);

        // Equip character with staff
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Staff staff = new Staff(x, y);
        playerChar.setWeapon(staff);

        // Create new slug on same tile as player
        Slug slug = new Slug(charP);
        world.addEnemy(slug);

        // Create a vampire on same tile as player
        Vampire vampire = new Vampire(charP, world);
        world.addEnemy(vampire);

        // Assert that slug is the first target
        assertEquals(slug, world.getEnemies().get(0));

        // Simulate battle manually to test each component of battle is working
        // correctly and set crit chance to 100%
        BattleManager bm = world.getBattleManager();
        bm.setCritMode(2);

        bm.update(world);

        // Assert that both enemies are in bm and there are no support/allies
        assertEquals(bm.getBattleEnemies().size(), 2);
        assertEquals(bm.getBattleEnemies().get(0), slug);
        assertEquals(bm.getBattleEnemies().get(1), vampire);
        assertEquals(bm.getSupportEnemies().size(), 0);
        assertEquals(bm.getAllies().size(), 0);

        // Do a single tick of damage
        bm.runTickBattle();

        // Assert that only vampire is in bm battleEnemies and slug has been
        // converted
        assertEquals(bm.getBattleEnemies().size(), 1);
        assertEquals(bm.getBattleEnemies().get(1), vampire);
        assertEquals(bm.getSupportEnemies().size(), 0);
        assertEquals(bm.getAllies().size(), 1);
        assertTrue(bm.getAllies().get(0) instanceof TrancedAlly);
        
        // Grab reference and check tranced ally statsis correct
        TrancedAlly reference = (TrancedAlly) bm.getAllies().get(0);
        assertEquals(reference.getTranceDuration(), 5);
        assertEquals(reference.getOriginalBody(), slug);

        // Set crit rate to 0% and run battle ticks until trance duration ends
        bm.setCritMode(1);
        int tranceDuration = 4;

        for (int i = 0; i < 4; i++) {
            world.runTickMoves();
            assertEquals(reference.getTranceDuration(), tranceDuration);
            vampire.setHp(vampire.getMaxHp());
            tranceDuration--;
        }

        // Check world state again
        assertEquals(bm.getBattleEnemies().size(), 1);
        assertEquals(bm.getBattleEnemies().get(1), vampire);
        assertEquals(bm.getSupportEnemies().size(), 0);
        assertEquals(bm.getAllies().size(), 1);
        assertTrue(bm.getAllies().get(0) instanceof TrancedAlly);

        // Run one more move and check everything is back to normal
        world.runTickMoves();

        assertEquals(bm.getBattleEnemies().size(), 2);
        assertEquals(bm.getBattleEnemies().get(0), slug);
        assertEquals(bm.getBattleEnemies().get(1), vampire);
        assertEquals(bm.getSupportEnemies().size(), 0);
        assertEquals(bm.getAllies().size(), 0);

        // Check that slug's damage has been retained
        // TODO:

        // Set crit rate to 100% and check that battle ends after two
        // consecutive trances
        bm.setCritMode(1);

        // TODO:
    }
}
