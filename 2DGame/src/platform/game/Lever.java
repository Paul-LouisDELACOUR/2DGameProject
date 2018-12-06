package platform.game;

import platform.game.Actor;
import platform.game.Color;
import platform.game.Damage;
import platform.game.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Lever extends Actor implements Signal {

	private boolean value = false;
	private Vector position;
	public final static double SIZE = 0.5;
	private double duration =Double.MAX_VALUE;;
	private double time =0;
	private double size=SIZE;
	
	public Lever(Vector position) {
		this.position = position;
	}
	public Lever() {}
	
	public Lever(Vector position, double size, double duration) {
		this.position = position;
		this.size=size;
		this.duration = duration;
	}
	
	public Lever(Vector position, double duration){
		this.position=position;
		this.duration=duration;
	}
	
	public boolean isSolid(){
		return false;
	}

	
	public boolean isActive() {
		if (value){
			return true;
		} else{
			return false;
		}
	}

	@Override
	public int getPriority() {
		return 0;
	}
	
	public void draw(Input input, Output output){
		
		Sprite leverLeft = getSprite ("lever.left");
		Sprite leverRight = getSprite ("lever.Right");
	
		if (isActive()){
			output.drawSprite(leverRight , getBox() );
		}else if (!isActive()){
			output.drawSprite(leverLeft , getBox() );
		}else {
			super.draw(input, output);
		}
		
	}
	
	public boolean hurt(Actor instigator, Damage type, double
			amount, Vector location) { 
		super.hurt(instigator, type, amount, location);
		switch (type) {
			case ACTIVATION:
				value = !value;
				
				if (isActive()){
					time=duration;
				}
				return true;
		
			default :
				return super.hurt(instigator, type, amount, location);
			} 
	}
	
	public void interact(Actor actor){
		
		super.interact(actor);
	}
	
	public Box getBox(){
		return new Box (this.position, size, size);
	}
	
	public void update(Input input) {
		super.update(input) ;	
		
		if(time>0){
			time -= input.getDeltaTime();
		}else{
			value=false;
		}	
			
	}

}
