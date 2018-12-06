package platform.game;

import platform.util.Box;
import platform.util.Vector;

public class BackroundLevelTwo extends Actor {

	public final static double backroundSize = 100; 
	public BackroundLevelTwo() {
		 	
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
        world.register(new BackroundElement(new Box(new Vector(-2, 12), 2, 2), "sun",2));
        world.register(new BackroundElement(new Box(new Vector(1, 6), 3, 1.5), "cloud6"));
        world.register(new BackroundElement(new Box(new Vector(3, 8),3, 1.5), "cloud4"));
        world.register(new BackroundElement(new Box(new Vector(7, 9), 3, 1.5), "cloud6"));
        world.register(new BackroundElement(new Box(new Vector(12, 23),3, 1.5), "cloud4"));
        world.register(new BackroundElement(new Box(new Vector(15, 21), 3, 1.5), "cloud6"));
        world.register(new BackroundElement(new Box(new Vector(-6, 9),3, 1.5), "cloud4"));
        world.register(new BackroundElement(new Box(new Vector(10, 7), 2, 1.5), "cloud1"));
        world.register(new BackroundElement(new Box(new Vector(-10, 8), 2, 1.5), "cloud1"));
        world.register(new BackroundElement(new Box(new Vector(-12, 20), 2, 1.5), "cloud1"));
        world.register(new BackroundElement(new Box(new Vector(20, 9), 3, 1.5), "cloud6"));
        world.register(new BackroundElement(new Box(new Vector(23, 18),3, 1.5), "cloud4"));
        world.register(new BackroundElement(new Box(new Vector(28, 21), 3, 1.5), "cloud6"));
        world.register(new BackroundElement(new Box(new Vector(18, 25),3, 1.5), "cloud4"));
        world.register(new BackroundElement(new Box(new Vector(15, 7), 2, 1.5), "cloud1"));
        
        world.register(new SeasonBackroundElement(new Box(new Vector(-11, -8.8),1, 3), "tree3",-1,"treedead3"));
        world.register(new SeasonBackroundElement(new Box(new Vector(-9, -10),0.5, 0.5), "flower1",-1,"snowball2"));
        
        world.register(new SeasonBackroundElement(new Box(new Vector(-1, 0.5),1, 3), "tree2",-1,"treedead2"));
        world.register(new SeasonBackroundElement(new Box(new Vector(26,0.4),1, 3), "tree1",-1,"treedead1"));
        world.register(new SeasonBackroundElement(new Box(new Vector(3.3,15.5),1, 3), "tree3",-1,"treedead3"));
        world.register(new SeasonBackroundElement(new Box(new Vector(-17.5, 12.5),0.5, 0.5), "flower2",-1,"snowball2"));
        
       
    }
	@Override
	public Box getBox() {
		// TODO Auto-generated method stub
		return new Box(new Vector(0, 0), Double.MAX_VALUE, Double.MAX_VALUE);
	}
	

}
