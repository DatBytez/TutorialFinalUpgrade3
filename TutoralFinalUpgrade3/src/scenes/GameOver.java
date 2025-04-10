//package scenes;
//
//import static managers.SceneManager.MENU;
//import static managers.SceneManager.PLAYING;
//import static managers.SceneManager.SetGameState;
//
//import java.awt.Color;
//import java.awt.Font;
//
//import main.Artist;
//import main.Game;
//import ui.MyButton;
//
//public class GameOver extends GameScene implements SceneTemplate {
//
//	private MyButton bReplay, bMenu;
//
//	public GameOver(Game game) {
//		super(game);
//		initButtons();
//	}
//
//	private void initButtons() {
//
//		int w = 150;
//		int h = w / 3;
//		int x = 640 / 2 - w / 2;
//		int y = 300;
//		int yOffset = 100;
//
//		bMenu = new MyButton("Menu", x, y, w, h);
//		bReplay = new MyButton("Replay", x, y + yOffset, w, h);
//
//	}
//
//	@Override
//	public void render(Artist a) {
//		// game over text
//		a.setFont(new Font("LucidaSans", Font.BOLD, 50));
//		a.setColor(Color.red);
//		a.drawString("Game Over!", 160, 80);
//
//		// buttons
//		a.setFont(new Font("LucidaSans", Font.BOLD, 20));
//		bMenu.draw(a);
//		bReplay.draw(a);
//	}
//
//	private void replayGame() {
//		// reset everything
//		resetAll();
//
//		// change state to playing
//		SetGameState(PLAYING);
//
//	}
//
//	private void resetAll() {
//		game.getPlaying().resetEverything();
//	}
//
//	@Override
//	public void mouseClicked(int x, int y) {
//		if (bMenu.getBounds().contains(x, y)) {
//			SetGameState(MENU);
//			resetAll();
//		} else if (bReplay.getBounds().contains(x, y))
//			replayGame();
//	}
//
//	@Override
//	public void mouseMoved(int x, int y) {
//		bMenu.setMouseOver(false);
//		bReplay.setMouseOver(false);
//
//		if (bMenu.getBounds().contains(x, y))
//			bMenu.setMouseOver(true);
//		else if (bReplay.getBounds().contains(x, y))
//			bReplay.setMouseOver(true);
//	}
//
//	@Override
//	public void mousePressed(int x, int y) {
//		if (bMenu.getBounds().contains(x, y))
//			bMenu.setMousePressed(true);
//		else if (bReplay.getBounds().contains(x, y))
//			bReplay.setMousePressed(true);
//
//	}
//
//	@Override
//	public void mouseReleased(int x, int y) {
//		bMenu.resetBooleans();
//		bReplay.resetBooleans();
//
//	}
//
//	@Override
//	public void mouseDragged(int x, int y) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void update() {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
