package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import main.GameScreen;
import ui.ActionBar;

public class CombatScene extends GameScene implements SceneMethods {

	private ActionBar actionBar;
	private boolean gamePaused;
	int actionBarWidth = 640;
	int actionBarHeigh = 440;
	int actionBarHeightOffset = 200;
	int actionBarWidthOffset = 0;
	private BufferedImage sprite;

	public CombatScene(Game game) {
		super(game);
		actionBar = new ActionBar(actionBarWidthOffset, actionBarHeightOffset, GameScreen.XSIZE, GameScreen.YSIZE,this);
	}
	
//	private void drawBackground() {
//	g.drawImage(sprite, 0, 0, 32, 32, null);
//	}

	public void update() {
		if (!gamePaused) {
			updateTick();
			actionBar.update();
		}
	}

	public boolean isGamePaused() {
		return gamePaused;
	}

	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}

	@Override
	public void draw(Graphics g) {
		actionBar.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (y >= actionBarHeightOffset) {
			actionBar.mouseClicked(x, y);
		} else {
			
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (y >= actionBarHeightOffset)
			actionBar.mouseMoved(x, y);
		else {
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= actionBarHeightOffset)
			actionBar.mousePressed(x, y);
	}

	@Override
	public void mouseReleased(int x, int y) {
		actionBar.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y) {
	}

}
