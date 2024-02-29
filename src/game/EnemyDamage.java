package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

import javax.swing.*;

public class EnemyDamage implements CollisionListener {
    private final Enemies Enemies;
    private static final JLabel EnemyHealth = new JLabel();

    public EnemyDamage(Enemies Enemies) {
        this.Enemies = Enemies;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Ammunition) {
            Enemies.reduceHealth();
            EnemyHealth.setText("" + Enemies.getHealth());
            int x = (int) Enemies.getPosition().x;
            int y = (int) Enemies.getPosition().y;
            EnemyHealth.setBounds(x, y, 2, 2);
            System.out.println(Enemies.getHealth());
            if (Enemies.getHealth() <= 0){
                EnemyHealth.removeAll();
                Enemies.destroy();
            }
        }
    }

    public static JLabel getEnemyHealth() {
        return EnemyHealth;
    }
}
