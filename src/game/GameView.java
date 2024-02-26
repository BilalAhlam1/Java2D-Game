package game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {
    private Image background;
    public GameView(World w, int width, int height) {
        super(w, width, height);
        background = new ImageIcon("data/Clouds_GIF.gif").getImage();
        setCentre(Game.getGameWorld().getStudentWalker().getPosition());
    }
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }

}
