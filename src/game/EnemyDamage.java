package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EnemyDamage implements CollisionListener {
    private final Enemies Enemies;
    private final Character character;
    private static final JLabel EnemyHealth = new JLabel();

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

    public EnemyDamage(Enemies Enemies, Character character) throws LineUnavailableException, IOException {
        this.Enemies = Enemies;
        this.character = character;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Ammunition) {
            Enemies.reduceHealth();
            //EnemyHealth.setBounds((int) Enemies.getPosition().x, (int) Enemies.getPosition().y, 100, 20);
            EnemyHealth.setText("" + Enemies.getHealth());
            System.out.println(Enemies.getHealth());
            if (Enemies.getHealth() <= 0){
                EnemyHealth.removeAll();
                Enemies.destroy();
                Clip.setFramePosition(0);
                Clip.start();
                character.setXPCount(character.getXPCount() + 5);
                character.setScoreLabel();
            }
        }
    }

    public static JLabel getEnemyHealth() {
        return EnemyHealth;
    }
}
