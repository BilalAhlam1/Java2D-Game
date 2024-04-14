package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

/**
 * Provides platforms, enemies and pickup objects for the first chapter in the world
 */

public class Chapter1 extends GameWorld {
    private static final BodyImage Mountain = new BodyImage("data/mountainTile.png", 7f);
    //Mountain Platforms
    private static final BodyImage purpleMountain = new BodyImage("data/PurpleMountainTile.png", 7f);
    //Bouncy Platform
    private static final BodyImage MovementInfo = new BodyImage("data/GameMessages/Movement-Info.png", 2f);
    //How To Move Text
    private static final BodyImage JumpBoost = new BodyImage("data/GameMessages/JumpBoost.png", 2f);
    //How To Jump Text
    private static final BodyImage ShootingMessage1 = new BodyImage("data/GameMessages/ShootingMessage1.png", 1f);
    //How To Shoot Text
    private static final BodyImage ShootingMessage2 = new BodyImage("data/GameMessages/ShootingMessage2.png", 1f);
    //How To Shoot Text


    /**
     * Constructor Loads/Generates basic platforms, enemy and ammunition
     * @param game Game Class
     * @param Arrows Arrows Count
     * @param Score Score Count
     * @param Health Health Count
     * @param Lives Lives Count
     */
    public Chapter1(Game game,  int Arrows, int Score, int Health, int Lives) {
        super();

        //Set Coordinates for platforms
        Vec2[] PlatformCoordinates = new Vec2[]{new Vec2(0f, 0), new Vec2(6f, 8), new Vec2(-6f, 16)};
        Vec2[] BouncyPlatformCoordinates = new Vec2[]{new Vec2(8f, 24)};

        //Set values of statistics and enemy count
        setStatistics(Arrows, Score, Health, Lives);
        setEnemies(1);

        //Set the character position
        getCharacter().setPosition(new Vec2(0, 3));

        //Add collisions to the character
        CharacterCollisions Collisions = new CharacterCollisions(getCharacter(), game);
        getCharacter().addCollisionListener(Collisions);

        //Spawn Basic platforms
        for (Vec2 coordinate : PlatformCoordinates) {
            Shape shape1 = new BoxShape(6, 0.5f);
            StaticBody platform1 = new StaticBody(this, shape1);
            platform1.setPosition(coordinate);
            platform1.addImage(Mountain);
        }

        //How To Move Text
        Shape movementText = new BoxShape(6, 0.5f);
        StaticBody movementTextBody = new StaticBody(this, movementText);
        movementTextBody.getFixtureList().removeFirst().destroy();
        GhostlyFixture movementTextFixture = new GhostlyFixture(movementTextBody, movementText);
        movementTextBody.setPosition(new Vec2(0f, -5));
        movementTextBody.addImage(MovementInfo);

        //Jump Boost Text
        Shape jumpBoostText = new BoxShape(6, 0.5f);
        StaticBody jumpBoostTextBody = new StaticBody(this, jumpBoostText);
        jumpBoostTextBody.getFixtureList().removeFirst().destroy();
        GhostlyFixture jumpBoostTextFixture = new GhostlyFixture(jumpBoostTextBody, jumpBoostText);
        jumpBoostTextBody.setPosition(new Vec2(5f, 20));
        jumpBoostTextBody.addImage(JumpBoost);

        //Bouncy Platform
        Shape shape4 = new BoxShape(6, 0.5f);
        StaticBody BouncyPlatform = new StaticBody(this, shape4);
        BouncyPlatform.setPosition(BouncyPlatformCoordinates[0]);
        SolidFixture platformFixture = new SolidFixture(BouncyPlatform, shape4);
        platformFixture.setRestitution(3);
        BouncyPlatform.addImage(purpleMountain);

        //Spawn Arrows
        Shape shape5 = new BoxShape(6, 0.5f);
        StaticBody platform4 = new StaticBody(this, shape5);
        platform4.setPosition(new Vec2(-8f, 36));
        platform4.addImage(Mountain);
        Quiver quiver = new Quiver(this, platform4.getPosition());

        //How To Shoot Text
        Shape ShootText = new BoxShape(6, 0.5f);
        StaticBody ShootTextBody = new StaticBody(this, ShootText);
        ShootTextBody.getFixtureList().removeFirst().destroy();
        GhostlyFixture ShootFixture = new GhostlyFixture(ShootTextBody, ShootText);
        ShootTextBody.setPosition(new Vec2(9f, 37));
        ShootTextBody.addImage(ShootingMessage1);

        Shape ShootText2 = new BoxShape(6, 0.5f);
        StaticBody ShootTextBody2 = new StaticBody(this, ShootText2);
        ShootTextBody2.getFixtureList().removeFirst().destroy();
        GhostlyFixture ShootFixture2 = new GhostlyFixture(ShootTextBody2, ShootText2);
        ShootTextBody2.setPosition(new Vec2(8f, 38));
        ShootTextBody2.addImage(ShootingMessage2);

        //Spawn Enemy
        Shape shape6 = new BoxShape(6, 0.5f);
        StaticBody platform5 = new StaticBody(this, shape6);
        platform5.setPosition(new Vec2(10f, 42));
        platform5.addImage(Mountain);
        Enemy Enemy = new Zombie(this, platform5.getPosition(), getCharacter());
        EnemyDamage enemyDamage = new EnemyDamage(Enemy, getCharacter());
        Enemy.addCollisionListener(enemyDamage);

        //Spawn Portal
        Shape shape7 = new BoxShape(6, 0.5f);
        StaticBody platform6 = new StaticBody(this, shape7);
        platform6.setPosition(new Vec2(-8f, 48));
        platform6.addImage(Mountain);
        Portal portal = new Portal(this, platform6.getPosition());
    }
}
