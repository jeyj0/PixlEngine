package jeyj0.pixlengine.in;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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
	}

	/**
	 * @return The key listener
	 */
	public EKeyListener getKeyListener() {
		return keyListener;
	}

	/**
	 * A key listener class. Use reference of parent-class
	 * 
	 * @author jeyj0
	 */
	public class EKeyListener implements KeyListener {

		/**
		 * List of all suppoerted keys
		 */
		public ArrayList<Key> keys = new ArrayList<Key>();

		/**
		 * W key reference
		 */
		public Key W = new Key(KeyEvent.VK_W);

		/**
		 * S key reference
		 */
		public Key S = new Key(KeyEvent.VK_S);

		/**
		 * A key reference
		 */
		public Key A = new Key(KeyEvent.VK_A);

		/**
		 * D key reference
		 */
		public Key D = new Key(KeyEvent.VK_D);

		/**
		 * Instantiates a new listener-object
		 * 
		 * @param engine
		 *            The engine this listener is for
		 */
		public EKeyListener(PixlEngine engine) {
			engine.getFrame().addKeyListener(this);
			engine.addKeyListener(this);

			keys.add(W);
			keys.add(S);
			keys.add(A);
			keys.add(D);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			toggleKey(e.getKeyCode(), true);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			toggleKey(e.getKeyCode(), false);
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		/**
		 * Toggles given key's object to given value
		 * 
		 * @param keyCode
		 *            Key-Code to reference the key-object
		 * @param isPressed
		 *            Whether the key is pressed
		 */
		public void toggleKey(int keyCode, boolean isPressed) {
			switch (keyCode) {
			case KeyEvent.VK_W:
				W.toggle(isPressed);
				break;
			case KeyEvent.VK_S:
				S.toggle(isPressed);
				break;
			case KeyEvent.VK_A:
				A.toggle(isPressed);
				break;
			case KeyEvent.VK_D:
				D.toggle(isPressed);
				break;
			}
		}

		/**
		 * A virtual key abstraction
		 * 
		 * @author jeyj0
		 */
		public class Key {

			private boolean pressed = false;
			private int timesPressed = 0;
			private int keyCode;

			public Key(int keyCode) {
				this.keyCode = keyCode;
			}

			public int getKeyCode() {
				return keyCode;
			}

			/**
			 * @return Whether or not this key is currently pressed
			 */
			public boolean isPressed() {
				return pressed;
			}

			public void toggle(boolean isPressed) {
				if (isPressed)
					this.timesPressed++;
				this.pressed = isPressed;
			}

			public int getTimesPressed() {
				return timesPressed;
			}

		}

	}

}
