package unsw.loopmania.battles;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Triplet;

import unsw.loopmania.Items.Armor.ChestArmor;
import unsw.loopmania.Items.Armor.Helmet;
import unsw.loopmania.Items.Armor.Shield;
import unsw.loopmania.Items.Weapons.Weapon;
import unsw.loopmania.enemies.BasicEnemy;

public class BattleLogBuilder implements LogBuilder {
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

    public BattleLogBuilder() {
        initialEnemies = new ArrayList<>();
        supportingEnemies = new ArrayList<>();
        battleTriplets = new ArrayList<>();
    }

    @Override
    public void setStartingHp(int startingHp) {
        this.startingHp = startingHp;
    }

    @Override
    public void setInitialEnemies(List<BasicEnemy> initialEnemies) {
        List<BasicEnemy> clone = new ArrayList<>(initialEnemies);
        this.initialEnemies = clone;
    }

    @Override
    public void setSupportingEnemies(List<BasicEnemy> supportingEnemies) {
        List<BasicEnemy> clone = new ArrayList<>(supportingEnemies);
        this.supportingEnemies = clone;
    }

    @Override
    public void setInCampfireRange(boolean inCampfireRange) {
        this.inCampfireRange = inCampfireRange;
    }

    @Override
    public void setInTowerRange(boolean inTowerRange) {
        this.inTowerRange = inTowerRange;
    }

    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void setHelmet(Helmet helmet) {
        this.helmet = helmet;
    }

    @Override
    public void setShield(Shield shield) {
        this.shield = shield;
    }

    @Override
    public void setArmor(ChestArmor armor) {
        this.armor = armor;
    }

    @Override
    public void setAttackExchange(int playerDmg, int enemyDmg, int targetHp) {
        Triplet<Integer, Integer, Integer> attackInfo =
                new Triplet<>(playerDmg, enemyDmg, targetHp);
        battleTriplets.add(attackInfo);
    }

    @Override
    public void setFinalHp(int finalHp) {
        this.finalHp = finalHp;
    }

    @Override
    public void setDefeated(List<BasicEnemy> defeated) {
        this.defeated = defeated;
    }

    @Override
    public void setRewards(List<String> rewards) {
        this.rewards = rewards;
    }

    public BattleLog getResult() {
        return new BattleLog(startingHp, initialEnemies, supportingEnemies,
            inCampfireRange, inTowerRange, weapon, helmet, shield, armor,
            battleTriplets, finalHp, defeated, rewards);
    }
}
