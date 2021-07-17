package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.Items.Item;
import unsw.loopmania.Items.Armor.ChestArmor;
import unsw.loopmania.Items.Armor.Helmet;
import unsw.loopmania.Items.Weapons.Weapon;
import unsw.loopmania.movement.MoveClockwise;
import unsw.loopmania.Buildings.*;



/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private int hp;
    private int maxHp;
    private boolean buffed;
    private int gold;
    private double damageTaken;
    private ArrayList<Ally> allyList;
    private Boolean isSupported;
    private Boolean mobSupport;
    private Weapon equippedWeapon;
    private ArrayList<Item> inventory;
    private int xp;
    private int loops;
    private Helmet equippedHelmet;
    private ChestArmor equippedChestArmor;
    private List<Building> inRange;
    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position);
        hp = 100;
        maxHp = 100;
        damageTaken = 1.0;
        allyList = new ArrayList<Ally>();
        inventory = new ArrayList<Item>();
        inRange = new ArrayList<Building>();
        isSupported = false;
        mobSupport = false;
        buffed = false;

        setMoveBehaviour(new MoveClockwise());
    }
    public Helmet getHelmet(){
        return equippedHelmet;
    }
    public void setHelmet(Helmet newHelmet){
        equippedHelmet = newHelmet;
    }

    public ChestArmor equippedChestArmor(){
        return equippedChestArmor;
    }
    public void setChestArmor(ChestArmor newChestArmor){
        equippedChestArmor = newChestArmor;
    }

    public ArrayList<Item> getInventory(){
        return inventory;
    }
    public List<Building> getInRange() {
        return inRange;
    }

    public void addInRange(Building building) {
        inRange.add(building);
    }

    public Boolean removeInRange(Building building) {
        for (Building b : inRange) {
            if (b.getX() == building.getX() && b.getY() == (building.getY())) {
                inRange.remove(b);
                return true;
            }
        }
        return false;
    }

    public Weapon getWeapon(){
        return equippedWeapon;
    }

    public void setWeapon(Weapon newWeapon){
        this.equippedWeapon = newWeapon;
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

    public void buy(Item item) {
        gold -= item.getGoldValue();
        addToInventory(item);
    }

    public void addToInventory(Item item) {
        inventory.add(item);
        if (inventory.size() > 8) {
            inventory.remove(0);
            gold += 5;
            xp += 5;
        }
    }

    public int getXp() {
        return xp;
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

    public void newLoop() {
        loops +=1;
    }

    public int getLoop() {
        return loops;
    }
}
