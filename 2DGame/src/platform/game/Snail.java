package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Snail extends Monstre implements Generate {

	public static double standardSnailSize = 0.5;
	public static final double standardFireballFrequency = 2;
	private double fireballFrequency = standardFireballFrequency;
	private boolean left = true;
	private boolean canFire = false;
	private final double standardVariation = 0.2;
	private double variation = standardVariation;
	private final Vector LeftSpeed = new Vector(-1, 0);
	private final Vector RightSpeed = new Vector(1, 0);

	public Snail(Vector position, Vector velocity, double size, Player player,double fireballFrequency,Double health) {
		super(position, velocity, size, player,health);
		this.fireballFrequency = fireballFrequency;
		
	}

	public Snail(Vector position, Vector velocity, double size, Player player) {
		super(position, velocity, size, player);

	}

	public Snail(Generate snail) {
		super(snail);
		this.fireballFrequency = ((Snail) snail).fireballFrequency;

	}
	
	@Override
	public Box getVision() {
		return new Box(getPosition(), standardVisionRadius*1.5, standardVisionRadius*1.5);
	}
	@Override
	public Generate generate(Generate snail) {
		return new Snail(snail);
	}

	public boolean doesActions() {
		return (canFire);
	}
	/**
	 * envoie une boule de feu sil peut en tirer (il peut en tirer toutes les standardFireballFrequency (a ce moment 3s)) vers la direction du joueur
	 * De plus il se dirige vers le joueur a une vitesse constante. 
	 */
	public void actions() {

		getWorld().register(new Fireball(getBox().getCenter(), playerDirection().mul(1.5).add(Vector.Y.mul(2)), this, 1.5));
		canFire = false;
		fireballFrequency = standardFireballFrequency; //on reset le timer et on met canFire a false apres avoir tirer
		if (playerDirection().getX() < 0) {
			left = true;
			setVelocity(LeftSpeed);
			
			
		} else {
			left = false;
			setVelocity(RightSpeed);
			
			

		}

	}

	@Override
	public void draw(Input input, Output output) {
		
		super.draw(input, output);
		Sprite sprite;
		if (left) {

			if (variation <= standardVariation / 2) {
				sprite = getSprite("snail.left.2");
			} else {
				sprite = getSprite("snail.left.1");
			}
		} else {
			if (variation <= standardVariation / 2) {
				sprite = getSprite("snail.right.2");
			} else {
				sprite = getSprite("snail.right.1");
			}

		}

		if (sprite == null) {
			super.draw(input, output);
		} else {
			output.drawSprite(sprite, getBox());

		}
	}

	@Override
	public Box getBox() {
		
		return new Box(getPosition(), getSize(), getSize());
	}

	@Override
	public void update(Input input) {
		
		super.update(input);
		double delta = input.getDeltaTime();
		
		setVelocity(getVelocity().add(Player.gravity.mul(delta))); // update de la position, getVelocity() ne peut que renvoyer (+ ou - 1, 0 )
		setPosition(getPosition().add(getVelocity().mul(delta)));
		
		variation -= delta;
		fireballFrequency -= delta;
		
		if (fireballFrequency < 0 && !canFire) {
			

			canFire = true;
		}

		if (variation < 0) {
			variation = standardVariation;
		}
	}

	@Override
	public void interact(Actor other) {
		
		super.interact(other); 

		if (other.isSolid()) {

			Vector delta = other.getBox().getCollision(getBox());

			if (delta != null) {

				setPosition(getPosition().add(delta));

				if (delta.getX() != 0.0) 
					setVelocity(new Vector(0.0, getVelocity().getY())); //pour l'enpecher de traverser les murs
				if (delta.getY() != 0.0)
					setVelocity(new Vector(getVelocity().getX(), 0.0)); //pour l'enpecher de tomber a travers le sol
			}
		}
	}

}
