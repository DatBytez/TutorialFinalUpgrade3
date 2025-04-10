//package scenes;
//
//import static managers.SceneManager.MENU;
//import static managers.SceneManager.SetGameState;
//
//import java.awt.Color;
//import java.awt.Graphics;
//
//import main.Artist;
//import main.Game;
//import ui.MyButton;
//
//public class Settings extends GameScene implements SceneTemplate {
//
//	private MyButton bMenu;
//
//	public Settings(Game game) {
//		super(game);
//		initButtons();
//
//	}
//
//	private void initButtons() {
//		bMenu = new MyButton("Menu", 2, 2, 100, 30);
//	}
//
//	@Override
//	public void render(Artist a) {
//		a.setColor(Color.BLUE);
//		a.fillRect(0, 0, 640, 640);
//
//		drawButtons(a);
//	}
//
//	private void drawButtons(Artist a) {
//		bMenu.draw(a);
//	}
//
//	@Override
//	public void mouseClicked(int x, int y) {
//		if (bMenu.getBounds().contains(x, y))
//			SetGameState(MENU);
//
//	}
//
//	@Override
//	public void mouseMoved(int x, int y) {
//		bMenu.setMouseOver(false);
//		if (bMenu.getBounds().contains(x, y))
//			bMenu.setMouseOver(true);
//
//	}
//
//	@Override
//	public void mousePressed(int x, int y) {
//		if (bMenu.getBounds().contains(x, y))
//			bMenu.setMousePressed(true);
//	}
//
//	@Override
//	public void mouseReleased(int x, int y) {
//		resetButtons();
//	}
//
//	private void resetButtons() {
//		bMenu.resetBooleans();
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
