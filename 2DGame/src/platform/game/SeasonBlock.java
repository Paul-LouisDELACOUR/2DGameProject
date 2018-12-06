package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
/**
 * 
 * Block qui change de dessin lorsqu'on passe en hiver ou en ete
 *
 */
public class SeasonBlock extends Block implements Season{

	private StaticSignal winter = new StaticSignal();
	private Sprite winterSprite;
	
	
	public SeasonBlock(Box box, Sprite summerSprite,Sprite winterSprite) {
		super(box, summerSprite);
		this.winterSprite=winterSprite;
		// TODO Auto-generated constructor stub
	}
	public void draw(Input input, Output output) {

		if (winter.isActive()) {
			output.drawSprite(winterSprite, super.getBox());
		} else {
			super.draw(input, output);
		}

	}

}
