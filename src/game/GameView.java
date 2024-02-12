package game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import java.awt.*;

public class GameView extends UserView {
    private Image background;
    public GameView(World w, int width, int height) {
        super(w, width, height);
        //background = new ImageIcon().getImage();
    }
}
