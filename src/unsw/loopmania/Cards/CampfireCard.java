package unsw.loopmania.Cards;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Buildings.Campfire;

public class CampfireCard extends Card {

    public CampfireCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setAllowedTiles(new onlyPathTiles());
        // allowedTilesList = new onlyPathTiles();
    }

    @Override
    public List<Pair<Integer, Integer>> getValidTilesList(LoopManiaWorld world) {
        return getTileStrategy().getvalidTiles(world);
    }

    @Override
    public void placeCard(LoopManiaWorld world, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Pair<SimpleIntegerProperty, SimpleIntegerProperty> cardCoord = new Pair<>(x, y);

        List<Pair<Integer, Integer>> validTiles = getValidTilesList(world);

        // System.out.println(cardCoord);

        int tempx = x.get();
        int tempy = y.get();

        Pair<Integer, Integer> tempcardCoord = new Pair<>(tempx, tempy);

        // System.out.println(validTiles);

        if (validTiles.contains(tempcardCoord)) {
            // System.out.println("Yoyo");
            Campfire newCampfire = new Campfire(x, y);
            world.addBuilding(newCampfire);

        }

    }

    @Override
    public Building getBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return (new Campfire(x, y));
    }
    
}
