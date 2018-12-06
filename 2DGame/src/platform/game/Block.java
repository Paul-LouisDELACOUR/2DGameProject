package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Vector;
import platform.util.Output;

/**
 * Simple solid actor that does nothing.
 */
public class Block extends Actor {
	
	private Box rectangle;
	private Sprite sprite;
	
	private Vector position;
	
	public Block(Box rectangle, Sprite sprite){
		this.rectangle = rectangle;
		this.sprite = sprite;
		
		this.position = rectangle.getCenter();
	}
   
	public boolean isSolid(){
		return true;
	}
	
	public Box getBox(){
		return rectangle;
	}
	
	public int getPriority(){
		return 0;
	}
	
	public void draw(Input input , Output output){
		if (sprite == null){
			super.draw(input, output);
		} else {
			
		 output.drawSprite(sprite, getBox());
		}
		
	}
	
	public Sprite getSprite (){
		return sprite;
	}
	
	
	
	
	
}
