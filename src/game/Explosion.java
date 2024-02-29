package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Explosion implements CollisionListener {
    private static Character character;
    private GameWorld world;
    Explosion(Character character, GameWorld gameWorld){
        Explosion.character = character;
        this.world = gameWorld;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Character){
            //reduce character health by 5 and update health on user view when in contact with the bomb
            character.reduceHealth(5);
            world.getHealth().setText("Health = " + character.getHealth());
            System.out.println(character.getHealth());
            if (character.getHealth() <= 0){
                //if character health is 0 or below, reset the character
                character.reset();
            }
        }
    }
}
