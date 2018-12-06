package platform.game;

import platform.util.Box;
import platform.util.Vector;

public class BackroundLevelOne extends Actor {
	
	public final static double backroundSize = 100; 
	
	public BackroundLevelOne() {
		// TODO Auto-generated constructor stub
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
        world.register(new BackroundElement(new Box(new Vector(-2, 16), 2, 2), "sun",2));
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
        
        world.register(new BackroundElement(new Box(new Vector(-5, 1), 2, 1.5), "cloud1"));
        world.register(new BackroundElement(new Box(new Vector(-1, 0), 3, 1.5), "cloud6"));
        world.register(new BackroundElement(new Box(new Vector(-3, -8),3, 1.5), "cloud4"));
        world.register(new BackroundElement(new Box(new Vector(-7, -9), 3, 1.5), "cloud6"));
        world.register(new BackroundElement(new Box(new Vector(-12, -11),3, 1.5), "cloud4"));
        world.register(new BackroundElement(new Box(new Vector(-15, -21), 3, 1.5), "cloud6"));
        world.register(new BackroundElement(new Box(new Vector(-6, -9),3, 1.5), "cloud4"));
        world.register(new BackroundElement(new Box(new Vector(-10, -7), 2, 1.5), "cloud1"));
        world.register(new BackroundElement(new Box(new Vector(-10, -8), 2, 1.5), "cloud1"));
        world.register(new BackroundElement(new Box(new Vector(-12, -10), 2, 1.5), "cloud1"));
        world.register(new BackroundElement(new Box(new Vector(-20, -9), 3, 1.5), "cloud6"));
        world.register(new BackroundElement(new Box(new Vector(-23, -11),3, 1.5), "cloud4"));
        world.register(new BackroundElement(new Box(new Vector(-28, -21), 3, 1.5), "cloud6"));
        world.register(new BackroundElement(new Box(new Vector(18, 25),3, 1.5), "cloud4"));
        world.register(new BackroundElement(new Box(new Vector(15, 7), 2, 1.5), "cloud1"));
        
        world.register(new SeasonBackroundElement(new Box(new Vector(-30, 10),1, 3), "tree3",-1,"treedead3"));
        world.register(new SeasonBackroundElement(new Box(new Vector(-33.3, 9.28),0.5, 0.5), "flower1",-1,"snowball2"));
        
        world.register(new SeasonBackroundElement(new Box(new Vector(-22.8,10),1, 3), "tree2",-1,"treedead2"));

        world.register(new SeasonBackroundElement(new Box(new Vector(0.07,-8.95),0.5, 0.5), "flower2",-1,"snowball2"));
        
       
    }
	@Override
	public Box getBox() {
		// TODO Auto-generated method stub
		return new Box(new Vector(0, 0), Double.MAX_VALUE, Double.MAX_VALUE);
	}

}
