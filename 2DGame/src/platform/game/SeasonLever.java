package platform.game;

import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;
/**
 * 
 * Levier qui interagit avec la saison du jeu.
 *
 */
public class SeasonLever extends Lever implements Season {
	
	private StaticSignal currentSeason = new StaticSignal();
	
	public SeasonLever(Vector position,StaticSignal season) {
		super(position);
		this.currentSeason=season;
	
	}

	@Override
	public boolean isActive() {
		return currentSeason.isActive();
	}
	
	public boolean hurt(Actor instigator, Damage type, double
			amount, Vector location) { 
		super.hurt(instigator, type, amount, location);
		switch (type) {
			case ACTIVATION:
				currentSeason.SetIsActive(!currentSeason.isActive()); //on modifie la saison a son oppose
		
			default :
				return super.hurt(instigator, type, amount, location);
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
