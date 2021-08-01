package unsw.loopmania.battles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Ennead;
import org.javatuples.Triplet;

import unsw.loopmania.Items.Armor.ChestArmor;
import unsw.loopmania.Items.Armor.Helmet;
import unsw.loopmania.Items.Armor.Shield;
import unsw.loopmania.Items.Weapons.Weapon;
import unsw.loopmania.enemies.BasicEnemy;

public class BattleLog {
    private int startingHp;
    private List<BasicEnemy> initialEnemies;
    private List<BasicEnemy> supportingEnemies;
    private boolean inCampfireRange;
    private boolean inTowerRange;
    private Weapon weapon;
    private Helmet helmet;
    private Shield shield;
    private ChestArmor armor;
    private List<Triplet<Integer, Integer, Integer>> battleTriplets;
    private int finalHp;
    private List<BasicEnemy> defeated;
    private List<String> rewards;

    public BattleLog(int startingHp, List<BasicEnemy> initialEnemies,
            List<BasicEnemy> supportingEnemies, boolean inCampfireRange,
            boolean inTowerRange, Weapon weapon, Helmet helmet, Shield shield,
            ChestArmor armor,
            List<Triplet<Integer, Integer, Integer>> battleTriplets,
            int finalHp, List<BasicEnemy> defeated, List<String> rewards) {
        this.startingHp = startingHp;
        this.initialEnemies = initialEnemies;
        this.supportingEnemies = supportingEnemies;
        this.inCampfireRange = inCampfireRange;
        this.inTowerRange = inTowerRange;
        this.weapon = weapon;
        this.armor = armor;
        this.battleTriplets = battleTriplets;
        this.finalHp = finalHp;
        this.defeated = defeated;
        this.rewards = rewards;
    }

    public void generateRewards(int totalWeight) {
        List<String> lowWeightRewards = new ArrayList<>();
        lowWeightRewards.add("Staff");
        lowWeightRewards.add("Trap");
        lowWeightRewards.add("ZombiePit");
        lowWeightRewards.add("HealthPotion|2");

        List<String> midWeightRewards = new ArrayList<>();
        midWeightRewards.add("VampireCastleBuilding");
        midWeightRewards.add("Barracks");
        midWeightRewards.add("Helmet");
        midWeightRewards.add("Village");
        midWeightRewards.add("Sword");
        midWeightRewards.add("HealthPotion|5");

        List<String> highWeightRewards = new ArrayList<>();
        highWeightRewards.add("Campfire");
        highWeightRewards.add("Tower");
        highWeightRewards.add("Armour");
        highWeightRewards.add("Stake");
        highWeightRewards.add("HealthPotion|10");

        // Generating random rewards
        Random random = new Random();
        int i = 0;

        while (totalWeight != 0 || i < 3) {
            int bound = Math.min(totalWeight, 3);
            
            // Generating random number between 1-3
            int rewardRarity = random.nextInt(bound) + 1;

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
        
        // Generating chance of extra health potions
        for (i = 0; i < totalWeight; i++) {
            int roll = random.nextInt(100);

            if (roll < 30) {
                rewards.add("HealthPotion|1");
            }
        }

        // Generate set amount of xp and gold
        int xpGained = totalWeight * 10;
        int gold = totalWeight;
        rewards.add("xp|" + xpGained);
        rewards.add("gold|" + gold);
    }

    public int getStartingHp() {
        return startingHp;
    }

    public List<BasicEnemy> getInitialEnemies() {
        return initialEnemies;
    }

    public List<BasicEnemy> getSupportingEnemies() {
        return supportingEnemies;
    }

    public boolean isInCampfireRange() {
        return inCampfireRange;
    }

    public boolean isInTowerRange() {
        return inTowerRange;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Helmet getHelmet() {
        return helmet;
    }

    public Shield getShield() {
        return shield;
    }

    public ChestArmor getArmor() {
        return armor;
    }

    public List<Triplet<Integer, Integer, Integer>> getBattleTriplets() {
        return battleTriplets;
    }

    public int getFinalHp() {
        return finalHp;
    }

    public List<BasicEnemy> getDefeated() {
        return defeated;
    }

    public List<String> getRewards() {
        return rewards;
    }

    /**
     * Gets all necessary values for setup screen
     * @return a septet containing these values
     */
    public Ennead<Integer, List<BasicEnemy>, List<BasicEnemy>, Boolean,
            Boolean, Weapon, Helmet, Shield, ChestArmor> displaySetup() {

        // Creating septet
        Ennead<Integer, List<BasicEnemy>, List<BasicEnemy>, Boolean,
                Boolean, Weapon, Helmet, Shield, ChestArmor> initialVals =
        
        new Ennead<>(startingHp, initialEnemies, supportingEnemies,
                inCampfireRange, inTowerRange, weapon, helmet, shield, armor);

        return initialVals;
    }

    /**
     * Gets all necessary values for result screen
     * @return a triplet containing these values
     */
    public Triplet<Integer, List<BasicEnemy>, List<String>> displayResult() {

        // Creating triplet
        Triplet<Integer, List<BasicEnemy>, List<String>> finalVals =
                new Triplet<>(finalHp, defeated, rewards);

        return finalVals;
    }
}
