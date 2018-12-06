package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;

public class Door extends Block implements Signal {
	
	private Signal signal;
	
	public Door(Box rectangle, Sprite sprite, Signal signal) {
		super(rectangle, sprite);
		this.signal=signal;
	}

	public boolean isActive() {
		
		return signal.isActive();
	}
	
	public boolean isSolid(){
		if ( ! isActive() ){
			return true;
		} else {
			return false;
		}
	}
	
	public Box getBox(){
		if (isSolid() ){
			return super.getBox();
		} else {
			return null;
		}
	}
	
	public void draw(Input input , Output output){
		if (!isActive()){	
		super.draw(input, output);
		}
	} 

}
