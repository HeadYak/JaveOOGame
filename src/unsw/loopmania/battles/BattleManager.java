package unsw.loopmania.battles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Ally;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.TrancedAlly;
import unsw.loopmania.Buildings.Barracks;
import unsw.loopmania.Buildings.Campfire;
import unsw.loopmania.Buildings.Tower;
import unsw.loopmania.Buildings.Trap;
import unsw.loopmania.Buildings.VampireCastleBuilding;
import unsw.loopmania.Buildings.Village;
import unsw.loopmania.Buildings.ZombiePit;
import unsw.loopmania.Items.HealthPotion;
import unsw.loopmania.Items.Item;
import unsw.loopmania.Items.Armor.Helmet;
import unsw.loopmania.Items.Armor.TreeStump;
import unsw.loopmania.Items.Armor.basicChestArmor;
import unsw.loopmania.Items.Armor.basicHelmet;
import unsw.loopmania.Items.Weapons.FOTW;
import unsw.loopmania.Items.Weapons.Staff;
import unsw.loopmania.Items.Weapons.Stake;
import unsw.loopmania.Items.Weapons.Sword;
import unsw.loopmania.Items.Weapons.Weapon;
import unsw.loopmania.Items.Ring.OneRing;
import unsw.loopmania.enemies.BasicEnemy;

public class BattleManager {
    private Character character;
    private int critMode;
    private List<Ally> allies;
    private List<BasicEnemy> battleEnemies;
    private List<BasicEnemy> supportEnemies;
    private List<BasicEnemy> defeated;
    private BattleLogBuilder battleLogBuilder;
    private SummaryBuilder summaryBuilder;
    private int xpSnapshot;
    private int goldSnapshot;

    /**
     * Constructor for the Battle Manager
     * @param character character that battle manager uses
     */
    public BattleManager(Character character) {
        this.character = character;
        allies = new ArrayList<Ally>();
        battleEnemies = new ArrayList<BasicEnemy>();
        supportEnemies = new ArrayList<BasicEnemy>();
        defeated = new ArrayList<BasicEnemy>();
        battleLogBuilder = new BattleLogBuilder();
        summaryBuilder = new SummaryBuilder();
        critMode = 0;
        xpSnapshot = character.getXp();
        goldSnapshot = character.getGold();
    }

    /**
     * Function that updates what enemies we are battling
     * @param world
     */
    public void update(LoopManiaWorld world) {
        // Loop through enemies in world and add them to lists
        for (BasicEnemy enemy : world.getEnemies()) {
            int br = enemy.getBattleRadius();
            int sr = enemy.getSupportRadius();
            PathPosition enemyP = enemy.getPosition();

            if (enemyP.distanceToCharacter(character) <= br) {
                battleEnemies.add(enemy);
            } else if (enemyP.distanceToCharacter(character) <= sr) {
                supportEnemies.add(enemy);
            }
        }

        allies = character.getAllyList();
    }

    /**
     * Function that runs battles
     * @return
     */
    public void battle() {
        battleLogBuilder = new BattleLogBuilder();
        summaryBuilder = new SummaryBuilder();

        buildSetup(battleLogBuilder, summaryBuilder);

        while (battleEnemies.size() > 0 && character.getHp() > 0) {
            runTickBattle();
        }

        // Go through tranced allies, remove them and add them to defeated
        Iterator<Ally> allyIter = allies.iterator();
        while (allyIter.hasNext()) {
            Ally ally = allyIter.next();

            if (ally instanceof TrancedAlly) {
                TrancedAlly clone = (TrancedAlly) ally;
                defeated.add(clone.getOriginalBody());
                allyIter.remove();
            }
        }

        buildResult(battleLogBuilder, summaryBuilder);
        defeated.clear();
    }
    
    /**
     * Function that runs a single turn of a battle
     * @return the total enemy hp after battle has finished
     */
    public void runTickBattle() {
        Weapon weapon = character.getWeapon();
        BasicEnemy target = battleEnemies.get(0);
        int totalPlayerDmg = 0;
        int totalEnemyDmg = 0;
        int targetHpSnapshot = target.getHp();
        int characterHpSnapshot = character.getHp();

        // If character is stunned, skip turn and unstun
        if (character.isStunned()) {
            character.setStunned(false);

        // Deal character's base dmg/weapon dmg
        } else if (weapon != null && randomRoll() < weapon.getCritChance() * 100) {
            BasicEnemy trancedEnemy = character.critAttack(battleEnemies);

            // Add tranced ally if it exists due to staff crit
            if (trancedEnemy != null) {
                TrancedAlly trancedAlly =
                        new TrancedAlly(character.getPosition(), trancedEnemy);
                allies.add(trancedAlly);
            } else {
                totalPlayerDmg += (targetHpSnapshot - target.getHp());
            }
        
        } else {
            character.attack(target);
            totalPlayerDmg += (targetHpSnapshot - target.getHp());
        }

        // Deal ally damage (update target in case of trance)
        allies = character.getAllyList();
        if (battleEnemies.size() > 0) {
            target = battleEnemies.get(0);
            targetHpSnapshot = target.getHp();
        } else {
            target = null;
            targetHpSnapshot = 0;
        }
        
        if (target != null) {
            for (Ally ally : allies) {
                ally.attack(target);
            }

            totalPlayerDmg += (targetHpSnapshot - target.getHp());
            targetHpSnapshot = target.getHp();
        }

        // Iterate through allies in search of tranced allies
        Iterator<Ally> trancedIter = allies.iterator();
        while (trancedIter.hasNext()) {
            Ally ally = trancedIter.next();

            // Find tranced allies and check if their trance duration has run
            // out
            if (ally instanceof TrancedAlly) {
                TrancedAlly clone = (TrancedAlly) ally;
                
                if (clone.getTranceDuration() == 0) {
                    trancedIter.remove();
                    battleEnemies.add(clone.getOriginalBody());
                }
            }
        }

        // Defeat enemy if hp is less than 0
        if (target != null && target.getHp() <= 0) {
            defeated.add(battleEnemies.remove(0));
        }

        // Deal each enemy's damage
        for (int i = 0; i < battleEnemies.size(); i++) {
            BasicEnemy enemy = battleEnemies.get(i);

            if (randomRoll() < enemy.getCritChance() * 100) {
                enemy.critAttack(character, battleEnemies);
            } else {
                enemy.attack(character);
            }
        }

        // Deal support enemy's damage
        for (BasicEnemy enemy : supportEnemies) {
            enemy.supportAttack(character);
        }

        totalEnemyDmg += characterHpSnapshot - character.getHp();

        if (character.getHp() <= 0 && character.hasOneRing()) {
            character.setHp(character.getMaxHp());

            for (Item item : character.getInventory()) {
                if (item instanceof OneRing) {
                    character.getInventory().remove(item);
                    break;
                }
            }
        }

        // Deal tower damage if in range (-100 to all)
        if (character.getIsSupported()) {
            for (BasicEnemy enemy : battleEnemies) {
                enemy.setHp(enemy.getHp() - 100);
                totalPlayerDmg += 100;
            }
            for (BasicEnemy enemy : supportEnemies) {
                enemy.setHp(enemy.getHp() - 100);
                totalPlayerDmg += 100;
            }
        }

        // Loop through enemies and remove all defeated enemies due to tower
        Iterator<BasicEnemy> battleIter = battleEnemies.iterator();
        Iterator<BasicEnemy> supportIter = supportEnemies.iterator();

        while (battleIter.hasNext()) {
            BasicEnemy enemy = battleIter.next();

            if (enemy.getHp() <= 0) {
                battleIter.remove();
                defeated.add(enemy);
            }
        }

        while (supportIter.hasNext()) {
            BasicEnemy enemy = supportIter.next();

            if (enemy.getHp() <= 0) {
                supportIter.remove();
                defeated.add(enemy);
            }
        }

        // Final targetHpSnapshot
        if (battleEnemies.size() > 0) {
            targetHpSnapshot = battleEnemies.get(0).getHp();
        } else {
            targetHpSnapshot = 0;
        }

        buildBodySegment(battleLogBuilder, summaryBuilder, totalPlayerDmg,
                totalEnemyDmg, targetHpSnapshot);
    }

    public List<StaticEntity> generateRewards(int totalWeight) {
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();


        List<StaticEntity> lowWeightRewards = new ArrayList<>();
        lowWeightRewards.add(new Trap(x, y));
        lowWeightRewards.add(new ZombiePit(x, y));
        lowWeightRewards.add(new Staff(x, y));
        lowWeightRewards.add(new HealthPotion(x, y));

        List<StaticEntity> midWeightRewards = new ArrayList<>();
        midWeightRewards.add(new VampireCastleBuilding(x, y));
        midWeightRewards.add(new Barracks(x, y));
        midWeightRewards.add(new basicHelmet(x, y));
        midWeightRewards.add(new Village(x, y));
        midWeightRewards.add(new Sword(x, y));

        List<StaticEntity> highWeightRewards = new ArrayList<>();
        highWeightRewards.add(new Campfire(x, y));
        highWeightRewards.add(new Tower(x, y));
        highWeightRewards.add(new basicChestArmor(x, y));
        highWeightRewards.add(new Stake(x, y));

        List<StaticEntity> legendaryRewards = new ArrayList<>();
        legendaryRewards.add(new FOTW(x, y));
        legendaryRewards.add(new OneRing(x, y));
        legendaryRewards.add(new TreeStump(x, y));

        List<StaticEntity> rewards = new ArrayList<>();

        // Generate set amount of xp and gold
        int xpGained = totalWeight * 10;
        int gold = totalWeight;
        character.addXp(xpGained);
        character.addGold(gold);

        // Generating random rewards
        Random random = new Random();
        int i = 0;

        while (totalWeight != 0 && i < 3) {
            int bound = Math.min(totalWeight, 3);
            
            // Generating random number between 1-3
            int rewardRarity = random.nextInt(bound) + 1;

            if (totalWeight >= 10) {
                int index = random.nextInt(legendaryRewards.size());
                rewards.add(legendaryRewards.get(index));
                break;
            }

            // Generating low-weight reward
            if (rewardRarity == 1) {
                int index = random.nextInt(lowWeightRewards.size());
                rewards.add(lowWeightRewards.get(index));

            // Generating mid-weight reward
            } else if (rewardRarity == 2) {
                int index = random.nextInt(midWeightRewards.size());
                rewards.add(midWeightRewards.get(index));

            // Generating high-weight reward
            } else {
                int index = random.nextInt(highWeightRewards.size());
                rewards.add(highWeightRewards.get(index));
            }

            totalWeight -= bound;
            i++;
        }
        
        // Generating chance of extra health potion
        int roll = random.nextInt(100);
        if (roll < 50) {
            rewards.add(new HealthPotion(x, y));
        }

        return rewards;
    }

    public List<Ally> getAllies() {
        return allies;
    }

    public List<BasicEnemy> getBattleEnemies() {
        return battleEnemies;
    }

    public List<BasicEnemy> getSupportEnemies() {
        return supportEnemies;
    }

    /**
     * Function that sets crit mode for testing purposes
     * @param critMode 0: default behaviour
     * @param critMode 1: crits disabled
     * @param critMode 2: always crits
     */
    public void setCritMode(int critMode) {
        this.critMode = critMode;
    }
    
    /**
     * Does a random roll between 0-99 inclusive
     * @return the roll value
     */
    public int randomRoll() {
        Random random = new Random();

        // Default behaviour during game
        if (critMode == 0) {
            return random.nextInt(100);

        // If crits have been disabled for testing
        } else if (critMode == 1) {
            return 100;
        
        // If crits have been set to certain for testing
        } else {
            return -1;
        }
    }

    /**
     * Method that uses builders to create a setup log for frontend
     * @param battleLogBuilder builder used to build a battle log
     * @param summaryBuilder builder used to build a summary log
     */
    public void buildSetup(BattleLogBuilder battleLogBuilder,
            SummaryBuilder summaryBuilder) {
            
        // Adding initial setup as stated in LogBuilder for BattleLog
        battleLogBuilder.setStartingHp(character.getHp());
        battleLogBuilder.setInitialEnemies(battleEnemies);
        battleLogBuilder.setSupportingEnemies(supportEnemies);
        battleLogBuilder.setInCampfireRange(character.getBuffStatus());
        battleLogBuilder.setInTowerRange(character.getIsSupported());
        battleLogBuilder.setWeapon(character.getWeapon());
        battleLogBuilder.setHelmet(character.getHelmet());
        battleLogBuilder.setShield(character.getShield());
        battleLogBuilder.setArmor(character.equippedChestArmor());

        // Adding initial setup as stated in LogBuilder for Summary
        summaryBuilder.setStartingHp(character.getHp());
        summaryBuilder.setInitialEnemies(battleEnemies);
        summaryBuilder.setSupportingEnemies(supportEnemies);
        summaryBuilder.setInCampfireRange(character.getBuffStatus());
        summaryBuilder.setInTowerRange(character.getIsSupported());
        summaryBuilder.setWeapon(character.getWeapon());
        summaryBuilder.setHelmet(character.getHelmet());
        summaryBuilder.setShield(character.getShield());
        summaryBuilder.setArmor(character.equippedChestArmor());
    }

    /**
     * Method that uses builders to add a single exchange of damage to a log
     * for frontend
     * @param battleLogBuilder builder used to build a battle log
     * @param summaryBuilder builder used to build a summary log
     * @param playerDmg total damage player's party did
     * @param enemyDmg total damage enemy did
     * @param targetHp hp of current target
     */
    public void buildBodySegment(BattleLogBuilder battleLogBuilder,
            SummaryBuilder summaryBuilder, int playerDmg,
            int enemyDmg, int targetHp) {
        
        // Adding a body segment for BattleLog
        battleLogBuilder.setAttackExchange(playerDmg, enemyDmg, targetHp);

        // Adding a body segment for Summary
        summaryBuilder.setAttackExchange(playerDmg, enemyDmg, targetHp);
    }

    /**
     * Method that uses builder to create the result info to a log
     * @param battleLogBuilder builder used to build a battle log
     * @param summaryBuilder builder used to build a summary log
     */
    public void buildResult(BattleLogBuilder battleLogBuilder,
            SummaryBuilder summaryBuilder) {

        // Generate weight
        int totalWeight = 0;
        for (BasicEnemy enemy : defeated) {
            totalWeight += enemy.getWeight();
        }

        List<StaticEntity> rewards = generateRewards(totalWeight);

        // get xp and gold gained
        int xpGained = character.getXp() - xpSnapshot;
        int goldGained = character.getGold() - goldSnapshot;

        // Adding result as stated in LogBuilder for BattleLog
        battleLogBuilder.setFinalHp(character.getHp());
        battleLogBuilder.setDefeated(defeated);
        battleLogBuilder.setRewards(rewards, xpGained, goldGained);

        // Adding result as stated in LogBuilder for Summary
        summaryBuilder.setFinalHp(character.getHp());
        summaryBuilder.setDefeated(defeated);
        summaryBuilder.setRewards(rewards, xpGained, goldGained);
    }

    /**
     * Getter for final battle log (called at end of battle in world)
     * @return the battle log
     */
    public BattleLog getFinalBattleLog() {
        return battleLogBuilder.getResult();
    }

    /**
     * Getter for final summary log (called at end of battle in world)
     * @return the summary log
     */
    public Summary getFinalSummaryLog() {
        return summaryBuilder.getResult();
    }
}
