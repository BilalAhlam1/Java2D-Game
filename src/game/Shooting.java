package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Shooting implements MouseListener {

    private GameWorld world;
    private GameView view;
    private Walker Character;
    private Ammunition Bomb;

    public Shooting(GameWorld w, GameView v, Walker Character) {
        world = w;
        view = v;
        this.Character = Character;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //create a bullet object
        Bomb = new Ammunition(world);

        //get the coordinates of the mouse click
        Point mousePoint = e.getPoint();

        //the ball though needs to be added at *world* coordinates which
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
        float forceMagnitude = 10.0f;
        Vec2 force = direction.mul(forceMagnitude);
        Bomb.setLinearVelocity(force);

        //set a timer and explode the bomb
        explode(Bomb);
    }

    public void explode(Ammunition Bomb){
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //remove the bomb object
                Bomb.destroy();
                System.out.println("BOOOM");
            }
        };

        //explodes the bomb every 5 seconds
        Timer timer1 = new Timer(5000, a);
        //Doesn't repeat the timer
        timer1.setRepeats(false);
        timer1.start();
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
