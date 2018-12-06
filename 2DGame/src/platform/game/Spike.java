package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;
public class Spike extends Actor {

	private Vector position;
	private double WIDTH=1;
	private double HEIGHT=1;
	private double baseDamage=0.1;
	private Mover mover;
	private Sprite sprite;
	
	public Spike(Vector position, double WIDTH, double HEIGHT) {
		this.position=position;
		this.WIDTH=WIDTH;
		this.HEIGHT = HEIGHT;
	}

	public Spike(Vector position) {
		this.position=position;
	}
	
	public Spike(Mover mover, double WIDTH, double HEIGHT, double baseDamage) {
		this.position = mover.getPosition();
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.baseDamage = baseDamage;
		this.mover = mover;
	}
	
	public Spike(Mover mover, double WIDTH, double HEIGHT, double baseDamage, Sprite sprite) {
		this.position = mover.getPosition();
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.baseDamage = baseDamage;
		this.mover = mover;
		this.sprite = sprite;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 700;
	}
	
	public void update(Input input) {
		super.update(input) ;
		if(mover!=null){
		position = mover.getPosition().add( new Vector (0, mover.getBox().getHeight()/2 )
				.add(new Vector (0, this.getBox().getHeight()/2)));
		}
	}
	
	public void interact (Actor actor){
		
		super.interact(actor);
		
		
		if ( (this.getBox().isColliding(actor.getPosition() )) && (actor.hurt(this, Damage.PHYSICAL, baseDamage, position))) {
		
		}
			
	}
	
	public void draw(Input input, Output output){
		
		if (sprite == null){
			sprite = getSprite ("spikes");
			
			if (sprite == null){
				super.draw(input, output);
			}else{
				output.drawSprite(sprite , getBox());
			}
			
		} else{
			output.drawSprite(sprite , getBox());
		}
	}
	
	public Box getBox(){
		return new Box(position, WIDTH, HEIGHT);
	}

}
