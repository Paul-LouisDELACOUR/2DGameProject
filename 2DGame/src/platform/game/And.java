package platform.game;

public class And implements Signal {

	private Signal left;
	private Signal right;

	public And(Signal left, Signal right) {
		this.left=left;
		this.right=right;
	}

	@Override
	public boolean isActive() {
		return left.isActive() && right.isActive() ;		
	}

}
