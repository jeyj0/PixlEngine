package jeyj0.pixlengine.entities;

/**
 * A class for all kind of entities
 * 
 * @author jeyj0
 */
public abstract class Entity {

	private double xPos;
	private double yPos;
	private int width;
	private int height;

	public Entity(double xPos, double yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
	}

	protected void addX(double toAdd) {
		xPos = xPos + toAdd;
	}
	
	protected void addY(double toAdd) {
		yPos = yPos + toAdd;
	}
	
	public double getX() {
		return xPos;
	}

	public double getY() {
		return yPos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double[] getBounds() {
		return new double[] { xPos, yPos, (double) width, (double) height };
	}

	public abstract void tick();

	public int getImageId() {
		return -1;
	}

}
