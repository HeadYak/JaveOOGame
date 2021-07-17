package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.ChaCha20ParameterSpec;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.Items.Item;
import unsw.loopmania.Items.Weapons.Sword;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

}
