package ui;

import static helpz.Constants.DEBUG;

import java.awt.Color;
import java.awt.Graphics;
import helpz.MyShipObject;
import scenes.BuildScene;
import shipArmor.ArmorList;
import shipHull.HullList;
import shipWeapons.WeaponList;
import static helpz.Constants.*;

public class BuildBar extends Bar {

	private MyButtonList activeList;
	private MyShipObject selectedItem;

	public BuildBar(int x, int y, int width, int height, BuildScene building) {
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

        switch (title) {
        case "CIVILIAN":
			activeList = new MyButtonList("CIVILIAN HULLS", HullList.getCivilianHulls(), HullList.getListTitles(), x, y, width - MARGIN);
            break;
        case "MILITARY":
        	activeList = new MyButtonList("MILITARY HULLS", HullList.getMilitaryHulls(), HullList.getListTitles(), x, y, width - MARGIN);
			break;
        case "ARMOR":
        	activeList = new MyButtonList("ARMOR", ArmorList.getListArmors(), ArmorList.getListTitles(), x, y, width - MARGIN);
			 break;
        case "BEAMS":
        	activeList = new MyButtonList("BEAMS", WeaponList.getListBeams(), WeaponList.getListTitles(), x, y, width - MARGIN);
			break;
        case "Friday":
        	activeList = new MyButtonList("PROJECTILES", WeaponList.getListProjectiles(), WeaponList.getListTitles(), x, y, width - MARGIN);
			break;
        default:
            
        }
	}
	
	public MyShipObject getSelecteItem() {
		return selectedItem;
	}
}
