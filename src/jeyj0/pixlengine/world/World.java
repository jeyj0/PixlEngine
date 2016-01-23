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
	 * @return A collection of all tiles in this world
	 */
	public HashSet<Tile> getAllTiles() {
		return tiles;
	}

	/**
	 * @return A collection of all tiles in this world
	 */
	public HashSet<Entity> getAllEntities() {
		return entities;
	}

	/**
	 * Removes the tile at the given location from the world
	 * 
	 * @param x
	 * @param y
	 */
	public void removeTileAt(int x, int y) {
		/*
		 * TODO set position of removed tile to null (but int can't be null)
		 */

		Iterator<Tile> it = tiles.iterator();
		Tile tile;

		while (it.hasNext()) {
			tile = it.next();
			if (tile.getX() == x && tile.getY() == y) {
				tiles.remove(tile);
				return;
			}
		}
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

}
