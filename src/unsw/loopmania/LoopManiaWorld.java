package unsw.loopmania;
import unsw.loopmania.Items.*;
import unsw.loopmania.Items.Armor.TreeStump;
import unsw.loopmania.Items.Armor.basicChestArmor;
import unsw.loopmania.Items.Armor.basicHelmet;
import unsw.loopmania.Items.Armor.basicShield;
import unsw.loopmania.Items.Ring.OneRing;
import unsw.loopmania.Items.Weapons.Stake;
import unsw.loopmania.Items.Weapons.Sword;
import unsw.loopmania.Items.Weapons.FOTW;
import unsw.loopmania.battles.BattleLog;
import unsw.loopmania.battles.BattleManager;
import unsw.loopmania.battles.Summary;
import unsw.loopmania.Items.Weapons.Staff;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.enemies.Doggie;
import unsw.loopmania.enemies.ElanMuske;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.Buildings.*;
import unsw.loopmania.Cards.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Math;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A backend world.
 *
 * A world can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 */
public class LoopManiaWorld {
    // TODO = add additional backend functionality

    public static final int unequippedInventoryWidth = 4;
    public static final int unequippedInventoryHeight = 4;

    /**
     * width of the world in GridPane cells
     */
    private int width;

    /**
     * height of the world in GridPane cells
     */
    private int height;
    private int loops;
    private GoalManager goals;
    /**
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private List<Entity> nonSpecifiedEntities;

    private Character character;
    // hp, maxHP, inventory (arrayList), equipped helmet, equipped sword, equipped armour, loop counter, ally list (arrayList), 
    // 

    // TODO = add more lists for other entities, for equipped inventory items, etc...
    private List<Ally> allies;

    // TODO = expand the range of enemies
    private List<BasicEnemy> enemies;

    // TODO = expand the range of cards
    private List<Card> cardEntities;

    // TODO = expand the range of items
    private List<Item> unequippedInventoryItems;

    // TODO = expand the range of buildings
    private List<Building> buildingEntities;

    private BattleManager battleManager;
    private int dogeValue;


    private List<BasicEnemy> defeatedEnemies;
    private List<BasicEnemy> buildingSpawns;
    private Boolean allBossKilled;

    private Summary lastSummary;
    private BattleLog lastLog;

    // Boss-related booleans
    private boolean doggieSpawned;
    private boolean doggieDefeated;
    private boolean elanMuskeSpawned;
    private boolean elanMuskeDefeated;

    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse them
     */
    private List<Pair<Integer, Integer>> orderedPath;

    /**
     * create the world (constructor)
     * 
     * @param width width of world in number of cells
     * @param height height of world in number of cells
     * @param orderedPath ordered list of x, y coordinate pairs representing position of path cells in world
     */
    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath) {
        this.width = width;
        this.height = height;
        nonSpecifiedEntities = new ArrayList<>();
        character = null;
        enemies = new ArrayList<BasicEnemy>();
        cardEntities = new ArrayList<>();
        unequippedInventoryItems = new ArrayList<Item>();
        this.orderedPath = orderedPath;
        this.goals = null;
        buildingEntities = new ArrayList<Building>();
        defeatedEnemies = new ArrayList<BasicEnemy>();
        loops = 0;
        allBossKilled = false;
        buildingSpawns = new ArrayList<BasicEnemy>();
        doggieSpawned = false;
        doggieDefeated = false;
        elanMuskeSpawned = false;
        elanMuskeDefeated = false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<BasicEnemy> getBuildingSpawns() {
        return buildingSpawns;
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity out of the file
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
        battleManager = new BattleManager(character);
    }

    public void setGoals(GoalManager goals) {
        this.goals = goals;
    }

    /**
     * Getter for log containing last battle summary
     * @return last battle summary
     */
    public Summary getLastSummary() {
        return lastSummary;
    }

    /**
     * Getter for log containing last battle log
     * @return last battle log
     */
    public BattleLog getLastLog() {
        return lastLog;
    }

    /**
     * Getter for elanMuskeDefeated boolean
     * @return whether elan muske has been defeated or not
     */
    public boolean isElanMuskeDefeated() {
        return elanMuskeDefeated;
    }

    public String goalString() {
        return goals.toString();
    }

    public List<Pair<Integer, Integer>> getPath(){
        return orderedPath;
    }
    
    /**
     * add a generic entity (without it's own dedicated method for adding to the world)
     * @param entity
     */
    public void addEntity(Entity entity) {
        // for adding non-specific entities (ones without another dedicated list)
        // TODO = if more specialised types being added from main menu, add more methods like this with specific input types...
        nonSpecifiedEntities.add(entity);
    }

    /**
     * spawns enemies if the conditions warrant it, adds to world
     * @return list of the enemies to be displayed on screen
     */
    public List<BasicEnemy> possiblySpawnEnemies(){
        // TODO = expand this very basic version
        Pair<Integer, Integer> pos = possiblyGetBasicEnemySpawnPosition();
        List<BasicEnemy> spawningEnemies = new ArrayList<>();
        if (pos != null){
            int indexInPath = orderedPath.indexOf(pos);
            Slug enemy = new Slug(new PathPosition(indexInPath, orderedPath));
            enemies.add(enemy);
            spawningEnemies.add(enemy);
        }
        return spawningEnemies;
    }
    /**
     * Spawns bosses if they satisfy spawn conditions
     */
    public void possiblySpawnBosses() {
        Pair<Integer, Integer> pos = getBossEnemySpawnPosition();

        // Spawning Doggie
        if (!doggieSpawned && loops == 20) {
            int indexInPath = orderedPath.indexOf(pos);
            PathPosition doggieP = new PathPosition(indexInPath, orderedPath);
            Doggie doggie = new Doggie(doggieP);
            enemies.add(doggie);
            buildingSpawns.add(doggie);

            doggieSpawned = true;
        }

        pos = getBossEnemySpawnPosition();

        // Spawning Elan Muske
        if (!elanMuskeSpawned && loops >= 40 && character.getXp() >= 10000) {
            int indexInPath = orderedPath.indexOf(pos);
            PathPosition elanP = new PathPosition(indexInPath, orderedPath);
            ElanMuske elan = new ElanMuske(elanP);
            enemies.add(elan);
            buildingSpawns.add(elan);

            elanMuskeSpawned = true;
        }
    }

    /**
     * kill an enemy
     * @param enemy enemy to be killed
     */
    private void killEnemy(BasicEnemy enemy){
        enemy.destroy();
        enemies.remove(enemy);
    }

    /**
     * spawn a card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public VampireCastleCard loadVampireCard(){
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()){
            removeCard(0);
            character.addGold(5);
            character.addXp(5);
        }
        VampireCastleCard vampireCastleCard = new VampireCastleCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(vampireCastleCard);
        return vampireCastleCard;
    }

    public ZombiePitCard loadZombieCard() {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()) {
            // oldest card
            removeCard(0);
            character.addGold(5);
            character.addXp(5);
        }
        ZombiePitCard zombiePitCard = new ZombiePitCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(zombiePitCard);
        return zombiePitCard;
    }


    public CampfireCard loadCampfireCard() {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()) {
            // oldest card
            removeCard(0);
            character.addGold(5);
            character.addXp(5);
        }
        CampfireCard campfireCard = new CampfireCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(campfireCard);
        return campfireCard;
    }

    public TowerCard loadTowerCard() {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()) {
            // oldest card
            removeCard(0);
            character.addGold(5);
            character.addXp(5);
        }
        TowerCard towerCard = new TowerCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(towerCard);
        return towerCard;
    }

    public TrapCard loadTrapCard(){
        if (cardEntities.size() >= getWidth()) {
            // oldest card
            removeCard(0);
            character.addGold(5);
            character.addXp(5);
        }
        TrapCard trapCard = new TrapCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(trapCard);
        return trapCard;

    }

    public VillageCard loadVillageCard() {
        if (cardEntities.size() >= getWidth()) {
            // oldest card
            removeCard(0);
            character.addGold(5);
            character.addXp(5);
        }
        VillageCard villageCard = new VillageCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(villageCard);
        return villageCard;

    }

    public BarracksCard loadBarracksCard() {
        if (cardEntities.size() >= getWidth()) {
            // oldest card
            removeCard(0);
            character.addGold(5);
            character.addXp(5);
        }
        BarracksCard barracksCard = new BarracksCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(barracksCard);
        return barracksCard;

    }




    /**
     * remove card at a particular index of cards (position in gridpane of unplayed cards)
     * @param index the index of the card, from 0 to length-1
     */
    private void removeCard(int index){
        Card c = cardEntities.get(index);
        int x = c.getX();
        c.destroy();
        cardEntities.remove(index);
        shiftCardsDownFromXCoordinate(x);
    }

    /**
     * spawn a sword in the world and return the sword entity
     * @return a sword to be spawned in the controller as a JavaFX node
     */
    public Sword addUnequippedSword(){
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        
        // now we insert the new sword, as we know we have at least made a slot available...
        Sword sword = new Sword(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new 
        SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        
        unequippedInventoryItems.add(sword);
        return sword;
    }

    public Stake addUnequippedStake() {
        // apart from swords
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        Stake stake = new Stake(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(stake);
        return stake;
    }

    public Staff addUnequippedStaff() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        Staff staff = new Staff(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(staff);
        return staff;
    }

    public basicChestArmor addUnequippedChestArmor() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        basicChestArmor chestArmor = new basicChestArmor(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(chestArmor);
        return chestArmor;
    }

    public basicHelmet addUnequippedHelmet() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        basicHelmet helmet = new basicHelmet(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(helmet);
        return helmet;
    }

    public HealthPotion addHealthPotion(){
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        HealthPotion healthPotion = new HealthPotion(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(healthPotion);
        return healthPotion; 
    }

    public basicShield addUnequippedShield(){
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        basicShield shield = new basicShield(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(shield);
        return shield; 
    }

    public DogeCoin addDogeCoin(){
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        DogeCoin dogeCoin = new DogeCoin(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(dogeCoin);
        return dogeCoin; 
    }

    public OneRing addOneRing(){
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        OneRing oneRing = new OneRing(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(oneRing);
        return oneRing; 
    }

    public TreeStump addTreeStump(){
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(treeStump);
        return treeStump; 
    }

    public FOTW addFOTW(){
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        FOTW fotw = new FOTW(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(fotw);
        return fotw; 
    }
    

    /**
     * run moves which occur with every tick without needing to spawn anything immediately
     */
    public void runTickMoves(){
        buildingSpawns = new ArrayList<BasicEnemy>();
        defeatedEnemies.clear();
        character.performMove();

        // Updates character's stats
        for (Building b: buildingEntities) {
            if (b.canInteract(character)) {
                b.interact(character);
            }
        }

        // checks if character has completed a loop
        Building heroCastle = buildingEntities.get(0);
        if (heroCastle.getX() == character.getX() && heroCastle.getY() == character.getY()) {
            newLoop();
            for (Building b: buildingEntities) {
                BasicEnemy newEnemy = b.newLoop(this);
                if (newEnemy != null) {
                    buildingSpawns.add(newEnemy);
                }
            }
        }

        moveBasicEnemies();
        possiblySpawnBosses();
        
        // Checks for trap damage
        for (Building b: buildingEntities) {
            for (BasicEnemy enemy: enemies) {
                if (b.canInteractMob(enemy)) {
                    b.interactMob(enemy);
                    b.destroy();
                    buildingEntities.remove(b);

                    if (enemy.getHp() <= 0) {
                        defeatedEnemies.add(enemy);
                        killEnemy(enemy);
                    }
                }
            }
        }

        // Fight enemies
        battleManager.update(this);
        List<BasicEnemy> killList = new ArrayList<BasicEnemy>();
        killList = battleManager.battle();
        defeatedEnemies = killList;
        for (BasicEnemy enemy : killList) {
            killEnemy(enemy);
        }
        /*if (goals != null) {
            if (goals.update()) {
                win();
            }
        }

        if (character.getHp() <= 0) {
            lose();
        }*/
    }

    public Boolean goals() {
        return goals.update();
    }



    /**
     * remove an item by x,y coordinates
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y){
        Item item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        removeUnequippedInventoryItem(item);
        /*if (item instanceof Sword || item instanceof Stake || item instanceof Staff || item instanceof FOTW) {
            character.setWeapon(item);
        } else if (item instanceof basicChestArmor) {
            character.setChestArmor(item);
        } else if (item instanceof basicHelmet) {
            character.setHelmet(item);
        } else if (item instanceof basicShield) {
            character.setShield(item);
        } */
        
    }
    
    /**
     * remove an item from the unequipped inventory
     * @param item item to be removed
     */
    private void removeUnequippedInventoryItem(Entity item){
        item.destroy();
        unequippedInventoryItems.remove(item);
    }

    /**
     * return an unequipped inventory item by x and y coordinates
     * assumes that no 2 unequipped inventory items share x and y coordinates
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    public Item getUnequippedInventoryItemEntityByCoordinates(int x, int y){
        for (Item e: unequippedInventoryItems){
            if ((e.getX() == x) && (e.getY() == y)){
                return e;
            }
        }
        return null;
    }

    /**
     * remove item at a particular index in the unequipped inventory items list (this is ordered based on age in the starter code)
     * @param index index from 0 to length-1
     */
    private void removeItemByPositionInUnequippedInventoryItems(int index){
        Entity item = unequippedInventoryItems.get(index);
        item.destroy();
        unequippedInventoryItems.remove(index);
    }

    /**
     * get the first pair of x,y coordinates which don't have any items in it in the unequipped inventory
     * @return x,y coordinate pair
     */
    private Pair<Integer, Integer> getFirstAvailableSlotForItem(){
        // first available slot for an item...
        // IMPORTANT - have to check by y then x, since trying to find first available slot defined by looking row by row
        for (int y=0; y<unequippedInventoryHeight; y++){
            for (int x=0; x<unequippedInventoryWidth; x++){
                if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null){
                    return new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return null;
    }

    /**
     * shift card coordinates down starting from x coordinate
     * @param x x coordinate which can range from 0 to width-1
     */
    private void shiftCardsDownFromXCoordinate(int x){
        for (Card c: cardEntities){
            if (c.getX() >= x){
                c.x().set(c.getX()-1);
            }
        }
    }

    /**
     * move all enemies
     */
    private void moveBasicEnemies() {
        for (BasicEnemy e: enemies){
            e.performMove();
        }
    }

    public int getDogeCoinValue() throws IOException{
        URL u = new URL("https://api.coindesk.com/v1/bpi/currentprice/BTC.json");
        try (InputStream in = u.openStream()) {
            String newString = new String(in.readAllBytes(), StandardCharsets.UTF_8);

            String[] arr = newString.split("\"");

            String regex = "(?<=[\\d])(,)(?=[\\d])";
            Pattern p = Pattern.compile(regex);
            String str = arr[29];
            Matcher m = p.matcher(str);
            str = m.replaceAll("");

            String substr = str.split("\\.")[0];

            int dogeValue = Integer.parseInt(substr);


            Random random = new Random();


            int number = random.nextInt(5000);

            if(elanMuskeDefeated) {
                return (dogeValue+number)/3;
            }

            return dogeValue+number;
        } 

    }

    public void setDogeCoinValue(){
        try {
            dogeValue = getDogeCoinValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * get a randomly generated position which could be used to spawn an enemy
     * @return null if random choice is that wont be spawning an enemy or it isn't possible, or random coordinate pair if should go ahead
     */
    private Pair<Integer, Integer> possiblyGetBasicEnemySpawnPosition(){
        // TODO = modify this
        
        // has a chance spawning a basic enemy on a tile the character isn't on or immediately before or after (currently space required = 2)...
        Random rand = new Random();
        int choice = rand.nextInt(2); // TODO = change based on spec... currently low value for dev purposes...
        // TODO = change based on spec
        if ((choice == 0) && (enemies.size() < 2)){
            List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
            int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
            // inclusive start and exclusive end of range of positions not allowed
            int startNotAllowed = (indexPosition - 2 + orderedPath.size())%orderedPath.size();
            int endNotAllowed = (indexPosition + 3)%orderedPath.size();
            // note terminating condition has to be != rather than < since wrap around...
            for (int i=endNotAllowed; i!=startNotAllowed; i=(i+1)%orderedPath.size()){
                orderedPathSpawnCandidates.add(orderedPath.get(i));
            }

            // choose random choice
            Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates.get(rand.nextInt(orderedPathSpawnCandidates.size()));

            return spawnPosition;
        }
        return null;
    }

    /**
     * Method that gets the enemy boss spawn position
     * @return Position boss can spawn in
     */
    private Pair<Integer, Integer> getBossEnemySpawnPosition() {
        List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
        int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));

        // inclusive start and exclusive end of range set to 5 spaces
        int startNotAllowed = (indexPosition - 5 + orderedPath.size())%orderedPath.size();
        int endNotAllowed = (indexPosition + 6)%orderedPath.size();

        // note terminating condition has to be != rather than < since wrap around...
        for (int i=endNotAllowed; i!=startNotAllowed; i=(i+1)%orderedPath.size()){
            orderedPathSpawnCandidates.add(orderedPath.get(i));
        }

        // choose random choice
        Random rand = new Random();
        Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates.get(rand.nextInt(orderedPathSpawnCandidates.size()));
        return spawnPosition;
    }

    /**
     * remove a card by its x, y coordinates
     * @param cardNodeX x index from 0 to width-1 of card to be removed
     * @param cardNodeY y index from 0 to height-1 of card to be removed
     * @param buildingNodeX x index from 0 to width-1 of building to be added
     * @param buildingNodeY y index from 0 to height-1 of building to be added
     */
    public Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        // start by getting card
        Card card = null;
        for (Card c: cardEntities){
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)){
                card = c;
                break;
            }
        }

        // Checks if card is placeable, if so spawns building
        if (card.isCardPlaceable(this, new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY))) {
            card.placeCard(this, new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));
        
            Building newBuilding = card.getBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));

            // destroy the card
            card.destroy();
            cardEntities.remove(card);
            shiftCardsDownFromXCoordinate(cardNodeX);

            return newBuilding;
        } else {
            return null;
        }
        
    }

    /**
     * takes the x and y coordinate and returns the path position of the closest path tile
     * @param x
     * @param y
     * @return PathPosition
     */
    public PathPosition findClosestPathTile(int x, int y) {
        double smallestDistance = 999;
        int indexInPath = 0;
        for (Pair<Integer, Integer> p : orderedPath) {
            double distance = Math.sqrt(Math.pow(p.getValue0() - x,2) + Math.pow(p.getValue1() - y,2));
            if (distance < smallestDistance) {
                indexInPath = orderedPath.indexOf(p);
                smallestDistance = distance;
            }
        }
        PathPosition closest = new PathPosition(indexInPath, orderedPath);
        return closest;
    }

    /**
     * adds an enemy to the world's enemy list
     * @param enemy
     */
    public void addEnemy(BasicEnemy enemy) {
        enemies.add(enemy);
    }

    /**
     * @returns the a list of enemies in the world
     */
    public List<BasicEnemy> getEnemies() {
        return enemies;
    }

    /**
     * @returns the character object
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Getter method for building entities
     * @return all building entities existing in world
     */
    public List<Building> getBuildingEntities() {
        return buildingEntities;
    }

    /**
     * Adds a building into the list of buildings in world
     * @param enemy
     */
    public void addBuilding(Building building) {
        buildingEntities.add(building);
    }
    
    /**
     * adds a card to the worlds card list
     * @param card
     */
    public void addCard(Card card) {
        cardEntities.add(card);
    }

    /**
     * @return loops completed by character
     */
    public int getLoops() {
        return loops;
    }

    /**
     * adds a loop to world loop counter
     */
    public void newLoop() {
        loops += 1;
    }

    /**
     * Getter for Battle Manager
     * @return battle manager of world
     */
    public BattleManager getBattleManager() {
        return battleManager;
    }

    /**
     * @returns the character object
     */
    public List<Ally> getAllies() {
        return allies;
    }

    /**
     * adds an ally to the world's ally list
     * @param ally the ally we are adding
     */
    public void addAlly(Ally ally) {
        allies.add(ally);
    }

    /**
     * Getter for defeated enemies list
     * @return list of all defeated enemies
     */
    public List<BasicEnemy> getDefeatedEnemies() {
        return defeatedEnemies;
    }

    /**
     * Clears defeated enemie
     */
    public void clearDefeatedEnemies() {
        defeatedEnemies.clear();
    }

    public Boolean getAllBossKilled() {
        return allBossKilled;
    }

    public void allBossKilled() {
        allBossKilled = true;
    }
    
    public void useHPotion() {
        getCharacter().useHealthPotion(unequippedInventoryItems);
    }
}
