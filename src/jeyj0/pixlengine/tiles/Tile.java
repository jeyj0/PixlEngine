package jeyj0.pixlengine.tiles;

/**
 * Superclass for all tiles (Basic field info)
 * 
 * @author jeyj0
 */
public abstract class Tile {

	/**
	 * Position of this Tile
	 */
	private int[] pos;

	/**
	 * Whether or not this Tile is solid. This toggles collisions.
	 */
	private boolean solid;

	/**
	 * Whether or not this Tile is transparent. Does not change anything yet.
	 */
	private boolean seeThrough;

	/**
	 * The loaded image id to use to display this Tile on the screen
	 */
	protected int imgID;

	/**
	 * Instantiates a new tile-object
	 * 
	 * @param imgID
	 *            The loaded image id to use for displaying this tile
	 * @param isSolid
	 *            Whether or not this tile is solid
	 * @param isSeeThrough
	 *            Whether or not this tile is transparent
	 */
	public Tile(int imgID, boolean isSolid, boolean isSeeThrough, int xPos,
			int yPos) {
		this.imgID = imgID;
		this.solid = isSolid;
		this.seeThrough = isSeeThrough;
		this.pos = new int[] { xPos, yPos };
	}

	/**
	 * @return Whether this tile is solid
	 */
	public boolean isSolid() {
		return solid;
	}

	/**
	 * @return Whether this tile is transparent
	 */
	public boolean isSeeThrough() {
		return seeThrough;
	}

	/**
	 * @return The loaded image id to use for displaying this tile. Can be
	 *         overridden for animations.
	 */
	public int getImageId() {
		return imgID;
	}

	/**
	 * Warning: This should not be called from outside! Call this over
	 * world-object!
	 * 
	 * Set the horizontal position of this tile
	 * 
	 * @param x
	 *            The horizontal coordinate
	 */
	public void setX(int x) {
		pos[0] = x;
	}

	/**
	 * The horizontal coordinate of this Tile's position
	 * 
	 * @return int The coordinate
	 */
	public int getX() {
		return pos[0];
	}

	/**
	 * Warning: This should not be called from outside! Call this over
	 * world-object!
	 * 
	 * Set the vertical position of this tile
	 * 
	 * @param x
	 *            The vertical coordinate
	 */
	public void setY(int y) {
		pos[1] = y;
	}

	/**
	 * The vertical coordinate of this Tile's position
	 * 
	 * @return int The coordinate
	 */
	public int getY() {
		return pos[1];
	}

	/**
	 * Sets the position of this tile
	 * 
	 * @param x
	 *            The x-coordinate
	 * @param y
	 *            The y-coordinate
	 */
	public void setPos(int x, int y) {
		setX(x);
		setY(y);
	}

	/**
	 * Sets the position array for this tile to null, so it can't be mistakenly
	 * rendered as a Tile that is still in the world.
	 */
	public void setPosToNull() {
		this.pos = null;
	}

	/**
	 * The position of this tile
	 * 
	 * @return int[2]{x,y} The Position of this tile
	 */
	public int[] getPos() {
		return pos;
	}

}
