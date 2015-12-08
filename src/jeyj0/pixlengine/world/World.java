package jeyj0.pixlengine.world;

import java.util.ArrayList;
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

	private ArrayList<Tile> tiles;
	private ArrayList<Entity> entities;

	public World() {
		tiles = new ArrayList<Tile>();
		entities = new ArrayList<Entity>();
	}

	public ArrayList<Tile> getTilesInRect(double x, double y, int width,
			int height) {
		ArrayList<Tile> list = new ArrayList<Tile>();

		Iterator<Tile> it = tiles.iterator();
		Tile t;
		while (it.hasNext()) {
			t = it.next();
			if (t.getX() >= x && t.getX() < x + width && t.getY() >= y
					&& t.getY() < y + height)
				list.add(t);
		}

		return list;
	}

	/*
	 * public void setTileAt(int x, int y, Tile t) { Iterator<Tile> it =
	 * tiles.iterator(); Tile t; while (it.hasNext()) { t = it.next(); if
	 * (t.getX() == x && t.getY() == y) { return; } }
	 * 
	 * 
	 * }
	 */

	/*
	 * public Tile getTileAt(int x, int y) { Iterator<Field> it =
	 * fields.iterator(); Field f; while (it.hasNext()) { f = it.next(); if
	 * (f.getX() == x && f.getY() == y) return f.getTile(); }
	 * 
	 * return null; }
	 */

	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public ArrayList<Entity> getAllEntities() {
		return entities;
	}
	
	public boolean hasEntity(Entity e) {
		return entities.contains(e);
	}

	public ArrayList<Entity> getEntitiesInRect(double x, double y, int width,
			int height) {
		ArrayList<Entity> list = new ArrayList<Entity>();
		Entity e;
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			e = it.next();

			// if any of the entities corners is in the rectangle...
			boolean is_left_x = e.getX() >= x && e.getX() < x + width;
			boolean is_right_x = e.getX() + e.getWidth() >= x && e.getX() + e.getWidth() < x + width;
			boolean is_some_x = is_left_x || is_right_x;
			
			boolean is_left_y = e.getY() >= y && e.getY() < y + height;
			boolean is_right_y = e.getY() + e.getHeight() >= y && e.getY() + e.getHeight() < y + height;
			boolean is_some_y = is_left_y || is_right_y;

			if (is_some_x && is_some_y) {
				// ... add it to the list
				list.add(e);
			}
		}

		return entities;
		// return list;
	}

	class Field {

		private int x;
		private int y;
		private Tile tile;

		public Field(int x, int y, Tile t) {
			this.x = x;
			this.y = y;
			this.tile = t;
		}

		public Field(int x, int y) {
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
