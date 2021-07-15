package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Building extends StaticEntity{
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    abstract public Boolean getIsSpawner();
    abstract public int getRange();
    abstract public Boolean canInteract(Character character);
    abstract public void interact(Character character);
}
