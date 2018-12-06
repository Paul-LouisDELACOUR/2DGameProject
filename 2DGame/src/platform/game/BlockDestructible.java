package platform.game;

import java.util.Random;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;
/**
 * Creation de block qui peuvent être détruits par des fireballs.
 * Ces blocks ont une probabilité de 1/2 de générer des Heart lors de leur destruction.
 * Utilisation de la classe smoke pour avoir une animation lors de la destruction du block.
 *
 */
public class BlockDestructible extends Block {

	public static final int BaseBlockResistance = 3;
	private Signal signal = new Not(new Constant());
	private double resistance = BaseBlockResistance;
	private boolean exploded;
	private Particule smoke = new Particule();
	private Random rand = new Random();
	private double lucky = rand.nextDouble();

	public BlockDestructible(Box box, Sprite sprite, Signal signal) {
		super(box, sprite);
		this.signal = signal;
	}

	public BlockDestructible(Box box, Sprite sprite) {
		super(box, sprite);
	}

	/**
	 * 
	 * Permet de modifier le pourcentage de vie du blocs
	 * Dès que le block a une vie négative ou nulle, il est considéré comme exploded
	 */
	public void setResistance(double resistance) {
		this.resistance = resistance;
		if (resistance <= 0) {
			exploded = true;

		}
	}

	public double getResistance() {
		return resistance;
	}

	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {

		switch (type) {

		case FIRE:
			setResistance(resistance - amount);
			return true;

		default:
			return super.hurt(instigator, type, amount, location);
		}
	}

	@Override
	public void draw(Input input, Output output) {

		super.draw(input, output);

	}

	@Override
	public void preUpdate(Input input) {
		super.preUpdate(input);

	}

	@Override
	public void postUpdate(Input input) {
		super.postUpdate(input);

		if (exploded) {
			if (lucky > 0.5) {
				getWorld().register(new Heart(getBox().getCenter(), Double.MAX_VALUE));
			}
			String name = "smoke.white.1"; 
			Sprite sprite = getWorld().getLoader().getSprite(name);
			getWorld().register (new Particule (sprite, getBox().getCenter(), 1, 0.15, 30));
			
			getWorld().unregister(this);

		}

	}

}
