package jeyj0.pixlengine.in;

import jeyj0.pixlengine.PixlEngine;

/**
 * A handler for user inputs. Please use reference from PixlEngine-object
 * 
 * @author jeyj0
 */
public class InputHandler {

	/**
	 * The engine this handler is for
	 */
	private PixlEngine engine;

	/**
	 * The key listener of this handler
	 */
	private EKeyListener keyListener;

	/**
	 * The mouse listener of this handler
	 */
	private EMouseListener mouseListener;

	/**
	 * Instantiates a new handler object
	 * 
	 * @param engine
	 *            The engine to create this handler for
	 */
	public InputHandler(PixlEngine engine) {
		this.engine = engine;
		registerListeners();
	}

	/**
	 * Helper method to register the child-listeners to the correct objects
	 */
	private void registerListeners() {
		keyListener = new EKeyListener(engine);
		mouseListener = new EMouseListener(engine);
	}

	/**
	 * @return The key listener
	 */
	public EKeyListener getKeyListener() {
		return keyListener;
	}

	/**
	 * @return The mouse listener
	 */
	public EMouseListener getMouseListener() {
		return mouseListener;
	}

}
