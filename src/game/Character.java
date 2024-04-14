package game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


/**
 * Initialises a Character of type Walker
 */
public class Character extends Walker {
    private static final Shape CharacterShape =  new BoxShape(1, 2);
    //Character shape

    private int ArrowCount = 0;
    //Number of arrows held in inventory

    private Vec2 spawnPosition = new Vec2(0, 3);
    //original spawn position

    private int healthCount = 100;
    //Character heath

    private int lives = 3;
    //Number of lives

    private int scoreCount = 0;
    //Score

    private int EnemiesKilled = 0;
    //Enemies Killed

    private static final BodyImage image = new BodyImage("data/Adventurer/Sprites/adventurer-idleRight-01.png", 4f);
    //Spawn Character Image

    private Boolean isAntiGravity = false;
    //Is Power Up Active

    static SoundClip CharacterHit;
    //Character Hit Sound Clip

    static {
        try {
            CharacterHit = new SoundClip("data/Sounds/CharacterHurt.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    static SoundClip CharacterDeath;
    //Character Dead Sound Clip

    static {
        try {
            CharacterDeath = new SoundClip("data/Sounds/CharacterDeath.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor initialising Character
     * @param w GameWorld Class Where Body Exists
     */
    public Character(GameWorld w) {
        //creates a walker object
        super(w, CharacterShape);

        //adds spawn idle image to character
        addImage(image);
    }

    /**
     * Setter Method to set boolean value of current condition
     * @param antiGravity Boolean for active power up
     */
    public void setAntiGravity(Boolean antiGravity) {
        isAntiGravity = antiGravity;
    }

    /**
     * Getter Method to get boolean value of current condition
     * @return Boolean for active or inactive power up
     */
    public Boolean getAntiGravity() {
        return isAntiGravity;
    }

    /**
     * Setter Method to set lives value of the character
     * @param lives New Lives Value
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Setter Method to set score count of the character and maintains above 0
     * @param scoreCount New Score Count Value
     */
    public void setScoreCount(int scoreCount) {
        //sets the score number
        if (scoreCount > -1) {
            this.scoreCount = scoreCount;
        } else {
            this.scoreCount = 0;
        }
    }

    /**
     * Getter Method to return the score count
     * @return Score Count
     */
    public int getScoreCount() {
        return scoreCount;
    }

    /**
     * Getter Method to return the lives count
     * @return lives Count
     */
    public int getLives() {
        return lives;
    }

    /**
     * Setter Method to set arrow count of the character
     * @param ArrowCount New Arrow Count Value
     */
    public void setArrowCount(int ArrowCount) {
        this.ArrowCount = ArrowCount;
    }

    /**
     * Getter Method to return the arrow count
     * @return Arrow Count
     */
    public int getArrowCount() {
        return ArrowCount;
    }

    /**
     * Reduce Health By Given Amount
     * <p>Used To Decrease Character Health When Under A Given Condition</p>
     */
    public void reduceHealth(int n){
        healthCount = healthCount - n;
        CharacterHit.play();
    }

    /**
     * Getter Method to return the health count
     * @return Health Count
     */
    public int getHealthCount() {
        return healthCount;
    }

    /**
     * Setter Method to set health value of the character
     * @param healthCount New Health Count Value
     */
    public void setHealthCount(int healthCount) {
        this.healthCount = healthCount;
    }

    /**
     * Reset the character
     * <p>Used To Reset The Character To Its Default Properties And De-Increment Lives/Score. The Character's position is set back to spawn</p>
     */
    public void reset(){
        //resets the characters arrow count, health, position, de-increments the number of lives and score
        CharacterDeath.play();
        ArrowCount = 0;
        this.healthCount = 100;
        lives = lives - 1;
        if (scoreCount > 0) {
            scoreCount = scoreCount - 1;
        }
        this.setPosition(spawnPosition);
    }

    /**
     * Setter Method to set spawn position of the character
     */
    public void setSpawn(){
        spawnPosition = this.getPosition();
    }

    /**
     * Setter Method to set number of enemies killed
     * @param enemiesKilled New enemiesKilled Value
     */
    public void setEnemiesKilled(int enemiesKilled) {
        EnemiesKilled = enemiesKilled;
    }

    /**
     * Getter Method to return the Enemies Killed
     * @return Enemies Killed
     */
    public int getEnemiesKilled() {
        return EnemiesKilled;
    }
}
