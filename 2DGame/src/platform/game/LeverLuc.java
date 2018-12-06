package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class LeverLuc extends Actor implements Signal{

	public static final double SIZE = 0.5;
	private boolean activated;
	private Vector position;
	private Timer timer;
	private double duration;
	
	public LeverLuc(Vector position) {
		// TODO Auto-generated constructor stub
		this.position=position;
		timer = new Timer();//doesn't have a timer : is immortal
	}
	public LeverLuc(Vector position,double duration) {
		// TODO Auto-generated constructor stub
		this.position=position;
		this.duration=duration;
		timer = new Timer(Double.MAX_VALUE);
	}
	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void draw(Input input, Output output) {
		Sprite sprite;
		if (activated) {
			sprite = getSprite("lever.left");
		} else {
			sprite = getSprite("lever.right");
		}
		if (sprite == null) {
			super.draw(input, output);

		} else {
			output.drawSprite(sprite, getBox());

		}

	}
	@Override
	public void update(Input input) {
		// TODO Auto-generated method stub
		super.update(input);
		if( timer.timeTickedAway(input)){
			activated=false;
		}
	}
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {

		switch (type) {
			
		case ACTIVATION : 
			activated = !activated;
			if( ! timer.isImmortal()){
				timer = new Timer(duration);
			}

			
		default:
			
			return super.hurt(instigator, type, amount, location);
		}
	}
	
	
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return activated;
	}
	@Override
	public Box getBox() {
		// TODO Auto-generated method stub
		return new Box(position,SIZE,SIZE);
	}
	

}
