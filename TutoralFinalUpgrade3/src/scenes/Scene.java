package scenes;


import main.Artist;

public abstract class Scene {
	// Abstract methods that each state must implement
	public abstract void update(); // Update the logic for this state

	public abstract void render(Artist artist); // Render the state

	public abstract void mouseClicked(int x, int y);

	public abstract void mouseMoved(int x, int y);

	public abstract void mousePressed(int x, int y);

	public abstract void mouseReleased(int x, int y);

	public abstract void mouseDragged(int x, int y);

	public abstract void keyPressed(int keyCode);

	public abstract void keyReleased(int keyCode);

	public abstract void keyTyped(char keyChar);
}
