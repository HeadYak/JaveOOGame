
package unsw.loopmania.Cards;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.StaticEntity;

/**
 * a Card in the world
 * which doesn't move
 */
public abstract class Card extends StaticEntity {
    private cardAllowedTiles allowedTiles;
    // TODO = implement other varieties of card than VampireCastleCard
    public Card(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void setAllowedTiles(cardAllowedTiles newAllowedTiles) {
        allowedTiles = newAllowedTiles;
    }
    abstract public void placeCard(LoopManiaWorld world, SimpleIntegerProperty x, SimpleIntegerProperty y);

    public cardAllowedTiles getTileStrategy(){
        return allowedTiles;
    }

    abstract public List<Pair<Integer, Integer>> getValidTilesList(LoopManiaWorld world);
}
