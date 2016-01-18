package jeyj0.pixlengine.in;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import jeyj0.pixlengine.PixlEngine;

/**
 * A key listener class. Use reference of parent-class
 * 
 * @author jeyj0
 */
public class EKeyListener implements KeyListener {

	/**
	 * List of all supported keys
	 */
	public ArrayList<Key> keys = new ArrayList<Key>();

	public Key W = new Key(KeyEvent.VK_W);
	public Key S = new Key(KeyEvent.VK_S);
	public Key A = new Key(KeyEvent.VK_A);
	public Key D = new Key(KeyEvent.VK_D);
	public Key SHIFT = new Key(KeyEvent.VK_SHIFT);

	/**
	 * Instantiates a new listener-object
	 * 
	 * @param engine
	 *            The engine this listener is for
	 */
	public EKeyListener(PixlEngine engine) {
		engine.addKeyListener(this);

		keys.add(W);
		keys.add(S);
		keys.add(A);
		keys.add(D);
		keys.add(SHIFT);
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
		case KeyEvent.VK_SHIFT:
			SHIFT.toggle(isPressed);
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
