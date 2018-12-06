package platform.game;

import java.awt.event.KeyEvent;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class PlayerPL extends Actor{

	private Vector position;
	private Vector velocity;
	public final static double SIZE =0.6;
	private boolean colliding=false;
	private static double health;
	public static final double healthMax =10; 
	public final static double basicHP =10;
	
	
	public PlayerPL(Vector position, Vector velocity, double health) {
		if ( (position == null )|| (velocity ==null) ){
			throw new NullPointerException() ;
		}
		
		this.position = position;
		this.velocity = velocity;
		
		if (health>healthMax){
			this.health=healthMax;
		}
		
		this.health = health;
	}
	
	public void preUpdate(Input input){
		super.preUpdate(input);
		colliding=false;
		
	}
	
	
	public Vector getPosition (){
	Box box = getBox();
		if (box == null)
			return null;
		return box.getCenter();
	}
	
	public void setPosition(Vector newPosition){
		position = newPosition;
	}
	
	public Box getBox(){
		return new Box (this.position,SIZE,SIZE);
	}
	
	public int getPriority(){
		return 42;
	}
	
	public void draw(Input input, Output output){
		Sprite sprite = getSprite ("blocker.happy");
		if (sprite == null){
			super.draw(input, output);
		} else {		
			output.drawSprite(sprite , getBox()) ;
		}
	}
	
	public void update(Input input) {
		super.update(input) ;
		double delta = input.getDeltaTime() ;
		
		Vector acceleration = new Vector(0.0, -9.81) ;
		
		double maxSpeed = 4.0 ;
		if (colliding) {
			double scale = Math.pow(0.001, input.getDeltaTime()); velocity = velocity.mul(scale);
		}
		
		if ((input.getKeyboardButton(KeyEvent.VK_RIGHT).isDown()) ) {
			if (velocity.getX() < maxSpeed) {
				double increase = 60.0 * input.getDeltaTime() ;
				double speed = velocity.getX() + increase ;
				if (speed > maxSpeed)
					speed = maxSpeed ;
				velocity = new Vector(speed , velocity.getY()) ;
			}
		}
		
		if ((input.getKeyboardButton(KeyEvent.VK_LEFT).isDown()) ){
			if (-velocity.getX() < maxSpeed) {
				double increase = 60.0 * input.getDeltaTime() ;
				double speed = velocity.getX() - increase ;
				if (-speed > maxSpeed)
					speed = -maxSpeed ;
				velocity = new Vector(speed , velocity.getY()) ;
			}
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_UP).isPressed() && colliding){
			velocity = new Vector(velocity.getX(), 7.0) ;
		}
		
		velocity = velocity.add(acceleration.mul(delta)) ;
		position = position.add(velocity.mul(delta)) ;
		
		if (input.getKeyboardButton(KeyEvent.VK_SPACE).isPressed()){
			Vector v = velocity.add(velocity.resized(2.0)) ;
			Fireball fireball = new Fireball (getPosition(), v, this);
			getWorld().register(fireball);
		}
		
		if (health<=0){
			this.endLife();
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_B).isPressed()) {
			getWorld().hurt(getBox(), this, Damage.AIR, 1.0, getPosition());
		}
	
		if (input.getKeyboardButton(KeyEvent.VK_E).isPressed()) {
			getWorld().hurt(getBox(), this, Damage.ACTIVATION, 1.0, getPosition());
		}	
	}
	
	public void endLife(){
		getWorld().unregister(this);
		getWorld().nextLevel();
		
}

	
	
	public void interact(Actor other) {
		super.interact(other) ;
		
		if (getBox().isColliding(other.getBox())) {


			if (other.isSolid()) {
				if (other.hurt(this, Damage.CRUMBLE, 0, getPosition())){
				}
				if (other.getClass() == Mover.class){
					other.hurt(this, Damage.TOUCHES, 1, getPosition());
				}
				Vector delta = other.getBox().getCollision(getBox()) ;
				if (delta != null) {
					
					position = position.add(delta) ;
					colliding = true;
					if (delta.getX() != 0.0)
						velocity = new Vector(0.0, velocity.getY()) ;
					if (delta.getY() != 0.0)
						velocity = new Vector(velocity.getX(), 0.0) ;
				}

			}
			
			if ( this.getBox().isColliding(other.getBox()) ) {
				
				if (other.hurt(this, Damage.TOUCHES, 0, getPosition())) {
				}
				
				
			}
			

		}		
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double
			amount, Vector location) { 
		super.hurt(instigator, type, amount, location);
		switch (type) {
			case AIR :
				velocity = getPosition().sub(location).resized(amount); 
				return true;
			case VOID :
				setHealth(0);
				return true;
			case HEAL :
				setHealth(this.getHealth()+amount);
				return true;
			case PHYSICAL : 
				if (this.velocity.getY()<-1){
					setHealth(this.getHealth()-amount);
				return true;	
				}
			case ACTIVATION :
				return true;
			case TOPSPIKE :
				setHealth(this.getHealth()-amount);
				return true;
			default :
				return super.hurt(instigator, type, amount, location);
			} 
	}
	
	public void setHealth(double x){
		if (health>healthMax){
			health=healthMax;
		} else if (health <=0){
			getWorld().nextLevel();
		}else{
			health = x;
		}
		
	}
	
	
	public void postUpdate(Input input){
		super.postUpdate(input);
		//setView(position, 8.0)
		getWorld().setView( this.getPosition(), 8.0);
	}
	
	public double getHealth(){
		return health;
	}
	
	public double getHealthMax(){
		return healthMax;
	}
	
	
	
	
	
	

}
