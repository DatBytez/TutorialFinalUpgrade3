package ui;

import java.awt.Color;

import main.Artist;

public class Bar {

	public int x;
	public int y;
	public int width;
	public int height;

	public Bar(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	protected void drawButtonFeedback(Artist a, MyButton b) {
		// MouseOver
		if (b.isMouseOver())
			a.setColor(Color.white);
		else
			a.setColor(Color.BLACK);

		// Border
		a.drawRect(b.x, b.y, b.width, b.height);

		// MousePressed
		if (b.isMousePressed()) {
			a.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
			a.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
		}
	}
}
