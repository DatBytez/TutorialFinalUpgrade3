package scenes;

import java.awt.Graphics;

import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class MenuScene extends GameScene implements SceneMethods {

	private MyButton bBuilding, bEdit, bCombat, bQuit;

	public MenuScene(Game game) {
		super(game);
		initButtons();
	}

	private void initButtons() {

		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;

		bBuilding = new MyButton("Build Ship", x, y, w, h);
		bEdit = new MyButton("Edit", x, y + yOffset, w, h);
		bCombat = new MyButton("Fight", x, y + yOffset * 2, w, h);
		bQuit = new MyButton("Quit", x, y + yOffset * 3, w, h);

	}

	@Override
	public void draw(Graphics g) {

		drawButtons(g);

	}

	private void drawButtons(Graphics g) {
		bBuilding.draw(g);
		bEdit.draw(g);
		bCombat.draw(g);
		bQuit.draw(g);
	}

//	@Override
	public void mouseClicked(int x, int y) {

		if (bBuilding.getBounds().contains(x, y))
			SetGameState(BUILD_STATE);
		else if (bEdit.getBounds().contains(x, y))
			SetGameState(EDIT_STATE);
		else if (bCombat.getBounds().contains(x, y))
			SetGameState(COMBAT_STATE);
		else if (bQuit.getBounds().contains(x, y))
			System.exit(0);
	}

	@Override
	public void mouseMoved(int x, int y) {
		bBuilding.setMouseOver(false);
		bEdit.setMouseOver(false);
		bCombat.setMouseOver(false);
		bQuit.setMouseOver(false);

		if (bBuilding.getBounds().contains(x, y))
			bBuilding.setMouseOver(true);
		else if (bEdit.getBounds().contains(x, y))
			bEdit.setMouseOver(true);
		else if (bCombat.getBounds().contains(x, y))
			bCombat.setMouseOver(true);
		else if (bQuit.getBounds().contains(x, y))
			bQuit.setMouseOver(true);

	}

	@Override
	public void mousePressed(int x, int y) {

		if (bBuilding.getBounds().contains(x, y))
			bBuilding.setMousePressed(true);
		else if (bEdit.getBounds().contains(x, y))
			bEdit.setMousePressed(true);
		else if (bCombat.getBounds().contains(x, y))
			bCombat.setMousePressed(true);
		else if (bQuit.getBounds().contains(x, y))
			bQuit.setMousePressed(true);

	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bBuilding.resetBooleans();
		bEdit.resetBooleans();
		bCombat.resetBooleans();
		bQuit.resetBooleans();

	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDoubleClicked(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}