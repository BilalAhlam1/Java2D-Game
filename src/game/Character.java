package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Character extends Walker {
    private static final Shape CharacterShape =  new BoxShape(1, 2);
    private int XPCount;
    private int HealthPoints = 100;
    private final GameWorld w;
    private final JLabel score;
    private final JLabel Health;
    private static final BodyImage image = new BodyImage("data/Adventurer/Sprites/adventurer-idleRight-01.png", 4f);
    public Character(GameWorld w, JLabel score, JLabel Health) {
        super(w, CharacterShape);
        this.w = w;
        this.score = score;
        this.Health = Health;
        SolidFixture characterFixture = new SolidFixture(this, CharacterShape);
        addImage(image);
    }

    public void setXPCount(int XPCount) {
        this.XPCount = XPCount;
        System.out.println("XP:" + XPCount);
    }
    public int getXPCount() {
        return XPCount;
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
        score.setText("Score = " + getXPCount());
    }

    public JLabel getScore() {
        return score;
    }

    public void setHealth() {
        score.setText("Health = " + getHealthPoints());
    }

    public JLabel getHealth() {
        return Health;
    }

    public void reset(){
        XPCount = 0;
        this.HealthPoints = 100;
        this.setPosition(new Vec2(0, -11));
        getHealth().setText("Health = 100");
    }

}
