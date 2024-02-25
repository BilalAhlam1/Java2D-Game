package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController implements MouseListener {

    private GameWorld world;
    private GameView view;
    private Walker Character;

    public MouseController(GameWorld w, GameView v, Walker Character) {
        world = w;
        view = v;
        this.Character = Character;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //create a round ball object
        Shape circleShape = new CircleShape(1);
        DynamicBody bullet = new DynamicBody(world, circleShape);
        GhostlyFixture bulletFixture = new GhostlyFixture(bullet, circleShape);

        //get the coordinates of the mouse click - these are in
        //pixels (the location in the window where the click happened)
        Point mousePoint = e.getPoint();

        //the ball though needs to be added at *world* coordinates which
        //are in meters. So, we transform mouse coordinates into
        //world coordinates using a method provided by the view class:
        Vec2 worldPoint = view.viewToWorld(mousePoint);
        bullet.setPosition(Character.getPosition());


        Vec2 ballPosition = bullet.getPosition();

        // Calculate the direction vector from ball position to mouse click position
        Vec2 direction = worldPoint.sub(ballPosition);
        direction.normalize(); // Normalize the direction vector to unit length

        // Apply a force in the direction of the mouse click
        float forceMagnitude = 1000.0f; // Adjust this value based on the desired force
        Vec2 force = direction.mul(forceMagnitude);
        bullet.setLinearVelocity(force);

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
