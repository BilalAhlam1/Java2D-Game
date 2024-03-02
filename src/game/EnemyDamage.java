package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

import javax.swing.*;

public class EnemyDamage implements CollisionListener {
    private final Enemies Enemies;
    private Character character;
    private static final JLabel EnemyHealth = new JLabel();

    public EnemyDamage(Enemies Enemies, Character character) {
        this.Enemies = Enemies;
        this.character = character;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Ammunition) {
            Enemies.reduceHealth();
            int x = (int) Enemies.getPosition().x;
            int y = (int) Enemies.getPosition().y;
            EnemyHealth.setAlignmentX(x);
            EnemyHealth.setAlignmentY(y);
            EnemyHealth.setText("" + Enemies.getHealth());
            System.out.println(Enemies.getHealth());
            if (Enemies.getHealth() <= 0){
                EnemyHealth.removeAll();
                Enemies.destroy();
                character.setXPCount(character.getXPCount() + 5);
                character.setScoreLabel();
            }
        }
    }

    public static JLabel getEnemyHealth() {
        return EnemyHealth;
    }
}
