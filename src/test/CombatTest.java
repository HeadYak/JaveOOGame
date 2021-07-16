package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;

public class CombatTest {
    List<Pair<Integer, Integer>> path = Arrays.asList(
            new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3),
            new Pair<>(1, 3), new Pair<>(2, 3), new Pair<>(3, 3),
            new Pair<>(3, 2), new Pair<>(3, 1), new Pair<>(3, 0),
            new Pair<>(2, 0), new Pair<>(1, 0), new Pair<>(0, 0));

    LoopManiaWorld world = new LoopManiaWorld(4, 4, path);

    @Test
    public void testSmth() {
        PathPosition characterP = new PathPosition()
    }
}
