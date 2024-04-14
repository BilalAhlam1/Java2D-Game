package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * Initialise Shooting of type mouse listener
 */
public class Shooting implements MouseListener {
    private GameWorld world;
    //GameWorld
    private UserView view;
    //UserView
    private Character Character;
    //Character
    private static final BodyImage bomb = new BodyImage("data/Loot/Seperate/tile373.png", 4f);
    private static final BodyImage bulletImage = new BodyImage("data/Loot/Seperate/Arrow.png", 4f);
    //Ammunition Images

    SoundClip explosionSound = new SoundClip("data/Sounds/Explosion.wav");
    //Explosion Sound
    static SoundClip ArrowShot;
    //Arrow Shooting Sound

    static {
        try {
            ArrowShot = new SoundClip("data/Sounds/ArrowShot.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    static SoundClip BombThrow;
    //Throwing Bomb Sound

    static {
        try {
            BombThrow = new SoundClip("data/Sounds/ThrowingBomb.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor Initialise Shooting
     * @param w GameWorld where shooting exists
     * @param v UserView Where Mouse Clicks Are Tracked
     * @param Character Current Character
     */
    public Shooting(GameWorld w, UserView v, Character Character) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        world = w;
        view = v;
        this.Character = Character;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Projects ammunition where mouse point is
     * <p>Projects Arrows or Bombs Depending on Mouse Event</p>
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //Create a bomb if right click
        if (e.getButton() == MouseEvent.BUTTON3) {
            //create an Ammunition object
            Ammunition Bomb = new Ammunition(world);
            Bomb.addImage(bomb);

            //get the coordinates of the mouse click
            Point mousePoint = e.getPoint();

            //the ball though needs to be added at world coordinates which
            //are in meters. So, we transform mouse coordinates into
            //world coordinates using a method provided by the view class:
            Vec2 worldPoint = view.viewToWorld(mousePoint);

            //set the direction and speed of the bomb
            setPosition(worldPoint, Bomb, 15.0f);

            BombThrow.play();

            //set a timer and explode the bomb
            timer(Bomb);
        } //Create a Bullet if left click
        else if (e.getButton() == MouseEvent.BUTTON1 && Character.getArrowCount() > 0) {
            //create an Ammunition object
            Ammunition Arrow = new Ammunition(world);
            Arrow.addImage(bulletImage);

            //get the coordinates of the mouse click
            Point mousePoint = e.getPoint();

            //the ball though needs to be added at world coordinates which
            //are in meters. So, we transform mouse coordinates into
            //world coordinates using a method provided by the view class:
            Vec2 worldPoint = view.viewToWorld(mousePoint);

            //set the direction and speed of the arrow
            setPosition(worldPoint, Arrow, 1000f);

            ArrowShot.play();

            // Calculate angle between bullet position and mouse pointer
            Vec2 bulletPosition = Arrow.getPosition();
            float dx = worldPoint.x - bulletPosition.x;
            float dy = worldPoint.y - bulletPosition.y;
            float angle = (float) Math.atan2(dy, dx);

            // Set the rotation of the bullet image
            Arrow.setAngle(angle);

            //remove the arrow from inventory
            Character.setArrowCount(Character.getArrowCount() - 1);

            //remove the arrow
            removeBullet(Arrow);
        }
    }

    /**
     * Removes Ammunition
     * <p>Removes ammunition after 2 seconds of projection</p>
     * @param ammunition Ammunition type
     */
    public void removeBullet(Ammunition ammunition){
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ammunition.destroy();
            }
        };

        //removes the bomb after 2 seconds
        Timer timer1 = new Timer(2000, a);
        //Doesn't repeat the timer
        timer1.setRepeats(false);
        timer1.start();
    }

    /**
     * Timer to set explosion
     * <p>Sets explosion after 1 second of projection</p>
     * @param Bomb Ammunition Type
     */
    public void timer(Ammunition Bomb){
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //set the bomb explosion
                setExplosion(Bomb);
            }
        };

        //explodes the bomb every second
        Timer timer1 = new Timer(1000, a);
        //Doesn't repeat the timer
        timer1.setRepeats(false);
        timer1.start();
    }

    /**
     * Changes state of ammunition to explosion
     * <p>Sets explosion image and timer for explosion object</p>
     * @param Bomb Ammunition type
     */
    public void setExplosion(Ammunition Bomb) {
        Bomb.removeAllImages();
        //replace bomb with the explosion
        Explosion explosion1 = new Explosion(world, Bomb);

        //destroy the bomb object and play sound-clip
        Bomb.destroy();
        explosionSound.play();

        ActionListener c = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                //Remove the Bomb object
                explosion1.destroy();
            }
        };
        Timer timer2 = new Timer(2000, c);

        //Doesn't repeat the timer
        timer2.setRepeats(false);
        timer2.start();
    }

    /**
     * Project object at mouse position
     * <p>Uses Mouse Pointer Position To Project Ammunition And Sets Spawn</p>
     * @param worldPoint Mouse Pointer Position
     * @param ammunition Ammunition type
     * @param speed Speed of trajectory
     */
    public void setPosition(Vec2 worldPoint, Ammunition ammunition, float speed){
        //Positions the shot depending on the position of the mouse
        if (worldPoint.x >= Character.getPosition().x && worldPoint.y >= Character.getPosition().y) {
            ammunition.setPosition(new Vec2(Character.getPosition().x + 1, Character.getPosition().y + 1));
        } else if (worldPoint.x >= Character.getPosition().x && worldPoint.y <= Character.getPosition().y) {
            ammunition.setPosition(new Vec2(Character.getPosition().x + 1, Character.getPosition().y - 1));
        } else if (worldPoint.x <= Character.getPosition().x && worldPoint.y <= Character.getPosition().y) {
            ammunition.setPosition(new Vec2(Character.getPosition().x - 1, Character.getPosition().y - 1));
        } else if (worldPoint.x <= Character.getPosition().x && worldPoint.y >= Character.getPosition().y) {
            ammunition.setPosition(new Vec2(Character.getPosition().x - 1, Character.getPosition().y + 1));
        }


        Vec2 ballPosition = ammunition.getPosition();

        // Calculate the direction vector from ball position to mouse click position
        Vec2 direction = worldPoint.sub(ballPosition);
        direction.normalize(); // Normalize the direction vector to unit length

        // Apply a force in the direction of the mouse click
        Vec2 force = direction.mul(speed);
        ammunition.setLinearVelocity(force);
    }

    /**
     * Setter to update character
     * @param character New Character
     */
    public void updateCharacter(Character character){
        this.Character = character;
    }

    /**
     * Setter to update world
     * @param w New GameWorld
     */
    public void setWorld(GameWorld w){
        this.world = w;
    }

    /**
     * Setter to update UserView
     * @param view New UserView
     */
    public void setView(UserView view) {
        this.view = view;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
