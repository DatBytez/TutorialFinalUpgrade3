package scenes;

import helpz.Debug;
import main.Artist;
import managers.SceneManager.SceneType;
import ui.MyButton;
import managers.SceneManager;

public class MenuScene extends Scene {

	private MyButton bPlaying, bEdit, bSettings, bQuit;

	public MenuScene() {
		Debug.msg("Loaded");
		initButtons();
	}

	private void initButtons() {

		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;

		bPlaying = new MyButton("Play", x, y, w, h);
		bEdit = new MyButton("Edit", x, y + yOffset, w, h);
		bSettings = new MyButton("Settings", x, y + yOffset * 2, w, h);
		bQuit = new MyButton("Quit", x, y + yOffset * 3, w, h);

	}

	@Override
	public void update() {
	}

	@Override
	public void render(Artist a) {
		drawButtons(a);
	}

	private void drawButtons(Artist artist) {
		bPlaying.draw(artist);
		bEdit.draw(artist);
		bSettings.draw(artist);
		bQuit.draw(artist);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bPlaying.getBounds().contains(x, y))
			return;
//			SceneManager.changeScene(SceneType.PLAYING);
		else if (bEdit.getBounds().contains(x, y))
			SceneManager.changeScene(SceneType.EDITING);
//		else if (bSettings.getBounds().contains(x, y))
//			SceneManager.changeScene(SceneType.SETTINGS);
		else if (bQuit.getBounds().contains(x, y))
			System.exit(0);
	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		bEdit.setMouseOver(false);
		bSettings.setMouseOver(false);
		bQuit.setMouseOver(false);

		if (bPlaying.getBounds().contains(x, y))
			bPlaying.setMouseOver(true);
		else if (bEdit.getBounds().contains(x, y))
			bEdit.setMouseOver(true);
		else if (bSettings.getBounds().contains(x, y))
			bSettings.setMouseOver(true);
		else if (bQuit.getBounds().contains(x, y))
			bQuit.setMouseOver(true);
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bPlaying.getBounds().contains(x, y))
			bPlaying.setMousePressed(true);
		else if (bEdit.getBounds().contains(x, y))
			bEdit.setMousePressed(true);
		else if (bSettings.getBounds().contains(x, y))
			bSettings.setMousePressed(true);
		else if (bQuit.getBounds().contains(x, y))
			bQuit.setMousePressed(true);
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bEdit.resetBooleans();
		bSettings.resetBooleans();
		bQuit.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {
	}

	@Override
	public void keyPressed(int keyCode) {
	}

	@Override
	public void keyReleased(int keyCode) {
	}

	@Override
	public void keyTyped(char keyChar) {
	}

	@Override
	public void rightMousePressed(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
