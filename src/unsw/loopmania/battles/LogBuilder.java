package unsw.loopmania.battles;

import java.util.HashMap;
import java.util.List;

import unsw.loopmania.Items.Armor.Armor;
import unsw.loopmania.Items.Weapons.Weapon;
import unsw.loopmania.enemies.BasicEnemy;

public interface LogBuilder {

    // Required for setup of battle
    public void setStartingHp(int startingHp);
    public void setInitialEnemies(List<BasicEnemy> initialEnemies);
    public void setSupportingEnemies(List<BasicEnemy> supportingEnemies);
    public void setInCampfireRange(boolean inCampfireRange);
    public void setInTowerRange(boolean inTowerRange);
    public void setWeapon(Weapon weapon);
    public void setArmor(Armor armor);

    // Required for actual battle
    public void setAttackExchange(int playerDmg, int enemyDmg, int targetHp);

    // Required for result
    public void setFinalHp(int finalHp);
    public void setDefeated(List<BasicEnemy> defeated);
    public void setRewards(List<String> rewards);
}
