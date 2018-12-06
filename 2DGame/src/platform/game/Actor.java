package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

/**
 * Base class of all simulated actors, attached to a world.
 */
public abstract class Actor implements Comparable<Actor> {

	// pour évoluer au cours du temps :
	public void update(Input input) {
	}
	// pour être dessiné

	public void preUpdate(Input input) {
	}

	public void postUpdate(Input input) {
	}

	public void draw(Input input, Output output) {
	}

	// TO BE COMPLETED

	public abstract int getPriority();

	public int compareTo(Actor other) {

		if (this.getPriority() > other.getPriority()) {
			return -1;

		} else if (this.getPriority() == other.getPriority()) {
			return 0;
		} else {
			return 1;
		}
	}

	public void interact(Actor other) {
	} // gerer les interactions avec un acteur

	public boolean isSolid() { // par défaut un acteur est non solide
		return false;
	}

	public Box getBox() { // par défaut un acteur n'a pas de rectangle englobant
		return null;
	}

	public Vector getPosition() {
		Box box = getBox();
		if (box == null)
			return null;
		return box.getCenter();
	}

	private World world;

	public void register(World world) {
		this.world = world;
	}

	public void unregister() {
		world = null;
	}
	
	protected World getWorld(){
		return world;
	}
	
	
	protected Sprite getSprite (String nom){
		return world.getLoader().getSprite(nom);
	}

	public boolean hurt(Actor instigator , Damage type, double
			amount , Vector location) {
		return false ;
	}

}
