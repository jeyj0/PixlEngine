package jannis.pixlengine.world;

import jannis.pixlengine.PixlEngine;

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
		for (Field[] field_i : fields) {
			for (Field field : field_i) {
				field = new Field();
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

}
