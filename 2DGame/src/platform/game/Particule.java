package platform.game;



import platform.game.Actor;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;
/**
 * Création d'un Acteur dessinable ajoutant une animation d'une durée duration.
 */

public class Particule extends Actor {

	private Sprite sprite;
	private Vector position;
	private double size;
	private double duration;
	private double time;
	private double rotationSpeed;
	
	public Particule() {
	}
	
	public Particule(Sprite sprite, Vector position, double size, double duration){
		this.sprite= sprite;
		this.position=position;
		this.size=size;
		
		this.duration=duration;
	}
	
	public Particule(Sprite sprite, Vector position, double size, double duration, double rotationSpeed){
		this.sprite= sprite;
		this.position=position;
		this.size=size;
		this.rotationSpeed=rotationSpeed;
		this.duration=duration;
	}

	@Override
	public int getPriority() {
		return 1337;
	}
	
	/**
	 * Prise en compte du temps de l'animation (time).
	 * Une fois l'animation terminée, l'objet est supprimé du monde.
	 * L’attribut time est décrémenté à chaque fois que la méthode update est appelée.
	 */
	public void update(Input input) {
		super.update(input) ;
	
		time += input.getDeltaTime() ;
		
		if (time >= duration){
			getWorld().unregister(this) ;
		}	
	}
	
	public Box getBox(){
		return new Box (position, size , size ); 
		
	}

	/**
	 * Ajout de la rotation (rotatino speed) lorsque l'on dessine l'objet.
	 */
	public void draw(Input input, Output output){

		if (sprite == null) {
			super.draw(input, output);

		} else if (time > 0) {

			output.drawSprite(sprite, getBox(), input.getTime() * rotationSpeed);
		}

	}
		
	/**
	 * Ajout de différentes setters pour modifier la position, la taille (size), la durée de 
	 * l'animation (duration), le sprite associé, ou encore la vitesse de rotation.
	 */
	public void setPosition(Vector newPosition){
		this.position = newPosition;
	}
		
	public void setSize(double size){
		this.size = size;
	}
	
	public void setDuration(double newDuration){
		this.duration = newDuration;
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	public void setRotationSpeed(double rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}
	
}