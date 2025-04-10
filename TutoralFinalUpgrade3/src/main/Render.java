//package main;
//
//import java.awt.Graphics;
//
//import managers.SceneManager;
//
//public class Render {
//
//	private Game game;
//
//	public Render(Game game) {
//		this.game = game;
//	}
//
//	public void render(Artist artist) {
//		switch (SceneManager.gameState) {
//		case MENU:
//			game.getMenu().render(artist);
//			break;
//		case PLAYING:
//			game.getPlaying().render(artist);
//			break;
//		case SETTINGS:
//			game.getSettings().render(artist);
//			break;
//		case EDIT:
//			game.getEditing().render(artist);
//			break;
//		case GAME_OVER:
//			game.getGameOver().render(artist);
//			break;
//		default:
//			break;
//		}
//	}
//}
//
////package main;
////
////import java.awt.Graphics;
////
////public class Render {
////
////	private Game game;
////
////	public Render(Game game) {
////		this.game = game;
////	}
////
////	public void render(Graphics g) {
////		switch (GameStates.gameState) {
////		case MENU:
////			game.getMenu().render(g);
////			break;
////		case PLAYING:
////			game.getPlaying().render(g);
////			break;
////		case SETTINGS:
////			game.getSettings().render(g);
////			break;
////		case EDIT:
////			game.getEditor().render(g);
////			break;
////		case GAME_OVER:
////			game.getGameOver().render(g);
////			break;
////		default:
////			break;
////		}
////	}
////}