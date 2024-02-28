package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

import javax.swing.*;

public class XPpickup implements CollisionListener {
    private final Character Character;
    static JLabel score;
    public XPpickup(Character c, JLabel score){
        this.Character = c;
        this.score = score;
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
        }
    }
    public static JLabel getScore () {
        return score;
    }
}
