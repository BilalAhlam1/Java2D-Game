package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Initialises a Collision Listener for The Character Class
 */
public class CharacterCollisions implements CollisionListener {
    private final Character Character;
    //Character Class
    private final Game game;
    //Game Class
    static SoundClip PortalCollision;
    // Portal Collision Sound

    static {
        try {
            PortalCollision = new SoundClip("data/Sounds/PortalCollision.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor for Character Collisions
     * @param c Character Class implementing collision
     * @param game Game Class implementing world for collision
     */
    public CharacterCollisions(Character c, Game game){
        this.Character = c;
        this.game = game;
    }

    /**
     * Collision Event For Character
     * <p>Increments Arrow Count If Collides With Quiver, Reduces Health When In Contact With Enemy Class, Changes State of The Chapter And Game
     * When In Contact With Portals and On Chapter Complete Condition. Gives Character Zero-Gravity When In Contact With The Anti-Gravity Object For
     * a Set Timer</p>
     * @param e Collision Event
     */
    @Override
    public void collide(CollisionEvent e) {
        //Increments Arrows, Sets Spawn And Destroys Other Body In Contact With Quiver
        if (e.getOtherBody() instanceof Quiver) {

            Character.setArrowCount(Character.getArrowCount() + 3);
            e.getOtherBody().destroy();
            Character.setSpawn();

        //Reduces Character health In Contact With Enemy
        } else if (e.getOtherBody() instanceof Enemy) {

            Character.reduceHealth(50);

        //Reduces Character health In Contact With Explosion
        } else if (e.getOtherBody() instanceof Explosion){

            Character.reduceHealth(50);

        //Increments Score, Sets Spawn, Destroy Portal Body, Play Sound And Change Chapter
        } else if (e.getOtherBody() instanceof Portal && game.getGameLevel().isChapterComplete()) {

            Character.setScoreCount(Character.getScoreCount()+1);
            Character.setSpawn();
            e.getOtherBody().destroy();
            PortalCollision.play();
            game.goToNextChapter(Character.getArrowCount(), Character.getScoreCount(), Character.getHealthCount(), Character.getLives());

        //Set Gravity To 0, Change Boolean Anti-Gravity State to True, Destroy Body And Set Default Gravity After 2 Seconds In Contact With AntiGravity
        } else if (e.getOtherBody() instanceof AntiGravity) {

            Character.setGravityScale(0);
            Character.setAntiGravity(true);

            e.getOtherBody().destroy();
            ActionListener c = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {

                    Character.setGravityScale(1);
                    Character.setAntiGravity(false);
                    ((AntiGravity) e.getOtherBody()).reset();
                }
            };
            Timer timer2 = new Timer(2000, c);

            //Doesn't repeat the timer
            timer2.setRepeats(false);
            timer2.start();
        }
    }
}
