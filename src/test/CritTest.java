package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Items.Weapons.Staff;
import unsw.loopmania.Items.Weapons.Stake;
import unsw.loopmania.Items.Weapons.Sword;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.enemies.Vampire;
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

        // Check that the damage done by character and slug are the correct
        // behaviour
        int newCharHp = 300 - (slug.getDmg() * 4 * 2);
        assertEquals(playerChar.getHp(), newCharHp);
        int newSlugHp = 100 - (playerChar.getDmg() * 4 * 2);
        assertEquals(slug.getHp(), newSlugHp);

        // Kill slug and regen character
        bm.battle();
        playerChar.regen(playerChar.getMaxHp());

        // Equip all weapons and test that they are working as intended against
        // modified slug with 10000 hp and 0 dmg
        Slug trainingDummy = new Slug(charP);
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

        int dummyHp = 10000 -
                ((playerChar.getDmg() + sword.getDamageValue()) * 8);
        assertEquals(trainingDummy.getHp(), dummyHp);

        // Stake:
        Stake stake = new Stake(x, y);
        playerChar.setWeapon(stake);

        bm.runTickBattle();

        dummyHp -= ((playerChar.getDmg() + stake.getDamageValue()) * 8);
        assertEquals(trainingDummy.getHp(), dummyHp);

        // Staff (does no damage):
        Staff staff = new Staff(x, y);
        playerChar.setWeapon(staff);

        // Anduril:
        // TODO:

        bm.runTickBattle();

        assertEquals(trainingDummy.getHp(), dummyHp);
        
        // Kill dummy
        bm.battle();
    }

    @Test
    public void testStakeAgainstVampire() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Create new slug on same tile as player
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
        int newVampireHp = 300 -
                ((playerChar.getDmg() + stake.getDamageValue()) * 8);
        assertEquals(vampire.getHp(), newVampireHp);

        vampire.setHp(300);
        bm.update(world);

        // Set crit chance to 100%
        bm.setCritMode(2);

        // Check that the vampire received expected damage (extra 4x dmg)
        newVampireHp = 300 -
                ((playerChar.getDmg() + stake.getDamageValue()) * 16);
        assertEquals(vampire.getHp(), newVampireHp);
    }

    @Test
    public void testAndurilAgainstBosses() {

    }

    @Test
    public void testZombieCrits() {

    }

    @Test
    public void testVampireCrits() {

    }

    @Test
    public void testDoggieCrits() {

    }

    @Test
    public void testElanMuskeCrits() {
        
    }

    @Test
    public void testClassSpecificDmgModifier() {

    }

    @Test
    public void testSpecialEffects() {

    }

}
