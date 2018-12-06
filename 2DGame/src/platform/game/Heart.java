package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;
/**
 * 
 * Permet de construire un Heart pour augmenter la vie du Player.
 * Cet acteur réapparait tout les "timeOut".
 *
 */

public class Heart extends Actor {

	private double timeOut = 10;
	private Vector position;
	private double cooldown;
	private final double SIZE = 0.5;
	private final double baseHeal = 2*(Player.maxHp/5); // gives 2 hearts
	
	public Heart(Vector position) {
		
		this.position=position;
	}
	public Heart( Vector position,double timeOut) {
		
		this.timeOut=timeOut;
		this.position=position;
	}

	@Override
	public int getPriority() {
		
		return 700;
	}
	@Override
	public void draw(Input input, Output output) {
		Sprite sprite;
		
		if (cooldown < 0) {
			sprite = getSprite("heart.full");
		}

		else {
			sprite = null;
		}
		
		if (sprite == null) {
			super.draw(input, output);

		} 
		else {
			output.drawSprite(sprite, getBox());

		}

	}
	
	@Override
	public void update(Input input) {
		
		super.update(input);
		
		cooldown-=input.getDeltaTime();
		
	}
	@Override
	public Box getBox() {
		
		return new Box(position, SIZE ,SIZE);
	}
	@Override
	public void interact(Actor other) {
		super.interact(other);
		
		if ( cooldown < 0 && getBox().isColliding(other.getBox())) {
			
			if (other.hurt(this, Damage.HEAL, baseHeal, position)){
				cooldown = timeOut;
				
			}
				
		}
	}
}
