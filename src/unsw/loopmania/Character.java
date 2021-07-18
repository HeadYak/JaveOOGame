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
    private Helmet equippedHelmet;
    private ChestArmor equippedChestArmor;
    private List<Building> inRange;

    public Character(PathPosition position) {
        super(position);
        hp = 300;
        maxHp = 300;
        setDmg(5);
        setMoveSpeed(1);
        damageTaken = 1.0;
        allyList = new ArrayList<Ally>();
        inventory = new ArrayList<Item>();
        inRange = new ArrayList<Building>();
        isSupported = false;
        mobSupport = false;
        buffed = false;

        setMoveBehaviour(new MoveClockwise());
    }

    /**
     * @returns the Helmet that is currently equiped by the character
     */
    public Helmet getHelmet(){
        return equippedHelmet;
    }

    /**
     * replaces the Helmet worn by the character
     * @param newHelmet
     */
    public void setHelmet(Helmet newHelmet){
        addToInventory(equippedHelmet);
        equippedHelmet = newHelmet;
    }

    /**
     * @returns the ChestArmor that the character currently has equiped
     */
    public ChestArmor equippedChestArmor(){
        return equippedChestArmor;
    }

    /**
     * replaces the ChestArmor currently equiped by the character
     * @param newChestArmor
     */
    public void setChestArmor(ChestArmor newChestArmor){
        addToInventory(equippedChestArmor);
        equippedChestArmor = newChestArmor;
    }

    /**
     * @return the character's inventory
     */
    public ArrayList<Item> getInventory(){
        return inventory;
    }

    /**
     * @return a list of buildings that the character is currently in range of/getting support
     * and buffs from
     */
    public List<Building> getInRange() {
        return inRange;
    }

    /**
     * adds a building to the list of buildings the character is in range of
     * @param building
     */
    public void addInRange(Building building) {
        inRange.add(building);
    }

    /**
     * checks if the building is in the list and removes it
     * @param building
     * @return true if the current building was already in the list false if not
     */
    public Boolean removeInRange(Building building) {
        for (Building b : inRange) {
            if (b.getX() == building.getX() && b.getY() == (building.getY())) {
                inRange.remove(b);
                return true;
            }
        }
        return false;
    }

    /**
     * @return the weapon that the character currently has equiped
     */
    public Weapon getWeapon(){
        return equippedWeapon;
    }

    /**
     * replaces the weapon equiped by the character
     * @param newWeapon
     */
    public void setWeapon(Weapon newWeapon){
        addToInventory(equippedWeapon);
        this.equippedWeapon = newWeapon;
    }

    /**
     * @return the max hp of teh character
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * restores health to the character, if restoration makes that the character heals over his max hp
     * it will set his hp back to maxhp
     * @param health
     */
    public void regen(int health) {
        if (health + hp > maxHp) {
            hp = maxHp;
        }
        else {
            hp += health;
        }
    }

    /**
     * activates the buff status of the character
     */
    public void activateBuff() {
        buffed = true;
    }

    /**
     * deactivates the buff status of the character
     */
    public void deactivateBuff() {
        buffed = false;
    }

    /**
     * @return the buff status of the character
     */
    public boolean getBuffStatus() {
        return buffed;
    }

    /**
     * purchase an item from the shop, spends gold and adds to inventory
     * @param item
     */
    public void buy(Item item) {
        gold -= item.getGoldValue();
        addToInventory(item);
    }

    /**
     * add an item to the characters inventory
     * @param item
     */
    public void addToInventory(Item item) {
        inventory.add(item);
        if (inventory.size() > 8) {
            inventory.remove(0);
            gold += 5;
            xp += 5;
        }
    }

    /**
     * @return the amount of xp the character has
     */
    public int getXp() {
        return xp;
    }

    public void addXp(int addXp){
        this.xp += addXp;
    }

    /**
     * @return the amount of gold the character has
     */
    public int getGold() {
        return gold;
    }

    /**
     * Adds gold to character
     * @param addgold gold to be added
     */
    public void addGold(int addgold){
        this.gold += addgold;
    }
    /**
     * @return all the characters current allies
     */
    public ArrayList<Ally> getAllyList() {
        return allyList;
    }

    /**
     * adds an ally to the characters ally list of there is less than 3
     * @param ally
     */
    public void recruitAlly(Ally ally) {
        if (allyList.size() < 3) {
            allyList.add(ally);
        }
    }

    /**
     * activates the support status of the character
     */
    public void activateSupport() {
        isSupported = true;
    }

    /**
     * deactivates the support status of the character
     */
    public void deactivateSupport() {
        isSupported = false;
    }

    /**
     * @return the support status of the character
     */
    public Boolean getIsSupported() {
        return isSupported;
    }

    /**
     * activates the mob support of the character ( in rnage of vampire castle)
     */
    public void activateMobSupport() {
        mobSupport = true;
    }

    /**
     * deactivates the mob support of the character (not in rnage of vampire castle)
     */
    public void deactivateMobSupport() {
        mobSupport = false;
    }

    /**
     * @return the mob support of the character
     */
    public Boolean getMobSupported() {
        return mobSupport;
    }

    /**
     * @return current hp of the character
     */
    @Override
    public int getHp() {
        return hp;
    }

    /**
     * sets the hp of the character
     * @param health
     */
    @Override
    public void setHp(int health) {
        hp = health;
    }
}
