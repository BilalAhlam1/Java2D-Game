package game;
import city.cs.engine.*;

import javax.sound.sampled.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Control extends KeyAdapter {

    Walker Character;
    UserView view;
    World world;

    //Character Movement images
    private static final BodyImage runRight = new BodyImage("data/Adventurer/Sprites/adventurer-run-00.png", 4f);
    private static final BodyImage runLeft = new BodyImage("data/Adventurer/Sprites/Run Left.png", 4f);
    private static final BodyImage jump = new BodyImage("data/Adventurer/Sprites/adventurer-fall-00.png", 4f);
    private static final BodyImage idleRight = new BodyImage("data/Adventurer/Sprites/adventurer-idle-00.png", 4f);
    private static final BodyImage idleLeft = new BodyImage("data/Adventurer/Sprites/adventurer-idleLeft-01.png", 4f);
    private static final AudioInputStream Jump;

    static {
        try {
            Jump = AudioSystem.getAudioInputStream(new File("data/Sounds/jump.wav"));
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Clip clip;

    static {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }


    //Last movement key. Used to add the appropriate orientated image to the character
    public String preKey = null;

    public Control(Walker StudentWalker, UserView View, World world){
        this.Character = StudentWalker;
        this.view = View;
        this.world = world;
        try {
            clip.open(Jump);
        } catch (LineUnavailableException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int Key = e.getKeyCode();

        // Move Right
        if (Key == KeyEvent.VK_RIGHT || Key == KeyEvent.VK_D) {
            Character.startWalking(7);
            //replace Character image
            Character.removeAllImages();
            Character.addImage(runRight);
            preKey = "Right";

            // Move Left
        } else if (Key == KeyEvent.VK_LEFT || Key == KeyEvent.VK_A) {
            Character.startWalking(-7);
            //replace Character image
            Character.removeAllImages();
            Character.addImage(runLeft);
            preKey = "Left";

            //Jump
        } else if (Key == KeyEvent.VK_UP || Key == KeyEvent.VK_W) {
            Character.jump(20);
            clip.setFramePosition(0);
            clip.start();
            //replace Character image
            Character.removeAllImages();
            Character.addImage(jump);
        }
    }

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
            //Set default image
            Character.removeAllImages();
            if(Objects.equals(preKey, "Left")) {
                Character.addImage(idleLeft);
            } else{
                Character.addImage(idleRight);
            }
        }
    }
}
