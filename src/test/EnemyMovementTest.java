package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.javatuples.Pair;
import unsw.loopmania.LoopManiaWorld;

public class EnemyMovementTest {

    @Test
    public void testSlugMovement() {
        List<Pair<Integer, Integer>> path = new ArrayList<Pair<Integer, Integer>>();
        path.add()
        LoopManiaWorld world = new LoopManiaWorld(4, 4, new ArrayList<>());
    }
    
    @Test
    public void blahTest2(){
        LoopManiaWorld d = new LoopManiaWorld(1, 2, new ArrayList<>());
        assertEquals(d.getWidth(), 1);
    }
}
