package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Barracks extends Building{
    private Ally ally;
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        ally = new Ally();
    }
}
