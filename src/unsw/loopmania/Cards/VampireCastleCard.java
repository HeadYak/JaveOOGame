package unsw.loopmania.Cards;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Buildings.VampireCastleBuilding;

/**
 * represents a vampire castle card in the backend game world
 */
public class VampireCastleCard extends Card {
    // TODO = add more types of card
    public VampireCastleCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setAllowedTiles(new onlyadjacentPathTiles());
        // allowedTilesList = new onlyadjacentPathTiles();
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
            System.out.println("Yoyo");
            VampireCastleBuilding newVampireCastle = new VampireCastleBuilding(x, y);
            world.addBuilding(newVampireCastle);

        }

    }
 
    @Override
    public Building getBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return (new VampireCastleBuilding(x, y));
    }
}
