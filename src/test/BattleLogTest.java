package test;

import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.HeroCastle;
import unsw.loopmania.Buildings.Tower;
import unsw.loopmania.Items.Armor.basicChestArmor;
import unsw.loopmania.Items.Weapons.Staff;
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
        
    }

    @Test
    public void testSummaryBuilder() {

    }

    @Test
    public void testBuildByBattleManager() {
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

        // Spawn 2 high health slug to fight
        PathPosition slugP = new PathPosition(0, path);
        Slug slug = new Slug(slugP);
        slug.setHp(1000);
        world.addEnemy(slug);
        world.addEnemy(slug);

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
    }
}
