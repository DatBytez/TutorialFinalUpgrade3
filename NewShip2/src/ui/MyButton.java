package ui;

import static ship.helpz.Constants.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;

public class MyButton {

	Font alternityBoldFont, alternityLiteFont, alternityLogoFont, alternityHeadFont;
	public int x, y, width, height, id;
	protected String text;
	protected Rectangle bounds;
	protected boolean mouseOver;
	protected boolean mousePressed;
	protected boolean isActive = true;

	// For normal Buttons
	public MyButton(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;

		initBounds();
		initFonts();
	}

	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void setBounds(int addX, int i, int labelWidth, int labelHeight) {
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

	public void draw(Graphics g) {
		if (isActive) {
		// Body
		drawBody(g);

		// Border
		drawBorder(g);

		// Text
		drawText(g);
		}
	}

	protected void drawBorder(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		if (mousePressed) {
			g.drawRect(x + 1, y + 1, width - 2, height - 2);
			g.drawRect(x + 2, y + 2, width - 4, height - 4);
		}

	}

	protected void drawBody(Graphics g) {
		if (mouseOver)
			g.setColor(Color.gray);
		else
			g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);

	}

	private void drawText(Graphics g) {

		g.setColor(PHB_TEXT);
		g.setFont(alternityHeadFont);
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 14F));
		int w = g.getFontMetrics().stringWidth(text);
		int h = g.getFontMetrics().getHeight();
		g.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2);

	}

	public void resetBooleans() {
		this.mouseOver = false;
		this.mousePressed = false;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void mouseMoved(int mouseX, int mouseY) {
		if (bounds.contains(mouseX, mouseY))
			mouseOver = true;
		else
			mouseOver = false;
	}
	
	public void mousePressed(int mouseX, int mouseY) {
		if (bounds.contains(mouseX, mouseY))
			mousePressed = true;
	}
	
	public void mouseReleased(int mouseX, int mouseY) {
		mouseOver = false;
		mousePressed = false;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	

}
