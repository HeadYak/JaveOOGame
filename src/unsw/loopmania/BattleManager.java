package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.Items.Weapons.Weapon;
import unsw.loopmania.enemies.BasicEnemy;

public class BattleManager {
    Character character;
    int totalEnemyHp;
    int alliesHp;
    List<Ally> allies;
    List<BasicEnemy> battleEnemies;
    List<BasicEnemy> supportEnemies;

    /**
     * Constructor for the Battle Manager
     * @param character character that battle manager uses
     */
    public BattleManager(Character character) {
        this.character = character;
        allies = new ArrayList<Ally>();
        battleEnemies = new ArrayList<BasicEnemy>();
        supportEnemies = new ArrayList<BasicEnemy>();
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
                totalEnemyHp += enemy.getHp();
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
    public List<BasicEnemy> battle() {

        while (totalEnemyHp > 0 && character.getHp() > 0) {
            runTickBattle();
        }

        List<BasicEnemy> allEnemiesAttacked = new ArrayList<BasicEnemy>();
        allEnemiesAttacked.addAll(battleEnemies);
        allEnemiesAttacked.addAll(supportEnemies);
        return allEnemiesAttacked;


    }
    
    /**
     * Function that runs a single turn of a battle
     * @return the total enemy hp after battle has finished
     */
    public void runTickBattle() {
        Weapon weapon = character.getWeapon();
        int enemyDmg = 0;
        int characterDmg = 0;
        int weaponDmg = 0;

        // No weapon
        if (weapon != null) {
            weaponDmg = weapon.getDamageValue();
        }

        characterDmg = (character.getDmg() + weaponDmg) * 4;

        // Character is buffed
        if (character.getBuffStatus()) {
            characterDmg *= 2;
        }

        // Checking for allies and adding damage
        for (Ally ally : allies) {
            if (ally.getHp() > 0) {
                characterDmg += ally.getDmg() * 4;
            }
        }

        // Getting total enemy dmg
        for (BasicEnemy enemy : battleEnemies) {
            if (enemy.getHp() > 0) {
                enemyDmg += enemy.getDmg() * 4;
            }
        }
        for (BasicEnemy enemy : supportEnemies) {
            if (enemy.getHp() > 0) {
                enemyDmg += enemy.getDmg() * 2;
            }
        }

        // Applying damage to character and first ally
        character.setHp(character.getHp() - enemyDmg);

        for (Ally ally : allies) {
            if (ally.getHp() > 0) {
                ally.setHp(ally.getHp() - enemyDmg);
                break;
            }
        }

        // Applying damage to first enemy
        for (BasicEnemy enemy : battleEnemies) {
            if (enemy.getHp() > 0) {
                enemy.setHp(enemy.getHp() - characterDmg);

                // Subtract from totalEnemyHp
                if (enemy.getHp() < 0) {
                    totalEnemyHp -= characterDmg + enemy.getHp();
                } else {
                    totalEnemyHp -= characterDmg;
                }
                break;
            }
        }

        // Doing tower damage if it is in the battle -100 TO ALL
        if (character.getIsSupported()) {
            for (BasicEnemy enemy : battleEnemies) {
                if (enemy.getHp() > 0) {
                    enemy.setHp(enemy.getHp() - 100);

                    // Subtract from totalEnemyHp
                    if (enemy.getHp() < 0) {
                        totalEnemyHp -= 100 + enemy.getHp();
                    } else {
                        totalEnemyHp -= characterDmg;
                    }
                }
            }
        }
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
    
}
