package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.javatuples.Ennead;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.Campfire;
import unsw.loopmania.Buildings.HeroCastle;
import unsw.loopmania.Buildings.Tower;
import unsw.loopmania.Items.Armor.ChestArmor;
import unsw.loopmania.Items.Armor.Helmet;
import unsw.loopmania.Items.Armor.Shield;
import unsw.loopmania.Items.Armor.basicChestArmor;
import unsw.loopmania.Items.Armor.basicHelmet;
import unsw.loopmania.Items.Armor.basicShield;
import unsw.loopmania.Items.Weapons.Staff;
import unsw.loopmania.Items.Weapons.Sword;
import unsw.loopmania.Items.Weapons.Weapon;
import unsw.loopmania.battles.BattleLog;
import unsw.loopmania.battles.Summary;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.enemies.Zombie;

public class BattleLogTest {
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
    public void testBattleLogBuilder() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        playerChar.setHp(playerChar.getMaxHp() - 1);
        world.setCharacter(playerChar);

        // Set a Hero Castle where character spawns
        SimpleIntegerProperty cX = new SimpleIntegerProperty(playerChar.getX());
        SimpleIntegerProperty cY = new SimpleIntegerProperty(playerChar.getY());
        HeroCastle heroCastle = new HeroCastle(cX, cY);
        world.addBuilding(heroCastle);

        // Equip character
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Sword sword = new Sword(x, y);
        playerChar.setWeapon(sword);
        
        basicChestArmor armor = new basicChestArmor(x, y);
        playerChar.setChestArmor(armor);

        basicHelmet helmet = new basicHelmet(x, y);
        playerChar.setHelmet(helmet);

        basicShield shield = new basicShield(x, y);
        playerChar.setShield(shield);

        // Spawn 2 zombies
        PathPosition battleP = new PathPosition(0, path);
        Zombie zombie1 = new Zombie(battleP, playerChar);
        Zombie zombie2 = new Zombie(battleP, playerChar);
        zombie1.setHp(300);
        zombie2.setHp(300);
        world.addEnemy(zombie1);
        world.addEnemy(zombie2);

        List<BasicEnemy> expectedBattleEnemies = new ArrayList<>();
        expectedBattleEnemies.add(zombie1);
        expectedBattleEnemies.add(zombie2);

        // Spawn a zombie and vampire to support the battle
        PathPosition zombieP = new PathPosition(3, path);
        Zombie zombie = new Zombie(zombieP, playerChar);
        world.addEnemy(zombie);

        PathPosition vampireP = new PathPosition(6, path);
        Vampire vampire = new Vampire(vampireP, world);
        world.addEnemy(vampire);

        List<BasicEnemy> expectedSupportEnemies = new ArrayList<>();
        expectedSupportEnemies.add(zombie);
        expectedSupportEnemies.add(vampire);

        // Spawn a campfire in battle
        x = new SimpleIntegerProperty(1);
        y = new SimpleIntegerProperty(2);
        Campfire campfire = new Campfire(x, y);
        world.addBuilding(campfire);

        // Disable crits before battling
        world.getBattleManager().setCritMode(1);

        // Battle
        world.runTickMoves();

        // Expected results
        List<BasicEnemy> expectedDefeated = new ArrayList<>();
        expectedDefeated.add(zombie1);
        expectedDefeated.add(zombie2);

        List<Triplet<Integer, Integer, Integer>> expectedBattle = Arrays.asList(
                new Triplet<>(120, 40, 180), new Triplet<>(120, 40, 60),
                new Triplet<>(60, 28, 300), new Triplet<>(120, 28, 180),
                new Triplet<>(120, 28, 60), new Triplet<>(60, 16, 0)
        );
        

        // Grab generated Battle Log
        BattleLog log = world.getLastLog();
        assertEquals(log.getStartingHp(), playerChar.getMaxHp() - 1);
        assertEquals(log.getWeapon(), sword);
        assertEquals(log.getHelmet(), helmet);
        assertEquals(log.getArmor(), armor);
        assertEquals(log.getShield(), shield);
        assertEquals(log.getInitialEnemies(), expectedBattleEnemies);
        assertEquals(log.getSupportingEnemies(), expectedSupportEnemies);
        assertTrue(log.isInCampfireRange());
        assertFalse(log.isInTowerRange());
        assertEquals(log.getFinalHp(), 119);
        assertEquals(log.getBattleTriplets(), expectedBattle);
        assertEquals(log.getDefeated(), expectedDefeated);
        // assertTrue(log.getRewards().size() > 0);

        Ennead<Integer, List<BasicEnemy>, List<BasicEnemy>, Boolean, Boolean,
                Weapon, Helmet, Shield, ChestArmor> expectedDisplay =

                new Ennead<>(playerChar.getMaxHp() - 1, expectedBattleEnemies,
                expectedSupportEnemies, true, false, sword, helmet, shield,
                armor);

        assertEquals(log.displaySetup(), expectedDisplay);

    }

    // Check this test in DEBUG Console
    @Test
    public void testSummaryBuilder() {
        PathPosition charP = new PathPosition(0, path);
        Character playerChar = new Character(charP);
        world.setCharacter(playerChar);

        // Set a Hero Castle where character spawns
        SimpleIntegerProperty cX = new SimpleIntegerProperty(playerChar.getX());
        SimpleIntegerProperty cY = new SimpleIntegerProperty(playerChar.getY());
        HeroCastle heroCastle = new HeroCastle(cX, cY);
        world.addBuilding(heroCastle);

        // Equip character
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Staff staff = new Staff(x, y);
        playerChar.setWeapon(staff);
        
        basicChestArmor armor = new basicChestArmor(x, y);
        playerChar.setChestArmor(armor);

        // Spawn a zombie and a vampire to fight
        PathPosition battleP = new PathPosition(1, path);
        Vampire vampire1 = new Vampire(battleP, world);
        Zombie zombie1 = new Zombie(battleP, playerChar);
        world.addEnemy(vampire1);
        world.addEnemy(zombie1);

        // Spawn a zombie and vampire to support the battle
        PathPosition zombieP = new PathPosition(3, path);
        Zombie zombie = new Zombie(zombieP, playerChar);
        world.addEnemy(zombie);

        PathPosition vampireP = new PathPosition(6, path);
        Vampire vampire = new Vampire(vampireP, world);
        world.addEnemy(vampire);

        // Spawn a tower in battle
        x = new SimpleIntegerProperty(1);
        y = new SimpleIntegerProperty(2);
        Tower tower = new Tower(x, y);
        world.addBuilding(tower);

        // Battle
        world.runTickMoves();

        Summary summary = world.getLastSummary();
        System.out.println(summary.printMe());
        
    }
}
