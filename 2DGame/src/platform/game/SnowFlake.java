package platform.game;
import java.util.Random;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

/**
 * 
 * Un acteur qui agit comme un backround mais qui est mobile. Il peut etre creer aleatoirement au-dessus 
 * de la vision du joueur et peut etre generer pour donner l'illusion quil neige
 *
 */


public class SnowFlake extends Actor implements Generate,Season{
	
	public static final double standardSnowFlakeSize = 0.1;
	public static final double standardSnowflakeApparition = 0.095;
	public static final Vector standardSnowflakeGravity= new Vector(0.0, -2);
	
	private Vector position;
	private Vector velocity;
	private double size;
	private static final Vector maxVelocity = new Vector (10,10);
	private double variation = 0;
	private Vector move = new Vector (0.01,0);
	private Vector acceleration = standardSnowflakeGravity ;
	private double frequency;
	private double temps1;
	private double temps2;
	private double temps3;
	private Signal winter;
	private Player player;
	
	/**
	 * Utilisation de la fonction rand.nexDouble() qui donne un nombre entre 0 et 1 
	 * pour avoir une position aléatoire de l'objet.
	 */
	public SnowFlake (Vector velocity, double size,Signal winter,Player player) {
		
		Random rand = new Random();
		this.player=player;
		this.position = new Vector(((rand.nextDouble()*2*Simulator.standardPlayerVisionRadius) - (rand.nextDouble()*2*Simulator.standardPlayerVisionRadius))+player.getPosition().getX() , player.getPosition().getY() + Simulator.standardPlayerVisionRadius);
		Test.testVelocity(velocity, maxVelocity);
		this.velocity=velocity;
		Test.testDouble(size);
		this.size=size;
		
		this.winter=winter;
		
		frequency=1.2;
		temps1=frequency*3/4;
		temps2=frequency*1/2;
		temps3=frequency*1/4;
	}
		
	
	public SnowFlake(Generate snowFlake) { // Il ne s'agit pas d'un vrai constructeur de copy car on veut que sa position soit aleatoir autour du joueur
		Random rand = new Random();
		this.player=((SnowFlake)snowFlake).player;
		this.position = new Vector(((rand.nextDouble()*2*Simulator.standardPlayerVisionRadius) - (rand.nextDouble()*2*Simulator.standardPlayerVisionRadius))+player.getPosition().getX() , player.getPosition().getY() + Simulator.standardPlayerVisionRadius);
		this.velocity= ((SnowFlake)snowFlake).velocity; //(comentaire d'au dessus) on creer un intervalle de probabilite uniforme  ou la neige peut etre 
		this.size= ((SnowFlake)snowFlake).size;//creer seulement dans une position a la limite de sa vision en hauteur. standardPlayerVisionRadiu correspond au radius de la vision du joueur
		frequency=1.2;							//et (2 * ce radius) pour que les flocons soit créés avant que le Player ne puisse les voir  
		temps1=frequency*3/4;
		temps2=frequency*1/2;
		temps3=frequency*1/4;
		this.winter=((SnowFlake)snowFlake).winter;
	}

	public int getPriority() {
		return 0;
	}
	
	/**
	 * On considère 4 intervalles de temps régulier
	 *  [0 temps1], [temps1, temps2], [temps2, temps3], [temps3, frequency]
	 *  L'objet SnowFlake est attiré par gravité de la même manière que pour une Fireball.
	 *  Cependant pour lui ajouter une descente sinusoïdale, on a pris en compte un petit vecteur horizontale : move.
	 *   [0 temps1] : la particule est décalé vers la droite par ajout de move.
	 *   [temps1, temps2] : la particule revient à sa position centrale par soustraction de move.
	 *   [temps2, temps3] : la particule est décalée vers la gauche par soustraction de move.
	 *   [temps3, frequency] : la particule revient à sa position centrale par ajout de move.
	 *   Cette séquence est répétée une fois que les 4 intervalles ont été parcourus en réinitialisant 
	 *   la variation (temps écoulé depuis la création du SnowFlake) à frequency
	 */
	public void update (Input input){
		super.update(input);
		
		double delta = input.getDeltaTime() ;
		velocity = velocity.add(acceleration.mul(delta)) ;
		position = position.add(velocity.mul(delta)) ;
		
		
		if (variation>= temps1 ){
			position=position.add(move);
		} else if (variation< temps1 && variation>=temps2){
			position=position.sub(move);
		} else if (variation<temps2 && variation>=temps3){
			position=position.sub(move);
		} else {
			position=position.add(move);
		}
		
		variation -= input.getDeltaTime();
		
		if (variation < 0.0){
			variation = frequency;
			
		}	
	}
	
	public void draw(Input input, Output output){
		
		
		if (winter.isActive()){
			Sprite snowFlake = getSprite ("sun");
			
				if (snowFlake == null){
					super.draw(input, output);
				} else {		
					output.drawSprite(snowFlake , getBox()) ;
				}
		}
			
					
	}
		
	
	public Box getBox(){
		return new Box(position, size, size);
	}
	

	public Generate generate( Generate snowFlake) {
		return new SnowFlake(snowFlake);
	}
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {

		switch (type) {
		
		
		case VOID:
			getWorld().unregister(this);
			return true;
		
		default:
			return super.hurt(instigator, type, amount, location);
		}
	}
	
}
