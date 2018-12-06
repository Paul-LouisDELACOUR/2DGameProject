package platform.game;

/**
 * Différents types de dégats
 * FIRE : dégats infligés par les Fireball
 * PHYSICAL : caractérise un contact direct
 * AIR :représente une propulsion sans blessure 
 * VOID :pour les dommages majeurs entraînant la fin d’un niveau de jeu par exemple
 * HEAL: permet d'augmenter la vie d'un acteur
 * TOUCHES : dégat qui n'est réaliser seulement lors d'une collision.
 * TOPSPIKE : permet de modifier la vie d'un acteur lorsque des SpikeIce tombent sur lui.
 * ICE : modifie le frottement d'un acteur sur le sol.
 */
public enum Damage {
 FIRE , PHYSICAL, AIR, VOID, ACTIVATION, HEAL, TOUCHES, TOPSPIKE, ICE
}

