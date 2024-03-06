package game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

public class Character extends Walker {
    private static final Shape CharacterShape =  new BoxShape(1, 2);
    private int ArrowCount;
    private Vec2 spawnPosition = new Vec2(0, 3);
    private int HealthPoints = 100;
    private int lives = 3;
    private final JLabel Arrows;
    private final JLabel Health;
    private final JLabel LivesLabel;
    private int levelNum = 0;
    private static final BodyImage image = new BodyImage("data/Adventurer/Sprites/adventurer-idleRight-01.png", 4f);
    //private static final BodyImage lives = new BodyImage("data/Loot/Seperate/Heart.png", 4f);

    public Character(GameWorld w, JLabel Arrows, JLabel Health, JLabel lives) {
        super(w, CharacterShape);
        this.Arrows = Arrows;
        this.Health = Health;
        LivesLabel = lives;
        addImage(image);
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
        return ArrowCount;
    }

    public void reduceHealth(int n){
        HealthPoints = HealthPoints - n;
        setHealth();
    }

    public int getHealthPoints() {
        return HealthPoints;
    }

    public void setHealthPoints(int HealthPoints) {
        this.HealthPoints = HealthPoints;
        setHealth();
    }


    public void setArrowLabel() {
        Arrows.setText("Arrows = " + getArrowCount());
    }

    public JLabel getArrows() {
        return Arrows;
    }

    public void setHealth() {
        Health.setText("Health = " + getHealthPoints());
    }

    public JLabel getHealth() {
        return Health;
    }

    public void reset(){
        ArrowCount = 0;
        this.HealthPoints = 100;
        lives = lives - 1;
        this.setPosition(spawnPosition);
        setHealth();
        setArrowLabel();
        setLives();
        if (lives == 0){
            System.exit(0);
        }
    }

    public void setSpawn(){
        spawnPosition = this.getPosition();
    }

}
