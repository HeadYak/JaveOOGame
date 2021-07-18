package unsw.loopmania.enemies;

import unsw.loopmania.PathPosition;

import unsw.loopmania.Character;
import unsw.loopmania.movement.MoveAntiClockwise;
import unsw.loopmania.movement.MoveClockwise;
import unsw.loopmania.movement.MoveRandomly;
import unsw.loopmania.enemies.crits.CritConvertToEnemy;

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
        setCritChance(0.1);
        setBattleRadius(1);
        setSupportRadius(2);
        setHp(200);
        setDmg(5);
        detectionRadius = 5;
        countdown = 1;

        // Behaviours
        setMoveBehaviour(new MoveRandomly());
        setCritBehaviour(new CritConvertToEnemy());
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
            PathPosition prevPos = currPos.getNextPosition();

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
}
