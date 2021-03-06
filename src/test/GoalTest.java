package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.GoalComposite;
import unsw.loopmania.GoalGold;
import unsw.loopmania.GoalLeaf;
import unsw.loopmania.GoalLoops;
import unsw.loopmania.GoalManager;
import unsw.loopmania.GoalOr;
import unsw.loopmania.GoalXp;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Character;
import unsw.loopmania.GoalAnd;
import unsw.loopmania.GoalBoss;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GoalTest {

    @Test
    public void GoalLoopTest(){
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);


        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        GoalManager goalManager = new GoalManager();
        GoalOr goals = new GoalOr();
        GoalLoops goalLoops = new GoalLoops(d, 5);
        goals.add(goalLoops);
        goalManager.getGoals().add(goals);
        d.setGoals(goalManager);
        assertFalse(goalManager.update());
        while (d.getLoops() != 5) {
            d.newLoop();
        }
        assertTrue(goalManager.update());
        assertEquals("Goals: (Complete 5 Loops)", goalManager.toString());
    }

    @Test
    public void GoalGoldTest(){
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);


        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        GoalManager goalManager = new GoalManager();
        GoalOr goals = new GoalOr();
        GoalGold goalGold = new GoalGold(d, 1000);
        goals.add(goalGold);
        goalManager.getGoals().add(goals);
        d.setGoals(goalManager);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);
        d.setCharacter(c);
        assertFalse(goalManager.update());
        c.addGold(1000);
        assertTrue(goalManager.update());
        assertEquals("Goals: (Acquire 1000 Gold)", goalManager.toString());
    }

    @Test
    public void GoalXPTest(){
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);


        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        GoalManager goalManager = new GoalManager();
        GoalOr goals = new GoalOr();
        GoalXp goalXP = new GoalXp(d, 1000);
        goals.add(goalXP);
        goalManager.getGoals().add(goals);
        d.setGoals(goalManager);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);
        d.setCharacter(c);
        assertFalse(goalManager.update());
        c.addXp(1000);
        assertTrue(goalManager.update());
        assertEquals("Goals: (Acquire 1000 XP)", goalManager.toString());
    }

    @Test
    public void NoGoalTest(){
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        GoalManager goalManager = new GoalManager();
        GoalComposite goals = new GoalComposite();
        goalManager.getGoals().add(goals);
        d.setGoals(goalManager);
        assertFalse(goalManager.update());
        assertEquals("Goals: None", goalManager.toString());
        goalManager = new GoalManager();
        GoalOr goalOr = new GoalOr();
        goalManager.getGoals().add(goalOr);
        assertEquals("Goals: None", goalManager.toString());
        goalManager = new GoalManager();
        GoalAnd goalAnd = new GoalAnd();
        goalManager.getGoals().add(goalAnd);
        assertEquals("Goals: None", goalManager.toString());

    }

    @Test
    public void goalLeafTest() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        GoalLeaf leaf = new GoalLeaf(d);
        assertFalse(leaf.completed());
    }

    @Test
    public void BossGoalTest(){
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        GoalManager goalManager = new GoalManager();
        GoalOr goals = new GoalOr();
        GoalBoss bossGoal = new GoalBoss(d);
        goals.add(bossGoal);
        goalManager.getGoals().add(goals);
        d.setGoals(goalManager);

        assertFalse(goalManager.update());
        d.allBossKilled();
        assertTrue(goalManager.update());
        assertEquals("Goals: (Kill All Bosses)", goalManager.toString());

        try {
            System.out.println(d.getDogeCoinValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }

    @Test
    public void GoalAndTest(){
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        GoalManager goalManager = new GoalManager();
        GoalAnd goals = new GoalAnd();
        GoalXp goalXP = new GoalXp(d, 1000);
        GoalGold goalGold = new GoalGold(d, 1000);
        GoalLoops goalLoop = new GoalLoops(d, 1);
        goals.add(goalXP);
        goals.add(goalGold);
        goals.add(goalLoop);
        goalManager.getGoals().add(goals);
        d.setGoals(goalManager);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);
        d.setCharacter(c);
        assertFalse(goalManager.update());
        c.addXp(1000);
        assertFalse(goalManager.update());
        c.addGold(1000);
        assertFalse(goalManager.update());
        d.newLoop();
        assertTrue(goalManager.update());
        assertEquals("Goals: (Acquire 1000 XP AND Acquire 1000 Gold AND Complete 1 Loops)", goalManager.toString());
    }

    @Test
    public void GoalOrTest(){
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        GoalManager goalManager = new GoalManager();
        GoalOr goals = new GoalOr();
        GoalXp goalXP = new GoalXp(d, 1000);
        GoalGold goalGold = new GoalGold(d, 1000);
        GoalLoops goalLoop = new GoalLoops(d, 1);
        goals.add(goalXP);
        goals.add(goalGold);
        goals.add(goalLoop);
        goalManager.getGoals().add(goals);
        d.setGoals(goalManager);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);
        d.setCharacter(c);
        assertFalse(goalManager.update());
        c.addXp(1000);
        assertTrue(goalManager.update());
        c = new Character(temp);
        d.setCharacter(c);
        assertFalse(goalManager.update());

        c.addGold(1000);
        assertTrue(goalManager.update());

        c = new Character(temp);
        d.setCharacter(c);
        assertFalse(goalManager.update());

        d.newLoop();
        assertTrue(goalManager.update());
        assertEquals("Goals: (Acquire 1000 XP OR Acquire 1000 Gold OR Complete 1 Loops)", goalManager.toString());

    }

    @Test
    public void complexGoalTest() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        GoalManager goalManager = new GoalManager();
        GoalOr goals = new GoalOr();
        GoalXp goalXP = new GoalXp(d, 1000);
        GoalGold goalGold = new GoalGold(d, 1000);
        GoalLoops goalLoop = new GoalLoops(d, 1);
        goals.add(goalXP);
        GoalAnd goalAnd = new GoalAnd();
        goalAnd.add(goalLoop);
        goalAnd.add(goalGold);
        goals.add(goalAnd);
        goalManager.getGoals().add(goals);
        d.setGoals(goalManager);
        PathPosition temp = new PathPosition(0, tempPath);
        Character c = new Character(temp);
        d.setCharacter(c);
        assertFalse(goalManager.update());
        c.addXp(1000);
        assertTrue(goalManager.update());
        c = new Character(temp);
        d.setCharacter(c);
        assertFalse(goalManager.update());

        c.addGold(1000);
        assertFalse(goalManager.update());

        assertFalse(goalManager.update());

        d.newLoop();
        assertTrue(goalManager.update());
        assertEquals("Goals: (Acquire 1000 XP OR (Complete 1 Loops AND Acquire 1000 Gold))", goalManager.toString());
    }

}
