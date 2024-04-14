package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Initialise Scene of type StepListener
 * <p>Provides Camera Following Characters Position</p>
 */
public class Scene implements StepListener {
    private final UserView view;
    //UserView
    private Character Character;
    //Character

    /**
     * Constructor Initialise Scene
     * <p>updates view and character</p>
     */
    public Scene(Character Character, UserView view) {
        this.view = view;
        this.Character = Character;
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    /**
     * Tracks Character
     * <p>Sets USerView To The Character Position, Checks If The Character Falls Below The Map And Tracks Fall Damage</p>
     */
    @Override
    public void postStep(StepEvent stepEvent) {
        // Game view follows the character
        view.setView(new Vec2(0, Character.getPosition().y), 20);

        //resets the character if it falls below the levels
        if (Character.getPosition().y < -20 ||Character.getHealthCount() <= 0) {
            Character.setLinearVelocity(new Vec2(0, 3));
            Character.reset();
        }

        //Fall Damage
        if (Character.getLinearVelocity().y < -20 && Character.getLinearVelocity().x > - 30){
            if (!Character.getBodiesInContact().isEmpty()){
                Character.reduceHealth(10);
            }
        } else if (Character.getLinearVelocity().y < -30){
            if (!Character.getBodiesInContact().isEmpty()){
                Character.reduceHealth(20);
            }
        }
    }

    /**
     * Setter updating character
     * @param character Current Character
     */
    public void updateCharacter(Character character){
        this.Character = character;
    }
}
