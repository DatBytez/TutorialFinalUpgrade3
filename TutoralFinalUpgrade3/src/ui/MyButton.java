package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Artist;

public class MyButton {

	public int x, y, width, height, id;
	private String text;
	private Rectangle bounds;
	private boolean mouseOver, mousePressed;

	// For normal Buttons
	public MyButton(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;

		initBounds();
	}

	// For tile buttons
	public MyButton(String text, int x, int y, int width, int height, int id) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;

		initBounds();
	}

	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void draw(Artist artist) {
		// Body
		drawBody(artist);

		// Border
		drawBorder(artist);

		// Text
		drawText(artist);
	}

	private void drawBorder(Artist artist) {

		artist.setColor(Color.black);
		artist.drawRect(x, y, width, height);
		if (mousePressed) {
			artist.drawRect(x + 1, y + 1, width - 2, height - 2);
			artist.drawRect(x + 2, y + 2, width - 4, height - 4);
		}

	}

	private void drawBody(Artist artist) {
		if (mouseOver)
			artist.setColor(Color.gray);
		else
			artist.setColor(Color.WHITE);
		artist.fillRect(x, y, width, height);

	}

	private void drawText(Artist artist) {
		int w = artist.getStringWidth(text);
		int h = artist.getFontHeight();
		artist.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2);

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

	public boolean isMousePressed() {
		return mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getId() {
		return id;
	}

}
