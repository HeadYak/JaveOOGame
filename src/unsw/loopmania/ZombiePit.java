package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

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
    }
}
