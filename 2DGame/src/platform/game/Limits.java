package platform.game;

import platform.util.Box;
import platform.util.Vector;

/**
 * Acteur modélisant les limites du monde à ne pas dépasser par le joueur.
 * Cet acteur inflige des dégâts irréparables aux autres acteurs qu’il rencontre.
 * Dégat void avec une valeur Infinie, ce qui réduit la vie du Player à 0.
 */

public class Limits extends Actor {

	private Box box;
	
	public Limits(Box box) {
		this.box = box;
	}

	public void interact(Actor other) {
		super.interact(other);
		
		if (!this.getBox().isColliding(other.getPosition())){
			other.hurt(this, Damage.VOID, Double.POSITIVE_INFINITY, Vector.ZERO);
		}
		
		
	}
	
	@Override
	public int getPriority() {
		return 55;
	}
	
	public Box getBox(){
		return box;
	}


}
