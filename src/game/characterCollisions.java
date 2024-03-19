package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;


public class CharacterCollisions implements CollisionListener {
    private final Character Character;

    //Last known position of the platform
    private float lastYpos = 0;

    //Difficulty Level
    private float Difficulty = 0;
    private final GameWorld world;

    //Level number for progress bar
    private int levelNum = -1;

    public CharacterCollisions(Character c, GameWorld world){
        this.Character = c;
        this.world = world;
    }
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Arrows) {

            // If character collides with arrows, arrow inventory increments
            Character.setArrowCount(Character.getArrowCount() + 1);

            //destroy collided object
            e.getOtherBody().destroy();

            //update the score
            Character.setArrowLabel();

        } else if (e.getOtherBody() instanceof Quiver) {

            // If character collides with a quiver, arrow inventory increments by 3
            Character.setArrowCount(Character.getArrowCount() + 3);

            //destroy collided object
            e.getOtherBody().destroy();

            //update the number of arrows and spawn position
            Character.setArrowLabel();
            Character.setSpawn();

            //Creates 3 levels
            if (levelNum < 3) {
                makeLevel();
            }

            //increments level number and score
            levelNum++;
            Character.setScoreNumber(Character.getScoreNumber() + 1);

            //updates number of levels for progress bar, and score label
            Character.setLevelNum(levelNum);
            Character.getScore().setText("Score = " + Character.getScoreNumber());

        } else if (e.getOtherBody() instanceof Enemy) {
            //reduce health by 50 on contact with enemy
            Character.reduceHealth(50);

            //if health decreases to or below 0, reset character
            if (Character.getHealthPoints() <= 0){
                Character.reset();
            }
        }else if (e.getOtherBody() instanceof Explosion){
            //reduce character health by 50 and update health on user view when in contact with the bomb
            Character.reduceHealth(50);

            if (Character.getHealthPoints() <= 0){
                //if character health is 0 or below, reset the character
                Character.reset();
            }
        }
    }

    public void makeLevel(){
        Levels level = new Levels(Character,lastYpos, world, Difficulty);

        //Creates platforms
        try {
            level.MakeLevel();
        } catch (LineUnavailableException | IOException ex) {
            throw new RuntimeException(ex);
        }

        // Gets the position of the highest platform
        lastYpos = level.getMaxLevel();

        // Increases difficulty(spawn rates) for every level by roughly 20%
        Difficulty += 0.2F;
    }
}
