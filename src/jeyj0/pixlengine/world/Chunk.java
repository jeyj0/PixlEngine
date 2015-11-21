package jeyj0.pixlengine.world;

import jeyj0.pixlengine.PixlEngine;

/**
 * Class to contain chunk info.
 * 
 * @author jeyj0
 */
public class Chunk {

	/**
	 * Contains all fields in the chunk
	 */
	private Field[][] fields;

	/**
	 * Instantiates a new Chunk
	 */
	public Chunk() {
		fields = new Field[PixlEngine.getChunkSize()][PixlEngine.getChunkSize()];

		// Instantiates all Fields in the chunk
		for (int xi = 0; xi < PixlEngine.getChunkSize(); xi++) {
			for (int yi = 0; yi < PixlEngine.getChunkSize(); yi++) {
				fields[xi][yi] = new Field();
			}
		}
	}

	/**
	 * Returns the field at the given location (relative to this chunk)
	 * 
	 * @param x
	 *            Chunk-relative horizontal coordinate of the field
	 * @param y
	 *            Chunk-relative vertical coordinate of the field
	 * @return The field at the given location or null on invalid argument
	 */
	public Field getField(int x, int y) {
		if (x < 0 || y < 0 || x >= PixlEngine.getChunkSize()
				|| y >= PixlEngine.getChunkSize())
			return null;
		return fields[x][y];
	}

	/**
	 * All fields in this chunk
	 * 
	 * @return Fields in this chunk
	 */
	public Field[][] getFields() {
		return fields;
	}

}
