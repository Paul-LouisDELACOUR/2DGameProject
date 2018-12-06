package platform.game.level;

import platform.game.BackroundLevelTwo;
import platform.game.BlockDestructible;
import platform.game.Color;
import platform.game.Door;
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
import platform.game.Monstre;
import platform.game.MonstreOverlay;
import platform.game.Mover;
import platform.game.Overlay;
import platform.game.Player;
import platform.game.SeasonLever;
import platform.game.Snail;
import platform.game.SnowBlock;
import platform.game.SnowFlake;
import platform.game.SpikeIce;
import platform.game.StaticSignal;
import platform.game.TimedSignal;
import platform.game.Verglas;
import platform.game.World;
import platform.util.Box;
import platform.util.Vector;

public class LevelTwo extends Level {
	
	private final static Vector startingPosition = new Vector(-10,-10);
	
	public LevelTwo() {
		// TODO Auto-generated constructor stub
	}
    @Override
    public void register(World world) {
        super.register(world);
        
        // Register a new instance, to restart level automatically
        world.setNextLevel(this);
        
        //world
        world.register(new Limits(new Box(Vector.ZERO, BackroundLevelTwo.backroundSize, BackroundLevelTwo.backroundSize)));
        //player initial location on plateform 1
        Player player = new Player( startingPosition, Vector.ZERO,Player.maxHp);// starting position(-10,-10)( 0 , 0)
        world.register( player );
        world.register(new Overlay ( player ));
        

      //Background 

	    StaticSignal winter = new StaticSignal(false);
        world.register(new BackroundLevelTwo());
        world.register(new Generator(new SnowFlake(Vector.ZERO, SnowFlake.standardSnowFlakeSize,winter,player), SnowFlake.standardSnowflakeApparition));
        
        //plateform 1
     
        SeasonLever winter1 = new SeasonLever(new Vector(-13 , -10),winter);
        
        
        //plateform 2
        MeUtil.createSeasonBlock(-10, -11, 8, 1.5, world);

        world.register( new SnowBlock(new Box(new Vector(-7, -9.5),2,1.5), world.getLoader().getSprite("igloo"), winter,3,19));
        world.register(winter1);
        
        //plateform 3
        MeUtil.createSeasonBlock(-3, -1.5, 6, 1, world);
       
        Key redKey = new Key(new Vector(-5, 1),Color.red) ; //at plateform 3
        Key blueKey = new Key(new Vector(24, 2),Color.blue) ; //at plateform 7
        Key greenKey1 = new Key(new Vector(15, 6),Color.green) ; //at plateform 3
        world.register(redKey);
        world.register(blueKey);
        world.register(greenKey1);
        
        Door redDoor = new Door(new Box(new Vector(-5, -0.5), 1,1), world.getLoader().getSprite("lock.red"), redKey);
        Door blueDoor1 = new Door(new Box(new Vector(-3, -0.5), 1, 1), world.getLoader().getSprite("lock.blue"), blueKey);
        Door blueDoor2 = new Door(new Box(new Vector(-4, -0.5), 1, 1), world.getLoader().getSprite("lock.blue"), blueKey);
        Door greenDoor1 = new Door(new Box(new Vector(-4, -0.5), 1, 1), world.getLoader().getSprite("lock.green"), greenKey1);
        world.register(redDoor);
        world.register(blueDoor1);
        world.register(greenDoor1);
        world.register(blueDoor2);
        
        FixedLever fixedlever1 = new FixedLever( null); // the mover is initialized later on
        Mover mover1 = new Mover( new Box(new Vector(-4, -1), 3, 0.5) ,new Vector(-4, -1),new Vector(-4, 9), world.getLoader().getSprite("stone.5"), fixedlever1, 0.5);
        world.register(mover1);
        world.register(fixedlever1);
        fixedlever1.setActor(mover1); 
        
        
        
        //plateform 4 => 4 little blocks with ice
        MeUtil.createSeasonBlock(5, -2, 1, 1, world);
        MeUtil.createSeasonBlock(9, -2,1, 1, world);
        MeUtil.createSeasonBlock(13, -2, 1, 1, world);
        MeUtil.createSeasonBlock(17, -2, 1, 1, world);

        
        world.register(new Verglas(new Box(new Vector(5, -1.5),1,1),world.getLoader().getSprite("grass.center"), world.getLoader().getSprite("iceBlockHalfAlt")));
        world.register(new Verglas(new Box(new Vector(9, -1.5),1,1),world.getLoader().getSprite("grass.center"), world.getLoader().getSprite("iceBlockHalfAlt")));
        world.register(new Verglas(new Box(new Vector(13, -1.5),1,1),world.getLoader().getSprite("grass.center"), world.getLoader().getSprite("iceBlockHalfAlt")));
        world.register(new Verglas(new Box(new Vector(17, -1.5),1,1),world.getLoader().getSprite("grass.center"), world.getLoader().getSprite("iceBlockHalfAlt")));
        
        //plateform 7 => with hearts
        
        MeUtil.createSeasonBlock(24, -1.5, 6, 1, world);
        
        world.register(new Heart( new Vector(24, 0.5),Double.MAX_VALUE));
        world.register(new Fly(new Vector(24, 3), Vector.ZERO, 0.5,player));
        //plateform 5
        TimedSignal timedSignal = new TimedSignal(2);
        world.register(timedSignal);
        world.register(new Mover( new Box(new Vector(32, -1.5), 3, 0.5) ,new Vector(32,-1.5),new Vector(32,5), world.getLoader().getSprite("stone.5"), timedSignal,0.5));
        
        //plateform 6
        FixedLever fixedlever2 = new FixedLever( null); // the mover is initialized later on
        Mover mover2 = new Mover( new Box(new Vector(29, 4), 3, 0.5) ,new Vector(29, 4),new Vector(0, 4), world.getLoader().getSprite("stone.5"), fixedlever2, 0.095);
        world.register(mover2);
        world.register(fixedlever2);
        fixedlever2.setActor(mover2); 
        
        
     
        //plateform 8 => roof with spikes and floor of monster layout
        
        MeUtil.createSeasonBlock(21.5, 12, 18, 1, world);
     
        world.register(new Generator((Generate)new SpikeIce(new Vector(11, 11), new Vector( 0, -10), 1), 3.1));
        world.register(new Generator((Generate)new SpikeIce(new Vector(14, 11), new Vector( 0, -10), 1), 3.2));
        world.register(new Generator((Generate)new SpikeIce(new Vector(17, 11), new Vector( 0, -10), 1), 3.3));
        world.register(new Generator((Generate)new SpikeIce(new Vector(20, 11), new Vector( 0, -10), 1), 2.8));
        world.register(new Generator((Generate)new SpikeIce(new Vector(23, 11), new Vector( 0, -10), 1), 2.9));
        world.register(new Generator((Generate)new SpikeIce(new Vector(26, 11), new Vector( 0, -10), 1), 3));
        
        //plateform 9 => after the snowblock
        
        Door greenDoor2 = new Door(new Box(new Vector(-7, 12), 1, 1), world.getLoader().getSprite("lock.green"), greenKey1);
//        world.register(greenDoor2);
        Key greenKey2 = new Key(new Vector(-7, 12),Color.green) ; 
        world.register(greenKey2);
        world.register(new Mover( new Box(new Vector(-4, 11.80), 1, 0.5) ,new Vector(-4, 11.80),new Vector(-10, 11.80), world.getLoader().getSprite("stone.5"), greenKey2,0.2));
        world.register(new Jumper(new Vector(-4, 11)));
        MeUtil.createSeasonBlock(-4, 10.75, 1.5, 0.5, world);
        //plateform 10 => after the jumper
        
       
        MeUtil.createSeasonBlock(5, 15, 6, 1, world);
       
        world.register(new BlockDestructible(new Box(new Vector(5, 16), 1, 1), world.getLoader().getSprite("box.warning.color")));
        world.register(new BlockDestructible(new Box(new Vector(5, 17), 1, 1), world.getLoader().getSprite("box.warning.color")));
        world.register(new BlockDestructible(new Box(new Vector(6, 16), 1, 1), world.getLoader().getSprite("box.warning.color")));
        world.register(new BlockDestructible(new Box(new Vector(6, 17), 1, 1), world.getLoader().getSprite("box.warning.color")));
        
        //plateform 11 => on the big plateform with ice
        
        Lever lever3 = new Lever(new Vector(21.5, 13));
        world.register(new Mover( new Box(new Vector(21.5, 35), 8, 0.5) ,new Vector(21.5, 35),new Vector(-30, 35), world.getLoader().getSprite("stone.5"), lever3,0.1));
       
        world.register(new Snail(new Vector(17,  14), Vector.ZERO, Snail.standardSnailSize, player));
        world.register(new Snail(new Vector(18,  37), Vector.ZERO, Snail.standardSnailSize, player));
        world.register(new Snail(new Vector(19,  37), Vector.ZERO, Snail.standardSnailSize, player));
        world.register(new Snail(new Vector(23,  37), Vector.ZERO, Snail.standardSnailSize, player));
        world.register(new Snail(new Vector(24,  37), Vector.ZERO, Snail.standardSnailSize, player));
        world.register(new Snail(new Vector(25,  37), Vector.ZERO, Snail.standardSnailSize, player));
        
       
        
        world.register(lever3);
        world.register(new Snail(new Vector(28, 13), Vector.ZERO, Snail.standardSnailSize*2, player,Snail.standardFireballFrequency,Monstre.standardMonstreMaxHp*5));
        world.register(new Fly(new Vector(27, 15.5), Vector.ZERO, Fly.standardFlySize*2,player,Fly.standardFireballFrequency*0.75,Monstre.standardMonstreMaxHp*2));
        
        Fly bossFly = new Fly(new Vector(27, 14), Vector.ZERO, Fly.standardFlySize*2,player,Fly.standardFireballFrequency*0.75,Monstre.standardMonstreMaxHp*2);
        world.register(bossFly);
        world.register(new MonstreOverlay(bossFly));
        world.register(new Exit(new Vector(28, 13), new LevelOne(), lever3));
        
    }
    

}
