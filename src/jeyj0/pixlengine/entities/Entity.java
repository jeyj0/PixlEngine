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

		// divided into two so that the entity can slide at a wall
		if (canMoveTo(newX, yPos))
			xPos = newX;
		if (canMoveTo(xPos, newY))
			yPos = newY;
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

			int[][] corners = new int[][] {
					new int[] { (int) Math.floor(x), 
							(int) Math.floor(y) },
					
					new int[] { (int) Math.floor(x + getWidth()),
							(int) Math.floor(y + getHeight()) },
					
					new int[] { (int) Math.floor(x),
							(int) Math.floor(y + getHeight()) },
					
					new int[] { (int) Math.floor(x + getWidth()),
							(int) Math.floor(y) }, };

			for (int[] c : corners) {
				if (world.getTileAt(c[0], c[1]) == null
						|| world.getTileAt(c[0], c[1]).isSolid())
					return false;
			}

			// test tiles in-between start and end tile
			/*
			 * for (int lx = start[0]; lx <= end[0]; lx++) { for (int ly =
			 * start[1]; ly <= end[1]; ly++) { if (world.getTileAt(lx,
			 * ly).isSolid()) return false; } }
			 */

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
	 * @return The current speed of this entity
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed this entity should have
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
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
