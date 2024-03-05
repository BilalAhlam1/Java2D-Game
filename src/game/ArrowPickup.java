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

        } else if (e.getOtherBody() instanceof Enemy) {
            Character.reduceHealth(25);
            System.out.println(Character.getHealthPoints());
            if (Character.getHealthPoints() <= 0){
                Character.reset();
            }
        }else if (e.getOtherBody() instanceof Explosion){
            //reduce character health by 5 and update health on user view when in contact with the bomb
            Character.reduceHealth(100);
            System.out.println(Character.getHealthPoints());
            if (Character.getHealthPoints() <= 0){
                //if character health is 0 or below, reset the character
                Character.reset();
            }
        }
    }
}
