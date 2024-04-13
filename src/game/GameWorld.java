package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GameWorld extends World {

    //Load platform image
    private static final BodyImage Cloud = new BodyImage("data/Cloud.png", 7f);
    private final Character Character;
    private int Chapter = 1;
    private float currentYPos = 0;
    private int Enemies = 0;
    public GameWorld(){
        super(60);

        //make walls
        Shape wallShape1 = new BoxShape(0.5f, 100);
        StaticBody wall1 = new StaticBody(this, wallShape1);
        wall1.getFixtureList().getFirst().destroy();
        SolidFixture wall1Fixture = new SolidFixture(wall1, wallShape1);
        wall1Fixture.setFriction(0);
        wall1.setPosition(new Vec2(21f, 0));

        //make walls
        Shape wallShape2 = new BoxShape(0.5f, 100);
        StaticBody wall2 = new StaticBody(this, wallShape2);
        wall2.getFixtureList().getFirst().destroy();
        SolidFixture wall2Fixture = new SolidFixture(wall2, wallShape2);
        wall2Fixture.setFriction(0);
        wall2.setPosition(new Vec2(-21f, 0));

        //Creates Character
        Character = new Character(this);

    }

    public void ResupplyArrows(World world, StaticBody platform){
        final Boolean[] quiverCollected = {false};
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (getCharacter().getArrowCount() == 0 && !quiverCollected[0]) {
                    Quiver quiver = new Quiver(world, platform.getPosition());
                    quiverCollected[0] = true;
                } else if (getCharacter().getArrowCount() > 0) {
                    quiverCollected[0] = false;
                }
            }
        };

        //Checks every second
        Timer timer1 = new Timer(1000, a);
        timer1.start();
    }

    public void movePlatform(StaticBody platform, int XDistance, int YDistance){
        final int[] x = {-XDistance};
        final int[] y = {-YDistance};
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                platform.move(new Vec2(x[0], y[0]));
                x[0] = -x[0];
                y[0] = -y[0];
            }
        };

        //explodes the bomb every second
        Timer timer1 = new Timer(2000, a);
        timer1.start();
    }

    //Set Statistics
    public void setStatistics(int Arrows, int Score, int Health, int Lives){
        Character.setArrowCount(Arrows);
        Character.setScoreCount(Score);
        Character.setHealthCount(Health);
        Character.setLives(Lives);
    }

    public Character getCharacter() {
        return Character;
    }

    public void setChapter(int Chapter) {
        this.Chapter = Chapter;
    }

    public int getChapter() {
        return Chapter;
    }

    public float getCurrentYPos() {
        return currentYPos;
    }

    public void setCurrentYPos(float currentYPos) {
        this.currentYPos = currentYPos;
    }

    public boolean isChapterComplete(){
        return Enemies == Character.getEnemiesKilled();
    }
    public void setEnemies(int enemies) {
        Enemies = enemies;
    }

    public int getEnemies() {
        return Enemies;
    }
}
