package unsw.loopmania.enemies;

import java.util.List;
import java.util.Random;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.Buildings.Campfire;
import unsw.loopmania.movement.MoveAntiClockwise;
import unsw.loopmania.movement.MoveClockwise;

public class Vampire extends BasicEnemy {
    private LoopManiaWorld worldReference;
    private boolean isMovingClockwise;
    private boolean isBuffed;
    private int buffDuration;
    private int buffDmg;

    /**
     * Constructor for Vampire
     */
    public Vampire(PathPosition position, LoopManiaWorld world) {
        super(position);
        worldReference = world;

        // Vampire stats
        setMoveSpeed(2);
        setCritChance(0.2);
        setBattleRadius(2);
        setSupportRadius(3);
        setHp(300);
        setDmg(8);
        isMovingClockwise = false;
        isBuffed = false;
        buffDuration = 0;
        buffDmg = 0;

        // Behaviours
        setMoveBehaviour(new MoveAntiClockwise());
    }

    /**
     * Checks if vampire is buffed
     * @return whether vampire is buffed or not
     */
    public boolean isBuffed() {
        return isBuffed;
    }

    /**
     * Sets or unsets the buff
     * @param isBuffed true if buffed, false otherwsie
     */
    public void setBuffed(boolean isBuffed) {
        this.isBuffed = isBuffed;
    }

    /**
     * Getter for buff duration
     * @return how long in secs until bath ends
     */
    public int getBuffDuration() {
        return buffDuration;
    }

    /**
     * Reduces buff duration
     * @param buffDuration new buff duration
     */
    public void setBuffDuration(int buffDuration) {
        this.buffDuration = buffDuration;
    }

    /**
     * Getter for if it is moving clockwise
     * @return if it is moving clockwise
     */
    public boolean isMovingClockwise() {
        return isMovingClockwise;
    }

    /**
     * Setter for if it is moving clockwise
     * @param isMovingClockwise whether it is moving clockwise or not
     */
    public void setMovingClockwise(boolean isMovingClockwise) {
        if (isMovingClockwise) {
            setMoveBehaviour(new MoveClockwise());
        } else {
            setMoveBehaviour(new MoveAntiClockwise());
        }
        this.isMovingClockwise = isMovingClockwise;
    }

    
    /**
     * Getter for buff damage
     * @return the amount of damage buffDmg is currently inflicting
     */
    public int getBuffDmg() {
        return buffDmg;
    }

    /**
     * Overridden method that moves vampire as usual, and then checks if it
     * is in the range of a campfire
     */
    @Override
    public void performMove() {
        super.performMove();

        // Check for campfires and see if they are in range
        for (Building building : worldReference.getBuildingEntities()) {
            double xComponent = Math.pow((building.getX() - getX()), 2);
            double yComponent = Math.pow((building.getY() - getY()), 2);

            double dist = Math.sqrt(xComponent + yComponent);

            if (building instanceof Campfire && dist <= building.getRange()) {
                setMovingClockwise(!isMovingClockwise);
                break;
            }
        }
    }
    
    /**
     * Overridden implementation of attack to add buff damage
     */
    @Override
    public void attack(Character player) {
        super.attack(player);

        // If buffed, deal extra buff damage
        if (isBuffed) {
            player.setHp(player.getHp() - buffDmg);
            buffDuration -= 1;
        }

        // Set isBuffed to false if buff duration has ended
        if (isBuffed && buffDuration == 0) {
            isBuffed = false;
            buffDmg = 0;
        }
    }

    /**
     * Overridden implementation of abstract method critAttack that buffs
     * vampire if critting
     */
    @Override
    public void critAttack(Character player, List<BasicEnemy> battleEnemies) {
        int damage = (getDmg() * 4) * 2;

        // Buffing vampire: sets isBuffed to true, add randomised rounds
        // between 1-5 inclusive and damage between 5-15 inclusive
        isBuffed = true;
        buffDuration += new Random().nextInt(5) + 1;
        buffDmg += new Random().nextInt(11) + 5;

        damage += (getDmg() * 4) * 2;
        damage += buffDmg;

        player.setHp(player.getHp() - damage);
    }
}
