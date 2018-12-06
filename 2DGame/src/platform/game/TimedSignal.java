package platform.game;

import platform.util.Box;
import platform.util.Input;

public class TimedSignal extends Actor implements Signal {

	private final double baseCooldown = 2;
	
	private double fixedCooldown = baseCooldown;
	private double cooldown = baseCooldown;
	
	private boolean active;
	public TimedSignal() {
		// TODO Auto-generated constructor stub
	}
	public TimedSignal(double baseCooldown) {
		if( baseCooldown < 0){
			this.fixedCooldown = baseCooldown;
		}
		this.fixedCooldown=baseCooldown;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return active;
	}
	public void decrementTimer(Input input){
		
		if(active){
			cooldown+=input.getDeltaTime();
			}
		else{
			cooldown-=input.getDeltaTime();
			}
		if(cooldown <= 0){
			active = true;
			
		}
		else if(cooldown >= fixedCooldown){
			active = false;
		}
	}
	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}
	@Override
	public void update(Input input) {
		// TODO Auto-generated method stub
		super.update(input);
		decrementTimer(input);
		System.out.println(cooldown);
	}
	

}
