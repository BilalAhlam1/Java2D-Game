package game;

import city.cs.engine.BodyImage;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

public class AntiGravity extends PowerUps{
    private static final BodyImage AntiGravityImage = new BodyImage("data/Loot/Seperate/tile281.png", 4f);
    public AntiGravity(World w, StaticBody staticBody, Character character) {
        super(w, staticBody, character);
        addImage(AntiGravityImage);
    }

    public void reset(){
        PowerUps powerUps = new AntiGravity(getWorld(), getStaticBody(), getCharacter());
    }
}
