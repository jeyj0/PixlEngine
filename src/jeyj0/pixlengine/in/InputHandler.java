package jeyj0.pixlengine.in;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import jeyj0.pixlengine.PixlEngine;

public class InputHandler implements KeyListener {

	public Key W = new Key();
	public Key S = new Key();
	public Key A = new Key();
	public Key D = new Key();
	
	public InputHandler(PixlEngine engine) {
		engine.addKeyListener(this);
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
	
	public class Key {
		
		private boolean pressed = false;
		private int timesPressed = 0;
		
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
