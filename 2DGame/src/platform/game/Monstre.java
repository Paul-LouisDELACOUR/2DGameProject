package platform.game;

import platform.util.Box;
import platform.util.Vector;

public class Monstre extends Actor implements Generate {

	private Player player;
	private double size;
	private Vector position;
	private Vector velocity;
	public static double standardVisionRadius = 7;
	public final static double standardMonstreMaxHp = 2;
	private double health = standardMonstreMaxHp;
	
	
	
	public Monstre() {
		// TODO Auto-generated constructor stub
	}

	public Monstre(Vector position, Vector velocity, double size,Player player) {
		this.position=position;
		this.size=size;
		this.velocity=velocity;
		this.player=player;
	}

	public Monstre(Vector position, Vector velocity, double size,Player player,double health) {
		this.position=position;
		this.size=size;
		this.velocity=velocity;
		this.player=player;
		this.health=health;
	}
	
	public Monstre(Generate monstre){
		this.position=((Monstre)monstre).position;
		this.size=((Monstre)monstre).size;
		this.velocity=((Monstre)monstre).velocity;
		this.player=((Monstre)monstre).player;
	}

	@Override
	public Generate generate(Generate monstre) {
		// TODO Auto-generated method stub
		return new Monstre(monstre);
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 30;
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		// TODO Auto-generated method stub
		
		switch (type) {
		case TOUCHES:
			if (doesActions()){
				actions();
				
			}
			return true;
		case FIRE:
			setHealth(getHealth() - amount);
			return true;

		default:

			return super.hurt(instigator, type, amount, location);
		}
	}
	public Vector playerDirection(){
		
		return player.getBox().getCenter().sub(getBox().getCenter());
	}
	@Override
	public Vector getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	public double getSize() {
		return size;
	}
	public boolean doesActions(){
		return true;
	}
	public void actions(){}
	
	public Box getVision(){
		
		return new Box(position, standardVisionRadius, standardVisionRadius);
		
	}
	public void setPosition(Vector position) {
		this.position = position;
	}
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	public Vector getVelocity() {
		return velocity;
	}
	public Box getBox() {
		
		return new Box(position, size, size );
	}
	public Player getPlayer() {
		return player;
	}
	public double getHealth() {
		return health;
	}

	public double getHealthMax() {
		return standardMonstreMaxHp;
	}

	public void setHealth(double hp) {

		if (hp > standardMonstreMaxHp) {

			health = standardMonstreMaxHp;
		} else if (hp <= 0) {

			getWorld().unregister(this);
		} else {
			health = hp;
		}
	}

	
}
