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
import unsw.loopmania.enemies.Slug;

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

        PathPosition slugP = new PathPosition(0, path);
        Slug slug = new Slug(slugP);
        world.addEnemy(slug);
    }
}
