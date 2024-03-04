package game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

public class Character extends Walker {
    private static final Shape CharacterShape =  new BoxShape(1, 2);
    private int ArrowCount;
    private Vec2 spawnPosition = new Vec2(0, -11);
    private int HealthPoints = 100;
    private final JLabel Arrows;
    private final JLabel Health;
    private static final BodyImage image = new BodyImage("data/Adventurer/Sprites/adventurer-idleRight-01.png", 4f);
    public Character(GameWorld w, JLabel Arrows, JLabel Health) {
        super(w, CharacterShape);
        this.Arrows = Arrows;
        this.Health = Health;
        SolidFixture characterFixture = new SolidFixture(this, CharacterShape);
        addImage(image);

        Arrows.setBounds(10,0,120,20);
        Arrows.setFont(new Font("Arial", Font.BOLD, 12));

        Health.setForeground(Color.GREEN);
        Health.setBounds(700, 0, 120, 20);
        Health.setFont(new Font("Arial", Font.BOLD, 12));
    }

    public void setArrowCount(int XPCount) {
        this.ArrowCount = XPCount;
        setScoreLabel();
    }

    public int getArrowCount() {
        return ArrowCount;
    }

    public void reduceHealth(int n){
        HealthPoints = HealthPoints - n;
    }

    public int getHealthPoints() {
        return HealthPoints;
    }

    public void setHealthPoints(int HealthPoints) {
        this.HealthPoints = HealthPoints;
    }


    public void setScoreLabel() {
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
        this.setPosition(spawnPosition);
        setHealth();
        setScoreLabel();
    }

    public void setSpawn(){
        spawnPosition = this.getPosition();
    }

}
