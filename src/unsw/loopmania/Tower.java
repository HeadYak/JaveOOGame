package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Tower extends Building{
    private Tower tower;
    public Tower(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        tower = new Tower();
    }
}
