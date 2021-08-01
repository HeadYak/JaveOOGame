package unsw.loopmania.enemies;

import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Items.Item;
import unsw.loopmania.Items.Ring.OneRing;

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
     * Attacks player using enemy's damage
     * @param player player's character to be attacked
     */
    public synchronized void attack(Character player) {
        int damage = (int) (getDmg() * 4 * player.getDamageTakenModifier());

        System.out.println(damage);
        player.setHp((player.getHp() - damage));

        if(player.hasOneRing() && (player.getHp() <= 0)){
            player.setHp(player.getMaxHp());
            for(Item i : player.getInventory()){
                if(i instanceof OneRing){
                    player.getInventory().remove(i);
                    break;
                }
            }

        }
    }

    /**
     * Attacks player using enemy's support damage (1/2)
     * @param player player's character to be attacked
     */
    public synchronized void supportAttack(Character player) {
        int damage = getDmg() * 2;
        player.setHp((int) (player.getHp() - damage*player.getDamageTakenModifier()));


        if (player.hasOneRing() && (player.getHp() <= 0)) {
            player.setHp(player.getMaxHp());
            for (Item i : player.getInventory()) {
                if (i instanceof OneRing) {
                    player.getInventory().remove(i);
                    break;
                }
            }

        }
    }

    /**
     * Abstract method for crit attacks
     * @param player player's character to be attacked 
     * @param battleEnemies list of all enemies currently in battle
     */
    public abstract void critAttack(Character player,
            List<BasicEnemy> battleEnemies);
}
