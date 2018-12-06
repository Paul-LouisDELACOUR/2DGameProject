package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Fireball extends Actor {

	public static final double basicLifeSpan =2;
	private Vector position;
	private Vector velocity;
	private Actor owner;
	private double time=basicLifeSpan;
	
	public Fireball(Vector position, Vector velocity) {
		if ( (position == null )|| (velocity ==null) ){
			throw new NullPointerException() ;
		}
		
		this.position = position;
		this.velocity = velocity;
		
	}
	
	public Fireball(Vector position, Vector velocity, Actor owner) {
		if ( (position == null )|| (velocity ==null) ){
			throw new NullPointerException() ;
		}
		
		this.position = position;
		this.velocity = velocity;
		this.owner=owner;
	}
	
	public Fireball(Vector position, Vector velocity, Actor owner, double timer) {
		if ( (position == null )|| (velocity ==null) ){
			throw new NullPointerException() ;
		}
		
		this.position = position;
		this.velocity = velocity;
		this.owner=owner;
		time = timer;
	}

	@Override
	public int getPriority() {
		return 666;
	}
	
	public final static double SIZE =0.4;
	
	public Box getBox() {
		// position est l'attribut position de l'objet
		// SIZE une constante choisie pour la taille , par exemple
		
		return new Box(position , SIZE, SIZE) ;
	}
	
	@Override
	
	 
	
	public void update(Input input) {
		super.update(input) ;
		double delta = input.getDeltaTime() ;
		Vector acceleration = new Vector(0.0, -9.81) ;
		velocity = velocity.add(acceleration.mul(delta)) ;
		position = position.add(velocity.mul(delta)) ;
		
		time-=input.getDeltaTime();
	}
	
	
	
	public void draw(Input input, Output output){
		Sprite sprite = getSprite ("fireball");
		if (sprite == null){
			super.draw(input, output);
		} else {		
			output.drawSprite(sprite , getBox(), input.getTime()) ;
		}
	}
	
	public void interact(Actor other) {
		super.interact(other) ;
		
		if(other != owner){
			
			
			if (other.isSolid()) {
				Vector delta = other.getBox().getCollision(position) ;
				if (delta != null) {
					position = position.add(delta) ;
					velocity = velocity.mirrored(delta) ;
				}
			}
			
			if (getBox().isColliding(other.getBox())) {
				if (other.hurt(this , Damage.FIRE, 1, getPosition())){
					getWorld().unregister(this);
				}
					
			if (time<=0){
				getWorld().unregister(this);
			}
					// faire en sorte ici que la boule feu disparaisse
					// une fois qu'elle a infligé un dommage.
			}
		
		}
	
	}

}
