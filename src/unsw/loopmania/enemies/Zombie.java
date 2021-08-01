package unsw.loopmania.enemies;

import unsw.loopmania.PathPosition;

import java.util.List;

import unsw.loopmania.Character;
import unsw.loopmania.movement.MoveAntiClockwise;
import unsw.loopmania.movement.MoveClockwise;
import unsw.loopmania.movement.MoveRandomly;

public class Zombie extends BasicEnemy {
    private Character targetCharacter;
    private int detectionRadius;
    private int countdown;

    /**
     * Constructor for zombie
     * @param position tile position of zombie
     */
    public Zombie(PathPosition position, Character character) {
        super(position);
        targetCharacter = character;

        // Zombie stats
        setMoveSpeed(0.5);
        setCritChance(0.3);
        setBattleRadius(1);
        setSupportRadius(2);
        setHp(200);
        setMaxHp(200);
        setDmg(5);
        setWeight(2);
        detectionRadius = 5;
        countdown = 1;

        // Behaviours
        setMoveBehaviour(new MoveRandomly());
    }

    /**
     * Getter for detection radius
     * @return detection radius of zombie (always 5)
     */
    public int getDetectionRadius() {
        return detectionRadius;
    }

    /**
     * Getter for countdown
     * @return the countdown of zombie before it moves again
     */
    public int getCountdown() {
        return countdown;
    }

    /**
     * Overridden method that moves the zombie towards the character if in
     * range, otherwise just wanders randomly
     */
    @Override
    public void performMove() {
        PathPosition currPos = getPosition();

        // Character has been detected
        if (currPos.distanceToCharacter(targetCharacter) <= detectionRadius) {
            PathPosition nextPos = currPos.getNextPosition();
            PathPosition prevPos = currPos.getPrevPosition();

            // Distances between character and the tile in front of and the tile
            // behind the zombie
            double forward = nextPos.distanceToCharacter(targetCharacter);
            double backward = prevPos.distanceToCharacter(targetCharacter);

            // Set moveBehaviour to move towards the tile that is closer to
            // character
            if (forward < backward) {
                setMoveBehaviour(new MoveClockwise());
            } else {
                setMoveBehaviour(new MoveAntiClockwise());
            }

        // Character has been lost
        } else {
            setMoveBehaviour(new MoveRandomly());
        }

        // Only move every two turns
        if (countdown == 1) {
            countdown = 0;
        } else {
            countdown = 1;
            super.performMove();
        }
    }

    /**
     * Overidden implementation of abstract method critAttack that converts ally
     * to zombie if there is one
     */
    @Override
    public void critAttack(Character player, List<BasicEnemy> battleEnemies) {
        int damage = (int) (getDmg() * 4 * 2 * player.getDamageTakenModifier());

        if (player.getAllyList().size() > 0) {
            player.getAllyList().remove(0);
            battleEnemies.add(new Zombie(getPosition(), player));
        }

        player.setHp(player.getHp() - damage);
    }
}
