package platform.game;
/**
 * 
 * Le seul signal static qui permet que chaque acteur puisse savoir si on est en ete ou en hivers
 *
 */
public class StaticSignal implements Signal {

	private static boolean isActive;
	
	public StaticSignal() {
		
	}
	
	public StaticSignal(Boolean active) {
		isActive = active;
	}

	@Override
	public boolean isActive() {

		return isActive;
	}
	
	public void SetIsActive(boolean active){
		
		isActive=active;
		
	}

}
