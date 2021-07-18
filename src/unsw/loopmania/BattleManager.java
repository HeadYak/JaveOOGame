package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.Items.Weapons.Weapon;
import unsw.loopmania.enemies.BasicEnemy;

public class BattleManager {
    Character character;
    List<Ally> allies;
    List<BasicEnemy> battleEnemies;
    List<BasicEnemy> supportEnemies;

    /**
     * Constructor for the Battle Manager
     * @param character character that battle manager uses
     */
    public BattleManager(Character character) {
        this.character = character;
    }

    // TODO: not sure how to
    // public lowerCrit(Class class, double percentage) {

    // }

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
        
    }

    /**
     * Function that runs battles
     * @return
     */
    // TODO:
    public List<BasicEnemy> battle() {
        int totalEnemyHp = 0;

        // Add each battle enemy's hp to totalEnemyHp
        for (BasicEnemy enemy : battleEnemies) {
            totalEnemyHp += enemy.getHp();
        }

        while (totalEnemyHp > 0) {
            runTickBattle();
        }

        List<BasicEnemy> allEnemiesAttacked = new ArrayList<BasicEnemy>();
        allEnemiesAttacked.addAll(battleEnemies);
        allEnemiesAttacked.addAll(supportEnemies);
        return allEnemiesAttacked;
    }
    
    /**
     * Function that runs a single turn of a battle
     */
    public void runTickBattle() {
        Weapon weapon = character.getWeapon();
        int enemyDmg = 0;
        int characterDmg = 0;

        characterDmg = (character.getDmg() + weapon.getDamageValue()) * 4;
        
        // Getting total enemy dmg
        for (BasicEnemy enemy : battleEnemies) {
            enemyDmg += enemy.getDmg() * 4;
        }
        for (BasicEnemy enemy : supportEnemies) {
            enemyDmg += enemy.getDmg() * 2;
        }

        // Applying damage to character
        character.setHp(character.getHp() - enemyDmg);

        // Applying damage to enemy
        for (BasicEnemy enemy : battleEnemies) {
            if (enemy.getHp() > 0) {
                enemy.setHp(enemy.getHp() - characterDmg);
                break;
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
