package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.Character;

public class Barracks extends Building{
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public addAlly(Character character) {
        Ally newAlly = new Ally();
        character.recruitAlly(newAlly);
    }
}
