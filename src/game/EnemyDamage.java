package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class EnemyDamage implements CollisionListener {
    private final Enemy Enemy;
    private final Character character;;

    //Enemy dying sound
    private static final AudioInputStream Death;

    static {
        try {
            Death = AudioSystem.getAudioInputStream(new File("data/Sounds/Zombie_Death.wav"));
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    //sound is set to a clip
    private static final Clip Clip;

    static {
        try {
            Clip = AudioSystem.getClip();
            Clip.open(Death);
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public EnemyDamage(Enemy Enemy, Character character){
        this.Enemy = Enemy;
        this.character = character;

    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Ammunition) {
            //enemy's health reduces by 25 in collision with ammunition objects like Arrows
            Enemy.reduceHealth(25);


            //if the enemy health is 0, remove enemy, set clip to frame 0 and play, set score
            if (Enemy.getHealth() <= 0) {
                Enemy.kill();
                Clip.setFramePosition(0);
                Clip.start();

                //Sets score and JLabel
                character.setScoreCount(character.getScoreCount() + 1);
            }
        } else if (e.getOtherBody() instanceof Explosion) {
            //enemy's health reduces by 100 in collision with explosions - bomb
            Enemy.reduceHealth(100);

            //if the enemy health is 0, remove enemy, set clip to frame 0 and play
            if (Enemy.getHealth() <= 0) {
                Enemy.kill();
                Clip.setFramePosition(0);
                Clip.start();
            }
        }
    }
}
