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
    private final Enemies Enemies;
    private final Character character;
    private final UserView view;
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

    public EnemyDamage(Enemies Enemies, Character character, UserView view) throws LineUnavailableException, IOException {
        this.Enemies = Enemies;
        this.character = character;
        this.view = view;

    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Ammunition) {
            Enemies.reduceHealth();
            Point frameLocationOnScreen = view.getLocationOnScreen();
            float frameX = Enemies.getPosition().x - frameLocationOnScreen.x;
            float frameY = Enemies.getPosition().y - frameLocationOnScreen.y;
            EnemyHealth.setBounds((int) frameY, (int) frameY, 50, 20);
            EnemyHealth.setText("" + Enemies.getHealth());
            System.out.println(Enemies.getHealth());
            if (Enemies.getHealth() <= 0){
                EnemyHealth.removeAll();
                Enemies.destroy();
                Clip.setFramePosition(0);
                Clip.start();
                //character.setArrowCount(character.getArrowCount() + 5);
                //character.setScoreLabel();
            }
        }
    }

    public static JLabel getEnemyHealth() {
        return EnemyHealth;
    }
}
