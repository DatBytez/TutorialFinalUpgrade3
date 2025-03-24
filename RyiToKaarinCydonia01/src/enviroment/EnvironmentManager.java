package enviroment;

import java.awt.Graphics2D;

import scenes.Playing;

public class EnvironmentManager {

	Playing playing;
	public Lighting lighting;

	public EnvironmentManager(Playing playing){
		this.playing = playing;
	}
	
	public void setup() {
		lighting = new Lighting(playing); //TODO this is the light circle size
	}
	public void update() {
			lighting.update();
	}
	
	public void draw(Graphics2D g2) {
		lighting.draw(g2);
	}

}
