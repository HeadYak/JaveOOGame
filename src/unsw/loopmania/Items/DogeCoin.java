package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;
public class DogeCoin extends Item {

    public DogeCoin(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setItemValue(0);
    }
    
}
