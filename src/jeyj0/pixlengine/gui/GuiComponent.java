package jeyj0.pixlengine.gui;

import jeyj0.pixlengine.LoadedImage;

/**
 * @author jeyj0
 */
public class GuiComponent {

	private int left;
	private int top;
	private boolean isFront;
	protected LoadedImage graphics;

	public GuiComponent(LoadedImage graphics, int left, int top, boolean isFront) {
		this.left = left;
		this.top = top;
		this.graphics = graphics;
		this.isFront = isFront;
	}
	
	public GuiComponent(LoadedImage graphics, int left, int top) {
		this(graphics, left, top, true);
	}
	
	public GuiComponent(LoadedImage graphics) {
		this(graphics, 0, 0, true);
	}

	public LoadedImage getGraphics() {
		return graphics;
	}

	public int getLeft() {
		return left;
	}
	
	public int getTop() {
		return top;
	}

	public boolean isFront() {
		return isFront;
	}
	
}
