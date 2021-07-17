package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.enemies.BasicEnemy;

public abstract class Building extends StaticEntity{
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    abstract public Boolean getIsSpawner();
    abstract public int getRange();
    abstract public Boolean canInteract(Character character);
    abstract public void interact(Character character);
    abstract public Boolean canInteractMob(BasicEnemy enemy);
    abstract public void interactMob(BasicEnemy enemy);
    abstract public void newLoop(LoopManiaWorld world, Character character);
}
