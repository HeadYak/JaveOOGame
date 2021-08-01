package unsw.loopmania.battles;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.Items.Armor.ChestArmor;
import unsw.loopmania.Items.Armor.Helmet;
import unsw.loopmania.Items.Armor.Shield;
import unsw.loopmania.Items.Weapons.Weapon;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.enemies.Doggie;
import unsw.loopmania.enemies.ElanMuske;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.enemies.Zombie;

public class SummaryBuilder implements LogBuilder {
    private List<String> introduction;
    private List<String> body;
    private List<String> conclusion;
    private int hpTracker;

    public SummaryBuilder() {
        introduction = new ArrayList<>();
        body = new ArrayList<>();
        conclusion = new ArrayList<>();
    }

    @Override
    public void setStartingHp(int startingHp) {
        introduction.add("Battle started at " + startingHp + " hp!");
        hpTracker = startingHp;
    }

    @Override
    public void setInitialEnemies(List<BasicEnemy> initialEnemies) {
        String fighting = new String("Battling:");

        // TODO: Replace with better classification and add bosses
        int i = 0;
        for (BasicEnemy enemy : initialEnemies) {
            if (i != 0) { fighting += ","; }

            if (enemy instanceof Slug) {
                fighting += " slug";
            } else if (enemy instanceof Zombie) {
                fighting += " zombie";
            } else if (enemy instanceof Vampire) {
                fighting += " vampire";
            } else if (enemy instanceof Doggie) {
                fighting += " DOGGIE";
            } else if (enemy instanceof ElanMuske) {
                fighting += " ELAN MUSKE";
            }

            i++;
        }

        introduction.add(fighting);
    }

    @Override
    public void setSupportingEnemies(List<BasicEnemy> supportingEnemies) {
        String supporting = new String("Supported by:");

        // TODO: Replace with better classification and add bosses
        int i = 0;
        for (BasicEnemy enemy : supportingEnemies) {
            if (i != 0) { supporting += ","; }

            if (enemy instanceof Slug) {
                supporting += " slug";
            } else if (enemy instanceof Zombie) {
                supporting += " zombie";
            } else if (enemy instanceof Vampire) {
                supporting += " vampire";
            } else if (enemy instanceof Doggie) {
                supporting += " DOGGIE";
            } else if (enemy instanceof ElanMuske) {
                supporting += " ELAN MUSKE";
            }

            i++;
        }

        introduction.add(supporting);
    }

    @Override
    public void setInCampfireRange(boolean inCampfireRange) {
        if (inCampfireRange) {
            introduction.add("Receiving x2 bonus from campfire!");
        }
    }

    @Override
    public void setInTowerRange(boolean inTowerRange) {
        if (inTowerRange) {
            introduction.add("Receiving support from tower!");
        }
    }

    @Override
    public void setWeapon(Weapon weapon) {
        if (weapon != null) {
            introduction.add("Equipped " + weapon.getClass().getSimpleName());
        }
    }


    @Override
    public void setHelmet(Helmet helmet) {
        if (helmet != null) {
            introduction.add("Equipped " + helmet.getClass().getSimpleName());
        }
    }

    @Override
    public void setShield(Shield shield) {
        if (shield != null) {
            introduction.add("Equipped " + shield.getClass().getSimpleName());
        }
    }

    @Override
    public void setArmor(ChestArmor armor) {
        if (armor != null) {
            introduction.add("Equipped " + armor.getClass().getSimpleName());
        }
    }

    @Override
    public void setAttackExchange(int playerDmg, int enemyDmg, int targetHp) {
        hpTracker = hpTracker - enemyDmg;
        body.add("PLAYER party dealt " + playerDmg +
                " to enemies: current target's hp at " + targetHp);
        body.add("ENEMY party dealt " + enemyDmg +
                " to player: player's hp at " + hpTracker);
    }

    @Override
    public void setFinalHp(int finalHp) {
        if (finalHp > 0) {
            conclusion.add("Battle finished with " + finalHp + " hp");
        } else {
            conclusion.add("GAME OVER");
        }
    }

    @Override
    public void setDefeated(List<BasicEnemy> defeated) {
        conclusion.add("Defeated " + defeated.size() + " enemies this battle");
    }

    @Override
    public void setRewards(List<String> rewards) {
        String rewarded = new String("Rewards:");

        // TODO: Change depending on how to implement generateRewards in BM
        int i = 0;
        int pots = 0;
        int xp = 0;
        int gold = 0;
        for (String reward : rewards) {
            if (i != 0) { rewarded += ","; }

            if (reward.contains("HealthPotion|")) {
                pots += Integer.parseInt(reward.replace("HealthPotion|", ""));
            } else if (reward.contains("xp|")) {
                xp = Integer.parseInt(reward.replace("xp|", ""));
            } else if (reward.contains("gold|")) {
                gold = Integer.parseInt(reward.replace("gold|", ""));
            } else {
                rewarded += reward;
            }

            i++;
        }

        conclusion.add(rewarded);
        conclusion.add("    + " + pots + " health potions");
        conclusion.add("    + " + xp + " xp");
        conclusion.add("    + " + gold + " gold");
    }
    
    public Summary getResult() {
        return new Summary(introduction, body, conclusion);
    }

}
