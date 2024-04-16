package game;
import city.cs.engine.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Objects;

/**
 * Initialise Movement Key Adapter
 * <p>Provides Key Event Conditions For Movement And Escape</p>
 */
public class Movement extends KeyAdapter {
    private Walker Character;
    //Character Class
    private final JFrame frame;
    //Game Frame
    private final GameView view;
    //UserView
    private final Game game;
    //Game Class

    //Character Movement images
    private static final BodyImage runRight = new BodyImage("data/Adventurer/Sprites/adventurer-run-00.png", 4f);
    private static final BodyImage runLeft = new BodyImage("data/Adventurer/Sprites/Run Left.png", 4f);
    private static final BodyImage jump = new BodyImage("data/Adventurer/Sprites/adventurer-fall-00.png", 4f);
    private static final BodyImage idleRight = new BodyImage("data/Adventurer/Sprites/adventurer-idle-00.png", 4f);
    private static final BodyImage idleLeft = new BodyImage("data/Adventurer/Sprites/adventurer-idleLeft-01.png", 4f);
    //Character Images
    SoundClip JumpSound = new SoundClip("data/Sounds/jump.wav");
    //Jump Sound
    public String preKey = null;
    //Last movement key. Used to add the appropriate orientated image to the character

    /**
     * Constructor For Character Movement and Escape To Menu
     */
    public Movement(Character character, JFrame frame, Game game, GameView view) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.Character = character;
        this.frame = frame;
        this.game = game;
        this.view = view;
    }

    /**
     * Move Character and Escape
     * <p>Provides key Events And Conditions For Movement and Escape To Main Menu</p>
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int Key = e.getKeyCode();

        // Move Right
        if (Key == KeyEvent.VK_RIGHT || Key == KeyEvent.VK_D) {
            Character.startWalking(9);
            //replace Character image
            Character.removeAllImages();
            Character.addImage(runRight);
            preKey = "Right";

            // Move Left
        } else if (Key == KeyEvent.VK_LEFT || Key == KeyEvent.VK_A) {
            Character.startWalking(-9);
            //replace Character image
            Character.removeAllImages();
            Character.addImage(runLeft);
            preKey = "Left";

            //Jump
        } else if (Key == KeyEvent.VK_UP || Key == KeyEvent.VK_W) {
            Character.jump(20);
            JumpSound.play();
            //replace Character image
            Character.removeAllImages();
            Character.addImage(jump);
        }
    }

    /**
     * Stops Character Movement And Updates Body Image
     * <p>Changes State of The Character Body Image When Keys Are Released</p>
     */
    @Override
    public void keyReleased(KeyEvent e) {

        //Stop Walking on release and set the appropriate image for the characters direction
        if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            Character.stopWalking();
            //Set default image
            Character.removeAllImages();
            Character.addImage(idleRight);
            preKey = "Right";
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            Character.stopWalking();
            //Set default image
            Character.removeAllImages();
            Character.addImage(idleLeft);
            preKey = "Left";
        }else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            //Set default image depending on the direction of the previous input
            Character.removeAllImages();
            if(Objects.equals(preKey, "Left")) {
                Character.addImage(idleLeft);
            } else{
                Character.addImage(idleRight);
            }
        }
    }

    /**
     * Setter to update the Character
     * @param character new Character Class
     */
    public void updateCharacter(Character character){
        this.Character = character;
    }
}
