package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class CharacterCollisions implements CollisionListener {
    private final Character Character;

    //Last known position of the platform
    private float lastYpos = 0;

    //Difficulty Level
    private float Difficulty = 0;

    //Level number for progress bar
    private int levelNum = -1;
    private Game game;

    public CharacterCollisions(Character c, Game game){
        this.Character = c;
        this.game = game;
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
        } else if (e.getOtherBody() instanceof Explosion){
            //reduce character health by 50 and update health on user view when in contact with the bomb
            Character.reduceHealth(50);

            if (Character.getHealthPoints() <= 0){
                //if character health is 0 or below, reset the character
                Character.reset();
            }
        } else if (e.getOtherBody() instanceof Portal) {
            game.goToNextChapter();
        }
    }
}
