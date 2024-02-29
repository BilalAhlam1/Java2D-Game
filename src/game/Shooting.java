package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Shooting implements MouseListener {

    private GameWorld world;
    private GameView view;
    private Character Character;
    private Ammunition Bomb;
    private static final BodyImage bomb = new BodyImage("data/Loot/Seperate/tile373.png", 4f);
    private static final BodyImage broken = new BodyImage("data/Loot/Seperate/tile373broken.png", 4f);
    private static final AudioInputStream explosion;

    static {
        try {
            explosion = AudioSystem.getAudioInputStream(new File("data/Sounds/Explosion.wav"));
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

    public Shooting(GameWorld w, GameView v, Character Character) throws LineUnavailableException, IOException {
        world = w;
        view = v;
        this.Character = Character;
        clip.open(explosion);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //create a bullet object
        Bomb = new Ammunition(world);
        Bomb.addImage(bomb);

        //get the coordinates of the mouse click
        Point mousePoint = e.getPoint();

        //the ball though needs to be added at world coordinates which
        //are in meters. So, we transform mouse coordinates into
        //world coordinates using a method provided by the view class:
        Vec2 worldPoint = view.viewToWorld(mousePoint);
        System.out.println(worldPoint);
        System.out.println(Character.getPosition());

        //Positions the shot depending on the position of the mouse
        if (worldPoint.x > Character.getPosition().x && worldPoint.y > Character.getPosition().y) {
            Bomb.setPosition(new Vec2(Character.getPosition().x + 1, Character.getPosition().y + 1));
        } else if (worldPoint.x > Character.getPosition().x && worldPoint.y < Character.getPosition().y) {
            Bomb.setPosition(new Vec2(Character.getPosition().x + 1, Character.getPosition().y - 1));
        } else if (worldPoint.x < Character.getPosition().x && worldPoint.y < Character.getPosition().y) {
            Bomb.setPosition(new Vec2(Character.getPosition().x - 1, Character.getPosition().y - 1));
        } else if (worldPoint.x < Character.getPosition().x && worldPoint.y > Character.getPosition().y) {
            Bomb.setPosition(new Vec2(Character.getPosition().x - 1, Character.getPosition().y + 1));
        }


        Vec2 ballPosition = Bomb.getPosition();

        // Calculate the direction vector from ball position to mouse click position
        Vec2 direction = worldPoint.sub(ballPosition);
        direction.normalize(); // Normalize the direction vector to unit length

        // Apply a force in the direction of the mouse click
        float forceMagnitude = 15.0f;
        Vec2 force = direction.mul(forceMagnitude);
        Bomb.setLinearVelocity(force);

        //set a timer and explode the bomb
        timer(Bomb);
    }

    public void timer(Ammunition Bomb){
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //set the bomb explosion
                setExplosion();
            }
        };

        //explodes the bomb every second
        Timer timer1 = new Timer(1000, a);
        //Doesn't repeat the timer
        timer1.setRepeats(false);
        timer1.start();
    }

    public void setExplosion() {
        Explosion explode = new Explosion(Character, world);
        Bomb.addCollisionListener(explode);
        Bomb.removeAllImages();
        Bomb.addImage(broken);
        clip.setFramePosition(0);
        clip.start();
        System.out.println("BOOM");

        ActionListener c = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Bomb.removeAllCollisionListeners();
                Bomb.destroy();
            }
        };
        Timer timer2 = new Timer(2000, c);
        //Doesn't repeat the timer
        timer2.setRepeats(false);
        timer2.start();
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
