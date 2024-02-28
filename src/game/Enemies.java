package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Enemies extends Walker {
    private static final Shape Enemies =  new BoxShape(1, 2);
    private int Health = 100;
    private static final BodyImage enenmySprite = new BodyImage("data/Loot/Seperate/tile387.png", 4f);
    public Enemies(World w, StaticBody staticBody) {
        super(w, Enemies);
        addImage(enenmySprite);
        setPosition(new Vec2(staticBody.getPosition().x, staticBody.getPosition().y + 2));
        this.startWalking((float) 0.2);
        move(staticBody, this);
    }

    public void move(StaticBody staticBody, Walker enemy){
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (enemy.getPosition().x == staticBody.getPosition().x - 0.5){
                    enemy.startWalking(1);
                } else if (enemy.getPosition().x == staticBody.getPosition().x + 0.5) {
                    enemy.startWalking(-1);
                }
            }
        };

        //explodes the bomb every second
        Timer timer1 = new Timer(15, a);

        timer1.start();
    }

    public void reduceHealth() {
        Health = Health - 10;
    }

    public int getHealth() {
        return Health;
    }
}
