package ui;

import static ship.helpz.Constants.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import ship.systems.ShipSystem;

public class MyButtonList<T> extends Bar {

	private ArrayList<ShipSystem<T>> itemList = new ArrayList<>();
	private ArrayList<MyListButton<T>> listButtons = new ArrayList<>();
	private ArrayList<String> listTitles = new ArrayList<String>();
	private ArrayList<Integer> spacingList = new ArrayList<Integer>();

	Font alternityBoldFont, alternityLiteFont, alternityLogoFont, alternityHeadFont;
	private int x, y, width, height;
	private Rectangle bounds;
	private int buttonHeight = 20;
	private int yOffset = 30;
	private int titleOffset = 30;
	private int frontTAB = 20;
	private ShipSystem<T> selectedItem;
	private MyListButton<T> selectedButton;

	public MyButtonList(String title, ArrayList<ShipSystem<T>> itemList, ArrayList<String> listTitles, int x, int y,
			int width) {
		super(x, y, width, 0);
		this.y = y;
		this.x = x;
		this.width = width;
		this.title = title;
		this.itemList = itemList;
		this.listTitles = listTitles;
		initBounds();
		initFonts();
		setStyle("fancy");
		initButtons();
		initHeight();
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

	private void initHeight() {
		this.height = (itemList.size() + 2) * buttonHeight + titleOffset + 10;
	}

	private void initButtons() {
		int i = 0;
		for (ShipSystem<T> item : itemList) {
			listButtons.add(new MyListButton<T>(item, x, y + yOffset + titleOffset + (i * buttonHeight) + 5, this));
			i++;
		}
	}

	public void draw(Graphics g) {

		drawBackground(g);
		drawTitle(g);
		drawListTitles(g);
		drawListButtons(g);
	}

	private void drawListTitles(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(PHB_LIST_TITLE);
		g.setFont(alternityHeadFont);
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 14F));
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		int spacing = 0;
		int i = 1;
		spacingList.add(0);
		for (Object item : listTitles) {
			if (spacingList.size() <= i) {
				spacingList.add(0);
			}
			if (g.getFontMetrics().stringWidth(item.toString()) > spacingList.get(i))
				spacingList.set(i, g.getFontMetrics().stringWidth(item.toString()) + frontTAB / 2);
			spacing += spacingList.get(i - 1);

			g.drawString(item.toString(), frontTAB + x + spacing, y + yOffset + titleOffset);

			i++;
		}
	}

	private void drawListButtons(Graphics g) {

		for (MyListButton listItem : listButtons)
			listItem.draw(g);
	}

	protected void drawBackground(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		RadialGradientPaint gradient = new RadialGradientPaint(new Point2D.Double(x + width / 2, y + height / 2), // center
				(float) (Math.max(width - 400, (height / 2))), // radius of the gradient
				new float[] { 0.0f, 1.0f }, // start at teal, end at dark-blue
				new Color[] { PHB_LIST_BKGR_1, PHB_LIST_BKGR_2 } // center color teal, outer color dark-blue
		);
		g2d.setPaint(gradient);
		g2d.fillRoundRect(x, y, width, height, 50, 50);
	}

	public void mouseClicked(int x, int y) {
		for (MyListButton button : listButtons) {
			if (button.getBounds().contains(x, y)) {
				if (selectedButton != null) {
					selectedButton.setSelected(false);
				}
				selectedButton = button;
				if (button.getItem() != null) // TODO: Possibly not needed but here for troubleshooting
					selectedItem = button.getItem();
				button.setSelected(true);
				break;
			}
		}
	}

	public void mouseMoved(int x, int y) {
		listButtons.forEach(button -> button.setMouseOver(false));

		listButtons.forEach(button -> {

			if (button.getBounds().contains(x, y))
				button.setMouseOver(true);

		});
	}

	public void mousePressed(int x, int y) {
//		listButtons.forEach(button -> {
//			
//		if (button.getBounds().contains(x, y))
//			button.setMousePressed(true);
//		
//				});
	}

	public void mouseReleased(int x, int y) {
		listButtons.forEach(button -> button.resetBooleans());
	}

	public void update() {
		listButtons.forEach(button -> button.update());
		initBounds();
	}

	public ShipSystem<T> getSelectedItem() {
		return selectedItem;
	}

	public int getButtonHeight() {
		return buttonHeight;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ArrayList<Integer> getSpacingList() {
		return spacingList;
	}

	public int getFrontTAB() {
		return frontTAB;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public ArrayList<MyListButton<T>> getListButtons() {
		return listButtons;
	}
}
