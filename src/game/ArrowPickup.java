package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;


public class ArrowPickup implements CollisionListener {
    private final Character Character;
    public ArrowPickup(Character c){
        this.Character = c;
    }
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Arrows) {

            // If character collides with arrows, arrow inventory increments
            Character.setArrowCount(Character.getArrowCount() + 1);

            //destroy collided object
            e.getOtherBody().destroy();

            //update the score
            Character.setScoreLabel();

        } else if (e.getOtherBody() instanceof Quiver) {

            // If character collides with a quiver, arrow inventory increments by 3
            Character.setArrowCount(Character.getArrowCount() + 3);

            //destroy collided object
            e.getOtherBody().destroy();

            //update the score
            Character.setScoreLabel();
            Character.setSpawn();

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
