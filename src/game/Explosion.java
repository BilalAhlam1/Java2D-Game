package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class Explosion implements CollisionListener {
    private static Ammunition Bomb;
    private static Character character;
    Explosion(Ammunition Bomb, Character character){
        this.Bomb = Bomb;
        this.character = character;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Character){
            character.reduceHealth();
            System.out.println(character.getHealth());
        }
    }
}
