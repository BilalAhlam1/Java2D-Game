package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;


public class CharacterCollisions implements CollisionListener {
    private final Character Character;

    //Level number for progress bar
    private int levelNum = -1;
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

            //increments level number and score
            levelNum++;
            Character.setScoreCount(Character.getScoreCount() + 1);

        } else if (e.getOtherBody() instanceof Enemy) {
            //reduce health by 50 on contact with enemy
            Character.reduceHealth(50);

            //if health decreases to or below 0, reset character
            if (Character.getHealthCount() <= 0){
                Character.reset();
            }
        } else if (e.getOtherBody() instanceof Explosion){
            //reduce character health by 50 and update health on user view when in contact with the bomb
            Character.reduceHealth(50);

            if (Character.getHealthCount() <= 0){
                //if character health is 0 or below, reset the character
                Character.reset();
            }
        } else if (e.getOtherBody() instanceof Portal) {
            game.goToNextChapter(Character.getArrowCount(), Character.getScoreCount(), Character.getHealthCount(), Character.getLives());
        }
    }
}
