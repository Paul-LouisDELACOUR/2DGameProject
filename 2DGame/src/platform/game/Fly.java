package platform.game;


import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Fly extends Monstre implements Generate {

	public final static double standardFlySize = 0.5;
	public static final double standardFireballFrequency = 3;
	private double fireballFrequency = standardFireballFrequency;
	private final double standardVariation = 0.2;
	private double variation = standardVariation;
	private boolean left = true;
	private boolean canFire = false;
	
	

	public Fly() {
		// TODO Auto-generated constructor stub
	}

	public Fly(Vector position, Vector velocity, double size, Player player,double fireballFrequency) {
		super(position, velocity, size, player);
		this.fireballFrequency = fireballFrequency;
		// TODO Auto-generated constructor stub
	}
	public Fly(Vector position, Vector velocity, double size, Player player,double fireballFrequency,Double health) {
		super(position, velocity, size, player,health);
		this.fireballFrequency = fireballFrequency;
		// TODO Auto-generated constructor stub
	}

	public Fly(Vector position, Vector velocity, double size, Player player) {
		super(position, velocity, size, player);
		// TODO Auto-generated constructor stub
	}
	public Fly(Generate fly){
		super(fly);
		this.fireballFrequency = ((Fly)fly).fireballFrequency;
		
	}
	
	@Override
	public Generate generate(Generate fly) {
		// TODO Auto-generated method stub
		return new Fly(fly);
	}
	public boolean doesActions(){
		return (canFire);
	}
	public void actions() {
		// TODO Auto-generated method stub
		
			getWorld().register(new Fireball(getBox().getCenter(), playerDirection(), this,1.5));
			canFire=false;
			if(playerDirection().getX() < 0){
				left= true;
			}
			else{
				
				left= false;
			}
			
		

	}

	

	@Override
	public void draw(Input input, Output output) {
//		 TODO Auto-generated method stub
		super.draw(input, output);
		Sprite sprite;
		if (left) {

			if (variation <= standardVariation / 2) {
				sprite = getSprite("fly.left.2");
			} else {
				sprite = getSprite("fly.left.1");
			}
		} else {
			if (variation <= standardVariation / 2) {
				sprite = getSprite("fly.right.2");
			} else {
				sprite = getSprite("fly.right.1");
			}

		}

		if (sprite == null) {
			super.draw(input, output);
		} else {
			output.drawSprite(sprite,getBox());

		}
	}

	@Override
	public void update(Input input) {
		// TODO Auto-generated method stub
		super.update(input);
		variation -= input.getDeltaTime();
		fireballFrequency -=input.getDeltaTime();
		if( fireballFrequency < 0 &&  !canFire ){
			fireballFrequency = standardFireballFrequency;
			
			canFire = true;
		}
		
		if (variation < 0) {
			variation = standardVariation;
		}
	}



}
