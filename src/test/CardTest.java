package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.Village;
import unsw.loopmania.Cards.BarracksCard;
import unsw.loopmania.Cards.TowerCard;
import unsw.loopmania.Cards.VillageCard;


public class CardTest {


    @Test
    public void testVillage(){
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(0, 0);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(1, 2, tempPath);
        assertEquals(d.getWidth(), 1);
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();
        
        BarracksCard newBarracksCard = new BarracksCard(x, y);

        System.out.println(newBarracksCard.getValidTilesList(d));



        // System.out.println(newVillageCard.allowedTilesList.t);

    }
    @Test
    public void testTower() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(1, 1);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(3, 3, tempPath);
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        TowerCard newTowerCard = new TowerCard(x, y);

        System.out.println(newTowerCard.getValidTilesList(d));

        // System.out.println(newVillageCard.allowedTilesList.t);

    }


}
