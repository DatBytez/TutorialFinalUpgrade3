package ui;

import static ship.helpz.Constants.DEBUG;
import static ship.helpz.Constants.MARGIN;

import java.awt.Color;
import java.awt.Graphics;

import scenes.BuildScene;
import ship.systems.ArmorList;
import ship.systems.HullList;
import ship.systems.PowerList;
import ship.systems.ShipSystem;
import ship.systems.WeaponList;

public class BuyBar extends Bar {

	private MyButtonList<?> activeList;
	private ShipSystem<?> selectedItem;

	public BuyBar(int x, int y, int width, int height, BuildScene building) {
		super(x, y, width, height);
	}

	public void draw(Graphics g) {

		if (DEBUG) {
			g.setColor(Color.red);
			g.drawRect(x, y, width, height);
		}

		drawButtonLists(g);
	}

	private void drawButtonLists(Graphics g) {
		if (activeList != null)
			activeList.draw(g);
	}

	public void mouseClicked(int x, int y) {
		if (activeList != null)
			activeList.getListButtons().forEach(button -> {

				if (button.getBounds().contains(x, y)) {
					activeList.mouseClicked(x, y);
					selectedItem = activeList.getSelectedItem();
				}
			});
	}

	public void mouseMoved(int x, int y) {
		if (activeList != null)
			activeList.mouseMoved(x, y);
	}

	public void mousePressed(int x, int y) {
		if (activeList != null)
			activeList.mousePressed(x, y);
	}

	public void mouseReleased(int x, int y) {
		if (activeList != null)
			activeList.mouseReleased(x, y);
	}

	public void update() {
		if (activeList != null)
			activeList.update();
	}

	public void setActiveList(String title) {
		activeList = SystemListUtilz.createList(title, x, y, width - MARGIN);
	}

	public ShipSystem<?> getSelecteItem() {
		return selectedItem;
	}
}
