package platform.game;

import platform.util.Box;
import platform.util.Vector;

public class FixedLever extends Lever {

	Actor actor;
	private double decallage;
	
	public FixedLever(Actor actor) {
		super();
		this.actor=actor;
		// TODO Auto-generated constructor stub
	}
	
	public FixedLever(double decallage, Actor actor) {
		super();
		this.decallage=decallage;
		this.actor=actor;
		// TODO Auto-generated constructor stub
	}

	public FixedLever(Vector position, double duration,Actor actor) {
		super(position, duration);
		this.actor=actor;
	}
	@Override
	public Box getBox() {
		// TODO Auto-generated method stub
		return new Box( actor.getBox().getCenter().add(  new Vector(decallage, ((actor.getBox().getHeight()+Lever.SIZE)/2) )) ,Lever.SIZE,Lever.SIZE);
//		return actor.getBox().add( new Vector(0, Lever.SIZE));
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
}
