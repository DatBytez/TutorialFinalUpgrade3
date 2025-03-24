package ui;


import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import helpz.MyShipObject;
import static helpz.Constants.*;
import scenes.BuildScene;

public class ShipObjectDescriptionBar extends Bar {

	private BuildScene buildScene;

	private int width, height;
	private int titleOffset = 30;

	public ShipObjectDescriptionBar(int x, int y, int width, int height, BuildScene buildScene) {
		super(x, y, width, height);
		this.buildScene = buildScene;
		this.width = width;
		this.height = height;
		this.buildScene = buildScene;
	}

	private void drawButtons(Graphics g) {
	}

	public void draw(Graphics g) {
		drawInfo(g);
		drawButtons(g);
	}

	private void drawInfo(Graphics g) {
		int infoY = y + 20 + titleOffset;
		int infoGap = 20;
		int textWidth = width - 50; // Ensuring text fits inside the bar

		g.setColor(PHB_DARK);
		g.setFont(alternityHeadFont);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 16F));
		
		if (buildScene.getSelectedItem() != null) {
		g.drawString(buildScene.getSelectedItem().getName(), x, infoY);
		infoY += infoGap;

		g.setColor(PHB_TEXT);
		g.setFont(alternityLiteFont);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 14F));
		
		String description = buildScene.getSelectedItem().getDescription();
		
		wrapText(g, description, x, infoY, textWidth, infoGap);
		}
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
	            // Draw the current line and start a new one
	            g.drawString(line.toString(), x, currentY);
	            line = new StringBuilder(word);
	            currentY += lineHeight; // Move to the next line
	        } else {
	            // Keep adding words to the current line
	            line.append((line.length() == 0 ? "" : " ") + word);
	        }
	    }

	    // Draw the last line
	    if (!line.isEmpty()) {
	        g.drawString(line.toString(), x, currentY);
	    }
	}

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
