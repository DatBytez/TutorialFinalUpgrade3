package scenes;

import static helpz.Constants.MARGIN;
import static helpz.Constants.PHB_BKGR;
import static helpz.Constants.TEST_SHIP;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import ship.ShipSystem;
import shipArmor.Armor;
import shipHull.Hull;
import ui.BuildBar;
import ui.ButtonSideBar;
import ui.MyButton;
import ui.MyButtonList;
import ui.ShipInfoBar;

//RANDOM TODO:
//	Make lists scrollable
//	Add page description to blank build pages
//
//Select HUll (Grey out Armor and all other catagories) (Add note "Select a hull")
//Select ARMOR (Grey out all other catagories) (Add a note with "Select Armor, or Skip")
//	[Create Ship] -> Ask for Name and Crew Quality. Create Zones and Health Track
//Select Zone
//	[Add System]

public class BuildScene extends GameScene implements SceneMethods {

	private ButtonSideBar sideBar;
	private ButtonSideBar leftSideBar;
	private BuildBar buildBar;
	private ShipInfoBar shipInfoBar;
	private Ship newShip = new Ship();
	private boolean gamePaused;

	private MyButtonList activeList;
	private ShipSystem selectedItem;
	private MyButton bBuy;

	public BuildScene(Game game) {
		super(game);

		newShip = new Ship();

		shipInfoBar = new ShipInfoBar(MARGIN * 4, MARGIN, (GameScreen.XSIZE / 3) - 20, GameScreen.YSIZE - 40, this);
		buildBar = new BuildBar((GameScreen.XSIZE / 2) + MARGIN, MARGIN, (GameScreen.XSIZE / 2) - MARGIN * 4,
				GameScreen.YSIZE - MARGIN * 2, this);

		initButtons();
		initSideBar();
		initLeftSideBar();
	}

	private void initButtons() {

		int w = 150;
		int h = w / 3;

		bBuy = new MyButton("BUY", ((GameScreen.XSIZE / 4)*3) - w / 2, GameScreen.YSIZE - MARGIN - h, w, h);
	}

	public void initSideBar() {
		ArrayList<String> buttonTitles = new ArrayList<>();
		buttonTitles.add("HULL");
		buttonTitles.add("POWER");
		buttonTitles.add("ENGINES");
		buttonTitles.add("WEAPONS");
		buttonTitles.add("DEFENSE");
		buttonTitles.add("COMMAND");
		buttonTitles.add("SENSORS");
		buttonTitles.add("MISC");
		sideBar = new ButtonSideBar(buttonTitles, GameScreen.XSIZE - MARGIN * 3, 0 + MARGIN, 90);
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
		sideBar.draw(g);
		leftSideBar.draw(g);
		if (activeList != null)
			activeList.draw(g);
		drawButtons(g);
		drawScreenEffect(g);
		drawVerticalVignette(g);
		drawCenterSpineVignette(g);

	}

	private void drawButtons(Graphics g) {
		bBuy.draw(g);
	}

	private void drawBackground(Graphics g) {
		g.setColor(PHB_BKGR);
		g.fillRect(0, 0, GameScreen.XSIZE, GameScreen.YSIZE);
		g.drawImage(getBackgroundImage(), 0, -150, GameScreen.XSIZE, GameScreen.YSIZE+ 150, null);
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
			sideBar.update();
		}
	}

	public boolean isGamePaused() {
		return gamePaused;
	}

	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}

	public void buyClicked() {
		if (buildBar.getSelecteItem() != null) {
			selectedItem = buildBar.getSelecteItem();
			if (selectedItem.getClass() == Hull.class)
				newShip.setHull((Hull) selectedItem);
			else if (selectedItem.getClass() == Armor.class)
				newShip.setArmor((Armor) selectedItem);
			else
				newShip.addSystem(selectedItem);

//			newShip.setName("NEW-UNNAMED");
			TEST_SHIP = newShip;
		}
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bBuy.getBounds().contains(x, y)) {
			buyClicked();
		}

		if (shipInfoBar.getBounds().contains(x, y)) {
			shipInfoBar.mouseClicked(x, y);
		}

		if (sideBar.getBounds().contains(x, y)) {
			sideBar.mouseClicked(x, y);
			if (sideBar.getSelectedItem().getText() == "HULL") {
				sideBar.changeList(new ArrayList<>(Arrays.asList("HULL>>", "CIVILIAN", "MILITARY", "ARMOR", "BACK")));
			} else if (sideBar.getSelectedItem().getText() == "ENGINES") {
				sideBar.changeList(new ArrayList<>(Arrays.asList("ENGINES >>", "SUBSPACE", "FTL", "BACK")));
			} else if (sideBar.getSelectedItem().getText() == "WEAPONS") {
				sideBar.changeList(new ArrayList<>(
						Arrays.asList("WEAPONS>>", "BEAMS", "PROJECTILES", "ORDINANCE", "SPECIAL", "BACK")));
			} else
				buildBar.setActiveList(sideBar.getSelectedItem().getText());
		}
		
		if (leftSideBar.getBounds().contains(x, y)) 
			leftSideBar.mouseClicked(x, y);

		if (buildBar.getBounds().contains(x, y))
			buildBar.mouseClicked(x, y);
		
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
		bBuy.setMouseOver(false);
		sideBar.mouseMoved(x, y);
		leftSideBar.mouseMoved(x, y);
		buildBar.mouseMoved(x, y);
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bBuy.getBounds().contains(x, y))
			bBuy.setMousePressed(true);
		if (sideBar.getBounds().contains(x, y))
			sideBar.mousePressed(x, y);
		if (leftSideBar.getBounds().contains(x, y))
			leftSideBar.mousePressed(x, y);
		if (buildBar.getBounds().contains(x, y))
			buildBar.mousePressed(x, y);

	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
		sideBar.mouseReleased(x, y);
		leftSideBar.mouseReleased(x, y);
		buildBar.mouseReleased(x, y);
	}

	private void resetButtons() {
		bBuy.resetBooleans();
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

	public ShipSystem getSelectedItem() {
		return selectedItem;
	}

}