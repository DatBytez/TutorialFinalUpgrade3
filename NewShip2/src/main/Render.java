package main;

import java.awt.Graphics;

public class Render {

	private Game game;

	public Render(Game game) {
		this.game = game;
	}

	public void render(Graphics g) {
		switch (GameStates.gameState) {
		case MENU_STATE:
			game.getMenuScene().draw(g);
			break;
		case BUILD_STATE:
			game.getBuildScene().draw(g);
			break;
		case COMBAT_STATE:
			game.getCombatScene().draw(g);
			break;
		case EDIT_STATE:
			game.getEditorScene().draw(g);
			break;
		case GAME_OVER_STATE:
			game.getGameOverScene().draw(g);
			break;
		default:
			break;

		}

	}

}