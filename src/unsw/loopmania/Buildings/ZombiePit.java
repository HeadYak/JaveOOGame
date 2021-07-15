package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Character;

public class ZombiePit extends Building implements Spawn{
    private Zombie zombie;
    private int loopReq;

    public ZombiePit(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        zombie = new ZombiePit();
    }

    @Override
    public void spawn(int loops, LoopManiaWorld world) {
        Zombie newZombie = new Zombie(world.findClosestPathTile(getX(), getY()));
        world.addEnemy(newZombie);
    }

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public Boolean getIsSpawner() {
        return true;
    }

    @Override
    public void interact(Character character) {
    }

    @Override
    public Boolean canInteract() {
        return false;
    }
}
