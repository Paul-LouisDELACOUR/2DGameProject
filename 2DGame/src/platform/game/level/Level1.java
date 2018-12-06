package platform.game.level;

import platform.game.PlayerPL;
import platform.game.World;
import platform.util.Vector;

public class Level1 extends Level {

	public Level1() {
		
	}
	
	public void register(World world) {
        super.register(world);
  
		world.register ( new PlayerPL (new Vector (2.5,0), new Vector (0,0), PlayerPL.basicHP)); 
	}    

}
