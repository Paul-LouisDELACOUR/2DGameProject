package platform.game;

import platform.util.Box;
import platform.util.Vector;

public class Backround extends Actor {

	public final static double backroundSize = 100; 
	public Backround() {
		 	
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void register(World world) {
        super.register(world);
        
        // Register a new instance, to restart level automatically
       
        
        world.register(new SeasonBackroundElement(new Box( new Vector(0, 0), backroundSize,  backroundSize), "background",-2 ,"backgroundhiver"));
        world.register(new BackroundElement(new Box(new Vector(-5, 6), 2, 1.5), "cloud1"));
        world.register(new BackroundElement(new Box(new Vector(0, 10), 2, 2), "sun",2));
        world.register(new BackroundElement(new Box(new Vector(1, 6), 3, 1.5), "cloud6"));
        world.register(new BackroundElement(new Box(new Vector(3, 8),3, 1.5), "cloud4"));
        world.register(new SeasonBackroundElement(new Box(new Vector(-1, 0.5),1, 3), "foliagePack_036",-1,"foliagePack_028"));
        
       
    }
	@Override
	public Box getBox() {
		// TODO Auto-generated method stub
		return new Box(new Vector(0, 0), Double.MAX_VALUE, Double.MAX_VALUE);
	}
	

}
