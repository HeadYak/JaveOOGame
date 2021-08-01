package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import unsw.loopmania.Items.Weapons.FOTW;
import unsw.loopmania.Items.Weapons.Staff;
import unsw.loopmania.Items.Weapons.Stake;
import unsw.loopmania.Items.Weapons.Sword;
import unsw.loopmania.battles.BattleManager;
import unsw.loopmania.enemies.Doggie;
import unsw.loopmania.enemies.ElanMuske;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.enemies.Zombie;
import unsw.loopmania.Ally;
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

        // Anduril:
        FOTW fotw = new FOTW(x, y);
        playerChar.setWeapon(fotw);

        System.out.println(bm.getBattleEnemies().size());
        bm.runTickBattle();

        dummyHp -= (fotw.getDamageValue() * 8);
        assertEquals(trainingDummy.getHp(), dummyHp);

        // Staff (does no damage):
        Staff staff = new Staff(x, y);
        playerChar.setWeapon(staff);

        bm.runTickBattle();

        assertEquals(trainingDummy.getHp(), dummyHp);
        assertEquals(bm.getBattleEnemies().size(), 0);
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
                    vampire.getBuffDmg() <= 15);

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
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Create new Doggie on same tile as player
        Doggie doggie = new Doggie(charP);
        world.addEnemy(doggie);

        // Simulate battle manually to test each component of battle is working
        // correctly and set crit chance to 100%
        BattleManager bm = world.getBattleManager();
        bm.setCritMode(2);

        bm.update(world);

        // Do a single tick of battle
        bm.runTickBattle();

        // Check that the doggie has stunned character
        assertTrue(playerChar.isStunned());

        // Check that the character and doggie received expected damage
        int newCharHp = playerChar.getMaxHp() - (doggie.getDmg() * 4);
        assertEquals(playerChar.getHp(), newCharHp);
        int newDoggieHp = doggie.getMaxHp() - (playerChar.getDmg() * 4);
        assertEquals(doggie.getHp(), newDoggieHp);

        // Set crit rate to 0% and do another a tick of battle and check that
        // doggie does another 60 dmg but character does not
        bm.setCritMode(1);
        bm.runTickBattle();

        assertEquals(doggie.getHp(), newDoggieHp);
        assertEquals(playerChar.getHp(), newCharHp - (playerChar.getMaxHp() -
                newCharHp));
        assertFalse(playerChar.isStunned());
    }

    @Test
    public void testElanMuskeCrits() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Create new Elan on same tile as player
        ElanMuske elan = new ElanMuske(charP);
        world.addEnemy(elan);

        // Create 3 slugs who have missing health
        Slug slug1 = new Slug(charP);
        slug1.setHp(1);
        Slug slug2 = new Slug(charP);
        slug2.setHp(slug2.getMaxHp()/2);
        Slug slug3 = new Slug(charP);
        slug3.setHp(slug3.getMaxHp() - 1);
        world.addEnemy(slug1);
        world.addEnemy(slug2);
        world.addEnemy(slug3);

        // Simulate battle manually to test each component of battle is working
        // correctly and set crit chance to 100%
        BattleManager bm = world.getBattleManager();
        bm.setCritMode(2);

        bm.update(world);

        // Do a single tick of battle
        bm.runTickBattle();

        // Check that the slugs have healed hp and Elan has not lost any hp
        // due to immediately healing it back up, and player took expected dmg
        // from Elan (who does not increase dmg when critting)
        int newCharHp = playerChar.getMaxHp() - (elan.getDmg() * 4) -
                (3 * (slug1.getDmg() * 8));
        assertEquals(playerChar.getHp(), newCharHp);
        int newElanHp = Math.min(elan.getMaxHp(), elan.getMaxHp() -
                (playerChar.getDmg() * 4) + elan.getHealValue());
        assertEquals(elan.getHp(), newElanHp);
        int newSlug1Hp = Math.min(slug1.getMaxHp(), 1 + elan.getHealValue());
        int newSlug2Hp = Math.min(slug2.getMaxHp(), slug2.getMaxHp()/2 +
                elan.getHealValue());
        int newSlug3Hp = Math.min(slug3.getMaxHp(), slug3.getMaxHp() - 1 +
                elan.getHealValue());
        assertEquals(slug1.getHp(), newSlug1Hp);
        assertEquals(slug2.getHp(), newSlug2Hp);
        assertEquals(slug3.getHp(), newSlug3Hp);

        // Set crit rate to 0% and do another a tick of battle and check that
        // doggie does another 60 dmg but character does not
    }

    @Test
    public void testDmgFromSlugConsistency() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Create new slug on same tile as player
        Slug slug = new Slug(charP);
        world.addEnemy(slug);

        // Simulate battle manually to test each component of battle is working
        // correctly and allow crit chance to be set to default
        BattleManager bm = world.getBattleManager();
        bm.update(world);
        
        // Loop 1000 times checking both slug's and character's dmg are
        // consistent given character is unequipped
        double crit = 0;

        for (int i = 0; i < 1000; i++) {
            playerChar.regen(playerChar.getMaxHp());
            slug.setHp(slug.getMaxHp());
            bm.runTickBattle();

            // Check that the slug is dealing consistent damage
            assertEquals(slug.getHp(), slug.getMaxHp() -
                    playerChar.getDmg() * 4);
            assertTrue(playerChar.getHp() == playerChar.getMaxHp() -
                    slug.getDmg() * 4 || playerChar.getHp() ==
                    playerChar.getMaxHp() - slug.getDmg() * 8);

            if (playerChar.getHp() == playerChar.getMaxHp() -
                    slug.getDmg() * 8) {
                crit++;
            }
        }

        System.out.println("Slug critical strike chance: "
                + crit/1000 * 100 + "%");
    }

    @Test
    public void testDmgFromZombieConsistency() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Create new zombie on same tile as player
        Zombie zombie = new Zombie(charP, playerChar);
        world.addEnemy(zombie);

        // Simulate battle manually to test each component of battle is working
        // correctly and allow crit chance to be set to default
        BattleManager bm = world.getBattleManager();
        bm.update(world);

        // Loop 1000 times checking both zombie's and character's dmg are
        // consistent given character is unequipped
        double crit = 0;

        for (int i = 0; i < 1000; i++) {
            playerChar.regen(playerChar.getMaxHp());
            zombie.setHp(zombie.getMaxHp());
            bm.runTickBattle();

            // Check that the zombie is dealing consistent damage
            assertEquals(zombie.getHp(), zombie.getMaxHp() -
                    playerChar.getDmg() * 4);
            assertTrue(playerChar.getHp() == playerChar.getMaxHp() -
                    zombie.getDmg() * 4 || playerChar.getHp() ==
                    playerChar.getMaxHp() - zombie.getDmg() * 8);

            if (playerChar.getHp() == playerChar.getMaxHp() -
                    zombie.getDmg() * 8) {
                crit++;
            }
        }

        System.out.println("Zombie critical strike chance: "
                + crit/1000 * 100 + "%");
    }

    @Test
    public void testDmgFromVampireConsistency() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Create new vampire on same tile as player
        Vampire vampire = new Vampire(charP, world);
        world.addEnemy(vampire);

        // Simulate battle manually to test each component of battle is working
        // correctly and allow crit chance to be set to default
        BattleManager bm = world.getBattleManager();
        bm.update(world);

        // Loop 1000 times checking both zombie's and character's dmg are
        // consistent given character is unequipped
        double crit = 0;

        for (int i = 0; i < 1000; i++) {
            playerChar.regen(playerChar.getMaxHp());
            vampire.setHp(vampire.getMaxHp());
            bm.runTickBattle();

            // Check that the vampire is dealing consistent damage
            assertEquals(vampire.getHp(), vampire.getMaxHp() -
                    playerChar.getDmg() * 4);
            assertTrue(playerChar.getHp() == playerChar.getMaxHp() -
                    vampire.getDmg() * 4 || playerChar.getHp() ==
                    playerChar.getMaxHp() - vampire.getDmg() * 8 -
                    vampire.getBuffDmg());

            
            if (playerChar.getHp() == playerChar.getMaxHp() -
                    vampire.getDmg() * 8 - vampire.getBuffDmg()) {
                crit++;
            }

            vampire.setBuffDuration(0);
            vampire.setBuffDmg(0);
        }

        System.out.println("Vampire critical strike chance: "
                + crit/1000 * 100 + "%");
    }

    @Test
    public void testDmgFromDoggieConsistency() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Create new doggie on same tile as player
        Doggie doggie = new Doggie(charP);
        world.addEnemy(doggie);

        // Simulate battle manually to test each component of battle is working
        // correctly and allow crit chance to be set to default
        BattleManager bm = world.getBattleManager();
        bm.update(world);

        // Loop 1000 times checking both doggie's and character's dmg are
        // consistent given character is unequipped
        double crit = 0;

        for (int i = 0; i < 1000; i++) {
            playerChar.regen(playerChar.getMaxHp());
            doggie.setHp(doggie.getMaxHp());
            bm.runTickBattle();

            // Check that the doggie is dealing no extra damage, but character
            // sometimes is stunned
            assertTrue(doggie.getHp() == doggie.getMaxHp() -
                    playerChar.getDmg() * 4 || doggie.getHp() ==
                    doggie.getMaxHp());
            assertEquals(playerChar.getHp(), playerChar.getMaxHp() -
                    doggie.getDmg() * 4);

            
            if (doggie.getHp() == doggie.getMaxHp()) {
                crit++;
            }
        }

        System.out.println("Doggie critical strike chance: "
                + crit/1000 * 100 + "%");
    }

    @Test
    public void testDmgFromElanConsistency() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Create new Elan on same tile as player
        ElanMuske elan = new ElanMuske(charP);
        world.addEnemy(elan);

        // Create a damaged slug with no attack on same tile as player after
        // Elan
        Slug slug = new Slug(charP);
        world.addEnemy(slug);
        slug.setHp(slug.getMaxHp()/2);
        slug.setDmg(0);

        // Simulate battle manually to test each component of battle is working
        // correctly and allow crit chance to be set to default
        BattleManager bm = world.getBattleManager();
        bm.update(world);

        // Loop 1000 times checking both elan's and character's dmg are
        // consistent given character is unequipped
        double crit = 0;

        for (int i = 0; i < 1000; i++) {
            playerChar.regen(playerChar.getMaxHp());
            elan.setHp(elan.getMaxHp());
            slug.setHp(slug.getMaxHp()/2);
            bm.runTickBattle();

            // Check that the elan is dealing no extra damage but sometimes
            // all enemies are healed
            assertTrue(elan.getHp() == elan.getMaxHp() -
                    (playerChar.getDmg() * 4) || elan.getHp() ==
                    Math.min(elan.getMaxHp(), elan.getMaxHp() -
                    (playerChar.getDmg() * 4) + elan.getHealValue()));
            assertEquals(playerChar.getHp(), playerChar.getMaxHp() -
                    elan.getDmg() * 4);

            assertTrue(slug.getHp() == slug.getMaxHp()/2 || slug.getHp() ==
                    Math.min(slug.getMaxHp(), slug.getMaxHp()/2 +
                    elan.getHealValue()));

            
            if (slug.getHp() == Math.min(slug.getMaxHp(), slug.getMaxHp()/2 +
                    elan.getHealValue())) {
                crit++;
            }

        }

        System.out.println("Elan Muske critical strike chance: "
                + crit/1000 * 100 + "%");
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
        slug.setHp(slug.getMaxHp() - 1);
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
        assertEquals(bm.getBattleEnemies().get(0).getHp(), slug.getMaxHp() - 1);
        assertEquals(bm.getBattleEnemies().get(1), vampire);
        assertEquals(bm.getSupportEnemies().size(), 0);
        assertEquals(bm.getAllies().size(), 0);

        // Do a single tick of damage
        bm.runTickBattle();

        // Assert that only vampire is in bm battleEnemies and slug has been
        // converted
        assertEquals(bm.getBattleEnemies().size(), 1);
        assertEquals(bm.getBattleEnemies().get(0), vampire);
        assertEquals(bm.getSupportEnemies().size(), 0);
        assertEquals(bm.getAllies().size(), 1);
        assertTrue(bm.getAllies().get(0) instanceof TrancedAlly);
        
        // Grab reference and check tranced ally status correct
        // NOTE: trance duration is immediately set from 5 -> 4 as it attacks
        // straight after being tranced
        TrancedAlly reference = (TrancedAlly) bm.getAllies().get(0);
        assertEquals(reference.getTranceDuration(), 4);
        assertEquals(reference.getOriginalBody(), slug);

        // Set crit rate to 0% and run battle ticks until trance duration ends
        bm.setCritMode(1);
        int tranceDuration = 3;

        for (int i = 0; i < 4; i++) {
            bm.runTickBattle();
            assertEquals(reference.getTranceDuration(), tranceDuration);
            vampire.setHp(vampire.getMaxHp());
            tranceDuration--;
        }

        // Check world state again
        assertEquals(bm.getBattleEnemies().size(), 2);
        assertEquals(bm.getBattleEnemies().get(0), vampire);
        assertEquals(bm.getBattleEnemies().get(1), slug);
        assertEquals(bm.getBattleEnemies().get(1).getHp(), slug.getMaxHp() - 1);
        assertEquals(bm.getSupportEnemies().size(), 0);
        assertEquals(bm.getAllies().size(), 0);

        // Run one more move and check everything is back to normal
        world.runTickMoves();

        // Set crit rate to 100% and check that battle ends after two
        // consecutive trances
        bm.setCritMode(1);

        // TODO:
    }
}
