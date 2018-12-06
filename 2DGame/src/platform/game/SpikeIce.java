package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class SpikeIce extends Actor implements Generate {

	/**
	 * Création d'un SpikeIce qui est attiré par gravité et qui peut infliger des dégats.
	 * C'est un objet Generate, qui va donc pouvoir être généré en plusieurs exemplaires avec 
	 * l'utilisation du générateur.
	 */
	
	private Vector position;
	private Vector velocity;
	private double size;
	private static final Vector maxVelocity = new Vector (10,10);
	private StaticSignal winter = new StaticSignal();
	public final static double standardSpikeIceDmg = Player.maxHp/5;
	
	public SpikeIce(Vector position, Vector velocity, double size) {
		if ( (position == null )|| (velocity ==null) ){
			throw new NullPointerException() ;
		}
		
		this.position=position;
		if( (velocity.getLength()   > maxVelocity.getLength()) ){
			this.velocity=maxVelocity;
		} else {
			this.velocity = velocity;
		}
		this.size = size;
	}
	
	public SpikeIce(Generate spikeIce){
		this.position = ((SpikeIce)spikeIce).position;
		this.velocity = ((SpikeIce)spikeIce).velocity;
		this.size = ((SpikeIce)spikeIce).size;
	}
	

	@Override
	public int getPriority() {
		return 400;
	}
	
	public Box getBox(){
		return new Box(position, size, size);
	}
	
	public void update(Input input){

		super.update(input) ;
		
		double delta = input.getDeltaTime() ;
			
		Vector acceleration = new Vector(0.0, -9.81) ;
		velocity = velocity.add(acceleration.mul(delta)) ;
		position = position.add(velocity.mul(delta)) ;
		
		
	
	}
	
	/**
	 * Le Player reçoit des dégats de type TOPSPIKE par cet objet.
	 * Dès que le SpikeIce inflige des dégats, il est supprimés du monde.
	 */
	public void interact(Actor other) {
		super.interact(other) ;
		
		if (getBox().isColliding(other.getBox()) ) {
		
			if(other instanceof Player){
				System.out.println(other.getBox().getCenter());
				if (other.hurt(this , Damage.TOPSPIKE,standardSpikeIceDmg, position)){				
					getWorld().unregister(this);
					
				}		
				
			}

		}
		
	}
	
	/**
	 * On a également redéfinit la méthode hurt car les SpikeIce doivent être détruits une fois qu'ils ont
	 * dépassé les limites du jeu. Ils sont donc susceptibles de recevoir un dégat de type void par l'acteur Limits.
	 */
	
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {

		switch (type) {
		
		case VOID:
			getWorld().unregister(this);
			return true;
		

		default:
			return super.hurt(instigator, type, amount, location);
		}
	}

	/**
	 * Redéfinition de la méthode draw pour les objets soient dessinés différemment si l'on est en hiver ou en été.
	 */
	public void draw(Input input, Output output){
		Sprite sprite;
		if(winter.isActive()){
			sprite = getSprite ("spikeTop");
		}
		else{
			sprite = getSprite ("fallingSpikes");
		}
		if (sprite == null){
			super.draw(input, output);
		} else {		
			output.drawSprite(sprite , getBox()) ;

		}
	}
	
	/**
	 * Redéfinition de la méthode de l'interface permettant de générer un nouveau SpikeIce
	 * contenant les mêmes attributs que l'actor passé en paramètre de la méthode.
	 */
	public Generate generate(Generate actor){
		return new SpikeIce(actor);
	}
}	
