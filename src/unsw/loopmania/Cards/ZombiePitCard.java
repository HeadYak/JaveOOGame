package unsw.loopmania.Cards;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Buildings.ZombiePit;

public class ZombiePitCard extends Card {

    public ZombiePitCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setAllowedTiles(new onlyadjacentPathTiles());
        // allowedTilesList = new onlyadjacentPathTiles();
    }

    @Override
    public List<Pair<Integer, Integer>> getValidTilesList(LoopManiaWorld world) {
        return getTileStrategy().getvalidTiles(world);
    }

    @Override
    public boolean isCardPlaceable(LoopManiaWorld world, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        List<Pair<Integer, Integer>> validTiles = getValidTilesList(world);

        Pair<Integer, Integer> tempcardCoord = new Pair<>(x.get(), y.get());
        if (validTiles.contains(tempcardCoord)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void placeCard(LoopManiaWorld world, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Pair<SimpleIntegerProperty, SimpleIntegerProperty> cardCoord = new Pair<>(x, y);

        ZombiePit newZombiePit = new ZombiePit(x, y);
        world.addBuilding(newZombiePit);

    }

    @Override
    public Building getBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return (new ZombiePit(x, y));
    }
}
