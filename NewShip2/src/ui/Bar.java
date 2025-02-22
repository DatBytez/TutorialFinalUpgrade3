package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;

public class Bar {

	protected int x, y, width, height;
	private Rectangle bounds;
	Font alternityBoldFont, alternityLiteFont, alternityLogoFont, alternityHeadFont;

	public Bar(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		initBounds();
		initFonts();
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
	
	protected void drawButtonFeedback(Graphics g, MyButton b) {
		// MouseOver
		if (b.isMouseOver())
			g.setColor(Color.white);
		else
			g.setColor(Color.BLACK);

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
