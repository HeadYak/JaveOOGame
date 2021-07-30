package unsw.loopmania;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import unsw.loopmania.Items.Weapons.Weapon;
import unsw.loopmania.Items.Weapons.Stake;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.enemies.Vampire;

public class BattleManager {
    private Character character;
    private int critMode;
    private List<Ally> allies;
    private List<BasicEnemy> battleEnemies;
    private List<BasicEnemy> supportEnemies;
    private List<BasicEnemy> defeated;

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
        critMode = 0;
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
    public List<BasicEnemy> battle() {

        while (battleEnemies.size() > 0 && character.getHp() > 0) {
            runTickBattle();
        }
        
        return defeated;
    }
    
    /**
     * Function that runs a single turn of a battle
     * @return the total enemy hp after battle has finished
     */
    public void runTickBattle() {
        Weapon weapon = character.getWeapon();
        BasicEnemy target = battleEnemies.get(0);
        boolean weaponEquipped = false;
        // int partyDmg for battle log if wanted

        // Deal character's base dmg + weapon dmg
        if (randomRoll() < weapon.getCritChance() * 100) {
            character.critAttack(target);
        } else {
            character.attack(target);
        }

        // Deal ally damage (update in case of trance)
        allies = character.getAllyList();
        target = battleEnemies.get(0);
        for (Ally ally : allies) {
            ally.attack(target);
        }

        // Defeat enemy if hp is less than 0
        if (target.getHp() <= 0) {
            defeated.add(battleEnemies.remove(0));
        }

        // Deal each enemy's damage
        for (BasicEnemy enemy : battleEnemies) {
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

        // Deal tower damage if in range (-100 to all)
        if (character.getIsSupported()) {
            for (BasicEnemy enemy : battleEnemies) {
                enemy.setHp(enemy.getHp() - 100);
            }
            for (BasicEnemy enemy : supportEnemies) {
                enemy.setHp(enemy.getHp() - 100);
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

    // Likely do not need
    // public boolean isCritsEnabled() {
    //     return critsEnabled;
    // }

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
}
