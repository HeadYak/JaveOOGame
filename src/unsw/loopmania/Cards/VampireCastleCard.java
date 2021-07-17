package unsw.loopmania.Cards;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a vampire castle card in the backend game world
 */
public class VampireCastleCard extends Card {
    // TODO = add more types of card
    public VampireCastleCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public List<Pair<Integer, Integer>> getValidTiles() {
        // TODO Auto-generated method stub
        return null;
    }    
}
