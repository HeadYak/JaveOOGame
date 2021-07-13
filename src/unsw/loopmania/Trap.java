package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Trap extends Building{

    public Trap(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
    
    public void trap(BasicEnemy enemy) {
        enemy.damage(60);
    }
}
