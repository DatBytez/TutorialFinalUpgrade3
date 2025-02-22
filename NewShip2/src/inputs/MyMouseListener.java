package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.Game;
import main.GameStates;

public class MyMouseListener implements MouseListener, MouseMotionListener {

	private Game game;

	public MyMouseListener(Game game) {
		this.game = game;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU_STATE:
			game.getMenuScene().mouseDragged(e.getX(), e.getY());
			break;
		case BUILD_STATE:
			game.getBuildScene().mouseDragged(e.getX(), e.getY());
			break;
		case COMBAT_STATE:
			game.getCombatScene().mouseDragged(e.getX(), e.getY());
			break;
		case EDIT_STATE:
			game.getEditorScene().mouseDragged(e.getX(), e.getY());
			break;
		case GAME_OVER_STATE:
			game.getGameOverScene().mouseDragged(e.getX(), e.getY());
			break;
		default:
			break;
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU_STATE:
			game.getMenuScene().mouseMoved(e.getX(), e.getY());
			break;
		case BUILD_STATE:
			game.getBuildScene().mouseMoved(e.getX(), e.getY());
			break;
		case COMBAT_STATE:
			game.getCombatScene().mouseMoved(e.getX(), e.getY());
			break;
		case EDIT_STATE:
			game.getEditorScene().mouseMoved(e.getX(), e.getY());
			break;
		case GAME_OVER_STATE:
			game.getGameOverScene().mouseMoved(e.getX(), e.getY());
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			switch (GameStates.gameState) {
			case MENU_STATE:
				game.getMenuScene().mouseClicked(e.getX(), e.getY());
				break;
			case BUILD_STATE:
				game.getBuildScene().mouseClicked(e.getX(), e.getY());
				break;
			case COMBAT_STATE:
				game.getCombatScene().mouseClicked(e.getX(), e.getY());
				break;
			case EDIT_STATE:
				game.getEditorScene().mouseClicked(e.getX(), e.getY());
				break;
			case GAME_OVER_STATE:
				game.getGameOverScene().mouseClicked(e.getX(), e.getY());
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU_STATE:
			game.getMenuScene().mousePressed(e.getX(), e.getY());
			break;
		case BUILD_STATE:
			game.getBuildScene().mousePressed(e.getX(), e.getY());
			break;
		case COMBAT_STATE:
			game.getCombatScene().mousePressed(e.getX(), e.getY());
			break;
		case EDIT_STATE:
			game.getEditorScene().mousePressed(e.getX(), e.getY());
			break;
		case GAME_OVER_STATE:
			game.getGameOverScene().mousePressed(e.getX(), e.getY());
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU_STATE:
			game.getMenuScene().mouseReleased(e.getX(), e.getY());
			break;
		case BUILD_STATE:
			game.getBuildScene().mouseReleased(e.getX(), e.getY());
			break;
		case COMBAT_STATE:
			game.getCombatScene().mouseReleased(e.getX(), e.getY());
			break;
		case EDIT_STATE:
			game.getEditorScene().mouseReleased(e.getX(), e.getY());
			break;
		case GAME_OVER_STATE:
			game.getGameOverScene().mouseReleased(e.getX(), e.getY());
			break;

		default:
			break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// We wont use this

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// We wont use this
	}

}
