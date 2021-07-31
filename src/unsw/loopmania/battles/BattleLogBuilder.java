package unsw.loopmania.battles;

import java.util.List;

import org.javatuples.Triplet;

import unsw.loopmania.Items.Armor.Armor;
import unsw.loopmania.Items.Weapons.Weapon;
import unsw.loopmania.enemies.BasicEnemy;

public class BattleLogBuilder implements LogBuilder {
    private int startingHp;
    private List<BasicEnemy> initialEnemies;
    private List<BasicEnemy> supportingEnemies;
    private boolean inCampfireRange;
    private boolean inTowerRange;
    private Weapon weapon;
    private Armor armor;
    private List<Triplet<Integer, Integer, Integer>> battleTriplets;
    private int finalHp;
    private List<BasicEnemy> defeated;
    private List<String> rewards;

    @Override
    public void setStartingHp(int startingHp) {
        this.startingHp = startingHp;
    }

    @Override
    public void setInitialEnemies(List<BasicEnemy> initialEnemies) {
        this.initialEnemies = initialEnemies;
    }

    @Override
    public void setSupportingEnemies(List<BasicEnemy> supportingEnemies) {
        this.supportingEnemies = supportingEnemies;
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
    public void setArmor(Armor armor) {
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
            inCampfireRange, inTowerRange, weapon, armor, battleTriplets,
            finalHp, defeated, rewards);
    }
}
