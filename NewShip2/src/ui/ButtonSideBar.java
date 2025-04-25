package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Stack;

import main.GameScreen;
import static helpz.Constants.*;
import static main.GameStates.MENU_STATE;
import static main.GameStates.SetGameState;

public class ButtonSideBar extends Bar {

	private ArrayList<MySideButton> sideButtonList = new ArrayList<MySideButton>();
	private ArrayList<String> buttonTitles = new ArrayList<String>();

	private int x, y, width, height, rotation;
	private Rectangle bounds;
	private MySideButton selectedItem;
	private Stack<ArrayList<String>> buttonHistory = new Stack<>();

	public ButtonSideBar(ArrayList<String> buttonTitles, int x, int y, int rotation) {
		super(x, y, 0, 0);
		this.y = y;
		this.x = x;
		this.width = 30;
		this.height = GameScreen.YSIZE - 2 * MARGIN;
		this.rotation = rotation;
		this.buttonTitles = buttonTitles;
		initBounds();
		initButtons();
	}
	
	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}
	
	private void initButtons() {
        sideButtonList.clear();
        int spaceBetweenButtons = 20; // Space between buttons
        int yOffset = y + spaceBetweenButtons;

        for (String title : buttonTitles) {
            int buttonHeight = title.length() * 10 + 20;
            sideButtonList.add(new MySideButton(title, x, yOffset, width, buttonHeight, rotation));
            yOffset += buttonHeight + spaceBetweenButtons;
        }
    }
	
	public void draw(Graphics g) {
		if(DEBUG) {
			g.setColor(Color.red);
			g.drawRect(x, y, width, height);
		}
		sideButtonList.forEach(button -> button.draw(g));	
	}
	
	public void mouseClicked(int x, int y) {
	    boolean shouldUpdateButtons = false; // Flag to check if button update is needed

	    for (MySideButton button : sideButtonList) {
	        if (button.getBounds().contains(x, y)) {
	            selectedItem = button;

	            if (button.getText().equals("BACK") && !buttonHistory.isEmpty()) {
	                buttonTitles = buttonHistory.pop();
	                shouldUpdateButtons = true; // Mark that an update is needed
	            }
	            if (button.getText().equals("MENU")) {
	            	SetGameState(MENU_STATE);
	            }
	        }
	    }

	    if (shouldUpdateButtons) {
	        initButtons(); // Now we update the button list safely
	    }
	}

	public void mouseMoved(int x, int y) {
		sideButtonList.forEach(button -> button.setMouseOver(false));
		
		sideButtonList.forEach(button -> {
		if (button.getBounds().contains(x, y))
			button.setMouseOver(true);
				});
	}

	public void mousePressed(int x, int y) {
		sideButtonList.forEach(button -> {
		if (button.getBounds().contains(x, y))
			button.setMousePressed(true);
				});
	}

	public void mouseReleased(int x, int y) {
		sideButtonList.forEach(button -> button.resetBooleans());
	}

	public void update() {
		sideButtonList.forEach(button -> button.update());
		initBounds();
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public MyButton getSelectedItem() {
		return selectedItem;
	}
	
	public void changeList(ArrayList<String> buttonTitles) {
	    if (!buttonTitles.equals(this.buttonTitles)) {
	        buttonHistory.push(new ArrayList<>(this.buttonTitles));
	        this.buttonTitles = buttonTitles;
	        initButtons();
	    }
	}

}