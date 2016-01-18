package jeyj0.pixlengine.in;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import jeyj0.pixlengine.PixlEngine;
import jeyj0.pixlengine.tiles.Tile;
import jeyj0.pixlengine.tiles.ITileClickable;

/**
 * @author jeyj0
 */
public class EMouseListener implements MouseListener {

	private PixlEngine engine;
	
	public EMouseListener(PixlEngine engine) {
		this.engine = engine;
		engine.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Tile t = engine.getWorld().getTileAt(eventCoordToGameCoord(e.getX()),
				eventCoordToGameCoord(e.getY()));
		
		if (t instanceof ITileClickable)
			((ITileClickable) t).clicked();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	private int eventCoordToGameCoord(int i1) {
		return i1 / engine.getScaleFactor() / engine.getPixelsPerField();
	}

}
