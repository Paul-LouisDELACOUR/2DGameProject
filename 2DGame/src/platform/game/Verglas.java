package platform.game;

import platform.util.Box;
import platform.util.Sprite;
import platform.util.Vector;
/**
 * 
 * Acteur qui hérite de seasonBlock et qui permet de retirer le frottement du joueur. Ainsi, on ajouter une methode SetAdditiveFriction()
 *
 */
public class Verglas extends SeasonBlock implements Season{

	private StaticSignal currentSeason = new StaticSignal();
	
	public Verglas(Box box, Sprite summerSprite,Sprite winterSprite) {
		super(box, summerSprite,winterSprite);
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {

		switch (type) {

		case ICE:
			if(currentSeason.isActive() && instigator instanceof Player){
				((Player)instigator).setAdditiveFriction(0.999);
				return true;
			}
			

		default:

			return super.hurt(instigator, type, amount, location);
		}
	}
}
