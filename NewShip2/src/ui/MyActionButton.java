package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import static helpz.Constants.PHB_TITLE;

public class MyActionButton extends MyButton {
	
	Color textColor = PHB_TITLE;

	public MyActionButton(String text, int x, int y, int width, int height) {
		super(text, x, y, width, height);
	}
	
	public void draw(Graphics g) {
		if (isActive) {
		float fontSize = mousePressed ? 16F : (mouseOver ? 18F : 16F);
		g.setFont(alternityHeadFont.deriveFont(Font.BOLD, fontSize));
		g.setColor(textColor);

		FontMetrics fm = g.getFontMetrics();
		int labelWidth = fm.stringWidth(text);
		int labelHeight = fm.getHeight();
		int addX = x + (width - labelWidth) / 2;
		int addY = y;

		g.drawString(text, addX, addY);
		bounds.setBounds(addX, addY - labelHeight + 4, labelWidth, labelHeight);
	}
	}
}
