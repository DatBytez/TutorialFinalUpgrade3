package scenes;

import static ship.helpz.Constants.*;
import static ship.helpz.Format.getDashedString;
import static ship.helpz.Format.getMoneyString;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Game;
import main.GameScreen;
import ship.Ship;
import ship.systems.Armor;
import ship.systems.Hull;
import ship.systems.ShipSystem;
import ui.Bar;
import ui.BuildBar;
import ui.ButtonSideBar;
import ui.MyActionButton;
import ui.ShipInfoBar;

//RANDOM TODO:
//	Make lists scrollable
//  Fix list resize wiggle
//	Add page description to blank build pages
//
//	[Create Ship] -> Ask for Name and Crew Quality. Create Zones and Health Track
// Implement Load/Save/Reset
// Add/Remove Systems to Compartments
// Remove items from ship

// Adjust descriptions to wrap around "ADD Bar"


public class BuildScene extends GameScene implements SceneMethods {

	private ButtonSideBar rightSideBar;
	private ButtonSideBar leftSideBar;
	private BuildBar buildBar;
	private ShipInfoBar shipInfoBar;
	private AddSubBar addBar;
	private Ship newShip = new Ship();
	private boolean gamePaused;

	private ShipSystem<?> selectedItem;

	public BuildScene(Game game) {
		super(game);

		newShip = new Ship();

		shipInfoBar = new ShipInfoBar(MARGIN * 4, MARGIN, (GameScreen.XSIZE / 3) - 20, GameScreen.YSIZE - 40, this);
		buildBar = new BuildBar((GameScreen.XSIZE / 2) + MARGIN, MARGIN, (GameScreen.XSIZE / 2) - MARGIN * 4,
				GameScreen.YSIZE - MARGIN * 2, this);
		int subWidth = 260;
		int subHeight = 160;
		int subX = GameScreen.XSIZE - (subWidth + MARGIN * 4);
		int subY = GameScreen.YSIZE - (subHeight + MARGIN);
		addBar = new AddSubBar(subX, subY, subWidth, subHeight);

		initRightSideBar();
		initLeftSideBar();
	}

	public void initRightSideBar() {
		ArrayList<String> buttonTitles = new ArrayList<>();
		buttonTitles.add("HULL");
		buttonTitles.add("POWER");
		buttonTitles.add("ENGINES");
		buttonTitles.add("WEAPONS");
		buttonTitles.add("DEFENSES");
		buttonTitles.add("COMMAND");
		buttonTitles.add("SENSORS");
		buttonTitles.add("MISC");
		rightSideBar = new ButtonSideBar(buttonTitles, GameScreen.XSIZE - MARGIN * 3, 0 + MARGIN, 90);
	}

	public void initLeftSideBar() {
		ArrayList<String> buttonTitles = new ArrayList<>();
		buttonTitles.add("MENU");
		buttonTitles.add("LOAD");
		buttonTitles.add("SAVE");
		buttonTitles.add("RESET");
		leftSideBar = new ButtonSideBar(buttonTitles, MARGIN, 0 + MARGIN, -90);
	}

	@Override
	public void draw(Graphics g) {
		drawBackground(g);
		buildBar.draw(g);
		shipInfoBar.draw(g);
		rightSideBar.draw(g);
		leftSideBar.draw(g);
		addBar.draw(g);
		drawScreenEffect(g);
		drawVerticalVignette(g);
		drawCenterSpineVignette(g);

	}

	private void drawBackground(Graphics g) {
		g.setColor(PHB_BKGR);
		g.fillRect(0, 0, GameScreen.XSIZE, GameScreen.YSIZE);
		g.drawImage(getBackgroundImage(), 0, -150, GameScreen.XSIZE, GameScreen.YSIZE + 150, null);
		g.setColor(Color.WHITE);
		g.fillRect(GameScreen.XSIZE / 2, 0, GameScreen.XSIZE / 2, GameScreen.YSIZE);
	}

	private void drawVerticalVignette(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int fadeWidth = 100; // How far in from each edge the fade goes
		int screenHeight = GameScreen.YSIZE;

		// Left vignette
		for (int i = 0; i < fadeWidth; i++) {
			int alpha = (int) (180 * (1.0 - (i / (float) fadeWidth))); // 180 max alpha
			alpha = clamp(alpha, 0, 255);
			g2d.setColor(new Color(0, 0, 0, alpha));
			g2d.drawLine(i, 0, i, screenHeight);
		}

		// Right vignette
		int screenWidth = GameScreen.XSIZE;
		for (int i = 0; i < fadeWidth; i++) {
			int alpha = (int) (180 * (1.0 - (i / (float) fadeWidth)));
			alpha = clamp(alpha, 0, 255);
			g2d.setColor(new Color(0, 0, 0, alpha));
			g2d.drawLine(screenWidth - i - 1, 0, screenWidth - i - 1, screenHeight);
		}
	}

	private void drawCenterSpineVignette(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int screenWidth = GameScreen.XSIZE;
		int screenHeight = GameScreen.YSIZE;
		int centerX = screenWidth / 2;

		// Draw the central spine line
		g2d.setColor(new Color(0, 0, 0, 200)); // Strong dark center line
		g2d.drawLine(centerX, 0, centerX, screenHeight);

		// Fade outward from center
		int fadeWidth = 80; // Distance to fade on both sides

		for (int i = 1; i <= fadeWidth; i++) {
			int alpha = (int) (180 * (1.0 - i / (float) fadeWidth)); // max 180 alpha
			alpha = clamp(alpha, 0, 255);
			Color fadeColor = new Color(0, 0, 0, alpha);

			g2d.setColor(fadeColor);

			// Draw on both sides of center
			g2d.drawLine(centerX - i, 0, centerX - i, screenHeight);
			g2d.drawLine(centerX + i, 0, centerX + i, screenHeight);
		}
	}

	public void drawScreenEffect(Graphics g) {
		BufferedImage noiseImage = generateNoise(GameScreen.XSIZE, GameScreen.YSIZE);
		g.drawImage(noiseImage, 0, 0, null);
	}

	private BufferedImage generateNoise(int width, int height) {
		BufferedImage noiseImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Random rand = new Random();

		Color baseColor = new Color(255, 255, 255, 50);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int noise = rand.nextInt(100) - 100;
				int red = clamp(baseColor.getRed() + noise, 0, 255);
				int green = clamp(baseColor.getGreen() + noise, 0, 255);
				int blue = clamp(baseColor.getBlue() + noise, 0, 255);

				noiseImage.setRGB(i, j, new Color(red, green, blue, baseColor.getAlpha()).getRGB());
			}
		}

		return noiseImage;
	}

	// Utility to clamp color values to the 0-255 range
	private int clamp(int value, int min, int max) {
		if (value < min)
			return min;
		if (value > max)
			return max;
		return value;
	}

	public static BufferedImage getBackgroundImage() {
		BufferedImage img = null;
		InputStream is = BuildScene.class.getClassLoader().getResourceAsStream("background.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	public void update() {
		if (!gamePaused) {
			updateTick();
			buildBar.update();
			shipInfoBar.update();
			rightSideBar.update();
			rightSideBar.update();
			rightSideBar.updateButtonLocks(newShip);
			addBar.update();

		}
	}

	public boolean isGamePaused() {
		return gamePaused;
	}

	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}

	public void addClicked() {
		ShipSystem<?> selected = buildBar.getSelecteItem();
		if (selected != null) {
			selectedItem = selected;

			if (selected instanceof Hull) {
				newShip.setHull((Hull) selected);
			} else if (selected instanceof Armor) {
				newShip.setArmor((Armor) selected);
			} else {
				newShip.addSystem(selected.copy());
			}

			TEST_SHIP = newShip;
		}
	}

	public void incClicked() {
		if (buildBar.getSelecteItem() != null) {
			selectedItem.incHullPoint();
		}
	}

	public void decClicked() {
		if (buildBar.getSelecteItem() != null) {
			selectedItem.decHullPoint();
		}
	}

	@Override
	public void mouseClicked(int x, int y) {

		if (shipInfoBar.getBounds().contains(x, y))
			shipInfoBar.mouseClicked(x, y);

		if (addBar.getBounds().contains(x, y))
			addBar.mouseClicked(x, y);

		if (rightSideBar.getBounds().contains(x, y)) {
			rightSideBar.mouseClicked(x, y);

			if (rightSideBar.getSelectedItem() != null) {
				String selectedText = rightSideBar.getSelectedItem().getText();

				if (selectedText.equals("HULL")) {
					rightSideBar.changeList(
							new ArrayList<>(Arrays.asList("HULL>>", "CIVILIAN", "MILITARY", "ARMOR", "BACK")));
				} else if (selectedText.equals("ENGINES")) {
					rightSideBar.changeList(new ArrayList<>(Arrays.asList("ENGINES >>", "SUBSPACE", "FTL", "BACK")));
				} else if (selectedText.equals("WEAPONS")) {
					rightSideBar.changeList(new ArrayList<>(
							Arrays.asList("WEAPONS>>", "BEAMS", "PROJECTILES", "ORDINANCES", "SPECIAL", "BACK")));
				} else if (selectedText.equals("MISC")) {
					rightSideBar.changeList(new ArrayList<>(
							Arrays.asList("MISC>>", "SUPPORT", "COMPUTERS", "MISCELLANEOUS", "BACK")));
				} else {
					buildBar.setActiveList(selectedText);
				}
			}
		}

		if (leftSideBar.getBounds().contains(x, y)) {
			leftSideBar.mouseClicked(x, y);
		}

		if (buildBar.getBounds().contains(x, y)) {
			buildBar.mouseClicked(x, y);
		}

		this.selectedItem = buildBar.getSelecteItem();
	}

	@Override
	public void mouseDoubleClicked(int x, int y) {
		if (shipInfoBar.getBounds().contains(x, y)) {
			shipInfoBar.mouseDoubleClicked(x, y);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		addBar.mouseMoved(x, y);
		rightSideBar.mouseMoved(x, y);
		leftSideBar.mouseMoved(x, y);
		buildBar.mouseMoved(x, y);
	}

	@Override
	public void mousePressed(int x, int y) {
		if (rightSideBar.getBounds().contains(x, y))
			rightSideBar.mousePressed(x, y);
		if (leftSideBar.getBounds().contains(x, y))
			leftSideBar.mousePressed(x, y);
		if (buildBar.getBounds().contains(x, y))
			buildBar.mousePressed(x, y);
		if (addBar.getBounds().contains(x, y))
			addBar.mousePressed(x, y);

	}

	@Override
	public void mouseReleased(int x, int y) {
		rightSideBar.mouseReleased(x, y);
		leftSideBar.mouseReleased(x, y);
		buildBar.mouseReleased(x, y);
		addBar.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y) {
	}

	public void keyPressed(KeyEvent e) {
		shipInfoBar.keyPressed(e);
	}

	public Ship getNewShip() {
		return newShip;
	}

	public void setNewShip(Ship newShip) {
		this.newShip = newShip;
	}

	public ShipSystem<?> getSelectedItem() {
		return selectedItem;
	}

	// =============================
	// Inner Class: AddSubBar
	// =============================
	private class AddSubBar extends Bar {
		private ArrayList<MyActionButton> buttonList = new ArrayList<>();
		private MyActionButton addButton, incButton, decButton;
		private ShipSystem<?> selectedSystem;

		public AddSubBar(int x, int y, int width, int height) {
			super(x, y, width, height);
			setStyle("extra");
			addButton = new MyActionButton("ADD", x, y, width, height);
			incButton = new MyActionButton("[+]", x, y, width, height);
			decButton = new MyActionButton("[-]", x, y, width, height);
			buttonList.add(addButton);
			buttonList.add(incButton);
			buttonList.add(decButton);
		}

		public void update() {
			if (selectedSystem != null) {
				incButton.setActive(selectedItem.isResizeable());
				decButton.setActive(selectedItem.isResizeable());
			}
		}

		public void draw(Graphics g) {
			if (getSelectedItem() == null)
				return;

			Graphics2D g2d = (Graphics2D) g;
			drawBackground(g);
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			selectedSystem = getSelectedItem();
			title = selectedSystem.getName();
			int powerCost = (int) selectedSystem.getCalculatedPowerCost();
			int hullCost = selectedSystem.getCalculatedHullCost(getNewShip().getHull()) * -1;
			int creditCost = selectedSystem.getCalculatedCost(getNewShip().getHull());

			drawTitle(g);

			// Info Text
			g2d.setColor(PHB_TEXT);
			g2d.setFont(alternityLiteFont.deriveFont(Font.BOLD, 14F));
			int lineHeight = 20;
			int textX = x + MARGIN;
			int infoY = y + 40 + lineHeight;

			// === HULL COST LINE ===
			String hullLabel;
			String hullValue;
			if(selectedSystem instanceof Hull hull && hull.getHullPoints() - hull.getBaseHullPoints() > 0) {
				hullValue = getDashedString(hull.getBaseHullPoints()) + "(+" + getDashedString(hull.getHullPoints() - hull.getBaseHullPoints() + ")");
			}else {
				hullValue = getDashedString(Math.abs(hullCost));
			}
			if (hullCost > 0) {
				hullLabel = "HULL PROVIDED: ";
			} else {
				hullLabel = "HULL ALLOCATED: ";
			}

			g2d.setColor(PHB_TEXT);
			g2d.drawString(hullLabel, textX, infoY);
			int hullLabelWidth = g2d.getFontMetrics().stringWidth(hullLabel);

			if (hullCost > 0) {
				g2d.setColor(PHB_LIST_TITLE);
				g2d.drawString("+" + hullValue, textX + hullLabelWidth, infoY);
			} else {
				g2d.setColor(PHB_TEXT);
				g2d.drawString(hullValue, textX + hullLabelWidth, infoY);
			}

			incButton.setX(textX + (MARGIN * 3) - 5);
			decButton.setX(textX + MARGIN * 4);
			incButton.setY(infoY);
			decButton.setY(infoY);
			infoY += lineHeight * 1.5;

			// === POWER COST LINE ===
			String powerLabel;
			String powerValue = getDashedString(Math.abs(powerCost));
			if (powerCost < 0) {
				powerLabel = "POWER PROVIDED: ";
			} else {
				powerLabel = "POWER REQUIRED: ";
			}

			g2d.setColor(PHB_TEXT);
			g2d.drawString(powerLabel, textX, infoY);
			int powerLabelWidth = g2d.getFontMetrics().stringWidth(powerLabel);

			if (powerCost < 0) {
				g2d.setColor(PHB_LIST_TITLE);
				g2d.drawString("+" + powerValue, textX + powerLabelWidth, infoY);
			} else {
				g2d.setColor(PHB_TEXT);
				g2d.drawString(powerValue, textX + powerLabelWidth, infoY);
			}

			infoY += lineHeight;
			g2d.setColor(PHB_TEXT);
			g2d.drawString("COST: " + getMoneyString(creditCost), textX, infoY);
			infoY += lineHeight * 1.5;

			addButton.setY(infoY);

			for (MyActionButton button : buttonList) {
				button.draw(g2d);
			}
		}


		public void mouseMoved(int mouseX, int mouseY) {
			addButton.mouseMoved(mouseX, mouseY);
			incButton.mouseMoved(mouseX, mouseY);
			decButton.mouseMoved(mouseX, mouseY);
		}

		public void mouseClicked(int mouseX, int mouseY) {
			if (addButton.getBounds().contains(mouseX, mouseY) && addButton.isActive())
				addClicked();
			if (incButton.getBounds().contains(mouseX, mouseY) && incButton.isActive())
				incClicked();
			if (decButton.getBounds().contains(mouseX, mouseY) && decButton.isActive())
				decClicked();
		}

		public void mousePressed(int mouseX, int mouseY) {
			addButton.mousePressed(mouseX, mouseY);
			incButton.mousePressed(mouseX, mouseY);
			decButton.mousePressed(mouseX, mouseY);
		}

		public void mouseReleased(int mouseX, int mouseY) {
			addButton.mouseReleased(mouseX, mouseY);
			incButton.mouseReleased(mouseX, mouseY);
			decButton.mouseReleased(mouseX, mouseY);
		}
	}
}