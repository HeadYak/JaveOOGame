package unsw.loopmania.Buildings;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.enemies.BasicEnemy;

public interface Spawn {
    public BasicEnemy spawn(LoopManiaWorld world);
}
