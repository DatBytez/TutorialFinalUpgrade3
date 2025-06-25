package ui;

import static ship.helpz.Constants.DEBUG;
import static ship.helpz.Constants.PHB_DARK;
import static ship.helpz.Constants.PHB_TEXT;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import scenes.BuildScene;

public class ShipObjectDescriptionBar extends Bar {

	private BuildScene buildScene;

	private int titleOffset = 30;

	public ShipObjectDescriptionBar(int x, int y, int width, int height, BuildScene buildScene) {
		super(x, y, width, height);
		this.buildScene = buildScene;
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

	public void mouseClicked(int mouseX, int mouseY) {
	}

	public void mouseMoved(int mouseX, int mouseY) {
	}

	public void mousePressed(int mouseX, int mouseY) {
	}

	public void mouseReleased(int mouseX, int mouseY) {
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
