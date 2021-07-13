package unsw.loopmania;

import java.util.ArrayList;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private int hp;
    private int maxHp;
    private boolean buffed;
    private int gold;
    private ArrayList<Ally> allyList;
    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position);
        hp = 100;
        maxHp = 100;
        allyList = new ArrayList<Ally>();
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void regen(int health) {
        if (health + hp > maxHp) {
            hp = maxHp;
        }
        else {
            hp += health;
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
    public ArrayList<Ally> getAllyList() {
        return allyList;
    }
    public void recruitAlly(Ally ally) {
        if (allyList.size() < 3) {
            allyList.add(ally);
        }
    }
}
