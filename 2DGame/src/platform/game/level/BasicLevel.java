package platform.game.level;

import platform.game.Block;
import platform.game.Fireball;
import platform.game.Heart;
import platform.game.Jumper;
import platform.game.Limits;
import platform.game.Overlay;
import platform.game.PlayerPL;
import platform.game.Spike;
import platform.game.Torch;
import platform.game.World;
import platform.util.Box;
import platform.util.Vector;

public class BasicLevel extends Level {
    // UNCOMMENT ME WHEN NEEDED
//    @Override
    public void register(World world) {
        super.register(world);
        
        // Register a new instance, to restart level automatically
        world.setNextLevel(new BasicLevel());
        
        // Create blocks
        
        world.register(new Block(new Box(new Vector(0, 0), 20, 2), world.getLoader().getSprite("stone.broken.2")));
        world.register(new Block(new Box(new Vector(-1.5, 1.5), 1, 1), world.getLoader().getSprite("stone.broken.1")));
        
      
		
		world.register (new Fireball ( new Vector(3,2), new Vector (-3,5) ) );
		PlayerPL player = new PlayerPL (new Vector (2,5), new Vector (0,0), PlayerPL.basicHP);
		world.register (player); 
		world.register(new Jumper(new Vector (2,1)));
		world.register(new Limits(new Box(Vector.ZERO, 40, 30)));
		world.register(new Overlay(player));
		world.register(new Heart(10, new Vector (1,1.5)));
		world.register(new Spike(new Vector(-3,1.5), 1,1));
		world.register(new Torch(new Vector(4,1.5), false));
    }
    
}
