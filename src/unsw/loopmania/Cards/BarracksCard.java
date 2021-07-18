package unsw.loopmania.Cards;

import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.DisplayNameGenerator.Simple;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Buildings.Barracks;
import unsw.loopmania.Buildings.Building;

public class BarracksCard extends Card {

  


    public BarracksCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setAllowedTiles(new onlyPathTiles());
        // allowedTilesList = new onlyPathTiles();
    }

    @Override
    public List<Pair<Integer, Integer>> getValidTilesList(LoopManiaWorld world) {
        
        return getTileStrategy().getvalidTiles(world);

        // return allowedTiles.getvalidTiles(world);
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
            Barracks newBarracks = new Barracks(x, y);
            world.addBuilding(newBarracks);

        }

    }

    @Override
    public Building getBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return (new Barracks(x, y));
    }
    
}
