package unsw.loopmania;

import java.util.ArrayList;
import unsw.loopmania.Items.Weapon;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private int hp;
    private int maxHp;
    private boolean buffed;
    private int gold;
    private ArrayList<Ally> allyList;
    private Boolean isSupported;
    private Boolean mobSupport;
    private Weapon equippedWeapon;
    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position);
        hp = 100;
        maxHp = 100;
        allyList = new ArrayList<Ally>();
    }
    public Weapon getWeapon(){
        return equippedWeapon;
    }

    public void setWeapon(Weapon newWeapon){
        this.equippedWeapon = newWeapon;
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

    public void activateSupport() {
        isSupported = true;
    }

    public void deactivateSupport() {
        isSupported = false;
    }

    public Boolean getIsSupported() {
        return isSupported;
    }

    public void activateMobSupport() {
        mobSupport = true;
    }

    public void deactivateMobSupport() {
        mobSupport = false;
    }

    public Boolean getMobSupported() {
        return mobSupport;
    }
}
