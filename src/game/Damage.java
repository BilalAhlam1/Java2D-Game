package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Damage implements CollisionListener {
    private final Character character;

    Damage(Character character){
        this.character = character;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Character){
            //reduce character health by 5 and update health on user view when in contact with the bomb
            character.reduceHealth(5);
            character.setHealth();
            System.out.println(character.getHealthPoints());
            if (character.getHealthPoints() <= 0){
                //if character health is 0 or below, reset the character
                character.reset();
            }
        }
    }
}
