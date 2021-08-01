package unsw.loopmania.battles;

import java.util.List;

import org.javatuples.Ennead;
import org.javatuples.Quintet;
import org.javatuples.Triplet;

import unsw.loopmania.StaticEntity;
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
    private List<StaticEntity> rewards;
    private int earnedXp;
    private int earnedGold;

    public BattleLog(int startingHp, List<BasicEnemy> initialEnemies,
            List<BasicEnemy> supportingEnemies, boolean inCampfireRange,
            boolean inTowerRange, Weapon weapon, Helmet helmet, Shield shield,
            ChestArmor armor,
            List<Triplet<Integer, Integer, Integer>> battleTriplets,
            int finalHp, List<BasicEnemy> defeated,
            List<StaticEntity> rewards, int earnedXp,
            int earnedGold) {
        this.startingHp = startingHp;
        this.initialEnemies = initialEnemies;
        this.supportingEnemies = supportingEnemies;
        this.inCampfireRange = inCampfireRange;
        this.inTowerRange = inTowerRange;
        this.weapon = weapon;
        this.helmet = helmet;
        this.shield = shield;
        this.armor = armor;
        this.battleTriplets = battleTriplets;
        this.finalHp = finalHp;
        this.defeated = defeated;
        this.rewards = rewards;
        this.earnedXp = earnedXp;
        this.earnedGold = earnedGold;
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

    public List<StaticEntity> getRewards() {
        return rewards;
    }

    public int getEarnedXp() {
        return earnedXp;
    }

    public int getEarnedGold() {
        return earnedGold;
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
    public Quintet<Integer, List<BasicEnemy>, List<StaticEntity>, Integer,
            Integer> displayResult() {

        // Creating triplet
        Quintet<Integer, List<BasicEnemy>, List<StaticEntity>, Integer, Integer>
                finalVals = new Quintet<>(finalHp, defeated, rewards, earnedXp,
                earnedGold);

        return finalVals;
    }
}
