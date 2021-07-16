package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Character;
import unsw.loopmania.BasicEnemy;

public class ZombiePit extends Building implements Spawn{
    private Zombie zombie;
    private int loopReq;

    public ZombiePit(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        zombie = new ZombiePit(x, y);
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
    public Boolean canInteract(Character character) {
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
        spawn(character.getLoop(), world);
    }
}
