package unsw.loopmania.Cards;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;

public class VillageCard extends Card{

    public VillageCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        allowedTilesList = new onlyPathTiles();
        //TODO Auto-generated constructor stub
    }

    @Override
    public List<Pair<Integer, Integer>> getValidTilesList(LoopManiaWorld world) {
        // TODO Auto-generated method stub
        return allowedTilesList.getvalidTiles(world);
    }

    
}
