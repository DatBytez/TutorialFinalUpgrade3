package tile_interactive;

import scenes.Playing;

public class IT_Trunk extends InteractiveTile{
	
	Playing playing;

	public IT_Trunk(Playing playing, int col, int row) {
		super(playing, col, row);
		this.playing = playing;
		
		this.worldX = playing.tileSize * col;
		this.worldY = playing.tileSize * row;
		
		down.add(setup("/tiles_interactive/trunk"));
		
		hitbox.x = 0;
		hitbox.y = 0;
		hitbox.width = 0;
		hitbox.height = 0;
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
	}
}
