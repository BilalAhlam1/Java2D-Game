package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class EnemyDamage implements CollisionListener {
    private final Enemies Enemies;

    public EnemyDamage(Enemies Enemies, game.Enemies enemies) {
        this.Enemies = enemies;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Ammunition) {
            Enemies.reduceHealth();
            System.out.println(Enemies.getHealth());
            if (Enemies.getHealth() <= 0){
                Enemies.destroy();
            }
        }
    }
}
