package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Building;
import unsw.loopmania.Character;
import unsw.loopmania.*;

public class Trap extends Building{

    public Trap(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public Boolean getIsSpawner() {
        return false;
    }
    
    public void trap(BasicEnemy enemy) {
        enemy.damage(60);
    }

    @Override
    public void interact(Character character) {
    }

    @Override
    public Boolean canInteract(Character character) {
        return false;
    }

    @Override
    public void interactMob(BasicEnemy enemy) {
        trap(enemy);
    }

    @Override
    public Boolean canInteractMob(BasicEnemy enemy) {
        return true;
    }

    @Override
    public void newLoop(LoopManiaWorld world, Character character) {
    }
}
