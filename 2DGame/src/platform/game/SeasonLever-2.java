package platform.game;

import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class SeasonLever extends Lever {
	
	private StaticSignal currentSeason = new StaticSignal();
	private boolean wasActive;
	private boolean isActive;
	
	public SeasonLever(Vector position,StaticSignal season) {
		super(position);
		this.currentSeason=season;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return super.isActive();
	}
	
	@Override
	public void preUpdate(Input input) {
		
		super.preUpdate(input);
		wasActive = isActive();
	}
	@Override
	public void postUpdate(Input input) {
		// TODO Auto-generated method stub
		super.postUpdate(input);
		isActive = isActive();
	
		if(wasActive && ! isActive)  {
			currentSeason.SetIsActive(false);
			
		}
		else if ( ! wasActive && isActive){
			currentSeason.SetIsActive(true);
			
		}
	}
	@Override
	public void draw(Input input, Output output) {
		Sprite sprite;
		Sprite season;
		if (isActive()) {
			season = getSprite("iceBlockHalfAlt");
			sprite = getSprite("lever.left");
			
		} else {
			season = getSprite("sun");
			sprite = getSprite("lever.right");
			
		}
		if (sprite == null) {
			super.draw(input, output);

		} else {
			output.drawSprite(sprite, getBox());
			output.drawSprite(season, getBox().add(new Vector(0,getBox().getHeight())));

		}

	}
	

}
