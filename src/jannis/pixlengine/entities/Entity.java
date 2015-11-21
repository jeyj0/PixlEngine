package jannis.pixlengine.entities;

/**
 * General entity class. Entities are movable objects not bound to integer
 * coordinates.
 * 
 * @author jeyj0
 */
public class Entity {

	/**
	 * The horizontal coordinate relative to the world the entity is in
	 */
	protected double xCoord;

	/**
	 * The vertical coordinate relative to the world the entity is in
	 */
	protected double yCoord;

	/**
	 * Instantiates a new Entity-object
	 * 
	 * @param x
	 *            The horizontal position
	 * @param y
	 *            The vertical position
	 */
	public Entity(double x, double y) {
		this.setPosX(x);
		this.setPosY(y);
	}

	/**
	 * Instantiates a new Entity-object
	 * 
	 * @param x
	 *            The horizontal position
	 * @param y
	 *            The vertical position
	 */
	public Entity(int x, int y) {
		this.setPosX((double) x);
		this.setPosY((double) y);
	}

	/**
	 * Instantiates a new Entity-object
	 * 
	 * @param x
	 *            The horizontal position
	 * @param y
	 *            The vertical position
	 */
	public Entity(float x, float y) {
		this.setPosX((double) x);
		this.setPosY((double) y);
	}

	/**
	 * The horizontal position of this entity, relative to the world
	 * 
	 * @return x-coordinate
	 */
	public double getPosX() {
		return xCoord;
	}

	/**
	 * Sets the horizontal position of this entity, relative to the world
	 * 
	 * @param xCoord
	 *            X-coordinate to set
	 */
	public void setPosX(double xCoord) {
		this.xCoord = xCoord;
	}

	/**
	 * The vertical position of this entity, relative to the world
	 * 
	 * @return y-coordinate
	 */
	public double getPosY() {
		return yCoord;
	}

	/**
	 * Sets the vertical position of this entity, relative to the world
	 * 
	 * @param yCoord
	 *            Y-coordinate to set
	 */
	public void setPosY(double yCoord) {
		this.yCoord = yCoord;
	}

}
