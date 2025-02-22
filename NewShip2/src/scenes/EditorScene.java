package scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.Game;
//import ui.Toolbar;

//import static helpz.Constants.Tiles.ROAD_TILE;

public class EditorScene extends GameScene implements SceneMethods {

	private int[][] lvl;
	private int mouseX, mouseY;
	private int lastTileX, lastTileY, lastTileId;
	private boolean drawSelect;
//	private Toolbar toolbar;

	public EditorScene(Game game) {
		super(game);
//		toolbar = new Toolbar(0, 640, 640, 160, this);
	}

	public void update() {
		updateTick();
	}

	@Override
	public void draw(Graphics g) {

		drawLevel(g);
//		toolbar.draw(g);

	}

	private void drawLevel(Graphics g) {
		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
//				if (isAnimation(id)) {
//					g.drawImage(getSprite(id, animationIndex), x * 32, y * 32, null);
//				} else
//					g.drawImage(getSprite(id), x * 32, y * 32, null);
			}
		}
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 640) {
//			toolbar.mouseClicked(x, y);
		} else {
//			changeTile(mouseX, mouseY);
		}

	}

	@Override
	public void mouseMoved(int x, int y) {

		if (y >= 640) {
//			toolbar.mouseMoved(x, y);
			drawSelect = false;
		} else {
			drawSelect = true;
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		}

	}

	@Override
	public void mousePressed(int x, int y) {
//		if (y >= 640)
//			toolbar.mousePressed(x, y);

	}

	@Override
	public void mouseReleased(int x, int y) {
//		toolbar.mouseReleased(x, y);

	}

	@Override
	public void mouseDragged(int x, int y) {
		if (y >= 640) {

		} else {
//			changeTile(x, y);
		}

	}

	public void keyPressed(KeyEvent e) {
//		if (e.getKeyCode() == KeyEvent.VK_R)
//			toolbar.rotateSprite();
	}

}
