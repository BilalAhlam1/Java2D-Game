package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EnemyDamage implements CollisionListener {
    private final Enemy Enemy;
    private final Character character;
    //private static final JLabel EnemyHealth = new JLabel();

    private static final AudioInputStream Death;

    static {
        try {
            Death = AudioSystem.getAudioInputStream(new File("data/Sounds/Zombie_Death.wav"));
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Clip Clip;

    static {
        try {
            Clip = AudioSystem.getClip();
            Clip.open(Death);
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public EnemyDamage(Enemy Enemy, Character character) throws LineUnavailableException, IOException {
        this.Enemy = Enemy;
        this.character = character;

    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Ammunition) {
            Enemy.reduceHealth(25);
            //Point frameLocationOnScreen = view.getLocationOnScreen();
            //float frameX = Enemy.getPosition().x - frameLocationOnScreen.x;
            //float frameY = Enemy.getPosition().y - frameLocationOnScreen.y;
            //EnemyHealth.setBounds((int) frameX, (int) frameY, 50, 20);
            //EnemyHealth.setText("" + Enemy.getHealth());
            System.out.println(Enemy.getHealth());

            //if the enemy health is 0, remove enemy
            if (Enemy.getHealth() <= 0) {
                Enemy.kill();
                Clip.setFramePosition(0);
                Clip.start();

                //gives character health
                if (character.getHealthPoints() < 100) {
                    character.setHealthPoints(character.getHealthPoints() + 5);
                }
            }
        } else if (e.getOtherBody() instanceof Explosion) {
            Enemy.reduceHealth(100);
            //Point frameLocationOnScreen = view.getLocationOnScreen();
            //float frameX = Enemy.getPosition().x - frameLocationOnScreen.x;
            //float frameY = Enemy.getPosition().y - frameLocationOnScreen.y;
            //EnemyHealth.setBounds((int) frameX, (int) frameY, 50, 20);
            //EnemyHealth.setText("" + Enemy.getHealth());
            System.out.println(Enemy.getHealth());

            //if the enemy health is 0, remove enemy
            if (Enemy.getHealth() <= 0) {
                Enemy.kill();
                Clip.setFramePosition(0);
                Clip.start();

                //gives character health
                if (character.getHealthPoints() < 100) {
                    character.setHealthPoints(character.getHealthPoints() + 5);
                }
            }
        }
    }
}
