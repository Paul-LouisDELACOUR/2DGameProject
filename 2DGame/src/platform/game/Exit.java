package platform.game;

import platform.game.level.BasicInteract;
import platform.game.level.Level;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Exit extends Actor implements Signal {

	private Signal s;
	private Vector position;
	private Level nextLevel;
	private double SIZE=1;
	
	public Exit(Vector position, Level nextLevel, Signal s) {
		this.position=position;
		this.nextLevel=nextLevel;
		this.s=s;
	}
	
	public Exit(Vector position, double SIZE, Level nextLevel, Signal s) {
		this.position=position;
		this.nextLevel=nextLevel;
		this.s=s;
		this.SIZE=SIZE;
	}

	@Override
	public boolean isActive() {
		return s.isActive();
	}
	
	public boolean isSolid(){
		return false;
	}

	@Override
	public int getPriority() {
		return 0;
	}
	

	public void update(Input input) {
		super.update(input);
	}
	
	public void interact(Actor actor){
			
			super.interact(actor);
			
			if ( (isActive())&&(this.getBox().isColliding(actor.getBox())) ){
				if (actor.hurt(actor, Damage.ACTIVATION, 0,position)) {
					getWorld().unregister(this);
					setNextLevel(nextLevel);
					getWorld().nextLevel();
					
				}	
			}		
		}
	
	public boolean hurt(Actor instigator, Damage type, double
			amount, Vector location) { 
		super.hurt(instigator, type, amount, location);
		switch (type) {
			case TOUCHES :
				if (s.isActive()){
					setNextLevel(nextLevel);
					getWorld().nextLevel();
				}
				return true;
			default :
				return super.hurt(instigator, type, amount, location);
			} 
	}	
	
	public void draw(Input input, Output output){
		Sprite doorOpen = getSprite ("door.open");
		Sprite doorClosed = getSprite ("door.closed");	
		
		if ( (doorOpen == null)|| (doorClosed == null) ){
			super.draw(input, output);
		
		} else if (isActive()){		
			output.drawSprite(doorOpen, getBox()) ;
		
		} else {
			output.drawSprite(doorClosed , getBox()) ;
		}
	}
	
	public Box getBox(){
			return new Box (position, SIZE, SIZE);
	}
	
	
	
	public void setNextLevel(Level nextLevel){
		getWorld().setNextLevel(nextLevel);
	}
	

}
