package jeyj0.pixlengine.entities;

import jeyj0.pixlengine.world.World;

/**
 * A class for all kind of entities
 * 
 * @author jeyj0
 */
public abstract class Entity {

	/**
	 * A world reference for the world the entity is in
	 */
	private World world;

	/**
	 * The x-Coordinate of this entity
	 */
	private double xPos;

	/**
	 * The y-Coordinate of this entity
	 */
	private double yPos;

	/**
	 * The width of this entity
	 */
	private double width;

	/**
	 * The height of this entity
	 */
	private double height;

	/**
	 * The current moving speed of this entity
	 */
	private double speed = 1D / 32;

	/**
	 * Whether this entity is able to move through solid objects. Default is
	 * false.
	 */
	private boolean canMoveThroughSolid = false;

	/**
	 * Instantiates a new Entity-Object
	 * 
	 * @param world
	 *            The world the entity is supposed to be in
	 * @param xPos
	 *            The x-coordinate the entity is supposed to be created at
	 * @param yPos
	 *            The y-coordinate the entity is supposed to be created at
	 * @param width
	 *            The width of this entity
	 * @param height
	 *            The height of this entity
	 */
	public Entity(World world, double xPos, double yPos, double width,
			double height) {
		this.world = world;
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
	}

	/**
	 * Attempts to move the entity at the given amount
	 * 
	 * @param x
	 *            The x amount to attempt to move
	 * @param y
	 *            The y amount to attempt to move
	 */
	protected void move(int x, int y) {
		double newX = xPos + (double) x * speed;
		double newY = yPos + (double) y * speed;

		if (canMoveTo(newX, newY)) {
			xPos = newX;
			yPos = newY;
		}
	}

	/**
	 * Helper method to determine whether the entity can move to a defined
	 * position
	 * 
	 * @param x
	 *            The x-coordinate of that position
	 * @param y
	 *            The y-coordinate of that position
	 * @return boolean Whether the entity is able to move to that position
	 */
	private boolean canMoveTo(double x, double y) {
		// if canMoveThroughSolid then tiles are unnecessary
		if (!canMoveThroughSolid) {

			for (int loopX = 0; loopX <= getWidth(); loopX++) {
				// TODO re-do condition

				for (int loopY = 0; loopY <= getHeight(); loopY++) {

					int xCoord = (int) x + loopX;
					int yCoord = (int) y + loopY;

					if (world.getTileAt(xCoord, yCoord) != null // handle
																// NullPointerException
							&& world.getTileAt(xCoord, yCoord).getSolid())
						return false;
				}
			}

		}

		return true;
	}

	/**
	 * @return The current x-coordinate of this entity
	 */
	public double getX() {
		return xPos;
	}

	/**
	 * @return The current y-coordinate of this entity
	 */
	public double getY() {
		return yPos;
	}

	/**
	 * @return The width of this entity
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @return The height of this entity
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @return The bounds of this entity as double array: { xPos, yPos, width,
	 *         height }
	 */
	public double[] getBounds() {
		return new double[] { xPos, yPos, (double) width, (double) height };
	}

	/**
	 * @return The id of the loaded image to use for displaying. Should be
	 *         overridden by extending classes.
	 */
	public int getImageId() {
		return -1;
	}

	/**
	 * @return Whether or not this entity is able to move through solid objects
	 */
	public boolean getCanMoveThroughSolid() {
		return canMoveThroughSolid;
	}

	/**
	 * Called on every game tick
	 */
	public abstract void tick();

}
