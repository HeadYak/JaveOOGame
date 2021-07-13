package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Barracks extends Building{
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public addAlly(Character character) {
        Ally newAlly = new Ally();
        character.recruitAlly(newAlly);
    }
}
