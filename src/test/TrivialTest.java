package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.javatuples.Pair;

import org.junit.jupiter.api.Test;

import unsw.loopmania.LoopManiaWorld;


/**
 * this class is a dummy class demonstrating how to setup tests for the project
 * you should setup additional test classes in a similar fashion, aiming to achieve high coverage.
 * A clickable "Run Test" link should appear if you have installed the Java Extension Pack properly.
 */
public class TrivialTest {
    @Test
    public void blahTest(){
        assertEquals("a", "a");
    }
    
    @Test
    public void blahTest2(){
        LoopManiaWorld d = new LoopManiaWorld(1, 2, new ArrayList<>());
        assertEquals(d.getWidth(), 1);
    }

    @Test
    public void test_worldheight(){
        LoopManiaWorld world = new LoopManiaWorld(1, 2, new ArrayList<>());
        assertEquals(world.getHeight(), 2);

    }
    @Test
    public void test_enemies(){
        List<Pair<Integer, Integer>> path = new ArrayList<>();
        Pair <Integer, Integer> path_tile = new Pair<Integer,Integer>(0, 0);
        Pair<Integer, Integer> path_tile1 = new Pair<Integer, Integer>(1, 0);
        Pair<Integer, Integer> path_tile2 = new Pair<Integer, Integer>(1, 1);
        Pair<Integer, Integer> path_tile3 = new Pair<Integer, Integer>(1, 0);
        // Pair<Integer, Integer> path_tile = new Pair<Integer, Integer>(1, 1);
        // Pair<Integer, Integer> path_tile1 = new Pair<Integer, Integer>(2, 1);
        // Pair<Integer, Integer> path_tile2 = new Pair<Integer, Integer>(2, 2);
        // Pair<Integer, Integer> path_tile3 = new Pair<Integer, Integer>(2, 1);
        path.add(path_tile);
        path.add(path_tile1);
        path.add(path_tile2);
        path.add(path_tile3);

        LoopManiaWorld world = new LoopManiaWorld(5, 5, path);
        System.out.println(world.possiblySpawnEnemies());


    }
}
