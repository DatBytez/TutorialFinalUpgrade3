package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.Entity;
import gfx.ImageLoader;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import scenes.GameState;
import scenes.Playing;

public class UI {

	Playing playing;
	Graphics2D g2;
	public Font arial_40, bungee;
	public boolean messageOn = false;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public BufferedImage logo, heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
	public MenuSelection menuSelection = MenuSelection.NEW_GAME;
	public OptionsSelection optionsSelection = OptionsSelection.FULL_SCREEN;
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	public int subState = 0;
	public int commandNum = 0;
	int counter = 0;
	public Entity npc;
	int charIndex = 0;
	String combinedText = "";

	public UI(Playing playing) {
		this.playing = playing;
		logo = ImageLoader.loadImage("/other/logo");

		arial_40 = new Font("Arial", Font.PLAIN, 40);
		InputStream inputStream = getClass().getResourceAsStream("/font/Bungee.ttf");
		try {
			bungee = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// CREATE HUD OBJECT
		Entity heart = new OBJ_Heart(playing);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		Entity crystal = new OBJ_ManaCrystal(playing);
		crystal_full = crystal.image;
		crystal_blank = crystal.image2;
		Entity bronzeCoin = new OBJ_Coin_Bronze(playing);
		coin = bronzeCoin.down.get(0);

	}

	public void addMessage(String text) {
		message.add(text);
		messageCounter.add(0);
	}

	public void draw(Graphics2D g2) {

		this.g2 = g2;

		g2.setFont(bungee); // TODO find a font you actually like
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);

		// TITLE STATE
		if (playing.gameState == GameState.TITLE) {
			drawTitleScreen();
		}
		// PLAY STATE
		if (playing.gameState == GameState.PLAY) {
			drawPlayerLife();
			drawMessage();
		}
		// PAUSE STATE
		if (playing.gameState == GameState.PAUSE) {
			drawPlayerLife();
			drawPauseScreen();
		}
		// DIALOGUE STATE
		if (playing.gameState == GameState.DIALOGUE) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		// CHARACTER STATE
		if (playing.gameState == GameState.CHARACTER) {
			drawCharacterScreen();
			drawInventory(playing.player, true);
		}
		// OPTIONS STATE
		if (playing.gameState == GameState.OPTIONS) {
			drawOptionsScreen();
		}
		// GAME OVER STATE
		if (playing.gameState == GameState.GAME_OVER) {
			drawGameOverScreen();
		}

		// TRANSITION STATE
		if (playing.gameState == GameState.TRANSITION) {
			drawTransition();
		}
		// TRADE STATE
		if (playing.gameState == GameState.TRADE) {
			drawTradeScreen();
		}

		// SLEEP STATE
		if (playing.gameState == GameState.SLEEP) {
			drawSleepScreen();
		}

		// EDITOR STATE
		if (playing.gameState == GameState.EDITOR) {
//			drawPlayerLife();
//			drawMessage();
		}
		// DEBUG
		if (playing.debugMode) {
			drawDebug();
		}
	}

	public void drawPlayerLife() {
		int x = playing.tileSize / 2;
		int y = playing.tileSize / 2;
		int width = playing.tileSize / 2;
		int height = playing.tileSize / 2;
		int i = 0;

		// DRAW MAX LIFE
		while (i < playing.player.maxLife / 2) {
			g2.drawImage(heart_blank, x, y, width, height, null);
			i++;
			x += playing.tileSize / 2;
		}

		x = playing.tileSize / 2;
		y = playing.tileSize / 2;
		i = 0;

		// DRAW CURRENT LIFE
		while (i < playing.player.life) {
			g2.drawImage(heart_half, x, y, width, height, null);
			i++;
			if (i < playing.player.life) {
				g2.drawImage(heart_full, x, y, width, height, null);
			}
			i++;
			x += playing.tileSize / 2;
		}

		// DRAW MAX MANA
		x = playing.tileSize / 2;
		y = playing.tileSize;
		i = 0;
		while (i < playing.player.maxMana) {
			g2.drawImage(crystal_blank, x, y, width, height, null);
			i++;
			x += playing.tileSize / 3;
		}

		// DRAW MANA
		x = playing.tileSize / 2;
		y = playing.tileSize;
		i = 0;
		while (i < playing.player.mana) {
			g2.drawImage(crystal_full, x, y, width, height, null);
			i++;
			x += playing.tileSize / 3;
		}
	}

	public void drawMessage() {
		int messageX = playing.tileSize;
		int messageY = playing.tileSize * 4;
		g2.setFont(arial_40);
		g2.setFont(g2.getFont().deriveFont(24F));

		for (int i = 0; i < message.size(); i++) {
			if (message.get(i) != null) {

				g2.setColor(Color.blue);
				g2.drawString(message.get(i), messageX + 2, messageY + 2);

				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);

				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i, counter);
				messageY += playing.tileSize / 2;

				if (messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}

	public void drawTitleScreen() {

		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(0, 0, playing.screenWidth, playing.screenHeight);
		// TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96));
		String text = "Wizard Party";
		int x = getXforCenteredText(text);
		int y = playing.tileSize * 3;

		// SHADOW
		g2.setColor(Color.black);
		g2.drawString(text, x + 5, y + 5);

		// MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);

		// SPLASH IMAGE
		x = playing.screenWidth / 2 - (playing.tileSize * 3 + playing.tileSize / 2);
		y += playing.tileSize / 2;
		g2.drawImage(logo, x, y, playing.tileSize * 7, playing.tileSize * 7, null);

		// MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += playing.tileSize * 3;
		g2.drawString(text, x, y);

		if (menuSelection == MenuSelection.NEW_GAME) {
			g2.drawString(">", x - playing.tileSize, y);
		}

		text = "LOAD GAME";
		x = getXforCenteredText(text);
		y += playing.tileSize;
		g2.drawString(text, x, y);

		if (menuSelection == MenuSelection.LOAD_GAME) {
			g2.drawString(">", x - playing.tileSize, y);
		}

		text = "QUIT";
		x = getXforCenteredText(text);
		y += playing.tileSize;
		g2.drawString(text, x, y);

		if (menuSelection == MenuSelection.QUIT) {
			g2.drawString(">", x - playing.tileSize, y);
		}

	}

	public void drawPauseScreen() {
		g2.setFont(arial_40); // TODO find a font you actually like
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = playing.screenHeight / 2 + 20;

		g2.drawString(text, x, y);
	}

	public void drawDialogueScreen() {
		// WINDOW
		int x = playing.tileSize * 3;
		int y = playing.tileSize / 2;
		int width = playing.screenWidth - (playing.tileSize * 6);
		int height = playing.tileSize * 4;

		drawSubWindow(x, y, width, height);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		x += playing.tileSize;
		y += playing.tileSize;
		
		//TODO: All of this should probably be in the NPC not UI
		if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
			currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
			
			char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
			
			if(charIndex < characters.length) {
				
				playing.playSoundEffect(17);
				String s = String.valueOf(characters[charIndex]);
				combinedText = combinedText + s;
				currentDialogue = combinedText;
				charIndex++;
			}
			
			if(playing.enterPressed) {
				
				charIndex = 0;
				combinedText = "";
				
				if(playing.gameState == GameState.DIALOGUE) {
					npc.dialogueIndex++;
					playing.enterPressed = false;
				}
			}
		}
		else { // If no text is in the array
			npc.dialogueIndex = 0;
			
			if(playing.gameState == GameState.DIALOGUE) {
				playing.gameState = GameState.PLAY;
			}
		}
		//-----------------------------------------------------------
		for (String line : currentDialogue.split("\n")) { // TODO this should be updated with actual wrapping text
			g2.drawString(line, x, y);
			y += 40;
		}
	}

	public void drawCharacterScreen() {
		// CREATE A FRAME
		final int frameX = playing.tileSize * 1;
		final int frameY = playing.tileSize;
		final int frameWidth = playing.tileSize * 5;
		final int frameHeight = playing.tileSize * 8;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(26F));

		int textX = frameX + 20;
		int textY = frameY + playing.tileSize;
		final int lineHeight = 35;

		g2.drawString(playing.player.name, frameX + getXforCenteredText(playing.player.name, frameWidth), textY);
		textY += lineHeight;

		// NAMES
		g2.drawString("Level: ", textX, textY);
		textY += lineHeight;
		g2.drawString("Life: ", textX, textY);
		textY += lineHeight;
		g2.drawString("Mana: ", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength: ", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity: ", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack: ", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense: ", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp: ", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin: ", textX, textY);
		textY += lineHeight + lineHeight / 6;
		g2.drawString("Weapon: ", textX, textY);
		textY += lineHeight + lineHeight / 6;
		g2.drawString("Shield: ", textX, textY);

		// VALUES
		int tailX = (frameX + frameWidth) - 30;
		// Reset textY
		textY = frameY + playing.tileSize;
		String value;

		textY += lineHeight;
		value = String.valueOf(playing.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(playing.player.life + "/" + playing.player.maxLife);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(playing.player.mana + "/" + playing.player.maxMana);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(playing.player.strength);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(playing.player.dexterity);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(playing.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(playing.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(playing.player.exp + "/" + playing.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(playing.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight / 2;

		g2.drawImage(playing.player.currentWeapon.down.get(1), tailX - playing.tileSize / 2, textY, null);
		textY += lineHeight;
		g2.drawImage(playing.player.currentShield.down.get(1), tailX - playing.tileSize / 2, textY, null);
	}

	public void drawInventory(Entity entity, boolean cursor) {

		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;

		if (entity == playing.player) {
			frameX = playing.tileSize * 13;
			frameY = playing.tileSize;
			frameWidth = playing.tileSize * 6;
			frameHeight = playing.tileSize * 5;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		} else {
			frameX = playing.tileSize * 2;
			frameY = playing.tileSize;
			frameWidth = playing.tileSize * 6;
			frameHeight = playing.tileSize * 5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}

		// FRAME

		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		// SLOT
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = playing.tileSize + 3;

		// DRAW PLAYER'S ITEMS
		for (int i = 0; i < entity.inventory.size(); i++) {

			// EQUIP CURSOR
			if (entity.inventory.get(i) == entity.currentWeapon || entity.inventory.get(i) == entity.currentShield
					|| entity.inventory.get(i) == entity.currentLight) {
				g2.setColor(new Color(240, 190, 90));
				g2.fillRoundRect(slotX, slotY, playing.tileSize, playing.tileSize, 10, 10);
			}

			g2.drawImage(entity.inventory.get(i).down.get(0), slotX, slotY, null);

			// DISPLAY AMOUNT
			if (entity == playing.player && entity.inventory.get(i).amount > 1) {
				g2.setFont(g2.getFont().deriveFont(32f));
				int amountX;
				int amountY;

				String s = "" + entity.inventory.get(i).amount;
				amountX = getXforAlignToRightText(s, slotX + 44);
				amountY = slotY + playing.tileSize;

				// SHADOW
				g2.setColor(new Color(60, 60, 60));
				g2.drawString(s, amountX, amountY);

				// NUMBER
				g2.setColor(Color.white);
				g2.drawString(s, amountX - 3, amountY - 3);
			}

			slotX += slotSize;

			if (i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}

		}

		// CURSOR
		if (cursor) {
			int cursorX = slotXstart + (slotSize * slotCol);
			int cursorY = slotYstart + (slotSize * slotRow);
			int cursorWidth = playing.tileSize;
			int cursorHeight = playing.tileSize;
			// DRAW CURSOR
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

			// DESCRIPTION FRAME
			int dFrameX = frameX;
			int dFrameY = frameY + frameHeight;
			int dFrameWidth = frameWidth;
			int dFrameHeight = playing.tileSize * 3;

			// DRAW DESCRIPTION TEXT
			int textX = dFrameX + 20;
			int textY = playing.tileSize * 7;
			g2.setFont(g2.getFont().deriveFont(28F));

			int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

			if (itemIndex < entity.inventory.size()) {

				drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

				for (String line : entity.inventory.get(itemIndex).description.split("\n")) {
					g2.drawString(line, textX, textY);
					textY += playing.tileSize / 2;
				}
			}
		}
	}

	public void drawGameOverScreen() {
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, playing.screenWidth, playing.screenHeight);

		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

		text = "Game Over";
		// Shadow
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = playing.tileSize * 4;
		g2.drawString(text, x, y);
		// Main
		g2.setColor(Color.white);
		x = getXforCenteredText(text);
		y = playing.tileSize * 4;
		g2.drawString(text, x - 4, y - 4);

		// Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Retry";
		x = getXforCenteredText(text);
		y += playing.tileSize * 4;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - 40, y);
		}

		// Quit
		text = "Quit";
		x = getXforCenteredText(text);
		y += playing.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - 40, y);
		}
	}

	public void drawOptionsScreen() {

		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));

		// SUB WINDOW
		int frameX = playing.tileSize * 6;
		int frameY = playing.tileSize;
		int frameWidth = playing.tileSize * 8;
		int frameHeight = playing.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		switch (subState) {
		case 0:
			options_top(frameX, frameY);
			break;
		case 1:
			options_fullScreenNotification(frameX, frameY);
			break;
		case 2:
			options_control(frameX, frameY);
			break;
		case 3:
			options_endGameConfirmation(frameX, frameY);
			break;
		}

		playing.enterPressed = false;

	}

	public void options_top(int frameX, int frameY) {
		int textX;
		int textY;

		// TITLE
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + playing.tileSize;
		g2.drawString(text, textX, textY);

		// FULL SCREEN ON/OFF
		textX = frameX + playing.tileSize;
		textY += playing.tileSize;
		g2.drawString("Full Screen", textX, textY);
		if (optionsSelection == OptionsSelection.FULL_SCREEN) {
			g2.drawString(">", textX - 25, textY);
			if (playing.enterPressed) {
					if (!playing.fullScreenOn) {
						playing.fullScreenOn = true;
					} else {
						playing.fullScreenOn = false;
					}
					subState = 1;
				playing.enterPressed = false;
					optionsSelection = OptionsSelection.BACK;
				}
			}

			// MUSIC
			textX = frameX + playing.tileSize;
			textY += playing.tileSize;
			g2.drawString("Music", textX, textY);
			if (optionsSelection == OptionsSelection.MUSIC) {
				g2.drawString(">", textX - 25, textY);
			}

			// SOUND EFFECTS
			textX = frameX + playing.tileSize;
			textY += playing.tileSize;
			g2.drawString("Sound", textX, textY);
			if (optionsSelection == OptionsSelection.SOUND_EFFECTS) {
				g2.drawString(">", textX - 25, textY);
			}

			// CONTROL
			textX = frameX + playing.tileSize;
			textY += playing.tileSize;
			g2.drawString("Control", textX, textY);
			if (optionsSelection == OptionsSelection.CONTROL) {
				g2.drawString(">", textX - 25, textY);
				if (playing.enterPressed) {
					subState = 2;
					playing.enterPressed = false;
					optionsSelection = OptionsSelection.BACK;
				}
			}

			// END GAME
			textX = frameX + playing.tileSize;
			textY += playing.tileSize;
			g2.drawString("End Game", textX, textY);
			if (optionsSelection == OptionsSelection.END_GAME) {
				g2.drawString(">", textX - 25, textY);
				if (playing.enterPressed) {
					subState = 3;
					playing.enterPressed = false;
					optionsSelection = OptionsSelection.BACK;
				}
			}

			// BACK
			textX = frameX + playing.tileSize;
			textY += playing.tileSize * 2;
			g2.drawString("Back", textX, textY);
			if (optionsSelection == OptionsSelection.BACK) {
				g2.drawString(">", textX - 25, textY);
				if (playing.enterPressed) {
					playing.gameState = GameState.PLAY;
					optionsSelection = OptionsSelection.FULL_SCREEN;
				}
			}

			// FULL SCREEN CHECK BOX
			textX = frameX + playing.tileSize * 5;
			textY = frameY + playing.tileSize + 40;
			g2.setStroke(new BasicStroke(5));
			g2.drawRect(textX, textY, 24, 24);
			if (playing.fullScreenOn) {
				g2.fillRect(textX, textY, 24, 24);
			}
			int maxVolume = playing.tileSize * 4;

			// MUSIC VOLUME
			textY += playing.tileSize;
			g2.drawRect(textX - (int) (playing.tileSize * 1.75), textY, maxVolume, 24);
			int musicVolume = (maxVolume / 10) * playing.music.volumeScale;
			g2.fillRect(textX - (int) (playing.tileSize * 1.75), textY, musicVolume, 24);

			// SOUND VOLUME
			textY += playing.tileSize;
			g2.drawRect(textX - (int) (playing.tileSize * 1.75), textY, maxVolume, 24);
			int soundVolume = (maxVolume / 10) * playing.soundEffect.volumeScale;
			g2.fillRect(textX - (int) (playing.tileSize * 1.75), textY, soundVolume, 24);

			playing.config.saveConfig();
	}

	public void options_fullScreenNotification(int frameX, int frameY) {

		int textX = frameX + playing.tileSize - 16;
		int textY = frameY + playing.tileSize * 3;

		currentDialogue = "You must restart the \ngame for this \neffect to take place.";

		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}

		// BACK
		textY = frameY + playing.tileSize * 6;
		g2.drawString("Back", textX, textY);
		if (optionsSelection == OptionsSelection.BACK) {
			g2.drawString(">", textX - 25, textY);
			if (playing.enterPressed) {
				subState = 0;
				optionsSelection = OptionsSelection.FULL_SCREEN;
			}
		}
	}

	public void options_control(int frameX, int frameY) {
		int textX;
		int textY;

		// TITLE
		String text = "Control";
		textX = getXforCenteredText(text);
		textY = frameY + playing.tileSize;
		g2.drawString(text, textX, textY);

		textX = frameX + playing.tileSize;
		textY += playing.tileSize;
		g2.drawString("Move", textX, textY);
		textY += playing.tileSize;
		g2.drawString("Use/Attack", textX, textY);
		textY += playing.tileSize;
		g2.drawString("Shoot/Cast", textX, textY);
		textY += playing.tileSize;
		g2.drawString("Invintory", textX, textY);
		textY += playing.tileSize;
		g2.drawString("Pause", textX, textY);
		textY += playing.tileSize;
		g2.drawString("Options", textX, textY);
		textY += playing.tileSize;

		textX = frameX + playing.tileSize * 5;
		textY = frameY + playing.tileSize * 2;
		g2.drawString("ARROWS", textX, textY);
		textY += playing.tileSize;
		g2.drawString("ENTER", textX, textY);
		textY += playing.tileSize;
		g2.drawString("SPACE", textX, textY);
		textY += playing.tileSize;
		g2.drawString("C", textX, textY);
		textY += playing.tileSize;
		g2.drawString("P", textX, textY);
		textY += playing.tileSize;
		g2.drawString("ESC", textX, textY);
		textY += playing.tileSize;

		// BACK
		textX = frameX + playing.tileSize;
		textY = frameY + playing.tileSize * 9;
		g2.drawString("back", textX, textY);
		if (optionsSelection == OptionsSelection.BACK) {
			g2.drawString(">", textX - 25, textY);
			if (playing.enterPressed) {
				subState = 0;
				optionsSelection = OptionsSelection.CONTROL;
			}
		}

	}

	public void options_endGameConfirmation(int frameX, int frameY) {
		int textX = frameX + playing.tileSize;
		int textY = frameY + playing.tileSize * 3;

		currentDialogue = "Quit and return \nto the title screen?";

		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}

		// YES
		String text = "Yes";
		textX = getXforCenteredText(text);
		textY += playing.tileSize * 3;
		g2.drawString(text, textX, textY);
		if (optionsSelection == OptionsSelection.BACK) {
			g2.drawString(">", textX - 25, textY);
			if (playing.enterPressed) {
				subState = 0;
				playing.gameState = GameState.TITLE;
				playing.resetGame(true);
			}
		}

		text = "No";
		textX = getXforCenteredText(text);
		textY += playing.tileSize;
		g2.drawString(text, textX, textY);
		if (optionsSelection == OptionsSelection.FULL_SCREEN) {
			g2.drawString(">", textX - 25, textY);
			if (playing.enterPressed) {
				subState = 0;
				optionsSelection = OptionsSelection.END_GAME;
			}
		}
	}

	public void drawTransition() {

		counter++;
		g2.setColor(new Color(0, 0, 0, counter * 5));
		g2.fillRect(0, 0, playing.screenWidth, playing.screenHeight);

		if (counter >= 50) {
			counter = 0;
			playing.gameState = GameState.PLAY;
			playing.currentMap = playing.eventHandler.tempMap;
			playing.player.worldX = playing.tileSize * playing.eventHandler.tempCol;
			playing.player.worldY = playing.tileSize * playing.eventHandler.tempRow;
			playing.eventHandler.previousEventX = playing.player.worldX;
			playing.eventHandler.previousEventY = playing.player.worldY;
		}

	}

	public void drawTradeScreen() {
		switch (subState) {
		case 0:
			trade_select();
			break;
		case 1:
			trade_buy();
			break;
		case 2:
			trade_sell();
			break;
		}
		playing.enterPressed = false;
	}

	public void trade_select() {

		npc.dialogueSet = 0;
		drawDialogueScreen();

		// DRAW WINDOW
		int x = playing.tileSize * 15;
		int y = playing.tileSize * 4;
		int width = playing.tileSize * 3;
		int height = (int) (playing.tileSize * 3.5);
		drawSubWindow(x, y, width, height);

		// DRAW TEXT
		x += playing.tileSize;
		y += playing.tileSize;
		g2.drawString("Buy", x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - 24, y);
			if (playing.enterPressed) {
				subState = 1;
			}
		}
		y += playing.tileSize;
		g2.drawString("Sell", x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - 24, y);
			if (playing.enterPressed) {
				subState = 2;
			}
		}
		y += playing.tileSize;
		g2.drawString("Leave", x, y);
		if (commandNum == 2) {
			g2.drawString(">", x - 24, y);
			if (playing.enterPressed) {
				commandNum = 0;
				npc.startDialogue(npc, 1);
			}
		}

	}

	public void trade_buy() {

		// DRAW PLAYER INVENTORY
		drawInventory(playing.player, false);
		// DRAW NPC INVENTORY
		drawInventory(npc, true);

		// DRAW HINT WINDOW
		int x = playing.tileSize * 2;
		int y = playing.tileSize * 9;
		int width = playing.tileSize * 6;
		int height = playing.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[ESC] Back", x + 24, y + 60);

		// DRAW PLAYER COIN WINDOW
		x = playing.tileSize * 13;
		y = playing.tileSize * 9;
		width = playing.tileSize * 6;
		height = playing.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Kellans: " + playing.player.coin, x + 24, y + 60);

		// DRAW PRICE WINDOW
		int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
		if (itemIndex < npc.inventory.size()) {
			x = (int) (playing.tileSize * 5.5);
			y = (int) (playing.tileSize * 5.5);
			width = (int) (playing.tileSize * 2.5);
			height = playing.tileSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x + 10, y + 14, 32, 32, null);

			int price = npc.inventory.get(itemIndex).price;
			String text = "" + price;
			x = getXforAlignToRightText(text, playing.tileSize * 8 - 20);
			g2.drawString(text, x, y + 40);

			// BUY AN ITEM
			if (playing.enterPressed) {
				if (npc.inventory.get(itemIndex).price > playing.player.coin) {
					subState = 0;
					npc.startDialogue(npc, 2);
				} else {
					if (playing.player.canObtainItem(npc.inventory.get(itemIndex))) {
						playing.player.coin -= npc.inventory.get(itemIndex).price;
					} else {
						subState = 0;
						npc.startDialogue(npc, 3);
					}
				}
			}
		}
	}

	public void trade_sell() {

		// DRAW PLAYER INVENTORY
		drawInventory(playing.player, true);

		int x;
		int y;
		int width;
		int height;

		// DRAW HINT WINDOW
		x = playing.tileSize * 2;
		y = playing.tileSize * 9;
		width = playing.tileSize * 6;
		height = playing.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[ESC] Back", x + 24, y + 60);

		// DRAW PLAYER COIN WINDOW
		x = playing.tileSize * 13;
		y = playing.tileSize * 9;
		width = playing.tileSize * 6;
		height = playing.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Kellans: " + playing.player.coin, x + 24, y + 60);

		// DRAW PRICE WINDOW
		int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
		if (itemIndex < playing.player.inventory.size()) {
			x = (int) (playing.tileSize * 16.5);
			y = (int) (playing.tileSize * 5.5);
			width = (int) (playing.tileSize * 2.5);
			height = playing.tileSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x + 10, y + 14, 32, 32, null);

			int price = playing.player.inventory.get(itemIndex).price / 2;
			String text = "" + price;
			x = getXforAlignToRightText(text, playing.tileSize * 19 - 20);
			g2.drawString(text, x, y + 40);

			// SELL AN ITEM
			if (playing.enterPressed) {
				if (playing.player.inventory.get(itemIndex) == playing.player.currentWeapon
						|| playing.player.inventory.get(itemIndex) == playing.player.currentShield) {
					commandNum = 0;
					subState = 0;
					npc.startDialogue(npc, 4);
				} else {
					if (playing.player.inventory.get(itemIndex).amount > 1) {
						playing.player.inventory.get(itemIndex).amount--;
					} else {
						playing.player.inventory.remove(itemIndex);

					}
					playing.player.coin += price;
				}
			}
		}
	}

	public void drawSleepScreen() {
		counter++;

		if (counter < 120) {
			playing.eManager.lighting.filterAlpha += 0.01f;
			if (playing.eManager.lighting.filterAlpha > 1f) {
				playing.eManager.lighting.filterAlpha = 1f;
			}
		}
		if (counter >= 120) {
			playing.eManager.lighting.filterAlpha -= 0.01f;
			if (playing.eManager.lighting.filterAlpha <= 0f) {
				playing.eManager.lighting.filterAlpha = 0f;
				counter = 0;
				playing.eManager.lighting.dayState = playing.eManager.lighting.day;
				playing.eManager.lighting.dayCounter = 0;
				playing.gameState = GameState.PLAY;
				playing.player.clearSprites();
//				playing.player.getImage();
			}
		}

	}

	public int getItemIndexOnSlot(int slotCol, int slotRow) {
		int itemIndex = slotCol + (slotRow * 5);
		return itemIndex;
	}

	public void drawSubWindow(int x, int y, int width, int height) {
		Color color = new Color(50, 0, 200, 200);
		g2.setColor(color);
		g2.fillRoundRect(x, y, width, height, 25, 25);

		color = new Color(255, 255, 255);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}

	public void drawDebug() {
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		long drawStart = 0;
		drawStart = System.nanoTime();

		long drawEnd = System.nanoTime();
		long passed = drawEnd - drawStart;
		g2.setColor(Color.white);
		g2.drawString("Draw Time: " + passed, playing.tileSize / 4, playing.tileSize * 11 + playing.tileSize / 2);
		g2.drawString("FPS: " + playing.drawCount, playing.tileSize / 4, playing.tileSize * 11);

	}

	public int getXforCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = playing.screenWidth / 2 - length / 2;
		return x;
	}

	public int getXforCenteredText(String text, int width) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = width / 2 - length / 2;
		return x;
	}

	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
}
