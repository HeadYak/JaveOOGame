package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.*;

public class Tower extends Building implements Support{
    private int range;
    public Tower(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        range = 1;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public Boolean getIsSpawner() {
        return false;
    }

    @Override
    public void support(Character character) {
        character.activateSupport();
    }

    @Override
    public void interact(Character character) {
        support(character);
    }

    @Override
    public Boolean canInteract(Character character) {
        if (range > Math.sqrt((character.getX() - getX())^2 + (character.getY() - getY())^2)) {
            character.addInRange(this);
            return true;
        }
        if (character.removeInRange(this)) {
            character.deactivateSupport();
        }
        return false;
    }

    @Override
    public void interactMob(BasicEnemy enemy) {
    }

    @Override
    public Boolean canInteractMob(BasicEnemy enemy) {
        return false;
    }

    @Override
    public void newLoop(LoopManiaWorld world, Character character) {
    }

    @Override
    public Boolean canInteract(java.lang.Character character) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void interact(java.lang.Character character) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void newLoop(LoopManiaWorld world, java.lang.Character character) {
        // TODO Auto-generated method stub
        
    }
}
