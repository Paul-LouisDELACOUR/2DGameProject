package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class SnowBlock extends Block implements Season {
	private Signal signal;
	private double delta = 0;
	public static final double standardGrowthRate = 2;
	private double growthRate = standardGrowthRate;
	private double maxGrowth = Double.MAX_VALUE;
	private final double standardDelay = 2;
	private double delay = standardDelay;
	private boolean wasActive;


	public SnowBlock(Box box, Sprite sprite, Signal signal) {
		super(box, sprite);
		this.signal = signal;
	
	}

	public SnowBlock(Box box, Sprite sprite, Signal signal, double growthRate, double maxGrowth) {
		super(box, sprite);
		this.signal = signal;
		this.maxGrowth = maxGrowth;

		assert (growthRate >= 0);
		this.growthRate = growthRate;
		
	}

	public SnowBlock(Box box, Sprite sprite, Signal signal, double growthRate) {
		super(box, sprite);
		this.signal = signal;

		assert (growthRate >= 0);
		this.growthRate = growthRate;
		
	}

	@Override
	public Box getBox() {
		

		return new Box(super.getBox().getMin(),
				new Vector(super.getBox().getMax().getX(), super.getBox().getMax().getY() + getGrowth()));
	}

	@Override
	public void draw(Input input, Output output) {

		if (getSprite() == null) {
			super.draw(input, output);

		} else {
			output.drawSprite(getSprite(), getBox());

		}

	}
/**
 * 
 * @return la taille que le block a augmente en total. Elle de peut pas depasser maxGroth ou etre negative 
 */
	private double getGrowth() {
		if (growthRate * delta > 0 && growthRate * delta <= maxGrowth) {
			return growthRate * delta;
		} else if (growthRate * delta > maxGrowth) {

			return maxGrowth;
		} else {
			return 0; //la taille de la boite pourrait etre negative ce qui provoquerait une erreur
		}
	}

	@Override
	public void preUpdate(Input input) {
		
		super.preUpdate(input);

		if (signal.isActive() && delay > 0) {
			delay -= input.getDeltaTime();

		}

		if (signal.isActive() && getGrowth() <= maxGrowth && delay < 0 ) {
			
			if(getGrowth() != maxGrowth){
				delta += input.getDeltaTime();
			}
			
			
		} else {
			if (delta > 0) {
				
				delta -= input.getDeltaTime();

			}
		}
		
		wasActive=signal.isActive();
		
	}
	@Override
	public void postUpdate(Input input) {
		
		super.postUpdate(input);
		if(wasActive != signal.isActive()){ //sil est en hivers il commence a grandir apres un cours delay de 2 s
			delay = standardDelay;
			
		}
	}

}
