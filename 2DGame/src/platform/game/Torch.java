package platform.game;

import java.awt.event.KeyEvent;

import platform.game.Actor;
import platform.game.Damage;
import platform.game.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Torch extends Actor implements Signal {

	private boolean lit=false;
	private Vector position;
	private double variation=0.0;
	
	
	public Torch(Vector position, boolean lit) { // Centre de la torche comme position
		this.position = position;
		this.lit=lit;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 34;
	}

	public boolean hurt(Actor instigator, Damage type, double
			amount, Vector location) { 
		super.hurt(instigator, type, amount, location);
		switch (type) {
			case FIRE:
				lit=true;
			return true;
			
			case AIR :
				lit=false;
				return true;
			default :
				return super.hurt(instigator, type, amount, location);
			} 
	}	
	
	
	public void draw(Input input, Output output){
		
		Sprite torchUnlit = getSprite ("torch");
	
		String name = "torch.lit.1";
		if (variation < 0.3){
			name = "torch.lit.2"; 
		}
		
		Sprite sprite = getSprite (name);
		
		if (lit){
			output.drawSprite(sprite , getBox() );
			
		} else if (!lit){
			output.drawSprite(torchUnlit , getBox() );
		}else {
			super.draw(input, output);
		}
		
		variation -= input.getDeltaTime(); 
		
		if (variation < 0.0)
			variation = 0.6;
	}
	
	public Box getBox(){
		return new Box(this.position, 0.8, 0.8);
	}
	
	@Override
	public boolean isActive() {
		return lit ;
	}
	
}
