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
    private int HealthPoints = 100;

    //Number of lives
    private int lives = 3;

    //Labels for arrows, health and number of lives
    private final JLabel Arrows;
    private final JLabel Health;
    private final JLabel LivesLabel;

    //the level number currently in play
    private int levelNum = 0;

    //spawn character image
    private static final BodyImage image = new BodyImage("data/Adventurer/Sprites/adventurer-idleRight-01.png", 4f);
    //private static final BodyImage lives = new BodyImage("data/Loot/Seperate/Heart.png", 4f);

    public Character(GameWorld w, JLabel Arrows, JLabel Health, JLabel lives) {
        //creates a walker object
        super(w, CharacterShape);

        //assigns parameters to variables
        this.Arrows = Arrows;
        this.Health = Health;
        this.LivesLabel = lives;

        //adds spawn idle image to character
        addImage(image);

        //sets the labels on the frame
        setJLabels();
    }

    public void setJLabels(){

        //set Arrows
        Arrows.setBounds(10,10,120,20);
        Arrows.setFont(new Font("Arial", Font.BOLD, 20));

        //set Health
        Health.setForeground(Color.GREEN);
        Health.setBounds(665, 10, 120, 20);
        Health.setFont(new Font("Arial", Font.BOLD, 20));

        //set Lives
        LivesLabel.setForeground(Color.RED);
        LivesLabel.setBounds(550, 10, 120, 20);
        LivesLabel.setFont(new Font("Arial", Font.BOLD, 20));
    }

    public int getLives() {
        //return number of lives
        return lives;
    }

    public JLabel getLivesLabel() {
        //return lives JLabel
        return LivesLabel;
    }

    public void setLives(){
        //Update Lives
        LivesLabel.setText("Lives = " + getLives());
    }

    public int getLevelNum() {
        //return level Number
        return levelNum;
    }
    public void setLevelNum(int n){
        //Set Level Number
        levelNum = n;
    }

    public void setArrowCount(int ArrowCount) {
        //Set Arrow count and update score label
        this.ArrowCount = ArrowCount;
        setArrowLabel();
    }

    public int getArrowCount() {
        //return the number of arrows in the character inventory
        return ArrowCount;
    }

    public void reduceHealth(int n){
        //reduce health by the set value 'n' and update this on the frame
        HealthPoints = HealthPoints - n;
        setHealth();
    }

    public int getHealthPoints() {
        //return the value of health remaining
        return HealthPoints;
    }

    public void setHealthPoints(int HealthPoints) {
        //set the value of health remaining and update this on the frame
        this.HealthPoints = HealthPoints;
        setHealth();
    }


    public void setArrowLabel() {
        //update arrows label
        Arrows.setText("Arrows = " + getArrowCount());
    }

    public JLabel getArrows() {
        //returns JLabel arrows
        return Arrows;
    }

    public void setHealth() {
        //sets the JLabel health
        Health.setText("Health = " + getHealthPoints());
    }

    public JLabel getHealth() {
        //returns the JLabel health
        return Health;
    }

    public void reset(){
        //resets the characters arrow count, health, position and de-increments the number of lives
        ArrowCount = 0;
        this.HealthPoints = 100;
        lives = lives - 1;
        this.setPosition(spawnPosition);
        setHealth();
        setArrowLabel();
        setLives();

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
