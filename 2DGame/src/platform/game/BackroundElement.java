package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;

public class BackroundElement extends Actor {
	
	private Box box;
	private String spriteName;
	private int priority = -1;
	
	public BackroundElement(Box box, String spriteName) {
		this.box=box;
		this.spriteName=spriteName;
	}
	public BackroundElement(Box box, String spriteName,int priority) {
		this.box=box;
		this.spriteName=spriteName;
		this.priority=priority;
	}
	public void draw(Input input, Output output) {
		
		Sprite sprite = getSprite(spriteName);
		
		if(sprite == null){
			super.draw(input,output);

		}
		else{
			output.drawSprite(sprite, box);
		}
	}
	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return priority;
	}
	@Override
	public Box getBox() {
		// TODO Auto-generated method stub
		return box;
	}

}
