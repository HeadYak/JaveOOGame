package unsw.loopmania.enemies;

import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;

import java.util.List;

import unsw.loopmania.Character;

/**
 * a basic form of enemy in the world
 */
public abstract class BasicEnemy extends MovingEntity {
    /**
     * enemy's battle radius
     */
    private int battleRadius;

    /**
     * enemy's's support radius
     */
    private int supportRadius;

    /**
     * enemy's's support radius
     */
    private int weight;

    /**
     * Create a basic enemy
     * @param position represents the current position in the path
     */
    public BasicEnemy(PathPosition position) {
        super(position);
    }

    /**
     * Getter for enemy's battle radius
     * @return the battle radius
     */
    public int getBattleRadius() {
        return battleRadius;
    }

    /**
     * Setter for battle radius
     * @param battleRadius new battle radius to be set
     */
    public void setBattleRadius(int battleRadius) {
        this.battleRadius = battleRadius;
    }

    /**
     * Getter for enemy's support radius
     * @return the support radius
     */
    public int getSupportRadius() {
        return supportRadius;
    }

    /**
     * Setter for support radius
     * @param supportRadius new support radius to be set
     */
    public void setSupportRadius(int supportRadius) {
        this.supportRadius = supportRadius;
    }

    /**
     * Getter for enemy's weight
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Setter for enemy's weight
     * @param supportRadius new weight to be set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Attacks player using enemy's damage
     * @param player player's character to be attacked
     */
    public void attack(Character player) {
        int damage = (int) (getDmg() * 4 * player.getDamageTakenModifier());
        player.setHp(player.getHp() - damage);
    }

    /**
     * Attacks player using enemy's support damage (1/2)
     * @param player player's character to be attacked
     */
    public void supportAttack(Character player) {
        int damage = (int) (getDmg() * 2 * player.getDamageTakenModifier());
        player.setHp(player.getHp() - damage);
    }

    /**
     * Abstract method for crit attacks
     * @param player player's character to be attacked 
     * @param battleEnemies list of all enemies currently in battle
     */
    public abstract void critAttack(Character player,
            List<BasicEnemy> battleEnemies);
}
