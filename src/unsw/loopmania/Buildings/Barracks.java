package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.Character;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.*;

public class Barracks extends Building{
    public Barracks(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void addAlly(Character character) {
        Ally newAlly = new Ally();
        character.recruitAlly(newAlly);
    }

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public Boolean getIsSpawner() {
        return false;
    }

    @Override
    public void interact(Character character) {
        if (character.getX() == getX() && character.getY() == getY()) {
            addAlly(character);
        }
    }

    @Override
    public Boolean canInteract(Character character) {
        return true;
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
}
