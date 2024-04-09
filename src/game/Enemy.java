package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Enemy extends Walker {
    private static final Shape Enemies =  new BoxShape(1, 2);
    //set the initial health
    private int Health = 100;
    private final Character character;
    private GameWorld w;

    private static final BodyImage enemySprite = new BodyImage("data/Loot/Seperate/tile387.png", 4f);
    private static final BodyImage enemyBullet = new BodyImage("data/Loot/Seperate/tile421.png", 4f);
    public Enemy(GameWorld w, Vec2 platform, Character character) {
        super(w, Enemies);
        this.character = character;
        this.w = w;

    }

    public void fly(Walker enemy, int speed){
        //enemy walks in alternating speeds if outside the range of the platform
        ActionListener a = ae -> {

            //Apply y-directional force

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

    public void reduceHealth(int n) {
        //reduce enemy health by n
        Health = Health - n;
    }

    public int getHealth() {
        //getter method to return enemy health
        return Health;
    }

    public Shape getEnemies(){
        return Enemies;
    }

    public void kill(){
        //remove enemy
        this.destroy();
        this.removeAllCollisionListeners();
    }

    public abstract Vec2 getPlatformPosition();
}
