package platform.game;

import java.awt.event.KeyEvent;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Player extends Actor {

	private Vector position;
	private Vector velocity;
	private Vector additivePosition;
	private final static double SIZE = 0.6;
	private boolean colliding = false;
	private double health;
	public static final Vector gravity = new Vector(0.0, -9.81);
	public final static double maxHp = 10;
	private double additiveFriction;
	private StaticSignal winter = new StaticSignal();
	private final double standardFireballCooldown = 0.5;
	private double fireballCooldown = standardFireballCooldown;
	private final int standardNumberOfFireballs = 5;
	private int numberOfFireball = standardNumberOfFireballs;

	public Player(Vector position, Vector velocity, double health) {
		// TODO Auto-generated constructor stub
		if (position == null || velocity == null) {

			throw new NullPointerException();

		}
		if (maxHp < health) {

			this.health = maxHp;

		}
		this.position = position;
		this.velocity = velocity;
		this.health = health;
	}

	public double getHealth() {
		return health;
	}

	public double getHealthMax() {
		return maxHp;
	}

	public void setAdditivePosition(Vector deltaPosition) {

		additivePosition = deltaPosition;

	}

	public void setAdditiveFriction(double additiveFriction) {
		this.additiveFriction = additiveFriction;
	}

	public void setHealth(double hp) {

		if (hp > maxHp) {

			health = maxHp;
		} else if (hp <= 0) {

			getWorld().nextLevel();

		} else {
			health = hp;
		}
	}

	public void setPosition(Vector position) {
		this.position = position;
	}
	@Override
	public void preUpdate(Input input) {
		// TODO Auto-generated method stub
		super.preUpdate(input);
		colliding = false;
		additivePosition = Vector.ZERO;
		additiveFriction = 0;

	}

	@Override
	public void update(Input input) {

		super.update(input);
		double delta = input.getDeltaTime();
		fireballCooldown -= delta;
		if(fireballCooldown < 0 && numberOfFireball<standardNumberOfFireballs){
			numberOfFireball+=1 ;
			fireballCooldown = standardFireballCooldown;
		}
		 Vector acceleration = new Vector(0.0, -9.81);

		double maxSpeed = 4.0;
		if (colliding) {
			double scale = Math.pow(0.001 + additiveFriction, input.getDeltaTime());
			velocity = velocity.mul(scale);
		}
		if ( input.getKeyboardButton(KeyEvent.VK_RIGHT).isDown() || input.getKeyboardButton(KeyEvent.VK_D).isDown() ) {
			if (velocity.getX() < maxSpeed) {
				double increase = 60.0 * input.getDeltaTime();
				double speed = velocity.getX() + increase;
				if (speed > maxSpeed)
					speed = maxSpeed;
				velocity = new Vector(speed, velocity.getY());
			}
		}

		if (input.getKeyboardButton(KeyEvent.VK_LEFT).isDown() || input.getKeyboardButton(KeyEvent.VK_Q).isDown() ) {
			if (-velocity.getX() < maxSpeed) {
				double increase = 60.0 * input.getDeltaTime();
				double speed = velocity.getX() - increase;
				if (-speed > maxSpeed)
					speed = -maxSpeed;
				velocity = new Vector(speed, velocity.getY());
			}
		}

		if ( (input.getKeyboardButton(KeyEvent.VK_UP).isPressed() || input.getKeyboardButton(KeyEvent.VK_Z).isDown() ) && colliding) {
			velocity = new Vector(velocity.getX(), 7.0);
		}

		if (input.getKeyboardButton(KeyEvent.VK_SPACE).isPressed()&& numberOfFireball > 0) {

			Vector v = velocity.add(velocity.resized(2.0));

			getWorld().register(new Fireball(getPosition(), v, this, Fireball.basicLifeSpan));
			numberOfFireball -= 1;
		}
		if (input.getMouseButton(1).isPressed() && numberOfFireball > 0) { 
			
			Vector v = input.getMouseLocation().sub(getBox().getCenter()).normalized().mul(8);
			
			getWorld().register(new Fireball(getPosition(), v, this, Fireball.basicLifeSpan));
			numberOfFireball -= 1;
		}

		if (input.getKeyboardButton(KeyEvent.VK_B).isPressed())
			getWorld().hurt(getBox(), this, Damage.AIR, 1.0, getPosition());

		if (input.getKeyboardButton(KeyEvent.VK_W).isPressed())
			winter.SetIsActive(!winter.isActive());

		if (input.getKeyboardButton(KeyEvent.VK_E).isPressed())
			getWorld().hurt(getBox(), this, Damage.ACTIVATION, 1.0, getPosition());

		velocity = velocity.add(acceleration.mul(delta));

		position = position.add(velocity.mul(delta));

	}

	@Override
	public void postUpdate(Input input) {
		// TODO Auto-generated method stub
		super.postUpdate(input);
		double radius = 8;
		getWorld().setView(getBox().getCenter(), radius);
	}

	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {

		switch (type) {
		case AIR:
			velocity = getPosition().sub(location).resized(amount);
			return true;
		case FIRE:
			setHealth(health - amount);
			return true;
		case VOID:
			setHealth(0);
			return true;
		case HEAL:
			setHealth(this.getHealth() + amount);
			return true;
		case PHYSICAL:

			if (instigator instanceof Spike && velocity.getY() < -1) {
				setHealth(getHealth() - amount);
			}
			return true;

		case TOPSPIKE:
			setHealth(this.getHealth() - amount);
			return true;

		case ACTIVATION:
			return true;

		default:
			return super.hurt(instigator, type, amount, location);
		}
	}

	public int getNumberOfFireball() {
		return numberOfFireball;
	}
	public Box getBox() {

		return new Box(position, SIZE, SIZE);
	}

	public Vector getPosition() {
		Box box = getBox();
		if (box == null)
			return null;
		return box.getCenter();
	}

	@Override
	public void draw(Input input, Output output) {
		Sprite sprite;
		if (winter.isActive()) {
			sprite = getSprite("blocker.sad");
		} else {
			sprite = getSprite("blocker.happy");
		}
		if (sprite == null) {
			super.draw(input, output);

		} else {
			output.drawSprite(sprite, getBox());

		}

	}

	@Override
	public void interact(Actor other) {
		super.interact(other);
		if (other.isSolid()) {

			if (getBox().isColliding(other.getBox())) {
				other.hurt(this, Damage.ICE, 0, getBox().getCenter());
				
				if (other.hurt(this, Damage.TOUCHES, 0, getBox().getCenter())) {
					position = position.add(additivePosition);
				}
			}
			Vector delta = other.getBox().getCollision(getBox());

			if (delta != null) {

				position = position.add(delta);
				colliding = true;
				if (delta.getX() != 0.0)
					velocity = new Vector(0.0, velocity.getY());
				if (delta.getY() != 0.0)
					velocity = new Vector(velocity.getX(), 0.0);
			}
		} else {
			
			if (other instanceof Monstre){
				if (getBox().isColliding(((Monstre)other).getVision())){
					other.hurt(this, Damage.TOUCHES, 0, getBox().getCenter());
				}
			}
			
			if (getBox().isColliding(other.getBox())) {
				other.hurt(this, Damage.TOUCHES, 0, getBox().getCenter());

			}
		}
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 42;
	}

}
