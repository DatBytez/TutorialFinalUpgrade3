package scenes;

import java.awt.Graphics;

public interface SceneMethods {

	public void draw(Graphics g);

	public void mouseClicked(int x, int y);
	
	public void mouseDoubleClicked(int x, int y);

	public void mouseMoved(int x, int y);

	public void mousePressed(int x, int y);

	public void mouseReleased(int x, int y);

	public void mouseDragged(int x, int y);

}
