package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Building;

public class Trap extends Building{

    public Trap(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
    
    public void trap(BasicEnemy enemy) {
        enemy.damage(60);
    }
}
