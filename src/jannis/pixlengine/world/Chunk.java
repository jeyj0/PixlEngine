package jannis.pixlengine.world;

import jannis.pixlengine.PixlEngine;

public class Chunk {

	private Field[][] fields;
	
	public Chunk() {
		fields = new Field[PixlEngine.getChunkSize()][PixlEngine.getChunkSize()];
		
		for (Field[] field_i : fields) {
			for (Field field : field_i) {
				field = new Field();
			}
		}
	}

	public Field getField(int x, int y) {
		return fields[x][y];
	}

	public void setField(int x, int y, Field field) {
		fields[x][y] = field;
	}
	
}
