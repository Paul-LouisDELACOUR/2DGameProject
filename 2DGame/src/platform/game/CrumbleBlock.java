package platform.game;

import platform.game.Actor;
import platform.game.Block;
import platform.game.Damage;
import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Vector;

/**
 * Création de Blocks qui sont entrainés par la gravité dès que le Player les a touché.
 *
 */
public class CrumbleBlock extends Block {

	private Vector velocity  ;
	private boolean canFall;
	private double width;
	private double height;
	private Vector position;
	private double timer =0.75;
	
	public CrumbleBlock(Box rectangle, Sprite sprite) {
		super(rectangle, sprite);
		this.velocity = new Vector (0,0);
		this.width = rectangle.getWidth();
		this.height = rectangle.getHeight();
		this.position = super.getBox().getCenter();
	}

	public int getPriority() {
		return 0;
	}
	
	public Box getBox(){
		
	return new Box(position, width, height);
		//return super.getBox().sub(getPosition());
	}

	/**
	 * Dès que le player est rentré en contact avec l'objet, celui-ci a la possibilité de 
	 * tomber (canFall=true)
	 */
	public boolean hurt(Actor instigator, Damage type, double
			amount, Vector location) { 
		super.hurt(instigator, type, amount, location);
		switch (type) {
			case TOUCHES :
			canFall =true;
				return true;
			default :
				return super.hurt(instigator, type, amount, location);
		}
	}

	/**
	 * À partir du moment où le Block peut tomber, on prend en compte un delai (timer) avant qu'il ne 
	 * soit attiré par la gravité.
	 * Dès que le timer est nul, l'objet tombe donc normalement.
	 */
	public void update (Input input){
		
		Vector acceleration = new Vector(0.0, -8) ;
		
		if  (canFall){
			timer-=input.getDeltaTime();
			if (timer<=0){
				double delta = input.getDeltaTime() ;
				velocity = velocity.add(acceleration.mul(delta));
				this.position = position.add(velocity.mul(delta));
				delta += input.getDeltaTime();
			}
		}
		
		
	}
}
