package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ZombiePit extends Building{
    private Zombie zombie;

    public ZombiePit(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        zombie = new ZombiePit();
    }
}
