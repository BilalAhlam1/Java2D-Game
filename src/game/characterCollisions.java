package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CharacterCollisions implements CollisionListener {
    private final Character Character;
    private final Game game;

    public CharacterCollisions(Character c, Game game){
        this.Character = c;
        this.game = game;
    }
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Arrows) {

            // If character collides with arrows, arrow inventory increments
            Character.setArrowCount(Character.getArrowCount() + 1);

            //destroy collided object
            e.getOtherBody().destroy();

        } else if (e.getOtherBody() instanceof Quiver) {

            // If character collides with a quiver, arrow inventory increments by 3
            Character.setArrowCount(Character.getArrowCount() + 3);

            //destroy collided object
            e.getOtherBody().destroy();

            //update the number of arrows and spawn position
            Character.setSpawn();

        } else if (e.getOtherBody() instanceof Enemy) {
            //reduce health by 50 on contact with enemy
            Character.reduceHealth(50);

        } else if (e.getOtherBody() instanceof Explosion){
            //reduce character health by 50 and update health on user view when in contact with the bomb
            Character.reduceHealth(50);

        } else if (e.getOtherBody() instanceof Portal) {

            Character.setScoreCount(Character.getScoreCount()+1);
            e.getOtherBody().destroy();
            game.goToNextChapter(Character.getArrowCount(), Character.getScoreCount(), Character.getHealthCount(), Character.getLives());

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
