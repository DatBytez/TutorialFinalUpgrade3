package ui;

import static helpz.Constants.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;

public class Bar {

	protected int x, y, width, height;
	private Rectangle bounds;
	Font alternityBoldFont, alternityLiteFont, alternityLogoFont, alternityHeadFont;
	String title = "";
	Color titleColor, titleBarColor, backgroundColor;
	boolean hasTitleBar = true;

	public Bar(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		initBounds();
		initFonts();
		setStyle("default");
	}

	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	private void initFonts() {
		try {
			InputStream is = getClass().getResourceAsStream("/font/geo703b.ttf");
			alternityBoldFont = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/geo703l.ttf");
			alternityLiteFont = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/alternity.ttf");
			alternityLogoFont = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/babylon5.ttf");
			alternityHeadFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void setStyle(String style) {
		switch (style) {
		case "default":
			titleColor = PHB_DARK;
			titleBarColor = TRANSPARENT;
			backgroundColor = TRANSPARENT;
			break;
		case "extra":
			titleColor = PHB_TITLE;
			titleBarColor = PHB_TITLE;
			backgroundColor = PHB_DARK;
			break;
		case "fancy":
			titleColor = PHB_LIST_TITLE;
			titleBarColor = PHB_DARK;
			backgroundColor = TRANSPARENT;
			break;
		}
	}

	protected void drawTitle(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(titleColor);
		g.setFont(alternityHeadFont);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 16F));
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		int titleWidth = g.getFontMetrics().stringWidth(title);

		g.drawString(title, x + (width / 2) - titleWidth / 2, y + TITLE_MARGIN);

		if (hasTitleBar) 
			drawTitleBar(g, TITLE_MARGIN);
	}

	protected void drawTitleBar(Graphics g, int yPos) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(titleBarColor);
		g2d.fillRect(x + 2 * MARGIN, y + yPos + 5, width - 4 * MARGIN, 5);
	}

	protected void drawBackground(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(backgroundColor);
		g2d.fillRoundRect(x, y, width, height, 50, 50);
	}

	protected void drawButtonFeedback(Graphics g, MyButton b) {
		// MouseOver
		if (b.isMouseOver())
			g.setColor(PHB_TEXT_HOVERED);
		else
			g.setColor(PHB_TEXT);

		// Border
		g.drawRect(b.x, b.y, b.width, b.height);

		// MousePressed
		if (b.isMousePressed()) {
			g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
			g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
		}
	}

	public Rectangle getBounds() {
		return bounds;
	}
}
