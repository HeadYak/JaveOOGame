package unsw.loopmania.Cards;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Buildings.Barracks;

public class BarracksCard extends Card {

  


    public BarracksCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        allowedTilesList = new onlyPathTiles();
    }

    @Override
    public List<Pair<Integer, Integer>> getValidTilesList(LoopManiaWorld world) {
        return allowedTilesList.getvalidTiles(world);
    }

    @Override
    public void placeCard(LoopManiaWorld world, SimpleIntegerProperty x, SimpleIntegerProperty y) {
    
        Pair<SimpleIntegerProperty, SimpleIntegerProperty> cardCoord = new Pair<>(x, y);

        List<Pair<Integer, Integer>> validTiles = getValidTilesList(world);

        if(validTiles.contains(cardCoord)){
            Barracks newBarracks = new Barracks(x, y);

            world.addBuilding(newBarracks);

        }


    }
    
}
