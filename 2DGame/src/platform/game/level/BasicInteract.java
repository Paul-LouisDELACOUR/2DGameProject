package platform.game.level;

import platform.game.Block;
import platform.game.BlockDestructible;
import platform.game.Color;
import platform.game.CrumbleBlock;
import platform.game.Door;
import platform.game.Exit;
import platform.game.FixedLever;
import platform.game.Heart;
import platform.game.Jumper;
import platform.game.Key;
import platform.game.Lever;
import platform.game.Limits;
import platform.game.Mover;
import platform.game.Overlay;
import platform.game.Player;
import platform.game.PlayerPL;
import platform.game.Signal;
import platform.game.Spike;
import platform.game.Torch;
import platform.game.World;
import platform.util.Box;
import platform.util.Vector;

public class BasicInteract extends Level {

	public void register(World world) {
        super.register(world);
        
        
        world.register(new Block(new Box(new Vector(2, 0), 4, 2), world.getLoader().getSprite("stone.broken.2")));
        world.register(new Block(new Box(new Vector(-1, 1), 2, 4), world.getLoader().getSprite("stone.broken.8")));
       
		Player player = new Player (new Vector (2.5,3), new Vector (0,0), PlayerPL.basicHP);
		world.register (player); 
		world.register(new Jumper(new Vector (0.5,1)));
		world.register(new Limits(new Box(Vector.ZERO, 40, 30)));
		world.register(new Overlay(player));
		world.register(new Heart(new Vector (-0.50,3.4), 10));
		world.register(new Spike(new Vector(1.5,1.25), 1,0.5));
		world.register(new Torch(new Vector(3.5,1.75), false));
		
		Key redKey = new Key(new Vector (1.5,1.25), Color.red);
		
		Key yellowKey = new Key(new Vector (5.5,2), Color.yellow);
		world.register(redKey);
		world.register(new Door(new Box( new Vector(-1.5, 4), 2,2) , 
					world.getLoader().getSprite("lock.red"), (Signal)redKey));
		
		world.register(yellowKey);
		world.register((new Exit(new Vector(-1.5, 3.5), new LevelOne(), yellowKey))) ;
		
		world.register(new Block( new Box (new Vector (6,0), 2, 2) , getSprite("box.single") ));

	}
    

}


