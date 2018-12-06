package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Overlay extends Actor {

	private Player player;
	private World world;
	private double SIZE =0.15;
	
	public Overlay(Player player2) {
		this.player = player2;
	}

	@Override
	public int getPriority() {
		return 0;
	}
		
	public void update(Input input) {
		super.update(input) ;
		if (player.getWorld() == null){
			getWorld().unregister(this);
		}
		
	}
		
	public void draw (Input input, Output output){
	
		double health = 5.0 * player.getHealth() / player.getHealthMax();
		String name="";
		
		for (int i = 1; i <= 5; ++i) { 
				
			if (health >= i){
			name = "heart.full";
			
			} else if (health >= i - 0.5){
				name = "heart.half";
			} else {
			name = "heart.empty";
			}
			Sprite sprite = player.getSprite (name);
			if (sprite == null){
				super.draw(input, output);
			} else {		
				output.drawSprite(sprite ,getBox(0.15*i-0.45, 0.5) ) ;
			}
		}
		
		for (int i = 1; i <= player.getNumberOfFireball(); ++i) { 
			
			
			Sprite sprite = player.getSprite ("fireball");
			if (sprite == null){
				super.draw(input, output);
			} else {		
				output.drawSprite(sprite ,getBox(0.15*i-0.45, 0.7) ) ;
			}
		}

	}
		
	
	public Box getBox(){
		
		return getBox(0, 0);
	}
	public World getWorld(){
		return world;
	}
	
	public Box getBox(double decallageX,double decallageY){ //décallage par rapport à la position du personnage avec le center
		Vector center = player.getBox().getCenter().add(new Vector(decallageX,decallageY));
		return new Box(center, SIZE, SIZE);
	}
}
