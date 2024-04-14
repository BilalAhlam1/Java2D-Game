package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Initialises Enemy Damage to Type Collision Listener
 */
public class EnemyDamage implements CollisionListener {
    private final Enemy Enemy;
    //Enemy Class
    private final Character character;;
    //Character Class
    private static final SoundClip Death;
    //Enemy Death Sound

    static {
        try {
            Death = new SoundClip("data/Sounds/Zombie_Death.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor Initialises Enemy Damage
     * @param Enemy Enemy Class
     * @param character Character Class
     */
    public EnemyDamage(Enemy Enemy, Character character){
        this.Enemy = Enemy;
        this.character = character;
    }

    /**
     * Collision Listener For Enemy Class
     * <p>Collision With Ammunition Will Reduce Enemy Health, Or Destroy Enemy Body And Set Score If Enemy Health Is 0 or Below.
     * Collision With Explosion Will Destroy Enemy Body</p>
     * @param e Collision Event
     */
    @Override
    public void collide(CollisionEvent e) {
        //Reduces Enemy Health By 25, Removes Enemy Body, Increments Score And Enemies Killed When In Contact With Ammunition
        if (e.getOtherBody() instanceof Ammunition) {

            Enemy.reduceHealth(25);
            if (Enemy.getHealth() <= 0) {
                Enemy.kill();
                Death.play();

                character.setScoreCount(character.getScoreCount() + 1);
                character.setEnemiesKilled(character.getEnemiesKilled() + 1);
            }
        }

        //Reduces Enemy Health By 100 And/or Removes Enemy Body In Contact With Explosion
        else if (e.getOtherBody() instanceof Explosion) {

            Enemy.reduceHealth(100);
            if (Enemy.getHealth() <= 0) {
                Enemy.kill();
                Death.play();
            }
        }
    }
}
