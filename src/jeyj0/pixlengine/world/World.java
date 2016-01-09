package jeyj0.pixlengine.world;

import java.util.HashSet;
import java.util.Iterator;

import jeyj0.pixlengine.entities.Entity;
import jeyj0.pixlengine.tiles.Tile;

/**
 * <pre>
 * Coordinate-system:
 * 
 *  .  . -2  .  . 
 *  .  . -1  .  . 
 * -2 -1  0  1  2 
 *  .  .  1  .  . 
 *  .  .  2  .  .
 * </pre>
 */
public class World {

	private HashSet<TileField> tileFields;
	private HashSet<Entity> entities;

	public World() {
		tileFields = new HashSet<TileField>();
		entities = new HashSet<Entity>();
	}
	
	public HashSet<Tile> getTilesInRect(double x, double y, double width,
			double height) {
		HashSet<Tile> tiles = new HashSet<Tile>();
		
		
		
		return tiles;
	}

	public HashSet<TileField> getFieldsInRect(double x, double y, int width,
			int height) {
		HashSet<TileField> hs = new HashSet<TileField>();
		Iterator<TileField> it = tileFields.iterator();
		TileField f;

		while (it.hasNext()) {
			f = it.next();
			if (f.getX() >= (int) x - 1 && f.getY() >= (int) y - 1
					&& f.getX() <= (int) x + width
					&& f.getY() <= (int) y + height)
				hs.add(f);
		}

		return hs;
	}

	public void setTileAt(int x, int y, Tile t) {
		if (t == null)
			return;

		TileField f = getFieldAt(x, y);
		if (f != null) {
			f.setTile(t);
		} else {
			tileFields.add(new TileField(x, y, t));
		}
	}

	public Tile getTileAt(int x, int y) {
		TileField f = getFieldAt(x, y);
		if (f != null) {
			return f.getTile();
		} else {
			return null;
		}
	}

	public void removeTileAt(int x, int y) {
		TileField f = getFieldAt(x, y);
		if (f != null) {
			tileFields.remove(f);
		}
	}

	public TileField getFieldAt(int x, int y) {
		Iterator<TileField> it = tileFields.iterator();
		TileField f;
		while (it.hasNext()) {
			f = it.next();
			if (f.getX() == x && f.getY() == y)
				return f;
		}
		return null;
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public HashSet<Entity> getAllEntities() {
		return entities;
	}

	public boolean hasEntity(Entity e) {
		return entities.contains(e);
	}

	public HashSet<Entity> getEntitiesInRect(double x, double y, double width,
			double height) {
		HashSet<Entity> list = new HashSet<Entity>();
		Entity e;
		Iterator<Entity> it = entities.iterator();
		
		while (it.hasNext()) {
			e = it.next();
			
			

			// if any of the entities corners is in the rectangle...
			boolean is_left_x = e.getX() >= x && e.getX() < x + width;
			boolean is_right_x = e.getX() + e.getWidth() >= x
					&& e.getX() + e.getWidth() < x + width;
			boolean is_some_x = is_left_x || is_right_x;

			boolean is_left_y = e.getY() >= y && e.getY() < y + height;
			boolean is_right_y = e.getY() + e.getHeight() >= y
					&& e.getY() + e.getHeight() < y + height;
			boolean is_some_y = is_left_y || is_right_y;

			if (is_some_x && is_some_y) {
				// ... add it to the list
				list.add(e);
			}
		}

		return list;
	}
	
	class Rectangle {
		
		private double x;
		private double y;
		private double width;
		private double height;
		
		Rectangle(double x, double y, double width, double height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		
		public boolean intersects(Rectangle other) {
			Rectangle left;
			Rectangle right;
			Rectangle top;
			Rectangle bottom;
			
			if (this.x > other.x) {
				left = this;
				right = other;
			} else {
				left = other;
				right = this;
			}
			
			if (this.y > other.y) {
				top = this;
				bottom = other;
			} else {
				top = other;
				bottom = this;
			}
			
			if (left.x + left.width > right.x || bottom.y + bottom.height > top.y)
				return true;
			return false;
		}
		
	}

	public class TileField {

		private int x;
		private int y;
		private Tile tile;

		public TileField(int x, int y, Tile t) {
			this.x = x;
			this.y = y;
			this.tile = t;
		}

		public TileField(int x, int y) {
			this(x, y, null);
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public boolean setTile(Tile t) {
			if (tile != null)
				return false;
			this.tile = t;
			return true;
		}

		public Tile getTile() {
			return tile;
		}

		public void removeTile() {
			this.tile = null;
		}

	}

}
