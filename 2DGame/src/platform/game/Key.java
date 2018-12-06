package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;
/**
 * Ajout d'un constructeur comprenant un attribut Mover.
 * Celui permet à la clé d'avoir une position fixe par rapport au Mover, lorsque celui-ci se déplace Verticalement.
 * Ainsi, à chaque update, la position de la clé est déterminée par la position
 * du centre du Mover, à laquelle on ajoute la demi-hauteur du Mover, ainsi que la demi-hauteur de la clé. 
 *
 */

public class Key extends Actor implements Signal {

	private Vector position;
	private boolean taken = false;
	private double SIZE=0.5;
	private Color color;
	private Mover mover;
	
	public Key(Vector position, Color color) {
		this.position=position;
		this.color=color;
	}
	
	public Key(Vector position, Color color, double SIZE) {
		this.position=position;
		this.SIZE = SIZE;
		this.color = color;
	}
	
	public Key(Mover mover, Color color, double size){
		position=mover.getPosition();
		this.mover=mover;
		this.SIZE=size;
		this.color=color;
	}

	
	public int getPriority() {
		return 800;
	}
	
	public boolean isActive(){
		return taken;	
	}
	
	public void update(Input input) {
		super.update(input) ;
		if(mover!=null){
		position = mover.getPosition().add( new Vector (0, mover.getBox().getHeight()/2 )
				.add(new Vector (0, this.getBox().getHeight()/2)));
		}
	}
	
	public void interact(Actor actor){
		
		super.interact(actor);
		
		if (this.getBox().isColliding(actor.getBox())){
			if (actor.hurt(actor, Damage.ACTIVATION, 0,position)) {
				getWorld().unregister(this);
				taken=true;
			}	
		}		
	}
	
	
	
	
	public Box getBox(){
		return new Box(position, SIZE, SIZE);
	}
	
	
	public void draw (Input input, Output output){
			
			Sprite sprite = getSprite ("key."+ color);
			
			if ((taken)||(sprite == null)){
				super.draw(input, output);
			} else {		
				output.drawSprite(sprite ,getBox() ) ;
			}
	}
	
	public Color getColor(){
		return color;
	}

	

}
