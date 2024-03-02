package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

import javax.swing.*;

public class XPpickup implements CollisionListener {
    private final Character Character;
    private final GameWorld world;
    public XPpickup(Character c, GameWorld w){
        this.Character = c;
        this.world = w;
    }
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof XP) {
            // If character collides with XP, score increments
            Character.setXPCount(Character.getXPCount() + 1);
            //destroy collided object
            e.getOtherBody().destroy();
            //update the score
            Character.setScoreLabel();
        } else if (e.getOtherBody() instanceof Enemies) {
            Character.reduceHealth(25);
            Character.setHealth();
            System.out.println(Character.getHealthPoints());
            if (Character.getHealthPoints() <= 0){
                Character.reset();
            }
        }
    }
}
