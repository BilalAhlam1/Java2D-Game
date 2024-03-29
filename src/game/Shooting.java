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
import java.io.File;
import java.io.IOException;

public class Shooting implements MouseListener {

    private final GameWorld world;
    private final GameView view;
    private final Character Character;
    private static final BodyImage bomb = new BodyImage("data/Loot/Seperate/tile373.png", 4f);
    private static final BodyImage broken = new BodyImage("data/Loot/Seperate/tile373broken.png", 4f);
    private static final BodyImage bulletImage = new BodyImage("data/Loot/Seperate/Arrow.png", 4f);

    SoundClip explosionSound = new SoundClip("data/Sounds/Explosion.wav");


    public Shooting(GameWorld w, GameView v, Character Character ) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        world = w;
        view = v;
        this.Character = Character;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
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

            //set the direction and speed of the bomb
            setPosition(worldPoint, Arrow, 1000f);

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

    public void removeBullet(Ammunition Bullet){
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Bullet.destroy();
            }
        };

        //removes the bomb after 5 seconds
        Timer timer1 = new Timer(5000, a);
        //Doesn't repeat the timer
        timer1.setRepeats(false);
        timer1.start();
    }
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

    public void setPosition(Vec2 worldPoint, Ammunition ammo, float speed){
        //Positions the shot depending on the position of the mouse
        if (worldPoint.x >= Character.getPosition().x && worldPoint.y >= Character.getPosition().y) {
            ammo.setPosition(new Vec2(Character.getPosition().x + 1, Character.getPosition().y + 1));
        } else if (worldPoint.x >= Character.getPosition().x && worldPoint.y <= Character.getPosition().y) {
            ammo.setPosition(new Vec2(Character.getPosition().x + 1, Character.getPosition().y - 1));
        } else if (worldPoint.x <= Character.getPosition().x && worldPoint.y <= Character.getPosition().y) {
            ammo.setPosition(new Vec2(Character.getPosition().x - 1, Character.getPosition().y - 1));
        } else if (worldPoint.x <= Character.getPosition().x && worldPoint.y >= Character.getPosition().y) {
            ammo.setPosition(new Vec2(Character.getPosition().x - 1, Character.getPosition().y + 1));
        }


        Vec2 ballPosition = ammo.getPosition();

        // Calculate the direction vector from ball position to mouse click position
        Vec2 direction = worldPoint.sub(ballPosition);
        direction.normalize(); // Normalize the direction vector to unit length

        // Apply a force in the direction of the mouse click
        Vec2 force = direction.mul(speed);
        ammo.setLinearVelocity(force);
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
