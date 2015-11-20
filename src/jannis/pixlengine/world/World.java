package jannis.pixlengine.world;

import jannis.pixlengine.PixlEngine;

/**
 * A world class to contain all world info.
 * 
 * @author jeyj0
 */
public class World {

	/**
	 * Contains all chunks in the World-instance
	 */
	private Chunk[][] chunks;

	/**
	 * Instantiates a new World-Object
	 * 
	 * @param x
	 *            Amount of horizontal chunks
	 * @param y
	 *            Amount of vertical chunks
	 */
	public World(int x, int y) {
		chunks = new Chunk[x][y];

		// Instantiate all chunks
		for (Chunk[] chunk_i : chunks) {
			for (Chunk chunk : chunk_i) {
				chunk = new Chunk();
			}
		}
	}

	/**
	 * Returns the chunk for the given chunk coordinates
	 * 
	 * @param x
	 *            The chunk's horizontal chunk coordinate
	 * @param y
	 *            The chunk's vertical chunk coordinate
	 * @return The chunk at the given chunk coordinates or null on invalid
	 *         argument
	 */
	public Chunk getChunk(int x, int y) {
		if (x < 0 || y < 0 || x >= chunks.length || y >= chunks[x].length)
			return null;
		return chunks[x][y];
	}

	/**
	 * Returns the field at the given location
	 * 
	 * @param x
	 *            The horizontal coordinate
	 * @param y
	 *            The vertical coordinate
	 * @return The field at the given position or null on invalid argument
	 */
	public Field getField(int x, int y) {
		if (x < 0 || y < 0)
			return null;

		// get the chunk x-coordinate
		int chunkX = (x - x % PixlEngine.getChunkSize())
				/ PixlEngine.getChunkSize();
		if (chunkX >= chunks.length)
			return null;

		// get the chunk y-coordinate
		int chunkY = (y - y % PixlEngine.getChunkSize())
				/ PixlEngine.getChunkSize();
		if (chunkY >= chunks[chunkX].length)
			return null;

		return chunks[chunkX][chunkY].getField(x % PixlEngine.getChunkSize(), y
				% PixlEngine.getChunkSize());
	}

}
