package ui;

import static helpz.Constants.DEBUG;
import static helpz.Constants.MARGIN;
import static helpz.Constants.PHB_DARK;
import static helpz.Constants.PHB_TEXT;
import static helpz.Format.getDashedString;
import static helpz.Format.getMoneyString;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import scenes.BuildScene;
import ship.ShipSystem;

public class ShipObjectDescriptionBar extends Bar {

	private BuildScene buildScene;
	private AddSubBar addSubBar;

	private int titleOffset = 30;

	public ShipObjectDescriptionBar(int x, int y, int width, int height, BuildScene buildScene) {
		super(x, y, width, height);
		this.buildScene = buildScene;

		// Position AddSubBar in bottom-right corner
		int subWidth = 260;
		int subHeight = 160;
		int subX = x + width - subWidth;
		int subY = y + height - subHeight;
		this.addSubBar = new AddSubBar(subX, subY, subWidth, subHeight);
	}

	private void drawButtons(Graphics g) {
	}

	public void draw(Graphics g) {
		if (DEBUG) {
			g.setColor(Color.green);
			g.drawRect(x, y, width, height);
		}
		drawInfo(g);
		drawButtons(g);
		addSubBar.draw(g); // Draw the sub-bar
	}

	private void drawInfo(Graphics g) {
		int infoY = y + 20 + titleOffset;
		int infoGap = 20;
		int textWidth = width - 50;
		String title = "Hull";
		String description = defaultDescription();

		g.setColor(PHB_DARK);
		g.setFont(alternityHeadFont);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 16F));

		if (buildScene.getSelectedItem() != null) {
			title = buildScene.getSelectedItem().getName();
			description = buildScene.getSelectedItem().getDescription();
		}

		g.drawString(title, x, infoY);
		infoY += infoGap;

		g.setColor(PHB_TEXT);
		g.setFont(alternityLiteFont);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 14F));

		wrapText(g, description, x, infoY, textWidth, infoGap);
	}

	private String defaultDescription() {
		return "The first step in designing a ship is to decide, in general terms, what hull size and type is going to best serve your purposes and budget...";
	}

	private void wrapText(Graphics g, String text, int x, int y, int maxWidth, int lineHeight) {
		FontMetrics fm = g.getFontMetrics();
		String[] words = text.split(" ");
		StringBuilder line = new StringBuilder();
		int currentY = y;

		for (String word : words) {
			String testLine = line + (line.length() == 0 ? "" : " ") + word;
			int lineWidth = fm.stringWidth(testLine);

			if (lineWidth > maxWidth) {
				g.drawString(line.toString(), x, currentY);
				line = new StringBuilder(word);
				currentY += lineHeight;
			} else {
				line.append((line.length() == 0 ? "" : " ") + word);
			}
		}
		if (line.length() > 0) {
			g.drawString(line.toString(), x, currentY);
		}
	}

	// =============================
	// Inner Class: AddSubBar
	// =============================
	private class AddSubBar extends Bar {
		ShipSystem selectedSystem;
		private boolean isMouseOver = false;
		private boolean isMousePressed = false;
		private Rectangle addBounds = new Rectangle(); // Area of the "ADD" text

		public AddSubBar(int x, int y, int width, int height) {
			super(x, y, width, height);
			setStyle("extra");
		}

		public void draw(Graphics g) {
			if (buildScene.getSelectedItem() == null)
				return;

			Graphics2D g2d = (Graphics2D) g;
			drawBackground(g);
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			selectedSystem = buildScene.getSelectedItem();
			title = selectedSystem.getName();
			int powerReq = selectedSystem.getPowerReq();
			int hullCost = selectedSystem.getCalculatedHullCost(buildScene.getNewShip().getHull());
			int cost = selectedSystem.getCalculatedCost(buildScene.getNewShip().getHull());

			drawTitle(g);

			// Info Text
			g2d.setColor(PHB_TEXT);
			g2d.setFont(alternityLiteFont.deriveFont(Font.BOLD, 14F));
			int lineHeight = 20;
			int textX = x + MARGIN;
			int infoY = y + 40 + lineHeight;

			g2d.drawString("HULL ALLOCATED: " + getDashedString(hullCost), textX, infoY);
			infoY += lineHeight * 1.5;
			g2d.drawString("POWER REQUIRED: " + getDashedString(powerReq), textX, infoY);
			infoY += lineHeight;
			g2d.drawString("COST: " + getMoneyString(cost), textX, infoY);
			infoY += lineHeight * 1.5;

			String addLabel = "ADD";
			
			float fontSize;
			if(isMousePressed)
			 fontSize = 16F; // Slightly smaller on press
			else
				fontSize = isMouseOver ? 18F : 16F; // Slightly larger on hover

			g2d.setFont(alternityHeadFont.deriveFont(Font.BOLD, fontSize));
			g2d.setColor(titleColor);

			FontMetrics fm = g2d.getFontMetrics();
			int labelWidth = fm.stringWidth(addLabel);
			int labelHeight = fm.getHeight();
			int addX = x + (width - labelWidth) / 2;
			int addY = infoY; // final Y position

			g2d.drawString(addLabel, addX, addY);

			// Update hover bounds
			addBounds.setBounds(addX, addY - labelHeight + 4, labelWidth, labelHeight);
		}
		
		public void mouseMoved(int mouseX, int mouseY) {
			isMouseOver = addBounds.contains(mouseX, mouseY);
		}

		public void mouseClicked(int mouseX, int mouseY) {
			if (addBounds.contains(mouseX, mouseY)) {
				buildScene.addClicked();
			}
		}

		public void mousePressed(int mouseX, int mouseY) {
			isMousePressed = addBounds.contains(mouseX, mouseY); 
		}

		public void mouseReleased(int mouseX, int mouseY) {
			this.isMouseOver = false;
			this.isMousePressed = false;
		}
	}

	public void mouseClicked(int mouseX, int mouseY) {
		addSubBar.mouseClicked(mouseX, mouseY);
	}

	public void mouseMoved(int mouseX, int mouseY) {
		addSubBar.mouseMoved(mouseX, mouseY);
	}

	public void mousePressed(int mouseX, int mouseY) {
		addSubBar.mousePressed(mouseX, mouseY);
	}

	public void mouseReleased(int mouseX, int mouseY) {
		addSubBar.mouseReleased(mouseX, mouseY);
	}

	public void update() {
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setY(int y) {
		this.y = y;
	}
}
