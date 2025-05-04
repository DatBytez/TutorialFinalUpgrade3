package ui;

import static helpz.Constants.DEBUG;
import static helpz.Constants.MARGIN;

import java.awt.Color;
import java.awt.Graphics;

import main.GameScreen;
import scenes.BuildScene;
import ship.ShipSystem;
import shipArmor.ArmorList;
import shipHull.HullList;
import shipPower.PowerList;
import shipWeapons.WeaponList;

public class BuildBar extends Bar {

	private MyButtonList activeList;
	private ShipSystem selectedItem;
	private ShipObjectDescriptionBar descriptionBox;

	public BuildBar(int x, int y, int width, int height, BuildScene building) {
		super(x, y, width, height);

		descriptionBox = new ShipObjectDescriptionBar(x + MARGIN, GameScreen.YSIZE/3, width - MARGIN*2, GameScreen.YSIZE/2, building);
	}

	public void draw(Graphics g) {

		if (DEBUG) {
			g.setColor(Color.red);
			g.drawRect(x, y, width, height);
		}

		descriptionBox.draw(g);
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
		if(descriptionBox.getBounds().contains(x, y))
			descriptionBox.mouseClicked(x, y);
	}

	public void mouseMoved(int x, int y) {
		if (activeList != null)
			activeList.mouseMoved(x, y);
		if(descriptionBox.getBounds().contains(x, y))
				descriptionBox.mouseMoved(x, y);
	}

	public void mousePressed(int x, int y) {
		if (activeList != null)
			activeList.mousePressed(x, y);
		if(descriptionBox.getBounds().contains(x, y))
			descriptionBox.mousePressed(x, y);
	}

	public void mouseReleased(int x, int y) {
		if (activeList != null)
			activeList.mouseReleased(x, y);
		if(descriptionBox.getBounds().contains(x, y))
			descriptionBox.mouseReleased(x, y);
	}

	public void update() {
		if (activeList != null) {
			activeList.update();
			descriptionBox.update();
			descriptionBox.setY(y + activeList.getHeight() + MARGIN);
			descriptionBox.setHeight(GameScreen.YSIZE - activeList.getHeight() - MARGIN*4);
		}
	}

	public void setActiveList(String title) {

		switch (title) {
		case "CIVILIAN":
			activeList = new MyButtonList("CIVILIAN HULLS", HullList.getCivilianHulls(), HullList.getListTitles(), x, y,
					width - MARGIN);
			break;
		case "MILITARY":
			activeList = new MyButtonList("MILITARY HULLS", HullList.getMilitaryHulls(), HullList.getListTitles(), x, y,
					width - MARGIN);
			break;
		case "ARMOR":
			activeList = new MyButtonList("ARMOR", ArmorList.getListArmors(), ArmorList.getListTitles(), x, y,
					width - MARGIN);
			break;
		case "BEAMS":
			activeList = new MyButtonList("BEAMS", WeaponList.getListBeams(), WeaponList.getListTitles(), x, y,
					width - MARGIN);
			break;
		case "PROJECTILES":
			activeList = new MyButtonList("PROJECTILES", WeaponList.getListProjectiles(), WeaponList.getListTitles(), x,
					y, width - MARGIN);
			break;
		case "POWER":
			activeList = new MyButtonList("POWER", PowerList.getListPowerSystems(), PowerList.getListTitles(), x, y,
					width - MARGIN);
			break;
		default:
		}
	}

	public ShipSystem getSelecteItem() {
		return selectedItem;
	}
}
