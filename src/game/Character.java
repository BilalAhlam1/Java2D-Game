package game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

public class Character extends Walker {

    //Character shape
    private static final Shape CharacterShape =  new BoxShape(1, 2);

    //Number of arrows held in inventory
    private int ArrowCount;

    //original spawn position
    private Vec2 spawnPosition = new Vec2(0, 3);

    //Character heath
    private int healthCount = 100;

    //Number of lives
    private int lives = 3;

    //Score
    private int scoreCount = 0;

    //spawn character image
    private static final BodyImage image = new BodyImage("data/Adventurer/Sprites/adventurer-idleRight-01.png", 4f);

    //private static final BodyImage lives = new BodyImage("data/Loot/Seperate/Heart.png", 4f);

    public Character(GameWorld w) {
        //creates a walker object
        super(w, CharacterShape);

        //adds spawn idle image to character
        addImage(image);
    }

    public void setHealthCount(int healthCount) {
        this.healthCount = healthCount;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setScoreCount(int scoreCount) {
        //sets the score number
        this.scoreCount = scoreCount;
    }

    public int getScoreCount() {
        //returns the score number
        return scoreCount;
    }

    public int getLives() {
        //return number of lives
        return lives;
    }

    public void setArrowCount(int ArrowCount) {
        //Set Arrow count and update score label
        this.ArrowCount = ArrowCount;
    }

    public int getArrowCount() {
        //return the number of arrows in the character inventory
        return ArrowCount;
    }

    public void reduceHealth(int n){
        //reduce health by the set value 'n' and update this on the frame
        healthCount = healthCount - n;
    }

    public int getHealthCount() {
        //return the value of health remaining
        return healthCount;
    }

    public void reset(){
        //resets the characters arrow count, health, position and de-increments the number of lives
        ArrowCount = 0;
        this.healthCount = 100;
        lives = lives - 1;
        this.setPosition(spawnPosition);

        //if the number of lives reaches 0, the game ends
        if (lives == 0){
            System.exit(0);
        }
    }

    public void setSpawn(){
        //set the new spawn positions on checkpoints
        spawnPosition = this.getPosition();
    }

}
