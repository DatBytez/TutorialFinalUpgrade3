package ui;

import static main.GameStates.MENU_STATE;
import static main.GameStates.SetGameState;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;

import helpz.MyShipObject;
import static helpz.Constants.*;
import scenes.BuildScene;
import shipWeapons.Weapon;
import shipfight.ShipCompartment;

public class ShipInfoBar extends Bar {

	private BuildScene building;

	private int width, height;
	private Rectangle bounds;
	private int buttonHeight = 20;
	private int yOffset = 30;
	private int titleOffset = 30;
	private int frontTAB = 20;
	private String title = "TEST";
//	private MyShipObject selectedItem;

	public ShipInfoBar(int x, int y, int width, int height, BuildScene building) {
		super(x, y, width, height);
		this.building = building;
		this.width = width;
		this.height = height;
		this.building = building;
		initBounds();
		initButtons();
	}

	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	private void initButtons() {
	}

	private void drawButtons(Graphics g) {
	}

	public void draw(Graphics g) {
		drawBackground(g);
		drawTitle(g);
		drawShipInfo(g);
		drawButtons(g);
	}

	private void drawTitle(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(PHB_TITLE);
		g.setFont(alternityHeadFont);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 16F));
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		int titleWidth = g.getFontMetrics().stringWidth(title);

		g.drawString(title, x + (width / 2) - titleWidth / 2, y + yOffset);

		g2d.setPaint(PHB_DARK);
		g2d.fillRect(x + 40, y + 35, width - 80, 5);
	}

	private void drawBackground(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(PHB_DARK);
		g2d.fillRoundRect(x, y, width, height, 50, 50);
	}

	private void drawShipInfo(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		int i = 0;
		int infoX = x + 20;
		int infoY = y + 20 + titleOffset;
		int infoTab = 50;
		int infoGap = 20;
		
		g.setColor(PHB_TEXT);
		g.setFont(alternityLiteFont);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 14F));
//		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g.drawString("Cost:", infoX, infoY + infoGap * i);
		g.drawString(String.valueOf(building.getNewShip().getCost()), infoX + infoTab, infoY + infoGap * i);
		i++;
		g.drawString("Name:", infoX, infoY + infoGap * i);
		g.drawString(building.getNewShip().getName(), infoX + infoTab, infoY + infoGap * i);
		i++;
		g.drawString("Hull:", infoX, infoY + infoGap * i);
		g.drawString(building.getNewShip().getHull().getName(), infoX + infoTab, infoY + infoGap * i);
		i++;
		g.drawString("Crew:", infoX, infoY + infoGap * i);
		g.drawString(building.getNewShip().getCrew().toString(), infoX + infoTab, infoY + infoGap * i);
		i++;
		g.drawString("Armor:", infoX, infoY + infoGap * i);
		if (building.getNewShip().getArmor() != null)
			g.drawString(building.getNewShip().getArmor().getName(), infoX + infoTab, infoY + infoGap * i);
		i++;
		drawCompInfo(g, infoX, infoY, i);
		
//		if(building.getNewShip().getWeapons() != null) {
//			int w = 0;
//		    for (Weapon weapon : building.getNewShip().getWeapons()) {
//		    	g.drawString(weapon.getName(), infoX + infoTab, infoY + infoGap * i);
//				i++;
//		    }
//		}
	}
	
	private void drawCompInfo(Graphics g, int infoX, int infoY, int i) {
		Graphics2D g2d = (Graphics2D) g;

		int infoTab = 50;
		int infoGap = 20;
		
		g.setColor(PHB_TEXT);
		g.setFont(alternityLiteFont);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 14F));
//		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

//		g.drawString("Compartment:", infoX, infoY + infoGap * i);
//		g.drawString(String.valueOf(building.getNewShip().getHull().getCompartmentCount()), infoX + infoTab, infoY + infoGap * i);
		i++;
//		g.drawString("Name:", infoX, infoY + infoGap * i);
//		g.drawString(building.getNewShip().getName(), infoX + infoTab, infoY + infoGap * i);
//		i++;
//		g.drawString("Hull:", infoX, infoY + infoGap * i);
//		g.drawString(building.getNewShip().getHull().getName(), infoX + infoTab, infoY + infoGap * i);
//		i++;
//		g.drawString("Crew:", infoX, infoY + infoGap * i);
//		g.drawString(building.getNewShip().getCrew().toString(), infoX + infoTab, infoY + infoGap * i);
//		i++;
//		g.drawString("Armor:", infoX, infoY + infoGap * i);
//		if (building.getNewShip().getArmor() != null)
//			g.drawString(building.getNewShip().getArmor().getName(), infoX + infoTab, infoY + infoGap * i);
//		i++;
		
		if(building.getNewShip().getHull() != null) {
	        for (ShipCompartment compartment : building.getNewShip().getCompartments()) {
		    	g.drawString("ZONE " +compartment.getName()+ " >>", infoX + infoTab, infoY + infoGap * i);
		    	i++;
	        }
		}
		}
//			int w = 0;
//		    for (ShipCompartment compartment : building.getNewShip().getWeapons()) {
//		    	g.drawString(compartment.getName(), infoX + infoTab, infoY + infoGap * i);
//				i++;
//		    }
//		}
		

	public void mouseClicked(int x, int y) {

	}

	public void mouseMoved(int x, int y) {

	}

	public void mousePressed(int x, int y) {

	}

	public void mouseReleased(int x, int y) {

	}

	public void update() {

	}
}
