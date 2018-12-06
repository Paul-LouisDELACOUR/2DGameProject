package platform.game;

import platform.util.Vector;
/**
 * C’est une classe comprenant tous les tests que nous sommes amenés à réaliser lors de 
 * la création d’un objet.
 *
 */
public class Test {

	/**
	 * Permet de tester si la vitesse est supérieur à la vitesse maximale (maxVelocity).
	 * Dans ce cas, la vitesse sera mise à la vitesse maximale. 
	 */
	public static void testVelocity( Vector velocity, Vector maxVelocity){
		if (velocity.getX() > maxVelocity.getX()||velocity.getX() > maxVelocity.getX()){
			velocity = maxVelocity;
		} 
	}
	
	/**
	 * Permet de tester si le vecteur position n'est pas nul.
	 */
	public static void testPosition( Vector position){
		if (position == null){
			throw new NullPointerException();
		} 
	}
	
	/**
	 * Permet de tester si la fréquence n'est pas égale à 0
	 */
	public static void testFrequency(double frequency){
		if (frequency<=0){
			throw new NullPointerException();
		}
	}
	
	/**
	 * Permet de tester si un attribut double n'est pas égale à 0.
	 * C'est un test util pour la size des différents objets.
	 */
	public static void testDouble (double x){
		if (x==0){
			throw new NullPointerException();
		}
	}
	

}
