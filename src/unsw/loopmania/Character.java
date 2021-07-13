package unsw.loopmania;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private int hp;
    private int maxHp;
    private boolean buffed;
    private int gold;
    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position);
        hp = 100;
        maxHp = 100;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void regen(int health) {
        if (health + hp > maxHp) {
            health = maxHp;
        }
        else {
            hp = maxHp;
        }
    }

    public void activateBuff() {
        buffed = true;
    }

    public void deactivateBuff() {
        buffed = false;
    }

    public boolean getBuffStatus() {
        return buffed;
    }

    public void spend(int price) {
        gold -= price;
    }

    public int getGold() {
        return gold;
    }
}
