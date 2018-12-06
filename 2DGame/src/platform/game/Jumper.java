package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

/**
 * Construction d'un acteur qui permet au player de sauter plus haut.
 *Utilisation d'un cooldown pour dessiner dessiner le Jumper.
 *Dès qu'il le jumper est activé, il sera desinné comme étendu pendant durée de cooldown.
 *Puis, quand la durée cooldown est écoulée, le jumper revient à sa position initiale.
 */
public class Jumper extends Actor {

	private double cooldown = 0;
	private Vector position;
	public final static double StandardAirPropultion = 10;
	
	public Jumper(Vector position) {
		if (position == null){
			throw new NullPointerException() ;
		}
		
		this.position = position;
	}

	
	public int getPriority() {
		
		return 660;
	}
	
	public Box getBox(){
		return new Box( new Vector (position.getX()-0.5, position.getY() ), new Vector (position.getX()+0.5, position.getY()+1)) ;	
	}
	
	public void update(Input input) {
		super.update(input);
		cooldown -= input.getDeltaTime(); 
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other);
		if (cooldown <= 0 && getBox().isColliding(other.getBox())) {
			Vector below = new Vector(position.getX(), position.getY() - 1.0);
			
			if (other.hurt(this, Damage.AIR, StandardAirPropultion, below)) 
				cooldown = 0.5;
		} 
	}
	
	public void draw(Input input, Output output){
		Sprite spriteNormal = getSprite ("jumper.normal");
		Sprite spriteExtended = getSprite ("jumper.extended");	
		
		if ( (spriteNormal == null)|| (spriteExtended == null) ){
			super.draw(input, output);
		
		} else if (cooldown>0){		
			output.drawSprite(spriteExtended , getBox()) ;
		
		} else {
			output.drawSprite(spriteNormal , getBox()) ;
		}
	}

}
