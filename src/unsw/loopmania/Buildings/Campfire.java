package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import jdk.internal.icu.impl.CharTrie;
import unsw.loopmania.Building;
import unsw.loopmania.Character;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.*;

public class Campfire extends Building{
    private int range;
    public Campfire(SimpleIntegerProperty x, SimpleIntegerProperty y) {
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

    public void buff(Character character) {
        character.activateBuff();
    }

    @Override
    public void interact(Character character) {
        character.activateBuff();
    }

    @Override
    public Boolean canInteract(Character character) {
        if (range > Math.sqrt((character.getX() - getX())^2 + (character.getY() - getY())^2)) {
            character.addInRange(this);
            return true;
        }
        character.removeInRange(this);
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
}

