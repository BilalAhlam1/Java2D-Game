package game;

import city.cs.engine.BodyImage;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;
/**
 * Creates an instantiation of PowerUps, giving the object for the zero gravity ability
 */
public class AntiGravity extends PowerUps{
    private static final BodyImage AntiGravityImage = new BodyImage("data/Loot/Seperate/tile281.png", 4f);
    //Object Image

    /**
     * Constructor Initialises Object Of Type AntiGravity
     * @param w World Class Where Body Exists
     * @param platform Spawn Position
     */
    public AntiGravity(World w, Vec2 platform) {
        super(w, platform);
        addImage(AntiGravityImage);
    }


    /**
     * Resets the PowerUp object
     * <p>Used to reset the objects state. Used when the ability of the object wears off from the character</p>
     */
    public void reset(){
        PowerUps powerUps = new AntiGravity(getWorld(), getStaticBody());
    }
}
