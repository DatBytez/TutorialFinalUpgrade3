package scenes;

import java.awt.Graphics2D;

public interface SceneMethods {

	public void render(Graphics2D g2);

	public void mouseClicked(int x, int y);

	public void mouseMoved(int x, int y);

	public void mousePressed(int x, int y);

	public void mouseReleased(int x, int y);

	public void mouseDragged(int x, int y);

}
