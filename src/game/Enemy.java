package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Initialises an Enemy Of Type Walker
 */
public abstract class Enemy extends Walker {
    private static final Shape Enemies =  new BoxShape(1, 2);
    //Enemy Shape
    private int Health = 100;
    //Health
    private final Character character;
    //Character Class

    /**
     * Constructor Initialises Enemy
     * <p>Sets the Walker For the Enemy And Sets The Character Class</p>
     * @param w GameWorld Class
     * @param character Character Class
     */
    public Enemy(GameWorld w, Character character) {
        super(w, Enemies);
        this.character = character;
    }

    /**
     * Gives The Enemy Ability to Follow Character
     * <p>Projects Enemy Towards The Character Position At a Set Trajectory And Speed Using a Timer</p>
     * @param enemy Enemy Class
     * @param speed Speed Of Trajectory
     */
    public void fly(Walker enemy, int speed){
        //enemy walks in alternating speeds if outside the range of the platform
        ActionListener a = ae -> {

            // Calculate the direction vector from Character
            Vec2 direction = character.getPosition();
            direction.normalize(); // Normalize the direction vector to unit length

            if (character.getPosition().y > enemy.getPosition().y){
                // Apply a force in the direction of the Character
                Vec2 force = direction.mul(speed);
                enemy.setLinearVelocity(force);
            } else if (character.getPosition().y < enemy.getPosition().y) {
                // Apply a force in the direction of the Character
                Vec2 force = direction.mul(-speed);
                enemy.setLinearVelocity(force);
            }

            //Apply x-directional force
            if (character.getPosition().x > enemy.getPosition().x){
                enemy.startWalking(speed);
            } else if (character.getPosition().x < enemy.getPosition().x) {
                enemy.startWalking(-speed);
            }
        };
        Timer timer1 = new Timer(15, a);
        timer1.start();
    }


    /**
     * Gives The Enemy a Fixed Movement Method
     * <p>Enemy Walks In Alternating Directions At a Set Speed And Position</p>
     * @param enemy Enemy Class
     * @param platform Platform Position
     * @param speed Speed of Movement
     */
    public void move(Walker enemy, Vec2 platform, int speed){
        //enemy walks in alternating speeds if outside the range of the platform
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (enemy.getPosition().x > platform.x + 3){
                    enemy.startWalking(-speed);

                } else if (enemy.getPosition().x < platform.x - 3) {
                    enemy.startWalking(speed);
                }
            }
        };
        Timer timer1 = new Timer(15, a);
        timer1.start();
    }

    /**
     * Reduce Enemy Health
     * <p>Reduce Enemy Health By The Set n Amount</p>
     * @param n Decrease Health by n
     */
    public void reduceHealth(int n) {
        Health = Health - n;
    }

    /**
     * Return Health Of Enemy
     * @return int Health
     */
    public int getHealth() {
        return Health;
    }

    /**
     * Returns Enemy Shape
     * @return BoxShape
     */
    public Shape getEnemies(){
        return Enemies;
    }

    /**
     * Removes Enemy Body And Collision Listener
     */
    public void kill(){
        this.destroy();
        this.removeAllCollisionListeners();
    }

    /**
     * Get Enemy Position
     * @return Enemy Coordinate Position
     */
    public abstract Vec2 getPlatformPosition();
}
