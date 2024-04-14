package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Initialises GameWorld Of Type World
 */
public abstract class GameWorld extends World {
    private static final BodyImage Cloud = new BodyImage("data/Cloud.png", 7f);
    //Platform
    private final Character Character;
    //Character Class
    private float MaxPlatformPosition = 0;
    //Current Maximum Y Position Of The Platform
    private int Enemies = 0;
    //Number Of Enemies

    /**
     * Constructor Initialises GameWorld
     * <p>Character And Walls Are Initialised</p>
     */
    public GameWorld(){
        super(60);

        //make walls
        Shape wallShape1 = new BoxShape(0.5f, 200);
        StaticBody wall1 = new StaticBody(this, wallShape1);
        wall1.getFixtureList().getFirst().destroy();
        SolidFixture wall1Fixture = new SolidFixture(wall1, wallShape1);
        wall1Fixture.setFriction(0);
        wall1.setPosition(new Vec2(21f, 0));

        //make walls
        Shape wallShape2 = new BoxShape(0.5f, 200);
        StaticBody wall2 = new StaticBody(this, wallShape2);
        wall2.getFixtureList().getFirst().destroy();
        SolidFixture wall2Fixture = new SolidFixture(wall2, wallShape2);
        wall2Fixture.setFriction(0);
        wall2.setPosition(new Vec2(-21f, 0));

        //Creates Character
        Character = new Character(this);

    }

    /**
     * Respawn Arrows
     * <p>Respawn arrows by checking if the current inventory is empty every second</p>
     * @param world World Class
     * @param platform Platform Position To Spawn
     */
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

    /**
     * Move platforms
     * <p>Change platform position in an alternating fixed distance</p>
     * @param platform Static Body To Move
     * @param XDistance X Distance to Change
     * @param YDistance Y Distance To Change
     */
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

    /**
     * Sets The Statistics
     * <p>Sets the statistics from the previous or loaded chapter</p>
     */
    public void setStatistics(int Arrows, int Score, int Health, int Lives){
        Character.setArrowCount(Arrows);
        Character.setScoreCount(Score);
        Character.setHealthCount(Health);
        Character.setLives(Lives);
    }

    /**
     * Getter to return character
     * @return Character Class
     */
    public Character getCharacter() {
        return Character;
    }

    /**
     * Getter to return platform position
     * @return Y position of highest platform
     */
    public float getMaxPlatformPosition() {
        return MaxPlatformPosition;
    }

    /**
     * Setter to set the new y position of the highest platform
     */
    public void setMaxPlatformPosition(float MaxPlatformPosition) {
        this.MaxPlatformPosition = MaxPlatformPosition;
    }

    /**
     * Returns a boolean for all enemies killed
     * <p>Boolean to check if the characters kills are equivalent to the number of enemies</p>
     * @return True for all enemies killed.
     * False if not
     */
    public boolean isChapterComplete(){
        return Enemies == Character.getEnemiesKilled();
    }

    /**
     * Setter to set the number of enemies in the game
     * @param enemies New enemy count
     */
    public void setEnemies(int enemies) {
        Enemies = enemies;
    }

    /**
     * Getter to return the number of enemies
     * @return Enemies count
     */
    public int getEnemies() {
        return Enemies;
    }
}
