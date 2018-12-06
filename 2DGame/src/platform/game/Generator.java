package platform.game;

import platform.util.Input;
import platform.util.Vector;
/**
 * La classe Generator permet de recréer des objets à des intervalles de temps régulier.
 * Elle prend donc en paramètre le type de l'objet et la fréquence à laquelle il doit être recréer.
 *
 */
public class Generator extends Actor {


	private double frequency;
	private double timer;
	
	private Generate actor;
	
	public Generator(Generate actor, double frequency ) {
		
		this.frequency=frequency;
		this.timer=-1;
		this.actor = actor;
	}


	@Override
	public int getPriority() {
		return Integer.MAX_VALUE;
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public void update(Input input){

		super.update(input) ;
		
		double delta = input.getDeltaTime() ;

		timer-= delta;
		
		if(timer<0){
			getWorld().register((Actor) actor.generate(actor));
			timer=frequency;
		}
		
	}

}
