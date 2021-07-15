package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.LoopManiaWorld;

public class ZombiePit extends Building implements Spawn{
    private Zombie zombie;
    private int loopReq;

    public ZombiePit(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        zombie = new ZombiePit();
        isSpawner = true;
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
}
