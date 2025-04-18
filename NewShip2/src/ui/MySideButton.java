package ui;

import static helpz.Constants.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

public class MySideButton extends MyButton{

	public int x, y, width, height;
	private String text;
	private Rectangle bounds;
	private boolean mouseOver, mousePressed;

	public MySideButton(String text, int x, int y, int width, int height) {
		super(text,x,y,width,height);
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;

		initBounds();
	}

	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) {
		if(DEBUG) {
			g.setColor(Color.red);
			g.drawRect(x, y, width, height);
		}
		drawText(g);
	}
	
	private void drawText(Graphics g) {
		
	    Graphics2D g2d = (Graphics2D) g;
	    g.setColor(PHB_SIDE_TEXT);
	    g.setFont(alternityLiteFont);
	    g.setFont(g.getFont().deriveFont(Font.BOLD, 24F));
	    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // Enable anti-aliasing

	    AffineTransform originalTransform = g2d.getTransform();

	    int centerX = x + (width / 2) + 6;
	    int centerY = y + height / 2;

	    g2d.translate(centerX, centerY);
	    double scaleY = 0.7;
	    g2d.scale(1.0, scaleY);
	    g2d.rotate(Math.toRadians(90));
	    String displayText = mouseOver ? String.format("<%s>", text) : text;
	    if(mousePressed)
	    	g.setFont(g.getFont().deriveFont(Font.BOLD, 26F));
	    else
	    	g.setFont(g.getFont().deriveFont(Font.BOLD, 24F));
	    g2d.drawString(displayText, -g2d.getFontMetrics().stringWidth(displayText) / 2, g2d.getFontMetrics().getHeight() / 2);
	    g2d.drawString(displayText, -g2d.getFontMetrics().stringWidth(displayText) / 2+1, g2d.getFontMetrics().getHeight() / 2+1);
	    g2d.setTransform(originalTransform);
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

	public String getText() {
		return text;
	}

	public void update() {

	}

}
