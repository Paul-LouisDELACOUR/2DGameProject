package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Mover extends Block implements Signal, Generate {

/**
 * Les attributs canDie(boolean) et  double lifeTime ne sont utilisés que pour les objets Generate, c’est à dire amené à être généré un grand nombre de fois.
Dans le cas où l’objet peut mourir (canDie = true), son temps de vie est décrémenté (lifeTime -= input.getDelaTime()), jusqu’à ce qu’il deviennent négatif ou null. À ce moment là, l’objet est alors supprimé du monde 
 */
	private Vector on;
	private Vector off;
	private Signal signal;
	private double current;
	
	private Vector prevPosition;
	private Vector currentPosition;
	private Vector deltaPosition;
	private boolean canDie;
	private double lifeTime;
	double speedFactor=1;
	
	public Mover(Box rectangle, Vector off, Vector on, Sprite sprite, Signal signal,double speedFactor, double lifeTime) {
		super(rectangle, sprite);
		this.signal = signal;
		this.on=on;
		this.off = off;
		currentPosition=center();
		prevPosition=off;	
		
		this.speedFactor=speedFactor;
		
		Test.testDouble(lifeTime);
		this.lifeTime=lifeTime;
		canDie=true;
		
		
	}
	
	public Mover(Box rectangle, Vector off, Vector on, Sprite sprite, Signal signal,double speedFactor) {
		super(rectangle, sprite);
		this.signal = signal;
		this.on=on;
		this.off = off;
		currentPosition=center();
		prevPosition=off;	
		
		this.speedFactor=speedFactor;
	}	
	
	public Mover(Box rectangle, Vector off, Vector on, Sprite sprite, Signal signal) {
		super(rectangle, sprite);
		this.signal = signal;
		this.on=on;
		this.off = off;
		currentPosition=center();
		prevPosition=off;	
		
	}
	
	/**
	  * Création d'un constructeur de copie permettant à la fonction Generate de créer un nouveau Mover.
	  * Ce constructeur construit un objet ayant les mêmes paramètres que celui en attribut dans la fonction.
	  */
	public Mover (Generate actor){
		super( ((Mover)actor).getBox(), ((Mover)actor).getSprite() );
		this.signal=((Mover)actor).signal;
		this.on=((Mover)actor).on;
		this.off=((Mover)actor).off;
		currentPosition=center();
		prevPosition=off;
		this.lifeTime=((Mover)actor).lifeTime;
		this.canDie=((Mover)actor).canDie;
		this.speedFactor=((Mover)actor).speedFactor;
		
	}
	public boolean isSolid(){
		return true;
	}
	
	public int getPriority(){
		return 0;
	}
	


	public boolean isActive() {
		if( current!=0){
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	  * Le Update garde en mémoire la position précédente du mover, et calcule la position courante.
	  * Ainsi on détermine le décalage du mover entre 2 frames (nommé deltaPostion)
	  * Ajout d'un attribut speedFactor permettant de régler la vitesse de déplacement du mover.
	  *
	  *
	  */
	public void update(Input input) {
		super.update(input) ;
		double delta = input.getDeltaTime() ;
		if (signal.isActive()) {
			current += delta*(speedFactor) ;
			if (current > 1.0) {
				current = 1.0;
			}
			
		} else {
			current -= delta*(speedFactor) ;
			if (current < 0.0){
				current = 0.0 ;
			}	
		}
		
		
		
		if(canDie){
			lifeTime-=delta;
			if (lifeTime<=0){
				getWorld().unregister(this);
			}
		}
		
		prevPosition = currentPosition;
		currentPosition=center();
			
		deltaPosition = currentPosition.sub(prevPosition);
		
		
	}
	
	public Box getBox(){
		return new Box(center(), super.getBox().getWidth(), super.getBox().getHeight());
	}
	
	public boolean hurt(Actor instigator, Damage type, double
			amount, Vector location) { 
		
		switch (type) {
			case TOUCHES :
				((Player)instigator).setPosition(((Player)instigator).getPosition().add(deltaPosition) ); 
			return true;	
			default :	
				return super.hurt(instigator, type, amount, location);
		}
	}
	
	
	
	/*
	 * f(x) = −2x^(3) + 3x^(2)  --> Fonction de la position
	 * f'(x) = -6x^(2) + 6x  --> f'(1) = f'(0) = 0    La vitesse est nulle en 0 et en 1 
	 */
	/**
	 * (position depart) + [(position on) - (position off)]*f(current)
	 * on ajoute à la position de départ le petit décalage de position ( donné par current). 
	 * @return
	 */
	public Vector center (){
		
		Vector position = off.add( (on.sub(off).mul( -2*Math.pow(current, 3) +3*Math.pow(current, 2) ) ));
		return position;
	}
	
	
	public void draw(Input input , Output output){
		Sprite sprite = super.getSprite();
		if (sprite == null){
			super.draw(input, output);
		} else {		
			output.drawSprite(sprite , getBox()) ;
		}
	}
	
	public void interact(Actor other) {
		super.interact(other) ;

		if (getBox().isColliding(other.getBox())) {
			if ( (isActive()) && (other.hurt(this, Damage.TOUCHES, 0, getPosition())) ){
			}
			
		}
	}
	
	public Vector getPosition(){
		return center();
	}
	/**
	  * Permet de Générer un nouveau Mover contenant les mêmes caractéristiques que celui passé en paramètre
	  */
	@Override
	public Generate generate( Generate actor) {
		return new Mover(actor);
	}
		
}
