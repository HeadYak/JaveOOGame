package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;

public class Tower extends Building{
    private Tower tower;
    private int range;
    public Tower(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        tower = new Tower();
        range = 1;
    }

    public int getRange() {
        return range;
    }
}
