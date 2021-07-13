package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import unsw.loopmania.LoopManiaWorld;

public class EnemyMovementTest {
    @Test
    public void blahTest(){
        assertEquals("a", "a");
    }
    
    @Test
    public void blahTest2(){
        LoopManiaWorld d = new LoopManiaWorld(1, 2, new ArrayList<>());
        assertEquals(d.getWidth(), 1);
    }
}
