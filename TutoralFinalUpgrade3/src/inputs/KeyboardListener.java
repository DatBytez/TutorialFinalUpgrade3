package inputs;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import helpz.Debug;

import managers.SceneManager;

public class KeyboardListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		SceneManager.getCurrentScene().getScene().keyTyped(e.getKeyChar());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_QUOTE) { // ~ key (same as ` key on most keyboards)
			Debug.toggle();
			return; // Prevent further processing
		}
		SceneManager.getCurrentScene().getScene().keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		SceneManager.getCurrentScene().getScene().keyReleased(e.getKeyCode());
	}
}
