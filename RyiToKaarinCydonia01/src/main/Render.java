package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Render {

	private Game game;

	public Render(Game game) {
		this.game = game;
	}

	public void render(Graphics2D g2) {
		switch (GameStates.gameState) {
		case MENU:
			game.getMenu().render(g2);
			break;
		case PLAYING:
			game.getPlaying().render(g2);
			break;
		case SETTINGS:
			game.getSettings().render(g2);
			break;
		case EDIT:
			game.getEditor().render(g2);
			break;
		case GAME_OVER:
			game.getGameOver().render(g2);
			break;
		default:
			break;

		}

	}

}