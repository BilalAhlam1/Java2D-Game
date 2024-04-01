package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chapter2 extends GameWorld{
    private static final BodyImage Cloud = new BodyImage("data/Blue Cloud.png", 7f);
    private static final BodyImage purpleCloud = new BodyImage("data/Purple Cloud.png", 7f);
    private static final BodyImage TimeYourJumpsMessage = new BodyImage("data/GameMessages/TimeYourJumps.png", 2f);
    private static final BodyImage DirectArrowsMessage = new BodyImage("data/GameMessages/DirectArrows.png", 1f);
    public Chapter2(Game game, int Arrows, int Score, int Health, int Lives){
        super();

        //Move Statistics from previous level to current Level
        setStatistics(Arrows, Score, Health, Lives);

        //Set Character Position
        getCharacter().setPosition(new Vec2(0, 3));

        //Displays score with collisions
        CharacterCollisions Collisions = new CharacterCollisions(getCharacter(), game);
        getCharacter().addCollisionListener(Collisions);

        //make a starting platform
        Shape shape1 = new BoxShape(6, 0.5f);
        StaticBody platform1 = new StaticBody(this, shape1);
        platform1.setPosition(new Vec2(-6f, 0));
        platform1.addImage(Cloud);

        //Time Your Jumps Message
        Shape TimeJumps = new BoxShape(6, 0.5f);
        StaticBody TimeJumpsBody = new StaticBody(this, TimeJumps);
        TimeJumpsBody.getFixtureList().removeFirst().destroy();
        GhostlyFixture TimeJumpsFixture = new GhostlyFixture(TimeJumpsBody, TimeJumps);
        TimeJumpsBody.setPosition(new Vec2(0f, -3));
        TimeJumpsBody.addImage(TimeYourJumpsMessage);

        //Moving Platform
        Shape shape2 = new BoxShape(6, 0.5f);
        StaticBody platform2 = new StaticBody(this, shape2);
        platform2.setPosition(new Vec2(-6f, 8));
        platform2.addImage(Cloud);
        movePlatform(platform2, 60, 0);

        Shape shape3 = new BoxShape(6, 0.5f);
        StaticBody platform3 = new StaticBody(this, shape3);
        platform3.setPosition(new Vec2(6f, 14));
        platform3.addImage(Cloud);
        //Resupply arrows if empty
        ResupplyArrows(this, platform3);

        //Direct Arrows Message
        Shape DirectArrows = new BoxShape(6, 0.5f);
        StaticBody DirectArrowsBody = new StaticBody(this, DirectArrows);
        DirectArrowsBody.getFixtureList().removeFirst().destroy();
        GhostlyFixture DirectArrowsFixture = new GhostlyFixture(DirectArrowsBody, DirectArrows);
        DirectArrowsBody.setPosition(new Vec2(-8f, 16));
        DirectArrowsBody.addImage(DirectArrowsMessage);

        //Bouncy cloud
        Shape shape4 = new BoxShape(6, 0.5f);
        StaticBody BouncyPlatform = new StaticBody(this, shape4);
        BouncyPlatform.setPosition(new Vec2(14, 26));
        BouncyPlatform.addImage(Cloud);
        SolidFixture platformFixture = new SolidFixture(BouncyPlatform, shape4);
        platformFixture.setRestitution(3);
        BouncyPlatform.addImage(purpleCloud);
        BouncyPlatform.setAngleDegrees(120);

        //Enemy Cloud
        Shape shape5 = new BoxShape(6, 0.5f);
        StaticBody platform4 = new StaticBody(this, shape5);
        platform4.setPosition(new Vec2(0f, 22));
        platform4.addImage(Cloud);
        Enemy enemy = new Zombie(this, platform4, getCharacter());
        EnemyDamage enemyDamage = new EnemyDamage(enemy, getCharacter());
        enemy.addCollisionListener(enemyDamage);

        //PowerUp Cloud
        Shape shape6 = new BoxShape(6, 0.5f);
        StaticBody platform5 = new StaticBody(this, shape6);
        platform5.setPosition(new Vec2(-10f, 31));
        platform5.addImage(Cloud);
        PowerUps antiGravity = new AntiGravity(this, platform5, getCharacter());

        Shape shape7 = new BoxShape(6, 0.5f);
        StaticBody platform6 = new StaticBody(this, shape7);
        platform6.setPosition(new Vec2(6f, 71));
        platform6.addImage(Cloud);
        //Resupply arrows if empty
        ResupplyArrows(this, platform6);

        //ENEMY PLATFORM
        Shape shape8 = new BoxShape(6, 0.5f);
        StaticBody platform7 = new StaticBody(this, shape8);
        platform7.setPosition(new Vec2(-8f, 81));
        platform7.addImage(Cloud);
        Enemy enemy1 = new Zombie(this, platform7, getCharacter());
        EnemyDamage enemyDamage1 = new EnemyDamage(enemy1, getCharacter());
        enemy1.addCollisionListener(enemyDamage1);


        Shape shape9 = new BoxShape(6, 0.5f);
        StaticBody platform8 = new StaticBody(this, shape9);
        platform8.setPosition(new Vec2(10f, 89));
        platform8.addImage(Cloud);
        movePlatform(platform8, 20, -8);

        Enemy enemy2 = new Guardian(this, platform8, getCharacter());
        EnemyDamage enemyDamage2 = new EnemyDamage(enemy2, getCharacter());
        enemy2.addCollisionListener(enemyDamage2);

        Shape shape10 = new BoxShape(6, 0.5f);
        StaticBody platform9 = new StaticBody(this, shape10);
        platform9.setPosition(new Vec2(10f, 105));
        platform9.addImage(Cloud);
        Portal portal = new Portal(this, platform9);
    }
}
