package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Enemy extends Walker {
    private static final Shape Enemies =  new BoxShape(1, 2);
    //set the initial health
    private int Health = 100;

    //set the initial speed
    private final int speed = 8;
    private static final BodyImage enemySprite = new BodyImage("data/Loot/Seperate/tile387.png", 4f);
    public Enemy(World w, StaticBody platform) {
        super(w, Enemies);
        addImage(enemySprite);

        //sets the primary position of the enemy before walking
        setPosition(new Vec2(platform.getPosition().x, platform.getPosition().y + 2));

        // Move the character in alternating speeds
        move(this, platform);
        this.startWalking(speed);
    }

    public void move(Walker enemy, StaticBody platform){
        //enemy walks in alternating speeds if outside the range of the platform
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (enemy.getPosition().x > platform.getPosition().x + 3){
                    enemy.startWalking(-speed);

                } else if (enemy.getPosition().x < platform.getPosition().x - 3) {
                    enemy.startWalking(speed);
                }
            }
        };

        //explodes the bomb every second
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

    public void kill(){
        //remove enemy
        this.destroy();
        this.removeAllCollisionListeners();
    }
}
