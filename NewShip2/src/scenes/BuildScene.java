package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

import helpz.MyShipObject;

import static helpz.Constants.*;
import static main.GameStates.MENU_STATE;
import static main.GameStates.SetGameState;

import main.Game;
import main.GameScreen;
import shipArmor.Armor;
import shipHull.Hull;
import shipHull.HullList;
import shipWeapons.Weapon;
import shipfight.Crew;
import shipfight.Ship;
import ui.BuildBar;
import ui.ButtonSideBar;
import ui.MyButton;
import ui.MyButtonList;
import ui.ShipInfoBar;
import ui.ShipObjectDescriptionBar;

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
	private BuildBar buildBar;
	private ShipObjectDescriptionBar descriptionBox;
	private ShipInfoBar shipInfoBar;
	private Ship newShip = new Ship();
	private boolean gamePaused;
	int actionBarHeightOffset = 0;
	int actionBarWidthOffset = 0;

	private MyButtonList activeList;
	private MyShipObject selectedItem;
	private MyButton bBuy, bMenu;

	public BuildScene(Game game) {
		super(game);

		newShip = new Ship("New", new Hull(HullList.None), Crew.MARGINAL);
		shipInfoBar = new ShipInfoBar(MARGIN, MARGIN, GameScreen.XSIZE - (GameScreen.XSIZE / 2 + 150) - 20,
				GameScreen.YSIZE - 40, this);
		buildBar = new BuildBar(GameScreen.XSIZE / 2, MARGIN, (GameScreen.XSIZE / 2) - MARGIN,
				GameScreen.YSIZE - MARGIN * 2, this);
		descriptionBox = new ShipObjectDescriptionBar(GameScreen.XSIZE / 2, (GameScreen.YSIZE / 3) * 2 - MARGIN * 2, (GameScreen.XSIZE / 2) - MARGIN,
				GameScreen.YSIZE - MARGIN * 2, this);

		initButtons();
		initSideBar();
	}

	private void initButtons() {

		int w = 150;
		int h = w / 3;

		bBuy = new MyButton("BUY", GameScreen.XSIZE / 2 - w / 2, GameScreen.YSIZE - MARGIN - h, w, h);
		bMenu = new MyButton("MENU", MARGIN, GameScreen.YSIZE - MARGIN - h, w, h);
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
		sideBar = new ButtonSideBar(buttonTitles, GameScreen.XSIZE - 40, 0 + MARGIN);
	}

	@Override
	public void draw(Graphics g) {
		drawBackground(g);
		buildBar.draw(g);
		shipInfoBar.draw(g);
		sideBar.draw(g);
		descriptionBox.draw(g);
		if (activeList != null)
			activeList.draw(g);
		drawButtons(g);
		drawScreenEffect(g);
	}

	private void drawButtons(Graphics g) {
		bBuy.draw(g);
		bMenu.draw(g);
	}

	private void drawBackground(Graphics g) {
		g.setColor(PHB_BKGR);
		g.fillRect(0, 0, GameScreen.XSIZE, GameScreen.YSIZE);
//		g.drawImage(getBackgroundImage(), 0, -150, GameScreen.XSIZE, GameScreen.YSIZE+ 150, null);
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
			else if (selectedItem.getClass() == Weapon.class)
				newShip.addWeapon((Weapon) selectedItem);

			newShip.setName("NEW-SHIP");
			TEST_SHIP = newShip;
		}
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bBuy.getBounds().contains(x, y)) {
			buyClicked();
		} else if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU_STATE);

		if (sideBar.getBounds().contains(x, y)) {
			sideBar.mouseClicked(x, y);
			if (sideBar.getSelectedItem().getText() == "HULL") {
				sideBar.changeList(new ArrayList<>(Arrays.asList("HULL >>", "CIVILIAN", "MILITARY", "ARMOR", "BACK")));
			} else if (sideBar.getSelectedItem().getText() == "ENGINES") {
				sideBar.changeList(new ArrayList<>(Arrays.asList("ENGINES >>", "SUBSPACE", "FTL", "BACK")));
			} else if (sideBar.getSelectedItem().getText() == "WEAPONS") {
				sideBar.changeList(new ArrayList<>(
						Arrays.asList("WEAPONS >>", "BEAMS", "PROJECTILES", "ORDINANCE", "SPECIAL", "BACK")));
			} else
				buildBar.setActiveList(sideBar.getSelectedItem().getText());
		}

		if (buildBar.getBounds().contains(x, y))
			buildBar.mouseClicked(x, y);
			this.selectedItem = buildBar.getSelecteItem();
	}

	@Override
	public void mouseMoved(int x, int y) {
		bBuy.setMouseOver(false);
		bMenu.setMouseOver(false);
		sideBar.mouseMoved(x, y);
		buildBar.mouseMoved(x, y);
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bBuy.getBounds().contains(x, y))
			bBuy.setMousePressed(true);
		else if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		if (sideBar.getBounds().contains(x, y))
			sideBar.mousePressed(x, y);
		if (buildBar.getBounds().contains(x, y))
			buildBar.mousePressed(x, y);

	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
		sideBar.mouseReleased(x, y);
		buildBar.mouseReleased(x, y);
	}

	private void resetButtons() {
		bBuy.resetBooleans();
		bMenu.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {
	}

	public Ship getNewShip() {
		return newShip;
	}

	public void setNewShip(Ship newShip) {
		this.newShip = newShip;
	}
	
	public MyShipObject getSelectedItem() {
		return selectedItem;
	}

}
