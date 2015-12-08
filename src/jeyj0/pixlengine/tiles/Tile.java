package jeyj0.pixlengine.tiles;

/**
 * Superclass for all tiles (Basic field info)
 * 
 * @author jeyj0
 */
public abstract class Tile {

	private int x;
	private int y;
	
	private boolean solid;
	private boolean seeThrough;
	
	public Tile(boolean isSolid, boolean isSeeThrough) {
		this.solid = isSolid;
		this.seeThrough = isSeeThrough;
	}
	
	public boolean getSolid() {
		return solid;
	}
	
	public boolean getSeeThrough() {
		return seeThrough;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract int getImageId();
	
}
