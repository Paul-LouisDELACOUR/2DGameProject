package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class MonstreOverlay extends Actor {

	public MonstreOverlay() {
		// TODO Auto-generated constructor stub
	}

	private Monstre monstre;
	private World world;
	private double SIZE =0.15;
	
	public MonstreOverlay(Monstre monstre) {
		this.monstre = monstre;
	}

	@Override
	public int getPriority() {
		return 0;
	}
		
	@Override
	public void postUpdate(Input input) {
		// TODO Auto-generated method stub
		super.preUpdate(input);
		if (monstre.getWorld() == null){
			getWorld().unregister(this);
		}
	}
	public void update(Input input) {
		super.update(input) ;
		if (monstre.getWorld() == null){
			getWorld().unregister(this);
		}
		
	}
		
	public void draw (Input input, Output output){
	
		
			Sprite sprite = getSprite ("hpBar");
			if (sprite == null){
				super.draw(input, output);
			} else {		
				output.drawSprite(sprite ,getBox() ) ;
			}
		
		
		

	}
		
	private double getHpLeft(){
		
		return monstre.getHealth()/monstre.getHealthMax();
	}
	
	public Box getBox(){
		
		return new Box( monstre.getBox().getCenter().add(new Vector( 0 , monstre.getBox().getHeight() ) )  , getHpLeft()/2, 0.1);
	}
	

}
