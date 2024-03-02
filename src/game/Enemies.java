package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Enemies extends Walker {
    private static final Shape Enemies =  new BoxShape(1, 2);

    //set the initial health
    private int Health = 100;

    //set the initial speed
    private int speed = 4;
    private static final BodyImage enenmySprite = new BodyImage("data/Loot/Seperate/tile387.png", 4f);
    public Enemies(World w, StaticBody platform) {
        super(w, Enemies);
        addImage(enenmySprite);
        setPosition(new Vec2(platform.getPosition().x - 2, platform.getPosition().y + 2));

        // Move the character in alternating speeds every 2 seconds
        move(platform, this);
    }

    public void move(StaticBody platform, Walker enemy){
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                enemy.startWalking(speed);
                speed = speed * -1;
            }
        };

        //explodes the bomb every second
        Timer timer1 = new Timer(2000, a);

        timer1.start();
    }

    public void reduceHealth() {
        Health = Health - 20;
    }

    public int getHealth() {
        return Health;
    }
}
