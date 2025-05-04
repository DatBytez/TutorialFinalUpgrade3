package ui;

import static helpz.Constants.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

public class MySideButton extends MyButton {

	public int x, y, width, height, rotation;
	private String text;
	private Rectangle bounds;
	private boolean mouseOver, mousePressed;
	private boolean isTopLevelCategory = false;
	private boolean isActiveCategory = false; // NEW FLAG
	private boolean isLockedCatagory = false;

	public MySideButton(String text, int x, int y, int width, int height, int rotation) {
		super(text, x, y, width, height);
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rotation = rotation;
		this.id = -1;

		initBounds();
		checkTopLevel();
	}

	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	private void checkTopLevel() {
		if (text.endsWith(">>")) {
			this.isTopLevelCategory = true;
			this.text = text.substring(0, text.length() - 2).trim();
		} else {
			this.isTopLevelCategory = false;
		}
	}

	public void draw(Graphics g) {
		if (DEBUG) {
			g.setColor(Color.red);
			g.drawRect(x, y, width, height);
		}
		drawText(g);
	}

	private void drawText(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		AffineTransform originalTransform = g2d.getTransform();

		int centerX = x + (width / 2);
		int centerY = y + (height / 2);

		g2d.translate(centerX, centerY);
		g2d.scale(1.0, 0.7);
		g2d.rotate(Math.toRadians(rotation));

		int fontSize = mousePressed ? 26 : 24;
		g2d.setFont(alternityLiteFont.deriveFont(Font.BOLD, fontSize));
		int textWidth = g2d.getFontMetrics().stringWidth(text);
		int textHeight = g2d.getFontMetrics().getAscent();
		if (isLockedCatagory) {
			g2d.setColor(Color.GRAY);
		} else {
			g2d.setColor(PHB_SIDE_TEXT);
		}
		g2d.drawString(text, -textWidth / 2, textHeight / 2);

		Font arrowFont = new Font("Dialog", Font.PLAIN, fontSize);
		g2d.setFont(arrowFont);
		int arrowY = textHeight / 2;

		if (isTopLevelCategory) {
			String doubleRight = "▶▶";
			int rightX = textWidth / 2 + 8;
			g2d.drawString(doubleRight, rightX, arrowY);
		} else if (mouseOver) {
			String leftArrow = "▶";
			String rightArrow = "◀";
			int arrowSpacing = 4;
			int leftX = -textWidth / 2 - arrowSpacing - g2d.getFontMetrics().stringWidth(rightArrow);
			int rightX = textWidth / 2 + arrowSpacing;
			g2d.drawString(rightArrow, leftX, arrowY);
			g2d.drawString(leftArrow, rightX, arrowY);
		}

		g2d.setTransform(originalTransform);
	}

	public void resetBooleans() {
		this.mouseOver = false;
		this.mousePressed = false;
	}

	public void setText(String text) {
		this.text = text;
		checkTopLevel();
	}

	public void setMousePressed(boolean mousePressed) {
	    if (!isLockedCatagory)
	        this.mousePressed = mousePressed;
	    else
	        this.mousePressed = false;
	}

	public void setMouseOver(boolean mouseOver) {
		if (!isLockedCatagory) {
			this.mouseOver = mouseOver;
		} else {
			this.mouseOver = false;
		}
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public String getText() {
		return text;
	}

	public void setTopLevelCategory(boolean topLevel) {
		this.isTopLevelCategory = topLevel;
	}

	public boolean isTopLevelCategory() {
		return isTopLevelCategory;
	}

	public void setActiveCategory(boolean active) {
		this.isActiveCategory = active;
	}

	public boolean isActiveCategory() {
		return isActiveCategory;
	}

	public void setLockedCatagory(boolean isLockedCatagory) {
		this.isLockedCatagory = isLockedCatagory;
	}

	public void update() {
	}

	public boolean isLockedCatagory() {
		return isLockedCatagory;
	}
}
