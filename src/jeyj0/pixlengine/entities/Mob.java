package jeyj0.pixlengine.entities;

import jeyj0.pixlengine.world.World;

/**
 * A class for all kind of "living" things, so called Mobs.
 * 
 * @author jeyj0
 */
public abstract class Mob extends Entity {

	/**
	 * Instantiates a new Mob
	 * @param world The world the Mob is supposed to be created in
	 * @param xPos The x-coordinate the Mob is supposed to be created at
	 * @param yPos The y-coordinate the Mob is supposed to be created at
	 * @param width The width this Mob is supposed to have
	 * @param height The height this Mob is supposed to have
	 */
	public Mob(World world, double xPos, double yPos, double width, double height) {
		super(world, xPos, yPos, width, height);
	}

}
