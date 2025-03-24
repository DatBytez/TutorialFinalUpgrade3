package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;
import main.GameStates;

public class KeyboardListener implements KeyListener {
	private Game game;

	public KeyboardListener(Game game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

    @Override
    public void keyPressed(KeyEvent e) {
        if (GameStates.gameState == GameStates.EDIT)
            game.getEditor().keyPressed(e);
        else if (GameStates.gameState == GameStates.PLAYING)
        	game.getPlaying().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (GameStates.gameState == GameStates.EDIT)
            game.getEditor().keyReleased(e);
        else if (GameStates.gameState == GameStates.PLAYING)
        	game.getPlaying().keyReleased(e);
    }
}
