package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.UserView;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Scene implements StepListener {
    private final UserView view;
    private Character Character;

    public Scene(Character Character, UserView view) {
        this.view = view;
        this.Character = Character;
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        // Game view follows the character
        view.setView(new Vec2(0, Character.getPosition().y), 20);

        //resets the character if it falls below the levels
        if (Character.getPosition().y < -20) {
            Character.reset();
        }
    }

    public void updateCharacter(Character character){
        this.Character = character;
    }
}
