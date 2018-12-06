package platform.game;

import platform.util.Box;
import platform.util.Vector;

/**
 * MeUtil permet de créer des blocs qui sont sont formés de 3 sous-blocs.
 * Le But est d'avoir un seul bloc qui soit plus naturel.
 *
 * L'utilisateur entre les coordonnées du bloc qu'il veut créer ainsi que la taille qu'il souhaite.
 * Les 3 sous-blocs ont la même coordonnée 'y', seul leurs coordonnées 'x' varient.
 * Le sous-bloc gauche est centré sur le centre 'x' moins le tiers de la largeur
 * Le sous-bloc droit est centré sur le centre 'x' plus le tiers de la largeur.
 * Le sous-bloc central à le même centre que le bloc général.
 * Ces 3 sous-blocs ont également tous la même largeur littleWidth, et la même hauteur Height.
 *
 */

public class MeUtil {

	static public void createSeasonBlock(double centerX, double centerY, double width, double height, World world) {

		double littleWidth = width/3;
		world.register(new SeasonBlock(new Box(new Vector(centerX - width / 3, centerY), littleWidth, height),
				world.getLoader().getSprite("grass.left"), world.getLoader().getSprite("snowLeft")));

		world.register(new SeasonBlock(new Box(new Vector(centerX, centerY), littleWidth, height),
				world.getLoader().getSprite("grass.middle"), world.getLoader().getSprite("snowMid")));

		world.register(new SeasonBlock(new Box(new Vector(centerX + width / 3, centerY),littleWidth, height),
				world.getLoader().getSprite("grass.right"), world.getLoader().getSprite("snowRight")));

	}
}
