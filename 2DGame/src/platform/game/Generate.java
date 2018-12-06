package platform.game;
/**
 * Création de l'Interface Generate pour tous les objets amenés à être reproduits plusieurs fois : 
 *	-Mover
 *	-SnowFlake
 *	-SpikeIce
 *
 */
public interface Generate {

	public Generate generate ( Generate actor );

}
