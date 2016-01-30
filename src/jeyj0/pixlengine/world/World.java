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

	/*
	 * TODO: Test ranges, especially double to int conversion!! (what if double
	 * width/height < 1???)
	 */

	/**
	 * Unordered container for all tiles in the world
	 */
	private HashSet<Tile> tiles;

	/**
	 * Unordered container for all entities in the world
	 */
	private HashSet<Entity> entities;

	/**
	 * Instantiates a new world object
	 */
	public World() {
		tiles = new HashSet<Tile>();
		entities = new HashSet<Entity>();
	}

	/**
	 * Adds the given entity into the world.
	 * 
	 * @param entity
	 *            The Entity to add into the world
	 */
	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	/**
	 * Sets the Tile at the given location to the Tile given. If there already
	 * is a Tile at that position returns false, true if succeeded.
	 * 
	 * @param x
	 *            The horizontal coordinate for the location
	 * @param y
	 *            The vertical coordinate for the location
	 * @param tile
	 *            The Tile to set at that location
	 * @return True on success or false if there already is a Tile at that
	 *         location
	 */
	public boolean setTileAt(int x, int y, Tile tile) {
		if (getTileAt(x, y) != null)
			return false;

		tiles.add(tile);
		return true;
	}

	/**
	 * Sets the Tile at the given location to the Tile given. If there already
	 * is a Tile at that position returns false, true if succeeded.
	 * 
	 * @param x
	 *            The horizontal coordinate for the location
	 * @param y
	 *            The vertical coordinate for the location
	 * @param tile
	 *            The Tile to set at that location
	 * @return True on success or false if there already is a Tile at that
	 *         location
	 */
	public boolean setTileAt(double x, double y, Tile tile) {
		return setTileAt((int) Math.floor(x), (int) Math.floor(y), tile);
	}

	/**
	 * @return A collection of all tiles in this world
	 */
	public HashSet<Entity> getAllEntities() {
		return entities;
	}

	/**
	 * Returns a collection of all entities that are with some part in the given
	 * range
	 * 
	 * @param x
	 *            The horizontal top-left coordinate of the range
	 * @param y
	 *            The vertical top-left coordinate of the range
	 * @param width
	 *            The width of the range
	 * @param height
	 *            The height of the range
	 * @return A collection containing all entities at least partly in the given
	 *         range
	 */
	public HashSet<Entity> getEntitiesInRect(double x, double y, double width,
			double height) {
		HashSet<Entity> temp_entities = new HashSet<Entity>();
		Iterator<Entity> it = entities.iterator();
		Entity entity;

		/*
		 * An entity is in the rectangle, when:
		 * 
		 * (top OR bottom) AND (left OR right) are in the rectangle
		 */

		// entity sides - values filled in on every loop
		double entity_top;
		double entity_bottom;
		double entity_left;
		double entity_right;

		// rectangle sides - constant
		double rect_top = y;
		double rect_bottom = y + height;
		double rect_left = x;
		double rect_right = x + width;

		while (it.hasNext()) {
			entity = it.next();

			// entity sides
			entity_top = entity.getY();
			entity_bottom = entity.getY() + entity.getHeight();
			entity_left = entity.getX();
			entity_right = entity.getX() + entity.getWidth();

			// if-preparation
			boolean vertical_inside = (entity_top >= rect_top && entity_top < rect_bottom)
					|| (entity_bottom > rect_top && entity_bottom <= rect_bottom);
			boolean horizontal_inside = (entity_left >= rect_left && entity_left < rect_right)
					|| (entity_right > rect_left && entity_right <= rect_right);

			// actual if
			if (vertical_inside && horizontal_inside) {
				temp_entities.add(entity);
			}
		}

		return temp_entities;
	}

	/**
	 * Returns a collection of all entities that are with some part in the given
	 * range
	 * 
	 * @param x
	 *            The horizontal top-left coordinate of the range
	 * @param y
	 *            The vertical top-left coordinate of the range
	 * @param width
	 *            The width of the range
	 * @param height
	 *            The height of the range
	 * @return A collection containing all entities at least partly in the given
	 *         range
	 */
	public HashSet<Entity> getEntitiesInRect(int x, int y, int width, int height) {
		return getEntitiesInRect((double) x, (double) y, (double) width,
				(double) height);
	}

	/**
	 * Whether or not the world contains the given entity at this point
	 * 
	 * @param entity
	 *            The entity to check for
	 * @return Whether the world contains the entity
	 */
	public boolean hasEntity(Entity entity) {
		return entities.contains(entity);
	}

	/**
	 * @return A collection of all tiles in this world
	 */
	public HashSet<Tile> getAllTiles() {
		return tiles;
	}

	/**
	 * Returns an unordered collection of Tiles that are in the given range of
	 * coordinates
	 * 
	 * @param x
	 *            The horizontal top-left coordinate of the range
	 * @param y
	 *            The vertical top-left coordinate of the range
	 * @param width
	 *            The width of the range
	 * @param height
	 *            The height of the range
	 * @return A collection containing all tiles in the given range
	 */
	public HashSet<Tile> getTilesInRect(int x, int y, int width, int height) {
		HashSet<Tile> temp_tiles = new HashSet<Tile>();
		Iterator<Tile> it = tiles.iterator();
		Tile tile;

		/*
		 * TODO The +1 only exists for wrong width/height conversion from double
		 * to int if the x/y is not exactly at a n.0 coordinate. Make this more
		 * exact for the n.0 cases and for direct int use.
		 */
		int maxTilesInRect = (width + 1) * (height + 1);

		int tilesCount = 0;

		while (it.hasNext() && tilesCount < maxTilesInRect) {
			tile = it.next();
			// Null-check since the position array can actually be set to null
			// on purpose, also from outside the engine
			if (tile.getPos() != null && tile.getX() >= x && tile.getY() >= y
					&& tile.getX() <= x + width && tile.getY() <= y + height) {
				temp_tiles.add(tile);
				tilesCount++;
			}
		}

		return temp_tiles;
	}

	/**
	 * Returns an unordered collection of Tiles that are in the given range of
	 * coordinates
	 * 
	 * @param x
	 *            The horizontal top-left coordinate of the range
	 * @param y
	 *            The vertical top-left coordinate of the range
	 * @param width
	 *            The width of the range
	 * @param height
	 *            The height of the range
	 * @return A collection containing all tiles in the given range
	 */
	public HashSet<Tile> getTilesInRect(double x, double y, double width,
			double height) {
		return getTilesInRect((int) Math.floor(x), (int) Math.floor(y),
				(int) Math.floor(width), (int) Math.floor(height));
	}

	/**
	 * Returns the Tile at the given position, or null if there is none
	 * 
	 * @param x
	 *            The horizontal coordinate
	 * @param y
	 *            The vertical coordinate
	 * @return The Tile at the position, or null if there is none
	 */
	public Tile getTileAt(int x, int y) {
		Iterator<Tile> it = tiles.iterator();
		Tile tile;

		while (it.hasNext()) {
			tile = it.next();
			// Null-check since the position array can actually be set to null
			// on purpose, also from outside the engine
			if (tile.getPos() != null && tile.getX() == x && tile.getY() == y)
				return tile;
		}
		return null;
	}

	/**
	 * Returns the Tile at the given position, or null if there is none
	 * 
	 * @param x
	 *            The horizontal coordinate
	 * @param y
	 *            The vertical coordinate
	 * @return The Tile at the position, or null if there is none
	 */
	public Tile getTileAt(double x, double y) {
		return getTileAt((int) Math.floor(x), (int) Math.floor(y));
	}

	/**
	 * Removes the given entity from the world
	 * 
	 * @param entity
	 *            The entity to remove
	 */
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}

	/**
	 * Removes the tile at the given location from the world
	 * 
	 * @param x
	 *            The horizontal coordinate
	 * @param y
	 *            The vertical coordinate
	 */
	public void removeTileAt(int x, int y) {
		Iterator<Tile> it = tiles.iterator();
		Tile tile;

		while (it.hasNext()) {
			tile = it.next();
			if (tile.getX() == x && tile.getY() == y) {
				tiles.remove(tile);
				tile.setPosToNull();
				return;
			}
		}
	}

	/**
	 * Removes the tile at the given location from the world
	 * 
	 * @param x
	 *            The horizontal coordinate
	 * @param y
	 *            The vertical coordinate
	 */
	public void removeTileAt(double x, double y) {
		removeTileAt((int) Math.floor(x), (int) Math.floor(y));
	}

	/**
	 * Removes all tiles in the rectangle provided
	 * 
	 * @param x
	 *            The top-left horizontal coordinate
	 * @param y
	 *            The top-left vertical coordinate
	 * @param width
	 *            The width (to the right) of the range to remove all tiles in
	 * @param height
	 *            The height (to the bottom) of the range to remove all tiles in
	 */
	public void removeTilesInRect(int x, int y, int width, int height) {
		Iterator<Tile> it = tiles.iterator();
		Tile tile;
		int maxTilesToRemove = width * height;
		int removedTiles = 0;

		while (it.hasNext() && removedTiles < maxTilesToRemove) {
			tile = it.next();
			if (tile.getX() >= x && tile.getX() <= x + width
					&& tile.getY() >= y && tile.getY() <= y + height) {
				tiles.remove(tile);
				tile.setPosToNull();
				maxTilesToRemove++;
			}
		}
	}

	/**
	 * Removes all tiles in the rectangle provided
	 * 
	 * @param x
	 *            The top-left horizontal coordinate
	 * @param y
	 *            The top-left vertical coordinate
	 * @param width
	 *            The width (to the right) of the range to remove all tiles in
	 * @param height
	 *            The height (to the bottom) of the range to remove all tiles in
	 */
	public void removeTilesInRect(double x, double y, double width,
			double height) {
		removeTilesInRect((int) Math.floor(x), (int) Math.floor(y),
				(int) Math.floor(width), (int) Math.floor(height));
	}

}
