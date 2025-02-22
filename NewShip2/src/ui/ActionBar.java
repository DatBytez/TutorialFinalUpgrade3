package ui;

import static main.GameStates.MENU_STATE;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import actions.AttackAction;
import main.GameScreen;
import scenes.CombatScene;
import shipArmor.Armor;
import shipArmor.ArmorList;
import shipHull.Hull;
import shipHull.HullList;
import shipWeapons.Weapon;
import shipWeapons.WeaponList;
import shipfight.Crew;
import shipfight.Result;
import shipfight.Ship;
import static helpz.Constants.*;

public class ActionBar extends Bar {

	private CombatScene combat;
	private MyButton bMenu, bReset, bInitiative, bBasicFire, bBurstFire, bAutoFire, bBatteryFire;

	private Ship alliedShip;
	private Ship enemyShip;

	private int yOffset;
	private int buttonGap = 40;

	private final int buttonWidth = 100;

	public ActionBar(int x, int y, int width, int height, CombatScene combat) {
		super(x, y, width, height);
		this.combat = combat;
		this.yOffset = y;
		loadShips();

		initButtons();
	}

	private void initButtons() {

		bMenu = new MyButton("Menu", GameScreen.XSIZE / 2 - buttonWidth / 2, yOffset + 10 + buttonGap * 0, buttonWidth,
				30);
		bReset = new MyButton("Reset", GameScreen.XSIZE / 2 - buttonWidth / 2, yOffset + 10 + buttonGap * 1,
				buttonWidth, 30);
		bInitiative = new MyButton("Initiative", GameScreen.XSIZE / 2 - buttonWidth / 2, yOffset + 10 + buttonGap * 2,
				buttonWidth, 30);
		bBasicFire = new MyButton("Basic Fire", GameScreen.XSIZE / 2 - buttonWidth / 2, yOffset + 10 + buttonGap * 3,
				buttonWidth, 30);
		bBurstFire = new MyButton("Burst Fire", GameScreen.XSIZE / 2 - buttonWidth / 2, yOffset + 10 + buttonGap * 4,
				buttonWidth, 30);
		bAutoFire = new MyButton("Automatic Fire", GameScreen.XSIZE / 2 - buttonWidth / 2, yOffset + 10 + buttonGap * 5,
				buttonWidth, 30);
		bBatteryFire = new MyButton("Battery Fire", GameScreen.XSIZE / 2 - buttonWidth / 2,
				yOffset + 10 + buttonGap * 6, buttonWidth, 30);
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		bReset.draw(g);
		bInitiative.draw(g);
		bBasicFire.draw(g);
		bBurstFire.draw(g);
		bAutoFire.draw(g);
		bBatteryFire.draw(g);

	}

	public void draw(Graphics g) {

		// Background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);

		// Buttons
		drawButtons(g);

		drawShipInfo(g, alliedShip, 0);
		drawShipInfo(g, enemyShip, GameScreen.XSIZE / 2 + 50);

		// Game paused text
		if (combat.isGamePaused()) {
			g.setColor(Color.black);
			g.drawString("Game is Paused!", 110, 790);
		}
	}

	private void drawShipInfo(Graphics g, Ship ship, int xOffset) {
		int yOffset = this.yOffset;
		int iOffset = 20;
		int singleLineMargin = 20;
		int doubleLineMargin = 120;

		g.setFont(new Font("LucidaSans", Font.BOLD, 20));
		g.drawString(ship.getName() + " (" + ship.getHull().getName() + ")", xOffset + 70, yOffset + iOffset * 1);
		g.setFont(new Font("LucidaSans", Font.BOLD, 15));
		g.drawString("Stun: " + ship.getStun(), xOffset + singleLineMargin, yOffset + iOffset * 2);
		g.drawString("Wound: " + ship.getWound(), xOffset + 120, yOffset + iOffset * 2);
		g.drawString("Mortal: " + ship.getMortal(), xOffset + singleLineMargin, yOffset + iOffset * 3);
		g.drawString("Critical: " + ship.getCritical(), xOffset + 120, yOffset + iOffset * 3);
		g.drawString("Crew: " + ship.getCrew(), xOffset + singleLineMargin, yOffset + iOffset * 4);
		g.drawString("Edge: " + ship.getShipEdge(), xOffset + singleLineMargin, yOffset + iOffset * 5);
		g.drawString("Weapon: " + ship.getShipWeapon().getName(), xOffset + singleLineMargin, yOffset + iOffset * 6);
		g.drawString("Armor: " + ship.getArmor().getName(), xOffset + singleLineMargin, yOffset + iOffset * 7);
		g.drawString("Status: " + ship.getStatus(), xOffset + singleLineMargin, yOffset + iOffset * 8);
//		g.drawString("Heading: Incomplete", xOffset+singleLineMargin, yOffset+iOffset*10);
//		g.drawString("Range: Incomplete", xOffset+singleLineMargin, yOffset+iOffset*11);

	}

	private void initiativeClicked() {
		System.out.println("- Initiative Check -");
		initiativeCheck();
	}

	private void basicFireClicked() {

		System.out.println("");
		if (alliedShip.getShipEdge()) {
			System.out.println("- " + alliedShip.getName() + " fires at " + enemyShip.getName() + " -");
			AttackAction.fullAttack(alliedShip, enemyShip);
			System.out.println("");
			System.out.println("- " + enemyShip.getName() + " fires at " + alliedShip.getName() + " -");
			AttackAction.basicFire(enemyShip, enemyShip.getWeapons().get(0), alliedShip);
		} else {
			System.out.println("- " + enemyShip.getName() + " fires at " + alliedShip.getName() + " -");
			AttackAction.fullAttack(enemyShip, alliedShip);
			System.out.println("");
			System.out.println("- " + alliedShip.getName() + " fires at " + enemyShip.getName() + " -");
			AttackAction.basicFire(alliedShip, enemyShip.getWeapons().get(0), enemyShip);
		}

//		System.out.println("");
//		if (alliedShip.getShipEdge()) {
//			System.out.println("- " + alliedShip.getName() + " fires at " + enemyShip.getName() + " -");
//			AttackAction.basicFire(alliedShip, alliedShip.getWeapons().get(0), enemyShip);
//			System.out.println("");
//			System.out.println("- " + enemyShip.getName() + " fires at " + alliedShip.getName() + " -");
//			AttackAction.basicFire(enemyShip, enemyShip.getWeapons().get(0), alliedShip);
//		} else {
//			System.out.println("- " + enemyShip.getName() + " fires at " + alliedShip.getName() + " -");
//			AttackAction.basicFire(enemyShip, alliedShip.getWeapons().get(0), alliedShip);
//			System.out.println("");
//			System.out.println("- " + alliedShip.getName() + " fires at " + enemyShip.getName() + " -");
//			AttackAction.basicFire(alliedShip, enemyShip.getWeapons().get(0), enemyShip);
//		}
	}

	private void burstFireClicked() {

		System.out.println("");
		if (alliedShip.getShipEdge()) {
			System.out.println("- " + alliedShip.getName() + " burst fires at " + enemyShip.getName() + " -");
			AttackAction.burstFire(alliedShip, alliedShip.getWeapons().get(0), enemyShip);
			System.out.println("");
			System.out.println("- " + enemyShip.getName() + " burst fires at " + alliedShip.getName() + " -");
			AttackAction.burstFire(enemyShip, enemyShip.getWeapons().get(0), alliedShip);
		} else {
			System.out.println("- " + enemyShip.getName() + " burst fires at " + alliedShip.getName() + " -");
			AttackAction.burstFire(enemyShip, alliedShip.getWeapons().get(0), alliedShip);
			System.out.println("");
			System.out.println("- " + alliedShip.getName() + " burst fires at " + enemyShip.getName() + " -");
			AttackAction.burstFire(alliedShip, enemyShip.getWeapons().get(0), enemyShip);
		}
	}

	private void autoFireClicked() {

		System.out.println("");
		if (alliedShip.getShipEdge()) {
			System.out.println("- " + alliedShip.getName() + " auto fires at " + enemyShip.getName() + " -");
			AttackAction.autoFire(alliedShip, alliedShip.getWeapons().get(0), enemyShip);
			System.out.println("");
			System.out.println("- " + enemyShip.getName() + " auto fires at " + alliedShip.getName() + " -");
			AttackAction.autoFire(enemyShip, enemyShip.getWeapons().get(0), alliedShip);
		} else {
			System.out.println("- " + enemyShip.getName() + " auto fires at " + alliedShip.getName() + " -");
			AttackAction.autoFire(enemyShip, alliedShip.getWeapons().get(0), alliedShip);
			System.out.println("");
			System.out.println("- " + alliedShip.getName() + " auto fires at " + enemyShip.getName() + " -");
			AttackAction.autoFire(alliedShip, enemyShip.getWeapons().get(0), enemyShip);
		}
	}

	private void batteryFireClicked() {

		System.out.println("");
		if (alliedShip.getShipEdge()) {
			System.out.println("- " + alliedShip.getName() + " battery fires at " + enemyShip.getName() + " -");
			AttackAction.batteryFire(alliedShip, alliedShip.getWeapons().get(0), enemyShip);
			System.out.println("");
			System.out.println("- " + enemyShip.getName() + " battery fires at " + alliedShip.getName() + " -");
			AttackAction.batteryFire(enemyShip, enemyShip.getWeapons().get(0), alliedShip);
		} else {
			System.out.println("- " + enemyShip.getName() + " battery fires at " + alliedShip.getName() + " -");
			AttackAction.batteryFire(enemyShip, alliedShip.getWeapons().get(0), alliedShip);
			System.out.println("");
			System.out.println("- " + alliedShip.getName() + " battery fires at " + enemyShip.getName() + " -");
			AttackAction.batteryFire(alliedShip, enemyShip.getWeapons().get(0), enemyShip);
		}
	}

//	private void togglePause() {
//		combat.setGamePaused(!combat.isGamePaused());
//
//		if (combat.isGamePaused())
//			bPause.setText("Unpause");
//		else
//			bPause.setText("Pause");
//	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU_STATE);
		else if (bReset.getBounds().contains(x, y)) {
			loadShips();
		}

		else if (bInitiative.getBounds().contains(x, y))
			initiativeClicked();
		else if (bBasicFire.getBounds().contains(x, y))
			basicFireClicked();
		else if (bBurstFire.getBounds().contains(x, y))
			burstFireClicked();
		else if (bAutoFire.getBounds().contains(x, y))
			autoFireClicked();
		else if (bBatteryFire.getBounds().contains(x, y))
			batteryFireClicked();
	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bReset.setMouseOver(false);
		bInitiative.setMouseOver(false);
		bBasicFire.setMouseOver(false);
		bBurstFire.setMouseOver(false);
		bAutoFire.setMouseOver(false);
		bBatteryFire.setMouseOver(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else if (bReset.getBounds().contains(x, y))
			bReset.setMouseOver(true);
		else if (bInitiative.getBounds().contains(x, y))
			bInitiative.setMouseOver(true);
		else if (bBasicFire.getBounds().contains(x, y))
			bBasicFire.setMouseOver(true);
		else if (bBurstFire.getBounds().contains(x, y))
			bBurstFire.setMouseOver(true);
		else if (bAutoFire.getBounds().contains(x, y))
			bAutoFire.setMouseOver(true);
		else if (bBatteryFire.getBounds().contains(x, y))
			bBatteryFire.setMouseOver(true);
	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else if (bReset.getBounds().contains(x, y))
			bReset.setMousePressed(true);
		else if (bInitiative.getBounds().contains(x, y))
			bInitiative.setMouseOver(true);
		else if (bBasicFire.getBounds().contains(x, y))
			bBasicFire.setMousePressed(true);
		else if (bBurstFire.getBounds().contains(x, y))
			bBurstFire.setMouseOver(true);
		else if (bAutoFire.getBounds().contains(x, y))
			bAutoFire.setMousePressed(true);
		else if (bBatteryFire.getBounds().contains(x, y))
			bBatteryFire.setMouseOver(true);
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bReset.resetBooleans();
		bBasicFire.resetBooleans();
		bBurstFire.resetBooleans();
		bAutoFire.resetBooleans();
		bBatteryFire.resetBooleans();
	}

	// ===============================================================

	public void loadShips() {
		enemyShip = new Ship("Demon", new Hull(HullList.LightCruiser), Crew.ORDINARY);
		enemyShip.addWeapon(new Weapon(WeaponList.NeutroniumDriver));
		Armor enemyArmor = new Armor(ArmorList.CerametalHeavy);
		enemyShip.setArmor(enemyArmor);

		if (TEST_SHIP != null)
			alliedShip = TEST_SHIP;
		else {
			alliedShip = new Ship("Striker", new Hull(HullList.LightCruiser), Crew.AMAZING);

			alliedShip.addWeapon(new Weapon(WeaponList.KineticLance));
			alliedShip.addWeapon(new Weapon(WeaponList.KineticLance));
			alliedShip.addWeapon(new Weapon(WeaponList.KineticLance));
			alliedShip.addWeapon(new Weapon(WeaponList.KineticLance));

			Armor alliedArmor = new Armor(ArmorList.NanofluidicLight);
			alliedShip.setArmor(alliedArmor);
		}

	}

	private void initiativeCheck() {
		Result alliedShipEdgeCheck = alliedShip.rollEdgeCheck();
		Result enemyShipEdgeCheck = enemyShip.rollEdgeCheck();
		if (alliedShipEdgeCheck.value > enemyShipEdgeCheck.value) {
			alliedShip.setEdge(true);
			enemyShip.setEdge(false);
		} else if (alliedShipEdgeCheck.value < enemyShipEdgeCheck.value) {
			alliedShip.setEdge(false);
			enemyShip.setEdge(true);
		}
	}

	int round = 1;

//	private void attackPhase() {
//		System.out.println("");
//		System.out.println("-= Round " + round + " =-");
//
//		System.out.println("");
//
//		round++;
//	}

	public void update() {
		alliedShip.update();
		enemyShip.update();
	}

}
