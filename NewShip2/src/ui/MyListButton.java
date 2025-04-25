package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import helpz.ShipSystem;
import static helpz.Constants.*;

public class MyListButton {

	public int x, y, width, height, index;
	private Rectangle bounds;
	private boolean mouseOver, mousePressed, selected;
	private ArrayList<Object> properties = new ArrayList<Object>();
	private MyButtonList myButtonList;
	private ShipSystem item;
	private Color translucent = new Color(0, 0, 0, 0);
	private int frontTAB;

	// For List Buttons
	public MyListButton(ShipSystem item, int x, int y, MyButtonList myButtonList) {
		this.item = item;
		this.x = x;
		this.y = y;
		this.width = myButtonList.getWidth();
		this.height = myButtonList.getButtonHeight();
		this.myButtonList = myButtonList;
		this.properties = item.getProperties();
		this.frontTAB = myButtonList.getFrontTAB();
		initBounds();
		
	}

	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) {
		width = myButtonList.getWidth();
		drawBody(g);
		drawText(g);
	}

	private void drawBody(Graphics g) {
		if (selected) {
			g.setColor(PHB_LIST_SELECTED);
		} else if (mousePressed)
			g.setColor(PHB_LIST_CLICKED);
		else if (mouseOver)
			g.setColor(PHB_LIST_HOVERED);
		else
			g.setColor(translucent);
		g.fillRect(x, y, width, height);

	}

	private void drawText(Graphics g) {
		g.setColor(Color.white);
		int h = g.getFontMetrics().getHeight();

		int spacing = 0;
		int i = 1;
		for (Object item : properties) {
			if (myButtonList.getSpacingList().size() <= i) {
				myButtonList.getSpacingList().add(0);
				myButtonList.getSpacingList().add(0);
			}
			if (g.getFontMetrics().stringWidth(item.toString()) > myButtonList.getSpacingList().get(i))
				myButtonList.getSpacingList().set(i, g.getFontMetrics().stringWidth(item.toString()) + frontTAB/2);
			spacing += myButtonList.getSpacingList().get(i - 1);

			g.drawString(item.toString(), frontTAB + x + spacing, y + (h / 2) - 2 + height / 2);
			i++;
		}
	}
	
	public void update() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void resetBooleans() {
		this.mouseOver = false;
		this.mousePressed = false;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
	
    public void setSelected(boolean selected) { // New method for setting selection
        this.selected = selected;
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
	
	public ShipSystem getItem() {
		return item;
	}
}
