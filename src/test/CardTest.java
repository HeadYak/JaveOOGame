package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Cards.BarracksCard;
import unsw.loopmania.Cards.CampfireCard;
import unsw.loopmania.Cards.TowerCard;
import unsw.loopmania.Cards.TrapCard;
import unsw.loopmania.Cards.VampireCastleCard;
import unsw.loopmania.Cards.VillageCard;
import unsw.loopmania.Cards.ZombiePitCard;


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
        
        VillageCard newVillageCard = new VillageCard(x, y);

        x.set(0);
        y.set(0);

        newVillageCard.placeCard(d, x, y);

        assertEquals(d.getBuildingEntities().size(), 1);

        assertEquals(newVillageCard.getValidTilesList(d).size(), 1);

        System.out.println(newVillageCard.getValidTilesList(d));


        assertTrue(newVillageCard.getBuilding(x, y) instanceof Building);

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

        // System.out.println(newTowerCard.getValidTilesList(d));

        assertEquals(newTowerCard.getValidTilesList(d).size(), 8);

        x.set(0);
        y.set(0);
        newTowerCard.placeCard(d, x, y);

        assertEquals(d.getBuildingEntities().size(), 1);
        // System.out.println(newVillageCard.allowedTilesList.t);

        assertTrue(newTowerCard.getBuilding(x, y) instanceof Building);

    }

    @Test
    public void testBarracks() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(1, 1);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(3, 3, tempPath);
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        BarracksCard newBarracksCard = new BarracksCard(x, y);

        // System.out.println(newTowerCard.getValidTilesList(d));

        assertEquals(newBarracksCard.getValidTilesList(d).size(), 1);

        x.set(1);
        y.set(1);
        newBarracksCard.placeCard(d, x, y);

        assertEquals(d.getBuildingEntities().size(), 1);
        // System.out.println(newVillageCard.allowedTilesList.t);


        assertTrue(newBarracksCard.getBuilding(x, y) instanceof Building);
    }

    @Test
    public void testCampire() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(1, 1);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(3, 3, tempPath);
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        CampfireCard newCampfireCard = new CampfireCard(x, y);

        // System.out.println(newTowerCard.getValidTilesList(d));

        assertEquals(newCampfireCard.getValidTilesList(d).size(), 1);

        x.set(1);
        y.set(1);
        newCampfireCard.placeCard(d, x, y);

        assertEquals(d.getBuildingEntities().size(), 1);


        assertTrue(newCampfireCard.getBuilding(x, y) instanceof Building);

    }

    @Test
    public void testTrap(){
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(1, 1);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(3, 3, tempPath);
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        TrapCard newTrapCard = new TrapCard(x, y);

        // System.out.println(newTowerCard.getValidTilesList(d));

        assertEquals(newTrapCard.getValidTilesList(d).size(), 1);

        x.set(1);
        y.set(1);
        newTrapCard.placeCard(d, x, y);

        assertEquals(d.getBuildingEntities().size(), 1);

        assertTrue(newTrapCard.getBuilding(x, y) instanceof Building);
    }

    @Test
    public void testVampireCastleCard() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(1, 1);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(3, 3, tempPath);
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        VampireCastleCard newVampireCastleCard = new VampireCastleCard(x, y);

        // System.out.println(newTowerCard.getValidTilesList(d));

        assertEquals(newVampireCastleCard.getValidTilesList(d).size(), 8);

        x.set(0);
        y.set(0);
        newVampireCastleCard.placeCard(d, x, y);

        assertEquals(d.getBuildingEntities().size(), 1);

        assertTrue(newVampireCastleCard.getBuilding(x, y) instanceof Building);
    }


    @Test
    public void testZombiePitCard() {
        List<Pair<Integer, Integer>> tempPath = new ArrayList<Pair<Integer, Integer>>();

        Pair<Integer, Integer> pathtile = new Pair<>(1, 1);

        tempPath.add(pathtile);

        LoopManiaWorld d = new LoopManiaWorld(3, 3, tempPath);
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        ZombiePitCard newZombiePitCard = new ZombiePitCard(x, y);

        
        // System.out.println(newZombiePitCard.getBuilding(x, y));
        assertTrue(newZombiePitCard.getBuilding(x, y) instanceof Building);
        // assertTrue(newZombiePitCard.getBuilding(x, y).getClass());


        assertEquals(newZombiePitCard.getValidTilesList(d).size(), 8);

        x.set(0);
        y.set(0);
        newZombiePitCard.placeCard(d, x, y);

        assertEquals(d.getBuildingEntities().size(), 1);
    }


}
