package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.Items.Item;
import unsw.loopmania.Items.HealthPotion;
import unsw.loopmania.Items.Armor.Armor;
import unsw.loopmania.Items.Armor.ChestArmor;
import unsw.loopmania.Items.Armor.Helmet;
import unsw.loopmania.Items.Armor.Shield;
import unsw.loopmania.Items.Ring.OneRing;
import unsw.loopmania.Items.Weapons.Staff;
import unsw.loopmania.Items.Weapons.Weapon;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.movement.MoveClockwise;
import unsw.loopmania.Buildings.*;



/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private boolean buffed;
    private int gold;
    private ArrayList<Ally> allyList;
    private Boolean isSupported;
    private boolean isStunned;
    private Weapon equippedWeapon;
    private List<Item> inventory;
    private int xp;
    private Helmet equippedHelmet;
    private ChestArmor equippedChestArmor;
    private List<Building> inRange;
    private Shield equippedShield;
    private double damageTakenModifier;
    private List<Armor> eqiuppedArmors;

    public Character(PathPosition position) {
        super(position);
        setHp(300);
        setMaxHp(300);
        damageTakenModifier = 1.0;
        setDmg(5);
        setMoveSpeed(1);
        allyList = new ArrayList<Ally>();
        inventory = new ArrayList<Item>();
        inRange = new ArrayList<Building>();
        isSupported = false;
        buffed = false;
        setMoveSpeed(1);
        setMoveBehaviour(new MoveClockwise());
        setMoveSpeed(1);
    }

    /**
     * 
     * @param inventory passes in the characters inventory and attempts to use a health potion. If a potion is used it is deleted from the characters inventory
     */
    public void useHealthPotion(List<Item> inventory){
        for (Item i : inventory) {
            if (i instanceof HealthPotion){
                this.setHp(this.getMaxHp());
                // this.hp = maxHp;
                inventory.remove(i);
                break;
            }
        }
    }
    
    public void updateEquippedArmors(){
        List<Armor> temp = new ArrayList<>();
        if(equippedChestArmor != null){
            temp.add(equippedChestArmor);
        }
        if(equippedHelmet != null){
            temp.add(equippedHelmet);
        }

        if(equippedShield != null){
            temp.add(equippedShield);
        }

        eqiuppedArmors = temp;
        setDamageTakenModifier();
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
    public void setHelmet(Item newHelmet){
        if(equippedHelmet != null){
            addToInventory(equippedHelmet); 
        }
        // addToInventory(equippedHelmet);
        equippedHelmet = (Helmet) newHelmet;
        updateEquippedArmors();

    }

    public Shield getShield(){
        return equippedShield;
    }

    public void setShield(Item newshield){
        if(equippedShield != null){
            addToInventory(equippedShield);
        }
        // addToInventory(equippedShield);
        equippedShield = (Shield) newshield;
        updateEquippedArmors();
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
    public void setChestArmor(Item newChestArmor){
        addToInventory(equippedChestArmor);
        equippedChestArmor = (ChestArmor) newChestArmor;
        updateEquippedArmors();
    }

    /**
     * @return the character's inventory
     */
    public List<Item> getInventory(){
        return inventory;
    }
    public void setDamageTakenModifier(){
        Double temp = 1.0;
        // List<Double> values = new ArrayList<>();
        for(Armor i: eqiuppedArmors){

            temp = temp * (1.0 - i.getDamageBlockModifier());

        }
        damageTakenModifier = 1.0 - temp;
    }
    public double getDamageTakenModifier(){
        return damageTakenModifier;
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
    public void setWeapon(Item newWeapon){
        if(equippedWeapon != null){
            addToInventory(equippedWeapon);
        }
        // addToInventory(equippedWeapon);
        this.equippedWeapon = (Weapon) newWeapon;
    }

    /**
     * restores health to the character, if restoration makes that the character heals over his max hp
     * it will set his hp back to maxhp
     * @param health
     */
    public void regen(int health) {
        if (health + getHp() > getMaxHp()) {
            setHp(getMaxHp());
        }
        else {
            setHp(getHp() + health);
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
     * sells item to shop, gets gold and removes item from inventory
     * @param item
     */
    public void sell(Item item) {
        gold += item.getGoldValue()/2;
        inventory.remove(item);
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

    public boolean hasOneRing(){
        for(Item i: inventory){
            if(i instanceof OneRing){
                return true;
            
            }
        }
        return false;
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
     * Getter for isStunned
     * @return whether character is stunned or not
     */
    public boolean isStunned() {
        return isStunned;
    }

    /**
     * Setter for isStunned
     * @param isStunned whether character is to be stunned or not
     */
    public void setStunned(boolean isStunned) {
        this.isStunned = isStunned;
    }

    /**
     * Attacks player using player + weapon damage
     * @param enemy enemy to be attacked
     */
    public void attack(BasicEnemy enemy) {
        
        // Character has an equipped weapon
        if (equippedWeapon != null) {
            equippedWeapon.attack(enemy);

            // Attack again if buffed
            if (buffed) {
                equippedWeapon.attack(enemy);
            }

        // No weapon, deal base damage instead
        } else {
            int damage = getDmg() * 4;

            // If character is buffed, multiply damage by 2
            if (buffed) {
                damage *= 2;
            }
            enemy.setHp(enemy.getHp() - damage);
        }
    }

    /**
     * Attacks using a crit attack
     * @param player player's character to be attacked 
     * @param battleEnemies list of all enemies currently in battle
     */
    public BasicEnemy critAttack(List<BasicEnemy> battleEnemies) {
        
        // If buffed, attack without crit effects
        if (buffed && !(equippedWeapon instanceof Staff)) {
            equippedWeapon.critAttack(battleEnemies);
        }

        return equippedWeapon.critAttack(battleEnemies);
    }
}
