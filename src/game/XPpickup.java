package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

import javax.swing.*;

public class XPpickup implements CollisionListener {
    private final Character Character;
    private static JLabel score;
    private final GameWorld world;
    public XPpickup(Character c, JLabel score, GameWorld w){
        this.Character = c;
        this.score = score;
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
            score.setText("Score = " + Character.getXPCount());
        } else if (e.getOtherBody() instanceof Enemies) {
            Character.reduceHealth(25);
            world.getHealth().setText("Health = " + Character.getHealth());
            System.out.println(Character.getHealth());
            if (Character.getHealth() <= 0){
                Character.reset();
            }
        }
    }
    public static JLabel getScore () {
        return score;
    }
}
