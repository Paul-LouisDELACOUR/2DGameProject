package platform.game.level;

import platform.game.And;
import platform.game.BackroundLevelOne;
import platform.game.Block;
import platform.game.BlockDestructible;
import platform.game.Color;
import platform.game.Constant;
import platform.game.CrumbleBlock;
import platform.game.Exit;
import platform.game.FixedLever;
import platform.game.Fly;
import platform.game.Generate;
import platform.game.Generator;
import platform.game.Heart;
import platform.game.Jumper;
import platform.game.Key;
import platform.game.Lever;
import platform.game.Limits;
import platform.game.MeUtil;
import platform.game.Mover;
import platform.game.Not;
import platform.game.Overlay;
import platform.game.Player;
import platform.game.SeasonLever;
import platform.game.Signal;
import platform.game.SnowBlock;
import platform.game.SnowFlake;
import platform.game.Spike;
import platform.game.StaticSignal;
import platform.game.Torch;
import platform.game.Verglas;
import platform.game.World;
import platform.util.Box;
import platform.util.Vector;

public class LevelOne extends Level {

	 public void register(World world) {
	        super.register(world);

	    
	    
	    //SeasonLever winter1 = new SeasonLever(new Vector(-13 , -10),saison);
	    
	        world.setNextLevel(new LevelOne());
	       
	     StaticSignal winter = new StaticSignal(false);
	  // Player player = new Player (new Vector (-7.5,1), new Vector (0,0), Player.basicHP);
	  //new Vector (25 ,-7)
	     //(-33,9.5)
	     
	   Player player = new Player (new Vector (-7.5,1), new Vector (0,0), Player.maxHp);
	   world.register (player); 
	   
	   world.register(new Generator(new SnowFlake(Vector.ZERO, SnowFlake.standardSnowFlakeSize,winter,player), SnowFlake.standardSnowflakeApparition));
	   world.register(new SnowFlake(Vector.ZERO, SnowFlake.standardSnowFlakeSize , winter, player));
	   
	   world.register(new BackroundLevelOne());
	   
	   world.register(new Limits(new Box(Vector.ZERO, 100, 100)));
	   world.register(new Overlay(player));
	   double sizeA = 3.5;
	   double sizeB= 1.2;
	   world.register(new Block(new Box(new Vector(-7, 0), 3, 2), world.getLoader().getSprite("stone.broken.2")));
       world.register(new CrumbleBlock( new Box (new Vector(-2, 1.5), sizeA , sizeB), world.getLoader().getSprite("box.double") )); // world.getLoader().getSprite("stone.broken.1")));
       world.register(new CrumbleBlock( new Box (new Vector(0.5 , 3), sizeA ,sizeB), world.getLoader().getSprite("box.double") ));
       world.register(new CrumbleBlock( new Box (new Vector(3 , 4.5), sizeA , sizeB), world.getLoader().getSprite("box.double") ));
       
       world.register(new Block( new Box (new Vector(7 , 4.5), sizeA-0.35 , sizeB), world.getLoader().getSprite("stone.2") ));
       Torch torch1 = new Torch(new Vector (8,5.5), false);
       world.register(torch1);
       world.register( new Mover( new Box(new Vector ( 10, 4.5), 4,1), new Vector(10.7, 4.6), new Vector (21, 9), world.getLoader().getSprite("stone.3"), torch1));
      // world.register(new Generator ((Generate)new SpikeIce(new Vector (-7.5,3), new Vector (0,0), 1),1 ));
      // world.register(new Generator ((Generate)new SnowFlake( new Vector (-7,6), new Vector (0,0), 0.4),1.5 ));
       
       double size1=29;
       world.register(new Generator ( (Generate)new Mover( new Box(new Vector(size1,3), 3 , 1.5), new Vector (size1,14), 
    		   new Vector (size1,-9), world.getLoader().getSprite("box.double"), new Constant(), 0.2, 8) , 3 ));
       world.register(new Block( new Box (new Vector (size1,-9), 5, 1.5), world.getLoader().getSprite("stone.broken.5") ));
       
       world.register(new Spike(new Vector(31.2,-8), 0.5,0.5) );
       world.register(new Spike(new Vector(27,-8), 0.5,0.5) );
       
   
       FixedLever fixedLever1 = new FixedLever(null); // the mover is initialized later on
       
       double size2=-9;
       Mover mover1 = new Mover( new Box(new Vector (22,size2), 3, 1.5),new Vector (25,size2) ,new Vector(9,size2), world.getLoader().getSprite("stone.3"), fixedLever1, 0.2);
       world.register(mover1);
       world.register(fixedLever1);
       fixedLever1.setActor(mover1);
       
       SeasonLever seasonLever = new SeasonLever (new Vector (1,-9),winter);
       world.register(seasonLever);
       
       world.register(new Fly(new Vector(20,-5), Vector.ZERO, 0.5, player));
       world.register(new Fly (new Vector (13,-5), Vector.ZERO, 0.5, player));
      
       world.register(new SnowBlock( new Box(new Vector (3.5 , size2), 2,2), world.getLoader().getSprite("igloo"), winter, 4, 20));
       world.register(new Block(new Box (new Vector (0.6,-9.7), 2,1), world.getLoader().getSprite("castle.center") ));
       
       
       world.register(new Block( new Box (new Vector(6,-9.5), 2,2), world.getLoader().getSprite("stone.1") ));
      
       Lever lever2 = new Lever(new Vector(6,-8.3));
       world.register(lever2);
     
       Mover spikeMover1 = new Mover(new Box (new Vector (3,-6.5), 9, 1), new Vector (3,-6.5), new Vector(-20,3), world.getLoader().getSprite("stone.2"), lever2 );
       world.register(spikeMover1);
    
       world.register(new Spike(spikeMover1, spikeMover1.getBox().getWidth(), 1, 20));
       
       world.register(new BlockDestructible(new Box (new Vector(7,10.25),1, 1), world.getLoader().getSprite("box.item") ));
       
      
       Mover spikeMover2 = new Mover(new Box (new Vector (-0.5,-9.5),1,4), new Vector (-1,-9.5), new Vector (0,-20), world.getLoader().getSprite("Stone.7"), lever2);
       world.register(spikeMover2);
       //world.register(new Spike(spikeMover2, 1,spikeMover2.getBox().getHeight(), 20, world.getLoader().getSprite("spikes.left")));
       
       FixedLever fixedLever2 = new FixedLever(1, null); // the mover is initialized later on 
       Mover mover2 = new Mover (new Box(new Vector (6,9), 3 , 1.5), new Vector (6,9), new Vector (7,9),  world.getLoader().getSprite("stone.broken.5"), fixedLever2, 0.8 );
       world.register(mover2);
       world.register(fixedLever2);
       fixedLever2.setActor(mover2);
       
       
       double size4=5;
       Mover mover3 = new Mover (new Box (new Vector (size4,9), 1, 1.5), new Vector (size4,-25), new Vector (size4,9), world.getLoader().getSprite("box.empty"), fixedLever2, 0.8);
       world.register(mover3);
       
       Key redKey= new Key(mover3, Color.red ,0.5);
       world.register(redKey);
       
       
      // world.register(new Block(new Box (new Vector(-1,6), 2,2), world.getLoader().getSprite(name));
      // world.register(new Block(new Box (new Vector(9,9), 3, 1.5), world.getLoader().getSprite("stone.broken.5") ));
      world.register(new Jumper(new Vector(1,10)));
      world.register(new Mover(new Box( new Vector (1,10.8),1.2,0.6), new Vector (1,10.8), new Vector (1,25), world.getLoader().getSprite("stone.broken.5"), redKey)); 
      world.register(new Block(new Box(new Vector (1,9.5), 2.5,1), world.getLoader().getSprite("stone.3") ));
      
      MeUtil.createSeasonBlock(-7, 12, 2,1, world);
      world.register( new Verglas(new Box(new Vector (-7, 12.54), 2, 0.5), world.getLoader().getSprite("grass.center"), world.getLoader().getSprite("iceBlockHalfAlt") )); 
       
      MeUtil.createSeasonBlock(-13, 10, 2,1, world);
      world.register( new Verglas(new Box(new Vector (-13, 10.54), 2, 0.5), world.getLoader().getSprite("grass.center"), world.getLoader().getSprite("iceBlockHalfAlt") ));
       
      MeUtil.createSeasonBlock(-19, 8, 2,1, world);
      world.register( new Verglas(new Box(new Vector (-19, 8.54), 2,0.5), world.getLoader().getSprite("grass.center"), world.getLoader().getSprite("iceBlockHalfAlt") ));
       
      
      MeUtil.createSeasonBlock(-28, 8, 12, 2, world);
      
      MeUtil.createSeasonBlock(-28, 8, 12, 2, world);
     
      
      Lever levier1 = new Lever(new Vector(-28 , 9.29));
      Lever levier2 = new Lever(new Vector(-26, 9.29));
      Lever levier3 = new Lever(new Vector(-24 , 9.29));
     
      world.register(new Heart(new Vector (-29,9.5)));
      world.register(new Fly(new Vector(-30 , 10), Vector.ZERO, 0.5, player) );
      world.register(new Fly(new Vector(-29 , 11), Vector.ZERO, 0.5, player) );
      world.register(new Fly(new Vector(-28 , 12), Vector.ZERO, 0.5, player) );
      world.register(levier1);
      world.register(levier2);
      world.register(levier3);
      
      
      Exit exit = new Exit(new Vector(-32 , 9.5), new LevelTwo(), new And( new And(levier1, levier3) , new Not(levier2) ));
      world.register(exit);
      
	 }
}
