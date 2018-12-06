package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
/**
 * 
 * Backround qui change de dessin lorsqu'on passe en hiver ou en ete
 *
 */
public class SeasonBackroundElement extends BackroundElement implements Season{
	
	private StaticSignal currentSeason = new StaticSignal();
	private String winterSpriteName;

	public SeasonBackroundElement(Box box, String summerSpriteName, String winterSpriteName) {
		super(box, summerSpriteName);
		this.winterSpriteName=winterSpriteName;
	}

	public SeasonBackroundElement(Box box, String summerSpriteName, int priority, String winterSpriteName) {
		super(box, summerSpriteName, priority);
		this.winterSpriteName=winterSpriteName;
		
	}

	public void draw(Input input, Output output) {

		if (currentSeason.isActive()) {
			output.drawSprite(getSprite(winterSpriteName), super.getBox());
		} else {
			super.draw(input, output);
		}

	}
}
